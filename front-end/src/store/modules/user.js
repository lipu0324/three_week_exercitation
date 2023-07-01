/*
 * @Descripttion: 
 * @Author: Dyl
 * @Date: 2021-10-20 09:24:12
 */
import { login, logout, getInfo } from '@/api/login'
import { getToken, setToken, removeToken,setExtensionNum, removeExtensionNum,setSitNum, removeSitNum } from '@/utils/auth'

const user = {
  state: {
    token: getToken(),
    name: '',
    roles: [],
    userId: [],
    deptMent:"",
    headimg:"",
    signStatus:'in'
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_DEPT: (state, dept) => {
      state.deptMent = dept
    },
    SET_ID: (state, id) => {
      state.userId = id
    },
    SET_HEADIMG: (state, img) => {
      state.headimg = img
    },
    SET_SIGNSTATUS: (state, status) => {
      state.signStatus = status
    },
  },

  actions: {
    // 登录
    Login({ commit }, userInfo) {
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        login(username, userInfo.password).then(response => {
          const data = response.data
          const tokenStr = data.tokenHead+data.token
          setToken(tokenStr)
          commit('SET_TOKEN', tokenStr)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    GetInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getInfo().then(response => {
          const data = response.data
          if (data.roles && data.roles.length > 0) { // 验证返回的roles是否是一个非空数组
            commit('SET_ROLES', data.roles)
          } else {
            reject('getInfo: roles must be a non-null array !')
          }
          commit('SET_NAME', data.username)
          commit('SET_DEPT',data.deptName)
          commit('SET_ID',data.id)
          commit('SET_HEADIMG',data.headImg)
          setSitNum(data.code) // 设置座席号
          setExtensionNum(data.exNum) // 设置分机号
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 登出
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          removeToken()
          removeExtensionNum()
          removeSitNum()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        removeExtensionNum()
        removeSitNum()
        resolve()
      })
    },
    
    // 修改签入状态
    ChangeSignStatus({ commit },status){
      return new Promise(resolve => {
        commit('SET_SIGNSTATUS', status)
        resolve()
      })
    }
  }
}

export default user
