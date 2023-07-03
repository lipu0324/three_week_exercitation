/*
 * @Descripttion: 部门管理
 * @Author: Dyl
 * @Date: 2021-11-11 10:55:55
 */
import request from '@/utils/request'

// 获取部门列表
export function getDeptList(params) {
  return request({
    url: '/admin-api/dept/list',
    method: 'post',
    params,
    setHeaders:true
  })
}

// 添加部门
export function addDept(params) {
  return request({
    url: '/admin-api/dept/add',
    method: 'post',
    data:params
  })
}

// 修改部门
export function editDept(params) {
  return request({
    url: '/admin-api/dept/updateDept',
    method: 'post',
    data:params
  })
}

// 批量封禁部门
export function batchBanDept(data) {
  return request({
    url: `/admin-api/dept/batchBan?${data}`,
    method: 'post'
  })
}

