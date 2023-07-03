package com.sifu.sfcc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sifu.sfcc.service.CcHandleTheBusinessService;
import com.sifu.sfcc.service.CcNGDService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CCHandleTheBusinessServiceImpl implements CcHandleTheBusinessService {

	@Autowired
	private CcNGDService ccNGDService;


	@Override
	public Map tourismBusiness(JSONObject context, String session_id,String phone) {

		Map map = new HashMap();
		String tourismBusiness = context.getString("TourismBusiness");
		String res = "";
		switch (tourismBusiness){
			case "保证金清退":
				res = "稍后会为您以短信的形式发送，请问还有什么可以帮助您？";
				String str = "您好，受理情形如下：\r\n" +
						"（一）旅行社因解散或破产清算、业务变更或撤减分社减交时提供如下材料：\r\n" +
						"1、旅游服务质量保证金取款申请书；\n" +
						"2、营业执照副本复印件；\n" +
						"3、旅行社业务经营许可证副本复印件；\n" +
						"4、质量保证金存单及协议原件复印件（质保金协议书存款金额必须与存单一致）；\n" +
						"5、出资人身份证复印件；\n" +
						"6、经办人身份证复印件；\n" +
						"7、属地市场监督管理局出具的\t《准予注销登记通知书》；\n" +
						"8、属地文旅局出具的《注销旅行社许可决定书》。\n" +
						"（二）旅行社因三年内未因侵害旅游者合法权益受到行政机关罚款以上处罚而降低保证金数额50%等原因需要支取保证金时提供如下材料：\n" +
						"1、营业执照副本复印件；\n" +
						"2、旅行社业务经营许可证副本复印件；\n" +
						"3、质量保证金存单及协议原件复印件；\n" +
						"4、出资人身份证复印件；\n" +
						"5、经办人身份证复印件；\n" +
						"6、属地文旅局出具的三年未因侵害旅游者合法权益受到行政机关处罚以上处罚的证明。\n" +
						"注：上述内容可登录黑龙江政务服务网，搜索“旅游服务质量保证金清退业务”查询详细内容，申请单位提交的所有材料必须真实、有效。";
				System.out.println(str);
				try{
					log.info("保存数据前");
					System.out.println("保存数据前");
					ccNGDService.saveQ(phone,"1",str,session_id);
					System.out.println("保存数据后");
					log.info("保存数据后");
				}catch (Exception e){
					e.printStackTrace();
					System.out.println("errMsg" + e);
					log.info("errMsg:{}"+e);
					log.error("errMsg:{}"+e);
					map.put("res",res);
					return map;
				}

				break;
			case "导游证":
				res = "请问您想咨询导游资格证补办还是电子导游证呢？";
				break;
			case "导游考试":
				res = "请问您想咨询异地考生还是考试取消退费呢？";
				break;
		}
		map.put("res",res);
		return map;
	}

	@Override
	public Map tourGuideCertifica(JSONObject context, String session_id,String phone) {
		Map map = new HashMap();
		String tourGuideCertifica = context.getString("TourGuideCertifica");
		String res = "";
		switch (tourGuideCertifica){
			case "导游资格证补办":
				res = "稍后会为您以短信的形式发送，请问还有什么可以帮助您？";
				String str = "您好，受理情形如下：\n" +
						"（一）通过导游人员资格考试并取得导游人员资格证；\n" +
						"（二）原导游资格证由黑龙江省印发。\n" +
						"申请材料如下：\n" +
						"1、《导游员资格证书申请表》；\n" +
						"2、登报声明报纸原件（在《中国旅游报》刊登遗失声明）；\n" +
						"3、一寸白底照片3张；\n" +
						"4、身份证复印件。\n" +
						"注：上述内容可登录黑龙江政务服务网，搜索“导游资格证补办”查询详细内容，申请人提交的所有材料必须真实、有效。";
			try{
				ccNGDService.saveQ(phone,"2",str,session_id);
			}catch (Exception e){
				e.printStackTrace();
				map.put("res",res);
				return map;
			}

				break;
			case "电子导游证":
				res = "您好，变更电子导游证信息请联系导游资格证所在地市文旅局进行变更。请问还有什么想了解的?";
				break;

		}
		map.put("res",res);
		return map;

	}

	@Override
	public Map tourGuideExaminati(JSONObject context, String session_id) {
		Map map = new HashMap();
		String tourGuideExaminati = context.getString("TourGuideExaminati");
		String res = "";
		switch (tourGuideExaminati){
			case "异地考生":
				res = "您好，考试地点可以选择，在哪考试由哪里管辖，可在监管平台申请变更。请问还有什么想了解的?";
				break;
			case "考试取消退费":
				res = "您好，导游考试取消，报名费退回至原付款账户。请问还有什么想了解的?";
				break;

		}
		map.put("res",res);
		return map;

	}
}
