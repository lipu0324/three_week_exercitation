/*
 * @Descripttion: 档案管理
 * @Author: Dyl
 * @Date: 2021-11-12 16:25:16
 */
import request from '@/utils/request'

// 获取档案列表
export function getArchiveList(params) {
  return request({
    url: '/admin-api/archives/list',
    method: 'get',
    params
  })
}

// 导出
export function exportData(params) {
  return request({
    url: '/admin-api/dept/list',
    method: 'post',
    params,
    setHeaders:true
  })
}

// 获取档案详情
export function getArchiveDetail(params) {
  return request({
    url: `/admin-api/archives/get/${params.id}`,
    method: 'get',
    params
  })
}

// 获取档案详情
export function getArchiveDetailByCode(params) {
  return request({
    url: `/admin-api/archives/getArchives/${params.code}`,
    method: 'get',
    params
  })
}

// 根据来电获取档案信息
export function getArchiveByNum(params) {
  return request({
    url: '/admin-api/archives/getArchivesByPhone',
    method: 'get',
    params
  })
}

// 编辑取档案信息
export function editArchive(data) {
  return request({
    url: '/admin-api/archives/update',
    method: 'post',
    data
  })
}

// 新增档案内容
export function addArchiveContent(data) {
  return request({
    url: '/admin-api/archives/addContent',
    method: 'post',
    data
  })
}

// 合并档案
export function mergeArc(data) {
  return request({
    url: '/admin-api/archives/updateBatch',
    method: 'post',
    data
  })
}

// 删除档案
export function delArchive(data) {
  return request({
    url: `/admin-api/archives/delete/${data.id}`,
    method: 'post',
    data
  })
}

// 批量导出
export function exportArchive(data) {
  return request({
    url: '/admin-api/archives/export',
    method: 'post',
    data,
    setHeaders:true
  })
}

// 通过标签筛选档案
export function searchArchive(data) {
  return request({
    url: '/admin-api/archives/getArchivesCount',
    method: 'post',
    data,
    setHeaders:true
  })
}