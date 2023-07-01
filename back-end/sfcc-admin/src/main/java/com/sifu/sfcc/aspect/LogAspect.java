package com.sifu.sfcc.aspect;

import com.sifu.sfcc.common.annotation.CcOperateLog;
import com.sifu.sfcc.common.exception.ApiException;
import com.sifu.sfcc.mapper.CcOperateLogMapper;
import com.sifu.sfcc.model.UmsAdmin;
import com.sifu.sfcc.service.UmsAdminService;
import io.swagger.annotations.ApiModelProperty;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private CcOperateLogMapper ccOperateLogMapper;

    @Autowired
    private UmsAdminService umsAdminService;

    @Pointcut("@annotation(com.sifu.sfcc.common.annotation.CcOperateLog)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            if(e instanceof ApiException){
                throw  e;
            }
        } finally {
            // 执行时长(毫秒)
            long time = System.currentTimeMillis() - beginTime;

            //保存日志
            saveLog(point, time);
        }

        return result;
    }

    public void saveLog(ProceedingJoinPoint joinPoint, long time) throws IllegalAccessException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.sifu.sfcc.model.CcOperateLog ccOperateLog = new com.sifu.sfcc.model.CcOperateLog();
        //SysLog sysLog = new SysLog();
        CcOperateLog logAnnotation = method.getAnnotation(CcOperateLog.class);

        if (logAnnotation != null) {
            // 注解上的描述
            ccOperateLog.setModule(logAnnotation.moudule());
            ccOperateLog.setOperateType(logAnnotation.type());
            //ccOperateLog.setOperation(logAnnotation.value());
        }
        // 请求的方法名
        //String className = joinPoint.getTarget().getClass().getName();
        //String methodName = signature.getName();
        UmsAdmin umsAdmin = umsAdminService.getCurrentUserAdmin();

        ccOperateLog.setMemberId(umsAdmin.getId());
        ccOperateLog.setMemberName(umsAdmin.getUsername());


        // 请求的方法参数值

        Object[] args = joinPoint.getArgs();
        for (int j = 0; j < args.length; j++) {
            if (!(args[j] instanceof HttpServletRequest) && !(args[j] instanceof HttpServletResponse)) {
                Field[] fields = args[j].getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object obj = field.get(args[j]);
                    if (!ObjectUtils.isEmpty(obj)) {
                        if (field.getName().equals("code")) {
                            ccOperateLog.setRecordId((String) field.get(args[j]));
                        }
                        ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
                        if (ObjectUtils.isEmpty(apiModelProperty)) {
                            continue;
                        }
                        String old = StringUtils.isEmpty(ccOperateLog.getOperateContent()) ? "" : ccOperateLog.getOperateContent();
                        ccOperateLog.setOperateContent(old.concat(apiModelProperty.value().concat(":").concat(obj.toString())));
                    }

                }
            }
        }

        // 请求的方法参数名称
//        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
//        String[] paramNames = u.getParameterNames(method);
//        if (args != null && paramNames != null) {
//            String params = "";
//            for (int i = 0; i < args.length; i++) {
//                params += "  " + paramNames[i] + ": " + args[i];
//            }
//            sysLog.setParams(params);
//        }
        // 获取request
        //HttpServletRequest request = HttpContextUtils.getHttpServletRequest();

        // 设置IP地址
        //sysLog.setIp(AddressUtils.getIpAddr(request));
        //sysLog.setLocation(AddressUtils.getCityInfo(sysLog.getIp()));

        //模拟一个用户名
        ccOperateLog.setCreateTime(new Date());
        ccOperateLogMapper.insertSelective(ccOperateLog);
        //sysLogRepo.save(sysLog);
    }

}
