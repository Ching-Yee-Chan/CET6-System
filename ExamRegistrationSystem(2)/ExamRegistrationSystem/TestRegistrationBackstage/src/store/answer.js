import { getAnswerList } from '@/api/answer'
import { exportAddScore } from '@/api/exam'

const answer = {
  state: {
    answerList: []
  },

  mutations: {
    SET_ANSWERLIST: (state, value) => {
      state.answerList = value
    }
  },

  actions: {
    // 获取答案列表
    getAnswerList({ commit }, params) {
      console.log('BBB!')
      return getAnswerList(params).then(res => {
        commit('SET_ANSWERLIST', res.list)
        console.log(res.list)
        return res
      }
      )
    },
    addScore2({ commit }, params) {
      console.log('BBB!')
      return exportAddScore(params).then(res => {
        // commit('SET_ANSWERLIST', res.list)
        console.log(res.list)
        return res
      }
      )
    }
  }
}

export default answer
