/*
 * @Author: your name
 * @Date: 2021-10-22 16:46:35
 * @LastEditTime: 2021-12-06 13:35:53
 * @LastEditors: your name
 * @Description: 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 * @FilePath: \sfcc\src\api\member.js
 */
// 用户管理
import request from '@/utils/request'

// 获取用户列表
export function memberList(data) {
  return request({
    url: '/admin-api/memberInfo/list',
    method: 'post',
    setHeaders: true,
    data
  })
}

// 封禁/解封
export function changeMemberStatus(id, data) {
  return request({
    url: `/admin-api/memberInfo/ban/${id}`,
    method: 'post',
    setHeaders: true,
    data
  })
}

// 批量封禁
export function memberBan(data) {
  return request({
    url: `/admin-api/memberInfo/batchBan?${data}`,
    method: 'post',
    // setHeaders: true,
    // data
  })
}

// 新增用户
export function addMember(data) {
  return request({
    url: '/admin-api/memberInfo/add',
    method: 'post',
    data
  })
}

// 合并档案用户
export function mergeMember(data) {
  return request({
    url: '/admin-api/memberInfo/updateBatch',
    method: 'post',
    data
  })
}

// 批量新增用户
export function batchAddMember(data) {
  return request({
    url: '/admin-api/memberInfo/addBatch',
    method: 'post',
    data
  })
}