import { getQuestionList, addQuestion, updateQuestion, deleteQuestion, queryQuestion } from '@/api/question'

const question = {
  state: {
    questionList: []
  },

  mutations: {
    SET_QUESTIONLIST: (state, value) => {
      state.questionList = value
    }
  },

  actions: {
    // 获取用户列表
    getQuestionList({ commit }, params) {
      return getQuestionList(params).then(res => {
        commit('SET_QUESTIONLIST', res.list)
        return res
      }
      )
    },
    // 添加用户
    addQuestion({ commit }, params) {
      return addQuestion(params).then(res => {})
    },
    // 修改用户
    updateQuestion({ commit }, params) {
      return updateQuestion(params)
    },
    // 删除用户
    deleteQuestion({ commit }, params) {
      return deleteQuestion(params)
    },
    // 获取用户列表
    queryQuestion({ commit }, params) {
      return queryQuestion(params).then(res => {
        commit('SET_QUESTIONLIST', res.list)
        return res
      }
      )
    }
  }
}

export default question
