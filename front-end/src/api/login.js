import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '/admin-api/admin/login',
    method: 'post',
    data: {
      username,
      password
    }
  })
}

export function addList(data) {
  return request({
    url: '/admin-api/login/index',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    url: '/admin-api/admin/info',
    method: 'get',
  })
}

export function logout() {
  return request({
    url: '/admin-api/admin/logout',
    method: 'post'
  })
}

export function fetchList(params) {
  return request({
    url: '/admin-api/admin/list',
    method: 'get',
    params: params
  })
}

export function createAdmin(data) {
  return request({
    url: '/admin-api/admin/register',
    method: 'post',
    data: data
  })
}

export function updateAdmin(id, data) {
  return request({
    url: '/admin-api/admin/update/' + id,
    method: 'post',
    data: data
  })
}

export function updateStatus(id, params) {
  return request({
    url: '/admin-api/admin/updateStatus/' + id,
    method: 'post',
    params: params
  })
}

export function deleteAdmin(id) {
  return request({
    url: '/admin-api/admin/delete/' + id,
    method: 'post'
  })
}

export function getRoleByAdmin(id) {
  return request({
    url: '/admin-api/admin/role/' + id,
    method: 'get'
  })
}

export function allocRole(data) {
  return request({
    url: '/admin-api/admin/role/update',
    method: 'post',
    data: data
  })
}
export function memberInfoList(params) {
  return request({
    url: '/admin-api/memberInfo/list',
    method: 'get',
    params: params
  })
}
export function watchMember(id) {
  return request({
    url: '/admin-api/memberInfo/info/' + id,
    method: 'get',
  })
}
export function disable(id, params) {
  return request({
    url: '/admin-api/memberInfo/updateStatus/' + id,
    method: 'post',
    params: params
  })
}
export function deleteMember(id) {
  return request({
    url: '/admin-api/memberInfo/updateDeleteStatus/' + id,
    method: 'post'
  })
}
export function batchBan(data) {
  return request({
    url: `/admin-api/admin/batchBan?${data}`,
    method: 'post',
  })
}
export function getInfoByAdmin(id) {
  return request({
    url: '/admin-api/admin/' + id,
    method: 'get'
  })
}