/*
 * @Descripttion: 
 * @Author: Dyl
 * @Date: 2021-10-20 09:24:12
 */
import Cookies from 'js-cookie'
import { orderNotice } from '@/api/workorder.js'

const app = {
  state: {
    sidebar: {
      opened: !+Cookies.get('sidebarStatus'),
      withoutAnimation: false
    },
    device: 'desktop',
    systemInfo: {
      name: "",
      logo: ""
    },
    noticeList:[]
  },
  mutations: {
    TOGGLE_SIDEBAR: state => {
      if (state.sidebar.opened) {
        Cookies.set('sidebarStatus', 1)
      } else {
        Cookies.set('sidebarStatus', 0)
      }
      state.sidebar.opened = !state.sidebar.opened
    },
    CLOSE_SIDEBAR: (state, withoutAnimation) => {
      Cookies.set('sidebarStatus', 1)
      state.sidebar.opened = false
      state.sidebar.withoutAnimation = withoutAnimation
    },
    TOGGLE_DEVICE: (state, device) => {
      state.device = device
    },
    SET_SYS_INFO: (state, systemInfo) => {
      state.systemInfo = systemInfo
    },
    SET_NOTICE: (state, list) => {
      state.noticeList = list
    },
  },
  actions: {
    ToggleSideBar: ({ commit }) => {
      commit('TOGGLE_SIDEBAR')
    },
    CloseSideBar({ commit }, { withoutAnimation }) {
      commit('CLOSE_SIDEBAR', withoutAnimation)
    },
    ToggleDevice({ commit }, device) {
      commit('TOGGLE_DEVICE', device)
    },
    SetSystemInfo({ commit }, info) {
      commit('SET_SYS_INFO', info)
    },
    GetNoticeList({ commit }, {}) {
      orderNotice().then((res)=>{
        commit('SET_NOTICE', res.data)
      })
    }
  }
}

export default app
