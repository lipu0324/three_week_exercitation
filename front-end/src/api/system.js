/*
 * @Descripttion: 企业信息
 * @Author: Dyl
 * @Date: 2021-11-10 16:54:39
 */
import request from '@/utils/request'

export function getInfo(params) {
  return request({
    url: '/admin-api/enterprise/list',
    method: 'get',
    params
  })
}
export function addInfo(params) {
  return request({
    url: '/admin-api/enterprise/add',
    method: 'post',
    data:params
  })
}
export function editInfo(params) {
  return request({
    url: '/admin-api/enterprise/update',
    method: 'post',
    data:params
  })
}