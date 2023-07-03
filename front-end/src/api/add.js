import request from '@/utils/request'

// export function proList(params){
//   return request({
//     url:'/admin-api/problem/detailProblem/list',
//     method: 'get',
//     params: params
//   })
// }
export  function fetchList(userid) {
  return request({
    url:"/admin-api/admin/"+userid,
    method:"get"
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
// export function deletePro(params){
//   return request({
//     url:'/admin-api/problem/deleteDetailProblem',
//     method: 'get',
//     params: params
//   })
// }
