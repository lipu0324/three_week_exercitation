/*
 * @Author: Dyl
 * @Date: 2022-02-14 15:18:35
 * @LastEditTime: 2022-02-25 11:17:16
 * @LastEditors: Please set LastEditors
 * @Description: 问卷接口
 * @FilePath: \sfcc\src\api\questionnaire.js
 */
import request from '@/utils/request'

// 新增问卷
export  function addQuestionnaire(data) {
  return request({
    url: "/admin-api/naire/addNaire",
    method:"post",
    data
  })
}

// 删除问卷
export  function delQuestionnaire(data) {
  return request({
    url: `/admin-api/naire/deleteNaire/${data.id}`,
    method:"post",
    data,
    setHeaders:true,
  })
}

// 获取问卷列表
export  function getList(params) {
  return request({
    url: "/admin-api/naire/listNaire",
    method:"get",
    params
  })
}

// 修改问卷
export  function changeQuestionnaire(data) {
  return request({
    url: "/admin-api/naire/updateNaire",
    method:"post",
    data
  })
}

// 新增问题
export  function addQuestions(data) {
  return request({
    url: "/admin-api/naire/addQuestion",
    method:"post",
    data
  })
}

// 删除问题
export  function delQuestions(data) {
  return request({
    url: `/admin-api/naire/deleteQuestion/${data.id}`,
    method:"post",
    data,
    setHeaders:true,
  })
}

// 获取问题列表
export  function getQuestionsList(params) {
  return request({
    url: "/admin-api/naire/listQuestion",
    method:"get",
    params
  })
}

// 修改问题
export  function changeQuestions(data) {
  return request({
    url: "/admin-api/naire/updateQuestion",
    method:"post",
    data
  })
}

// 获取回答列表
export  function getAnswersList(params) {
  return request({
    url: "/admin-api/naire/listAnswer",
    method:"get",
    params
  })
}

// 获取未被选择的问题
export  function getNoChooseQuse(params) {
  return request({
    url: "/admin-api/naire/listNoQuestion",
    method:"get",
    params
  })
}
