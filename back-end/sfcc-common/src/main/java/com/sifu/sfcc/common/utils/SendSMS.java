package com.sifu.sfcc.common.utils;



import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SendSMS {


    /**
     * 通过阿里云平台发送短信，如果发送成功，返回"OK" ;否则返回的是错误信息
     *
     * @param mobile       要接收该短信的用户手机号
     * @param sendTemplate 短信模板id，此处将其封装为变量，是为了由该方法的调用者决定自己来发送哪种类型的短信验证码，比如注册，重置密码类的
     * @param code         您将发送到用户手机的短信验证码
     */
    public static String sendCode(String mobile, String sendTemplate, String code) {
        //请求失败这里会抛ClientException异常
        String result = "";
        try {
            //设置超时时间-可自行调整
            System.setProperty("sun.net.client.defaultConnectTimeout", "60000");
            System.setProperty("sun.net.client.defaultReadTimeout", "60000");
            //初始化ascClient需要的几个参数
            final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
            final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
            //替换成你的AK
            //final String accessKeyId = "LTAI4FmMMygEFdDWN8QkyXq7";//你的accessKeyId,参考本文档步骤2
            //final String accessKeySecret = "NmnIRPb1n3GuhbxVDE9F4OojMYUhQR";//你的accessKeySecret，参考本文档步骤2
            final String accessKeyId = "LTAI5tKet8cFftAsuaZ6Wb4L";
            final String accessKeySecret = "gDfZuH5VpAitbrRqi5A5DhKJDZLTn5";

            //初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                    accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
            request.setPhoneNumbers(mobile);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("四福科技");//注意要与你在短信控制台中的签名一致
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(sendTemplate);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            request.setTemplateParam("{\"code\":\"" + code + "\"}");
            SendSmsResponse response = acsClient.getAcsResponse(request);
            result = response.getCode();
        } catch (Exception e) {
            result = e.getMessage();
        }
        return result;
    }


}