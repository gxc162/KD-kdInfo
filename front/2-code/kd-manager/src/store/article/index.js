import axios from '@/http/axios'
export default {
  namespaced: true,
  state: {
    article: [],
    total: 10,
    categories: [],
    articleDetails: {},
    editors: []
  },
  mutations: {
    // 1.重置文章
    resetArticle (state, article) {
      state.article = article
    },
    // 2.重置total
    resetTotal (state, total) {
      state.total = total
    },
    // 3.重置栏目信息
    changeCategories (state, categories) {
      const res = categories.filter(item => item.parent !== null)
      state.categories = res
    },
    // 4.重置文章详情
    changeArticleDetails (state, articleDetails) {
      state.articleDetails = articleDetails
    },
    // 5.重置所有编辑信息
    resetEditor (state, data) {
      const res = data.filter(item => item.role === 'editor')
      state.editors = res
    }
  },
  actions: {
    // 1.分页加载文章
    async loadArticle ({ commit }, payload) {
      await axios.get('/manager/article/findArticle', {
        params: payload
      }).then((response) => {
        commit('resetArticle', response.data.data.list)
        commit('resetTotal', response.data.data.total)
      })
    },
    // 2.保存或更新文章信息
    async saveOrUpDateArticle ({ dispatch }, article) {
      const response = await axios.post('/manager/article/saveOrUpdateArticle', article)
      return response
    },
    // 3.通过ID删除文章
    async deleteArticleById ({ dispatch }, id) {
      const response = await axios.get('/manager/article/deleteArticleById?id=' + id)
      return response
    },
    // 4. 批量删除文章
    async batchDelectArticle (context, ids) {
      const response = await axios.post('/manager/article/batchDeleteArticle', ids)
      return response
    },
    // 5.加载栏目信息
    async loadCategories (context) {
      axios.get('/manager/category/findAllCategory').then((res) => {
        context.commit('changeCategories', res.data.data)
      })
    },
    // 6.通过id查询文章详情
    async findArticleById (context, id) {
      const response = await axios.get('/manager/article/findArticleById?id=' + id)
      context.commit('changeArticleDetails', response.data.data)
      return response
    },
    // 7.通过ID审核文章，改变状态
    async checkArticle (context, params) {
      const response = await axios.get('/manager/article/checkArticle', { params })
      return response
    },
    // 8.查询所有编辑信息
    async findAllEditor (context) {
      const response = await axios.get('/manager/user/findAllUser')
      context.commit('resetEditor', response.data.data)
    }
  }
}
