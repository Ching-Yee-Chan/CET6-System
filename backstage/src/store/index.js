import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import user from './user'
import exam from './exam'
import enumList from './enum'
import getters from './getters'
import examIntroduction from './examIntroduction'
import examUser from './examUser'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    user,
    enumList,
    exam,
    examIntroduction,
    examUser
  },
  getters
})

export default store
