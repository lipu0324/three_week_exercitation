/*
 * @Author: your name
 * @Date: 2021-10-22 16:46:35
 * @LastEditTime: 2021-12-01 17:32:12
 * @LastEditors: Please set LastEditors
 * @Description: 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 * @FilePath: \sfcc\src\api\knowledge.js
 */
// 知识库管理
import request from '@/utils/request'

// 获取知识库列表
export function getKnowledgeList(params) {
  return request({
    url: '/admin-api/knowledge/list',
    method: 'get',
    params
  })
}

// 批量导出
export function exportKnowledge(data) {
  return request({
    url: '/admin-api/knowledge/export',
    method: 'post',
    data
  })
}

// 批量导入
export function importKnowledge(data) {
  return request({
    url: '/admin-api/knowledge/import',
    method: 'post',
    data,
    setHeaders:true
  })
}

// 删除
export function deleteKnowledge(id) {
  return request({
    url: `/admin-api/knowledge/delete/${id}`,
    method: 'post'
  })
}

// 下载导入模板
export function uploadTemplateUrl(data) {
  return request({
    url: '/admin-api/knowledge/exportTemplate',
    method: 'post',
    data
  })
}