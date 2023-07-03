// 标签管理

// 用户管理
import request from '@/utils/request'

export function labelList(params) {
  return request({
    url: '/admin-api/label/list',
    method: 'get',
    params
  })
}