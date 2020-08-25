import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import app from './modules/app'
import settings from './modules/settings'
import user from './modules/user'
import Users from './users/index'
import Article from './article/index'
import Lanmu from './lanmu/index'
import Comment from './comment/index'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    settings,
    user,
    Users,
    Article,
    Lanmu,
    Comment
  },
  getters
})

export default store
