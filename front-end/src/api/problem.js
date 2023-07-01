import request from '@/utils/request'


export function proTypeList(params) {
  return request({
    url: '/admin-api/problem/allProblemType/list',
    method: 'get',
    params: params
  })
}

export function searchProType(params) {
  return request({
    url: '/admin-api/problem/selectProblemByName',
    method: 'get',
    params: params
  })
}

export function deleteProType(params) {
  return request({
    url: '/admin-api/problem/deleteProblemById',
    method: 'get',
    params: params
  })
}

export function createProblem(data) {
  return request({
    url: '/admin-api/problem/uploadImage',
    method: 'post',
    data: data
  })
}

export function detailProList(params) {
  return request({
    url: '/admin-api/problem/detailProblem/list',
    method: 'get',
    params: params
  })
}

export function changeProStatus(data) {
  return request({
    url: '/admin-api/problem/status/update',
    method: 'post',
    data: data
  })
}

export function deleteDetailPro(params) {
  return request({
    url: '/admin-api/problem/deleteDetailProblem',
    method: 'get',
    params: params
  })
}
export function addPro(data) {
  return request({
    url: '/admin-api/problem/insertNormal',
    method: 'post',
    data: data
  })
}
export function uploadFile(data) {
    return request({
      url: "/admin-api/problem/uploadDetailImage",
      method: "post",
      data: data
    });
}