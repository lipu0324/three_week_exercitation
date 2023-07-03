package com.sifu.sfcc.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.sifu.sfcc.security.service.RedisService;
import com.sifu.sfcc.service.CcCulturalBusinessService;
import com.sifu.sfcc.service.CcHandleTheBusinessService;
import com.sifu.sfcc.service.CcNGDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class CcCulturalBusinessServiceImpl implements CcCulturalBusinessService {

	@Autowired
	private CcNGDService ccNGDService;

	@Override
	public Map culturalBusiness(JSONObject context, String session_id,String phone) {
		Map map = new HashMap();
		String culturalBusiness = context.getString("CulturalBusiness");
		String res = "";
		switch (culturalBusiness){
			case "涉外港澳台演出":
				res = "稍后会为您以短信的形式发送，请问还有什么可以帮助您？";
				String str = "您好，请登录全国文化技术监管与服务平台（新平台）在线申请办理。您需要准备营业性演出申请登记表；《营业性演出许可证》副本复印件；演员名单、演员有效身份证明复印件；演出举办单位与文艺表演团体（演员）的演出协议或文艺表演团体（演员）同意参加演出的书面函件；如有未成年人参加营业性演出，应当提供其监护人出具的书面同意函件；场地证明；消防安全证明复印件；消防批准文件复印件；演出节目单及与节目单对应的视听资料；";
				try{
					ccNGDService.saveQ(phone,"3",str,session_id);
				}catch (Exception e){
					e.printStackTrace();
					map.put("res",res);
					return map;
				}
				break;
			case "证件办理":
				res = "请问您想咨询网络文化经营许可证、营业性演出许可证、经纪人资格证还是艺术品进口经营呢？";
				break;
		}
		map.put("res",res);
		return map;
	}

	@Override
	public Map certificateHandling(JSONObject context, String session_id,String phone) {
		Map map = new HashMap();
		String certificateHandling = context.getString("CertificateHandling");
		String res = "";
		String str = "";
		switch (certificateHandling){
			case "网络文化经营许可证":
				res = "稍后会为您以短信的形式发送，请问还有什么可以帮助您？";
				str = "您好，受理条件如下：\r\n" +
						"（一）申请单位营业执照的经营范围载有利用信息网络从事互联网文化经营活动相关内容，《网络文化经营许可证》的审批范围包括：网络音乐、网络演出剧（节）目、网络表演、网络艺术品、网络动漫和展览、比赛活动，对于不符合审批范围从事其他互联网经营活动的单位，文化和旅游部门不予发放《网络文化经营许可证》；\r\n" +
						"（二）申请单位人员配置和工作场所：A.申请单位应配备适应所申请项目经营活动的至少8名专业人员（专业人员评定标准为：计算机专业或者具有计算机等级证书的人员），如项目管理人员、专业技术人员、网络安全保障人员、内容安全自审保障人员（申请单位必须有2名以上专业人员负责内容自审工作）等；B.申请单位应有充足的工作场地、一定数量的计算机、适应经营活动数量的服务器等；C.申请单位所使用服务器应在境内；并有健全的内容审查管理制度和适应内容管理需要的实时技术监管措施和未成年人保护措施。\r\n" +
						"（三）申请单位拥有本单位建设经营的网站，并且具有网站域名登记证明。\r\n" +
						"申请材料如下：\r\n" +
						"1、企业营业执照（副本）；\r\n" +
						"2、企业章程（工商部门备案）\r\n" +
						"3、法定代表人的身份证明（正反面）；\r\n" +
						"4、网站域名登记证明（网站名称与申请单位一致）；\r\n" +
						"5、业务发展说明。\r\n" +
						"注：上述内容可登录黑龙江政务服务网，搜索“设立经营性互联网文化单位审批”查询详细内容，请仔细查看常见问题——申办《网络文化经营许可证》注意事项，仔细查看办理材料目录中业务发展说明——填报须知。申请单位提交的所有材料必须真实、有效。";
				try{
					ccNGDService.saveQ(phone,"4",str,session_id);
				}catch (Exception e){
					e.printStackTrace();
					map.put("res",res);
					return map;
				}
				break;
			case "营业性演出许可证":
				res = "稍后会为您以短信的形式发送，请问还有什么可以帮助您？";
				str = "您好，受理条件如下：\r\n" +
						"（一）申请单位应至少拥有3名专职演出经纪人员（专职演出经纪人评定标准：演出经纪人资格证上从业单位的名称应当是申请单位）；网络表演经纪机构的演出经纪人比例应与公司签约主播比例为1:100\n" +
						"（二）申请单位应当有固定经营场所、与其业务相适应的专职演员、器材设备、资金。\n" +
						"申请材料如下：\r\n" +
						"1、营业执照（副本）；\r\n" +
						"2、法定代表人或主要负责人身份证明（正反面）；\r\n" +
						"3、至少3名专职演出经纪人员的资格证书（资格证书在有效期内且从业单位的名称应当是申请单位）。\r\n" +
						"注：上述内容可登录黑龙江政务服务网，搜索“内资演出经纪机构审批”查询详细内容，申请单位提交的所有材料必须真实、有效。";
				try{
					ccNGDService.saveQ(phone,"5",str,session_id);
				}catch (Exception e){
					e.printStackTrace();
					map.put("res",res);
					return map;
				}
				break;
			case "经纪人资格证":
				res = "您好，自2022年3月1日起，演出经纪人员可通过全国文化市场技术监管与服务平台的全国演出经纪人员管理与服务系统，按相关要求办理证书激活、遗失补证、证书验证查询和证件信息变更等业务。请问还有什么想了解的?";
				break;
			case "艺术品进口经营":
				res = "稍后会为您以短信的形式发送，请问还有什么可以帮助您？";
				str = "您好，您可登录全国文化市场技术监管与服务平台提交申请材料，您需要准备艺术品进出口申请表；营业执照副本复印件；对外贸易经营者备案登记表复印件（或海关出具的ATA单册证、《中华人民共和国海关报关企业报关注册登记证书》复印件）；艺术品进出口名录（包括作者、作品名称及图片）纸质版与电子版；";
				try{
					ccNGDService.saveQ(phone,"6",str,session_id);
				}catch (Exception e){
					e.printStackTrace();
					map.put("res",res);
					return map;
				}
				break;
		}
		map.put("res",res);
		return map;
	}
}
