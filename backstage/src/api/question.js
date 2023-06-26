import { request } from '@/utils/request'
import { getResult } from '@/utils/result'

/**
 * 获取用户列表
 */
export function getQuestionList(params) {
  return request({
    url: `question/${params.pageNum}/${params.pageSize}`,
    method: 'post',
    data: {}
  }).then(res => getResult(res))
}

/**
 * 新增用户信息
 */
export function addQuestion(params) {
  console.log(params)
  return request({
    url: `question`,
    method: 'post',
    data: params
  }).then(res => getResult(res))
}

/**
 * 修改用户信息
 */
export function updateQuestion(params) {
  console.log(params)
  return request({
    url: `question`,
    method: 'put',
    data: params
  }).then(res => getResult(res))
}

/**
 * 修改删除信息
 */
export function deleteQuestion(id) {
  // console.log(id)
  return request({
    url: `question/${id}`,
    method: 'delete'
  }).then(res => getResult(res))
}

/**
 * 按条件信息
 */
export function queryQuestion(params) {
  return request({
    url: `question/${params.pageNum}/${params.pageSize}`,
    method: 'post',
    data: params.condition
  }).then(res => getResult(res))
}

