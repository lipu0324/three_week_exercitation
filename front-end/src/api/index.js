/*
 * @Author: dyl
 * @Date: 2021-12-08 14:29:13
 * @LastEditTime: 2022-03-07 14:12:10
 * @LastEditors: Please set LastEditors
 * @Description: index接口
 * @FilePath: \sfcc\src\api\index.js
 */

import request from '@/utils/request'

// 获取首页数据
export function getIndexData(data) {
  return request({
    url: '/admin-api/index/',
    method: 'post',
    data,
    setHeaders:true
  })
}

// 获取评价统计
export function getScore(data) {
  return request({
    url: '/admin-api/score/getScore',
    method: 'post',
    data,
    setHeaders:true
  })
}