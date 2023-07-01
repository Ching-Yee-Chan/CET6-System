import {
  getUserInfo,
  getQuestionList,
  addAnswer,
  updateAnswer,
  finishExam
} from '../api/onlineExam'
import * as Api from "@/modules/personalCenter/api/personalCenter";
const question = {
  actions: {
    // 获取用户信息
    getUserId({commit}){
      return getUserInfo()
    },
    // 添加作答情况
    addAnswer({ commit }, params) {
      return addAnswer(params).then(res => {})
    },
    // 修改作答情况
    updateAnswer({ commit }, params) {
      return updateAnswer(params)
    },
    // 获取试题列表
    getQuestionList({ commit }, params) {
      return getQuestionList(params).then(res => {
          commit('SET_QUESTIONLIST', res.list)
          return res.list
        }
      )
    },
    finishExam({ commit }, param) {
      return finishExam(param)
    }
  }
}

export default question
