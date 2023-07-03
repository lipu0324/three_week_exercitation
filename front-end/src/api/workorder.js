/*
 * @Author: dyl
 * @Date: 2021-11-25 17:45:27
 * @LastEditTime: 2022-03-04 14:12:11
 * @LastEditors: Please set LastEditors
 * @Description: 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 * @FilePath: \sfcc\src\api\workorder.js
 */
import request from '@/utils/request'

// 分页获取工单
export function fetchList(params) {
  return request({
    url: '/admin-api/worder/list',
    method: 'get',
    params: params
  })
}

// 获取工单详情
export function getOrderDetail(params) {
  return request({
    url: `/admin-api/worder/getByNO/${params.worderCode}`,
    method: 'get',
    params: params
  })
}

// 创建工单
export function creatOrder(data) {
  return request({
    url: '/admin-api/worder/create',
    method: 'post',
    data
  })
}

// 更换工单负责人
export function changeOrderDealer(data) {
  return request({
    url: '/admin-api/worder/changeDealeer',
    method: 'post',
    data:data
  })
}

// 修改工单信息
export function editOrder(data) {
  return request({
    url: '/admin-api/worder/changeNormal',
    method: 'post',
    data:data
  })
}

// 作废工单
export function deleteOrder(data) {
  return request({
    url: `/admin-api/worder/cancelOrder/${data.worderCode}`,
    method: 'post',
    data
  })
}

// 处理工单信息
export function dealOrder(data) {
  return request({
    url: '/admin-api/worder/process',
    method: 'post',
    data:data
  })
}

// 处理工单信息
export function orderNotice() {
  return request({
    url: '/admin-api/worder/qryToDO',
    method: 'post'
  })
}

// 关联工单标签
export function bindlabel(data) {
  return request({
    url: '/admin-api/worder/makeLabel',
    method: 'post',
    data:data
  })
}

// 查询各状态工单数量
export function getOrderNum(data) {
  return request({
    url: '/admin-api/worder/getCount',
    method: 'post',
    data
  })
}
