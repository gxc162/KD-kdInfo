import axios from '@/http/axios'
export default {
  namespaced: true,
  state: {
    users: [],
    editors: [],
    admins: []
  },
  mutations: {
    resetUsers (state, data) {
      const res = data.filter(item => item.role === 'user')
      state.users = res
    },
    resetEditors (state, data) {
      const res = data.filter(item => item.role === 'editor')
      state.editors = res
    },
    resetAdmins (state, data) {
      const res = data.filter(item => item.role === 'admin')
      state.admins = res
    }
  },
  actions: {
    // 1.保存用户
    async saveUser (context, params) {
      const response = await axios.post('/manager/user/reg', params)
      return response
    },
    // 2.改变用户的状态
    async changeUserStatus (context, params) {
      const response = await axios.post('/manager/user/changeStatus', params)
      return response
    },
    // 3.加载所有的用户信息
    async loadUsers (context) {
      const response = await axios.get('/manager/user/findAllUser')
      context.commit('resetUsers', response.data.data)
      context.commit('resetEditors', response.data.data)
      context.commit('resetAdmins', response.data.data)
    },
    // 4.根据id删除用户信息
    async deleteUserById (context, id) {
      const response = await axios.get('/manager/user/deleteUserById?id=' + id)
      return response
    }
  }
}
