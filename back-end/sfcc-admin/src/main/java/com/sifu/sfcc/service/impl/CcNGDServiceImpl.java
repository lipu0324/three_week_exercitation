package com.sifu.sfcc.service.impl;

import com.sifu.sfcc.bo.SaveSMS;
import com.sifu.sfcc.dao.XmkKeywordDao;
import com.sifu.sfcc.mapper.XmkKeywordMapper;
import com.sifu.sfcc.model.XmkKeyword;
import com.sifu.sfcc.model.XmkKeywordExample;
import com.sifu.sfcc.service.CcNGDService;
import com.sifu.sfcc.util.RegexUtil;
import com.sifu.sfcc.util.SendSMS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class CcNGDServiceImpl implements CcNGDService {
	
	@Autowired
	private XmkKeywordMapper keywordMapper;

    @Autowired
    private XmkKeywordDao keywordDao;

    @Override
    public String dealMobile(String ngdStr, Integer count) {
        Pattern pattern = Pattern.compile(RegexUtil.isMobileRegexp());
        Matcher matcher = pattern.matcher(ngdStr);
        while (matcher.find()) {//处理座机
            //获取到之前查找到的字符串，并将其添加入set中
            String group = matcher.group();
            System.out.println("group = " + group);
            DecimalFormat df = new DecimalFormat("####,####,####");
            String formatGroup = "";
            Long l = Long.valueOf(group.replaceAll("-",""));
            if(group.startsWith("0")){//以0开头转换完毕要补0
                formatGroup = "0" + df.format(l);
            }else{
                formatGroup = df.format(l);
            }

            if(count == 2) //电话号重复一遍
                formatGroup += ("号码为:" + formatGroup);
            ngdStr = ngdStr.replaceAll(group , formatGroup);
        }
        pattern = Pattern.compile(RegexUtil.isPhoneRegexp());
        matcher = pattern.matcher(ngdStr);
        while (matcher.find()) {//处理手机号
            //获取到之前查找到的字符串，并将其添加入set中
            String group = matcher.group();
            System.out.println("group = " + group);
            DecimalFormat df = new DecimalFormat("###,####,####");
            String formatGroup = "";
            Long l = Long.valueOf(group.replaceAll("-",""));
            formatGroup = df.format(l);
            if(count == 2) //手机号重复一遍
                formatGroup += ("号码为:" + formatGroup);
            ngdStr = ngdStr.replaceAll(group , formatGroup);
        }

        return ngdStr;
    }

	@Override
	public String sendSms(String mobile,String type, String keyword) {
		//根据问题，生成查询链接
		try {
			String[] keys = keyword.split(",");
			String ids = "";
			for(int i=0;i<keys.length;i++) {
				
				XmkKeywordExample example = new XmkKeywordExample();
				XmkKeywordExample.Criteria  criteria = example.createCriteria();
				criteria.andKeywordEqualTo(keys[i]);
				List<XmkKeyword> key = keywordMapper.selectByExample(example);
				if(null != key && key.size() > 0) {
					ids += "," + key.get(0).getId();
				}else {
					XmkKeyword xk = new XmkKeyword();
					xk.setKeyword(keys[i]);
					keywordMapper.insert(xk);
					ids += "," + xk.getId();
				}
			}
			//String content = "感谢拨打热线，您要咨询的问题如下：http://sfcc.koobietech.com/#/userquestions?type="+type+"&keyword="+ids.substring(1);
			//return SendSMS.sendMobileUrl("接口联调账号","hljswt","passwd@1342H",mobile,content,"bM16CfN2B","10657204006");
//			return SendSMS.sendMobileUrl("黑龙江省文化和旅游厅","wlmas","Wl123$%^",mobile,content,"Gi0toDxMX","106573114202");
			return SendSMS.sendUrl(mobile,"SMS_231190198",type,ids.substring(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

    @Override
    public int saveQ(String mobile, String type, String keyword, String campaign_id) {
        SaveSMS saveSMS = new SaveSMS();
        String[] keys = keyword.split(",");
        saveSMS.setCallId(campaign_id);
        saveSMS.setType(type);
        saveSMS.setPhone(mobile);
        int res = 0;
        for (String key:keys) {
            saveSMS.setKeyword(key);
            res = keywordDao.saveQ(saveSMS);
        };
        return res;
    }


    @Override
    public String NGDSendSms(String sessionId) {
        List<SaveSMS> saveSMS = keywordDao.selectKeyWordPhoneBySessionId(sessionId);
        if(saveSMS.size()>0){
            String types = "";
            String ids = "";
            for (SaveSMS send:saveSMS) {
                ids += "," + send.getId();
                types += "," + send.getType();
            }
            if(!StringUtils.isEmpty(types)){
                types = types.substring(1);
            }
            if(!StringUtils.isEmpty(ids)){
                ids = ids.substring(1);
            }

            String mobile = saveSMS.get(0).getPhone();
            if(mobile.substring(0,1).equals("0")){
                mobile = mobile.substring(1,12);
            }
            log.info("请求丽丽前：{}"+saveSMS.get(0).getCallId());
            System.out.println("请求丽丽前：{}"+saveSMS.get(0).getCallId());
//        String content = "感谢拨打热线，您要咨询的问题如下：http://111.40.37.223:6201/#/userquestions?type="+types+"&keyword="+ids.substring(1);
            String content = "感谢拨打热线，您要咨询的问题如下：http://111.40.37.223:6201/#/messagePage?type="+types;
            System.out.println("请求丽丽后：{}"+saveSMS.get(0).getCallId());
            log.info("请求丽丽后：{}");
            //return SendSMS.sendMobileUrl("接口联调账号","hljswt","passwd@1342H",mobile,content,"bM16CfN2B","10657204006");
            String s = SendSMS.sendMobileUrl("黑龙江省文化和旅游厅", "wlmas", "Wl123$%^", mobile, content, "Gi0toDxMX", "106573114202");
            System.out.println("发送短信返回结果：{}"+s);
            return "OK";
//        return SendSMS.sendUrl(mobile,"SMS_231190198",types,ids.substring(1));
        }
        return null;
    }

    @Override
    public List getSendInfo(String typesStr) {
        List<String> list = new ArrayList<>();
        String[] types = typesStr.split(",");
        String res = "";
        for (String type:types) {
            switch (type){
                case "1":
                    res = "您好，受理情形如下：\r\n" +
                            "（一）旅行社因解散或破产清算、业务变更或撤减分社减交时提供如下材料：\r\n" +
                            "1、旅游服务质量保证金取款申请书；\r\n" +
                            "2、营业执照副本复印件；\r\n" +
                            "3、旅行社业务经营许可证副本复印件；\r\n" +
                            "4、质量保证金存单及协议原件复印件（质保金协议书存款金额必须与存单一致）；\r\n" +
                            "5、出资人身份证复印件；\r\n" +
                            "6、经办人身份证复印件；\r\n" +
                            "7、属地市场监督管理局出具的《准予注销登记通知书》；\r\n" +
                            "8、属地文旅局出具的《注销旅行社许可决定书》。\r\n" +
                            "（二）旅行社因三年内未因侵害旅游者合法权益受到行政机关罚款以上处罚而降低保证金数额50%等原因需要支取保证金时提供如下材料：\r\n" +
                            "1、营业执照副本复印件；\r\n" +
                            "2、旅行社业务经营许可证副本复印件；\r\n" +
                            "3、质量保证金存单及协议原件复印件；\r\n" +
                            "4、出资人身份证复印件；\r\n" +
                            "5、经办人身份证复印件；\r\n" +
                            "6、属地文旅局出具的三年未因侵害旅游者合法权益受到行政机关处罚以上处罚的证明。\r\n" +
                            "注：上述内容可登录黑龙江政务服务网，搜索“旅游服务质量保证金清退业务”查询详细内容，申请单位提交的所有材料必须真实、有效。\n";

                    break;
                case "2":
                    res = "您好，受理情形如下：\r\n" +
                            "（一）通过导游人员资格考试并取得导游人员资格证；\r\n" +
                            "（二）原导游资格证由黑龙江省印发。\r\n" +
                            "申请材料如下：\r\n" +
                            "1、《导游员资格证书申请表》；\r\n" +
                            "2、登报声明报纸原件（在《中国旅游报》刊登遗失声明）；\r\n" +
                            "3、一寸白底照片3张；\r\n" +
                            "4、身份证复印件。\r\n" +
                            "注：上述内容可登录黑龙江政务服务网，搜索“导游资格证补办”查询详细内容，申请人提交的所有材料必须真实、有效。\r\n";
                    break;
                case "3":
                    res = "您好，请登录全国文化技术监管与服务平台（新平台）在线申请办理。您需要准备营业性演出申请登记表；《营业性演出许可证》副本复印件；演员名单、演员有效身份证明复印件；演出举办单位与文艺表演团体（演员）的演出协议或文艺表演团体（演员）同意参加演出的书面函件；如有未成年人参加营业性演出，应当提供其监护人出具的书面同意函件；场地证明；消防安全证明复印件；消防批准文件复印件；演出节目单及与节目单对应的视听资料；";
                    break;
                case "4":
                    res = "您好，受理条件如下：\r\n" +
                            "（一）申请单位营业执照的经营范围载有利用信息网络从事互联网文化经营活动相关内容，《网络文化经营许可证》的审批范围包括：网络音乐、网络演出剧（节）目、网络表演、网络艺术品、网络动漫和展览、比赛活动，对于不符合审批范围从事其他互联网经营活动的单位，文化和旅游部门不予发放《网络文化经营许可证》；\r\n" +
                            "（二）申请单位人员配置和工作场所：A.申请单位应配备适应所申请项目经营活动的至少8名专业人员（专业人员评定标准为：计算机专业或者具有计算机等级证书的人员），如项目管理人员、专业技术人员、网络安全保障人员、内容安全自审保障人员（申请单位必须有2名以上专业人员负责内容自审工作）等；B.申请单位应有充足的工作场地、一定数量的计算机、适应经营活动数量的服务器等；C.申请单位所使用服务器应在境内；并有健全的内容审查管理制度和适应内容管理需要的实时技术监管措施和未成年人保护措施。\r\n" +
                            "（三）申请单位拥有本单位建设经营的网站，并且具有网站域名登记证明。\r\n" +
                            "申请材料如下：\r\n" +
                            "1、企业营业执照（副本）；\r\n" +
                            "2、企业章程（工商部门备案）\r\n" +
                            "3、法定代表人的身份证明（正反面）；\r\n" +
                            "4、网站域名登记证明（网站名称与申请单位一致）；\r\n" +
                            "5、业务发展说明。\r\n" +
                            "注：上述内容可登录黑龙江政务服务网，搜索“设立经营性互联网文化单位审批”查询详细内容，请仔细查看常见问题——申办《网络文化经营许可证》注意事项，仔细查看办理材料目录中业务发展说明——填报须知。申请单位提交的所有材料必须真实、有效。\r\n";

                    break;
                case "5":
                    res = "您好，受理条件如下：\r\n" +
                            "（一）申请单位应至少拥有3名专职演出经纪人员（专职演出经纪人评定标准：演出经纪人资格证上从业单位的名称应当是申请单位）；网络表演经纪机构的演出经纪人比例应与公司签约主播比例为1:100\r\n" +
                            "（二）申请单位应当有固定经营场所、与其业务相适应的专职演员、器材设备、资金。\r\n" +
                            "申请材料如下：\r\n" +
                            "1、营业执照（副本）；\r\n" +
                            "2、法定代表人或主要负责人身份证明（正反面）；\r\n" +
                            "3、至少3名专职演出经纪人员的资格证书（资格证书在有效期内且从业单位的名称应当是申请单位）。\r\n" +
                            "注：上述内容可登录黑龙江政务服务网，搜索“内资演出经纪机构审批”查询详细内容，申请单位提交的所有材料必须真实、有效。\r\n";
                    break;
                case "6":
                    res = "您好，您可登录全国文化市场技术监管与服务平台提交申请材料，您需要准备艺术品进出口申请表；营业执照副本复印件；对外贸易经营者备案登记表复印件（或海关出具的ATA单册证、《中华人民共和国海关报关企业报关注册登记证书》复印件）；艺术品进出口名录（包括作者、作品名称及图片）纸质版与电子版；";
                    break;
            }
            list.add(res);
        }
        return list;
//       return keywordDao.getSendInfo(typesStr);
    }

}
