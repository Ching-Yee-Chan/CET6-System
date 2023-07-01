import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import user from './user'
import exam from './exam'
import info from './info'
import examIntroduction from './examIntroduction'
import examUser from './examUser'
import feedback from './feedback'
import enumList from './enum'
import getters from './getters'
import dashboard from './dashboard'
import question from './question'
import answer from './answer'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    user,
    enumList,
    exam,
    examIntroduction,
    examUser,
    feedback,
    info,
    dashboard,
    question,
    answer
  },
  getters
})

export default store
