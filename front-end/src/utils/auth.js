/*
 * @Descripttion: 
 * @Author: Dyl
 * @Date: 2021-10-20 09:24:12
 */
import Cookies from 'js-cookie'

const TokenKey = 'loginToken'
const extensionNumKey = 'extension'
const sitNumKey = 'sitNum'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function getExtensionNum() {
  return Cookies.get(extensionNumKey)
}

export function setExtensionNum(extensionNum) {
  return Cookies.set(extensionNumKey, extensionNum)
}

export function removeExtensionNum() {
  return Cookies.remove(extensionNumKey)
}


export function getSitNum() {
  return Cookies.get(sitNumKey)
}

export function setSitNum(sitNum) {
  return Cookies.set(sitNumKey, sitNum)
}

export function removeSitNum() {
  return Cookies.remove(sitNumKey)
}