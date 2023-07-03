/*
 * @Descripttion: 
 * @Author: Dyl
 * @Date: 2021-10-20 09:24:12
 */
const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  noticeList:state => state.app.noticeList,
  systemInfo: state => state.app.systemInfo,
  token: state => state.user.token,
  name: state => state.user.name,
  roles: state => state.user.roles,
  userId: state => state.user.userId,
  headimg: state => state.user.headimg,
  deptMent: state => state.user.deptMent,
  signStatus: state => state.user.signStatus,
  addRouters: state => state.permission.addRouters,
  routers: state => state.permission.routers,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
}
export default getters
