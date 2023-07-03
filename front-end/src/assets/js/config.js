/*
 * @Descripttion: 
 * @Author: Dyl
 * @Date: 2021-10-20 09:24:12
 */
/**
 * Created by ronny, 2017/08/09
 * modified by ronny, 2019/04/19
 */


/*需要手动配置
 (注意：此处若配置错误，通话功能无法实现。)
 */
import { getExtensionNum,getSitNum } from '@/utils/auth';
window.msg_host = "111.40.37.223";//服务器IP地址
window.msg_port = "8097";//messageserver启用的端口，默认8097
window.http_url = "8098/6.0.x/";//http接口端口+版本号
window.http_urlB = "8099/";//http特殊接口端口
window.sound_url = "8181";//音频端口号
window.targetTenantId = 10000;//目标企业ID,根据实际情况填写，number类型，例如10001
window.exten = getExtensionNum();//登录座席所分配的分机号,四位，例如"1001".
window.queue = ["10000_8002"];//座席所在班组,注意必须是班组全称，例如10001_8000，不能是8000.
window.thisDN = getSitNum();//座席号，例如10001_5000
window.extenL = ""; //弹窗分机号（定义全局变量-这里不用赋值）
window.popupSteats = ""; //弹窗座席号（定义全局变量-这里不用赋值）
window.all = {} //设置全局参数，用于保存监控座席的callID（定义全局变量-这里不用赋值）
window.conferenceNumber = ""; //会议室号（定义全局变量-这里不用赋值）
window.conferenceParam = ""; //会议操作（定义全局变量-这里不用赋值）
/*手动配置完毕*/
// window.msg_host = "120.27.237.19";//服务器IP地址
// window.msg_port = "8097";//messageserver启用的端口，默认8097
// window.http_url = "8098/2.0.5.20190911_release/";//http接口端口+版本号
// window.targetTenantId = 10007;//目标企业ID,根据实际情况填写，number类型，例如10001
// window.exten = "1002";//登录座席所分配的分机号,四位，例如"1001".
// window.queue = ["10007_8000"];//座席所在班组,注意必须是班组全称，例如10001_8000，不能是8000.
// window.thisDN = "10007_5002"//弹窗三方通话座席号
// window.extenL = ""; //弹窗分机号（定义全局变量-这里不用赋值）
// window.popupSteats = ""; //弹窗座席号（定义全局变量-这里不用赋值）
// window.all = {} //设置全局参数，用于保存监控座席的callID（定义全局变量-这里不用赋值）
/*手动配置完毕*/
export default {}