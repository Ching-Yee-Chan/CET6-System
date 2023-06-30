import { request } from '@/utils/request'
import { getResult } from '@/utils/result'

/**
 * 获取用户列表
 */
export function getQuestionList(params) {
  return request({
    url: `question/all`,
    method: 'post',
    data: params.condition
  }).then(res => getResult(res))
}

export function getQueryList(params){
  return request({
    url: `/examUser/user/${params.pageNum}/${params.pageSize}`,
    method: 'post',
    data:params
  }).then(res => getResult(res))
}


/**
 * 新增用户信息
 */
export function addAnswer(params) {
  return request({
    url: `answer`,
    method: 'post',
    data: params
  }).then(res => getResult(res))
}

/**
 * 修改用户信息
 */
export function updateAnswer(params) {
  return request({
    url: `answer`,
    method: 'put',
    data: params
  }).then(res => getResult(res))
}

/**
 * 获取用户信息
 * @returns 返回用户信息
 */
export function getUserInfo(){
  return request({
    url:'user/info',
    method:'get'
  }).then(res=>getResult(res))
}

export function finishExam(param) {
  let temp = {
    id: param.id,
    score: param.score,
    status: 5
  }
  return request({
    url: '/examUser',
    method: 'put',
    data: temp
  }).then(() => {
    console.log('Finish Commit!')
  })
}
