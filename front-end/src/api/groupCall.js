/*
 * @Author: Dyl
 * @Date: 2022-02-16 16:12:18
 * @LastEditTime: 2022-02-16 16:22:22
 * @LastEditors: Please set LastEditors
 * @Description: 群呼接口
 * @FilePath: \sfcc\src\api\groupCall.js
 */
import request from '@/utils/request'

// 获取群呼列表
export function getGroupCallList(params) {
  return request({
    url: '/admin-api/task/list',
    method: 'get',
    params
  })
}

// 添加外呼任务
export function addGroupCall(data) {
  return request({
    url: '/admin-api/task/add',
    method: 'post',
    data
  })
}

// 删除外呼任务
export function delGroupCall(data) {
  return request({
    url: `/admin-api/task/delete/${data.id}`,
    method: 'post',
    data
  })
}

// 修改外呼任务
export function updateGroupCall(data) {
  return request({
    url: '/admin-api/task/update',
    method: 'post',
    data
  })
}

// 开始/结束外呼任务
export function changeGroupCall(data) {
  return request({
    url: '/admin-api/task/updateStatus',
    method: 'post',
    data
  })
}