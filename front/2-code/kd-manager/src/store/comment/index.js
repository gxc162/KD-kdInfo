import axios from '@/http/axios'
export default {
  namespaced: true,
  state: {
    comments: [],
    total: 0
  },
  mutations: {
    // 1.重置评论
    resetComments (state, data) {
      state.comments = data
    },
    // 2.重置total
    resetTotal (state, total) {
      state.total = total
    }
  },
  actions: {
    // 1.分页查询评论信息
    async loadComments ({ commit }, payload) {
      await axios.get('/manager/comment/findComment', {
        params: payload
      }).then((response) => {
        commit('resetComments', response.data.data.list)
        commit('resetTotal', response.data.data.total)
      })
    },
    // 2.通过ID审核评论
    async auditComment (context, payload) {
      const response = await axios.get('/manager/comment/checkComment', { params: payload })
      return response
    },
    // 3.通过ID删除评论信息
    async deleteCommentById (context, id) {
      const response = await axios.get('/manager/comment/deleteCommentById?id=' + id)
      return response
    }
  }
}
