import { getAnswerList } from '@/api/answer'

const answer = {
  state: {
    answerList: []
  },

  mutations: {
    SET_ANSWERLIST: (state, value) => {
      state.examList = value
    }
  },

  actions: {
    // 获取答案列表
    getAnswerList({ commit }, params) {
      console.log('BBB!')
      return getAnswerList(params).then(res => {
        commit('SET_ANSWERLIST', res.list)
        return res
      }
      )
    }
  }
}

export default answer
