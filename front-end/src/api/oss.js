import request from '@/utils/request'
export function policy() {
  return request({
    url:'/admin-api/aliyun/oss/policy',
    method:'get',
  })
}
