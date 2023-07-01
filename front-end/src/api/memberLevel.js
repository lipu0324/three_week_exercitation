import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/admin-api/memberLevel/list',
    method:'get',
    params:params
  })
}
