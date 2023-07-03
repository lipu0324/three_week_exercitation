/*
 * @Descripttion: 标签管理接口
 * @Author: Dyl
 * @Date: 2021-10-22 15:54:26
 */
import request from '@/utils/request'

export  function test(data) {
  return request({
    url:"/admin-api/knowledge/import",
    method:"post",
    data,
    setHeaders:true
  })
}

// 新增标签
export  function addTags(data) {
  return request({
    url: "/admin-api/label/add",
    method:"post",
    data
  })
}

// 删除标签
export  function delTags(data) {
  return request({
    url: `/admin-api/label/delete/${data.id}`,
    method:"post",
    data,
    setHeaders:true,
  })
}

// 获取标签列表
export  function getList(params) {
  return request({
    url: "/admin-api/label/list",
    method:"get",
    params
  })
}

// 修改标签
export  function changeTags(data) {
  return request({
    url: "/admin-api/label/update",
    method:"post",
    data
  })
}

// 修改标签显示状态
export  function tagsStatusChange(data) {
  return request({
    url: `/admin-api/label/updateStatus/${data.id}`,
    method:"post",
    data,
    setHeaders:true,
  })
}

// 批量删除标签
export  function batchDel(data) {
  return request({
    url: `/admin-api/label/batchDelete?${data}`,
    method: 'post',
  })
}