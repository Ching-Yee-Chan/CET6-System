import { request } from '@/utils/request'
import { getResult } from '@/utils/result'

/**
 * 先整一个查找所有答案的函数
 */
export function getAnswerList(params) {
  console.log('CCC!')
  return request({
    url: `answer/${params.pageNum}/${params.pageSize}`,
    method: 'post',
    data: {
      answertype: 2
    }
  }).then(res => {
    console.log(res)
    return getResult(res)
  })
}

