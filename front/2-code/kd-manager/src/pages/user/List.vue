<template>
	<div class="user">
		<div class="content">
			<template>
				<el-tabs v-model="activeName" type="card" addable @tab-add="adduser">
					<el-tab-pane label="管理员" name="first">
						<el-row :gutter="12">
							<el-col :span="6" v-for='item in admins' :key='item.id'>
								<el-card shadow="hover">
									<div class="image">
										<img :src="item.userface">
									</div>
									<div class="info">
										<div>
											<span>用&nbsp;户&nbsp;&nbsp;名:</span>
											<span>{{item.username}}</span>
										</div>
										<div>
											<span>真实姓名:</span>
											<span>{{item.nickname}}</span>
										</div>
										<div>
											<span>邮&emsp;&emsp;箱:</span>
											<span>{{item.email}}</span>
										</div>
										<div>
											<span>状&emsp;&emsp;态:</span>
											<span>
												<el-switch
													v-model="item.enabled"
													active-color="#13ce66"
													inactive-color="#ff4949"
													@change='changeStatus(item)'>
												</el-switch>
											</span>
										</div>
										<div>
											<span>操&emsp;&emsp;作:</span>
											<span style="margin-top: -11px">
												<el-button type="text" icon="el-icon-delete" @click='deleteHandler(item.id)'></el-button>
												<el-button type="text" icon="el-icon-edit" @click='editHandler(item)'></el-button>
											</span>
										</div>
									</div>
								</el-card>
							</el-col>
						</el-row>
					</el-tab-pane>
					<el-tab-pane label="编辑" name="second">
						<el-row :gutter="12">
							<el-col :span="6" v-for='item in editors' :key='item.id'>
								<el-card shadow="hover">
										<div class="image">
											<img :src="item.userface">
										</div>
										<div class="info">
											<div>
												<span>用&nbsp;户&nbsp;&nbsp;名:</span>
												<span>{{item.username}}</span>
											</div>
											<div>
												<span>真实姓名:</span>
												<span>{{item.nickname}}</span>
											</div>
											<div>
												<span>邮&emsp;&emsp;箱:</span>
												<span>{{item.email}}</span>
											</div>
											<div>
												<span>状&emsp;&emsp;态:</span>
												<span>
													<el-switch
														v-model="item.enabled"
														active-color="#13ce66"
														inactive-color="#ff4949"
														@change='changeStatus(item)'>
													</el-switch>
												</span>
											</div>
											<div>
											<span>操&emsp;&emsp;作:</span>
												<span style="margin-top: -11px">
													<el-button type="text" icon="el-icon-delete" @click='deleteHandler(item.id)'></el-button>
													<el-button type="text" icon="el-icon-edit" @click='editHandler(item)'></el-button>
												</span>
											</div>
										</div>
								</el-card>
							</el-col>
						</el-row>
					</el-tab-pane>
					<el-tab-pane label="会员" name="third">
						<el-row :gutter="12">
							<el-col :span="6" v-for='item in users' :key='item.id'>
								<el-card shadow="hover">
									<div class="image">
										<img :src="item.userface">
									</div>
									<div class="info">
										<div>
											<span>用&nbsp;户&nbsp;&nbsp;名:</span>
											<span>{{item.username}}</span>
										</div>
										<div>
											<span>真实姓名:</span>
											<span>{{item.nickname}}</span>
										</div>
										<div>
											<span>邮&emsp;&emsp;箱:</span>
											<span>{{item.email}}</span>
										</div>
										<div>
											<span>状&emsp;&emsp;态:</span>
											<span>
												<el-switch
											v-model="item.enabled"
											active-color="#13ce66"
											inactive-color="#ff4949"
											@change='changeStatus(item)'>
										</el-switch>
											</span>
										</div>
									</div>
								</el-card>
							</el-col>
						</el-row>
					</el-tab-pane>
				</el-tabs>
			</template>
		</div>
		<!-- 新增用户模态框开始 -->
		<el-dialog
		  title="新增用户"
		  :visible.sync="dialogVisible"
		  width="30%"
		  >
		  <!-- 表单开始 -->
		 <el-form label-position="right" label-width="80px" :model="userForm">
		  <el-form-item label="用 户 名">
		    <el-input v-model="userForm.username"></el-input>
		  </el-form-item>
		  <!-- <el-form-item label="密 码">
		    <el-input v-model="userForm.password"></el-input>
		  </el-form-item> -->
		   <el-form-item label="真实姓名">
		    <el-input v-model="userForm.nickname"></el-input>
		  </el-form-item>
		  <el-form-item label="邮 箱">
		    <el-input v-model="userForm.email"></el-input>
		  </el-form-item>
			<el-form-item label="角 色">
		    <el-select v-model="userForm.role" clearable placeholder="请选择">
					<el-option
						v-for="item in options"
						:key="item.value"
						:label="item.label"
						:value="item.value">
					</el-option>
				</el-select>
		  </el-form-item>
		</el-form>
			<!-- 表单结束 -->
		  <span slot="footer" class="dialog-footer">
		    <el-button size='small' @click="dialogVisible = false">取 消</el-button>
		    <el-button size='small' type="primary" @click="savaUser">确 定</el-button>
		  </span>
		</el-dialog>
		<!-- 新增用户模态框结束 -->
	</div>
</template>
<script type="text/javascript">
import {mapActions,mapGetters,mapMutations,mapState} from 'vuex';
	export default {
		data(){
			return {
				dialogVisible: false,
				userForm: {},
				activeName:'first',
				options: [{
          value: 'admin',
          label: '管理员'
        }, {
          value: 'editor',
          label: '编辑'
        }, {
          value: 'user',
          label: '会员'
        }],
			}
		},
		 computed: {
    		...mapState('Users',['users','editors','admins'])
  		},
  		created(){
			this.loadUsers()
  		},
  		methods:{
  			...mapActions('Users',['loadUsers','changeUserStatus','saveUser','deleteUserById']),
  			// 1.新增用户
  			adduser(){
					this.dialogVisible = true
					this.userForm = {}
  			},
  			// 2.保存用户
  			savaUser(){
  				this.dialogVisible = false
					this.userForm.enabled = true
					this.userForm.password = '123321'
  				this.userForm.userface = 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581326895641&di=dda42443eb3e835a91f0398f00b65909&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F35%2F34%2F19300001295750130986345801104.jpg'
					this.saveUser(this.userForm).then((response)=>{
						const status = response.data.status
						if(status === 200){
							this.loadUsers()
							this.$notify.success({
								title: '成功',
								message: '保存成功！'
							});
						} else {
							this.$notify.error({
								title: '错误',
								message: '保存失败！'
							});
						}
				})
  				
  			},
  			// 3.改变用户的状态
  			changeStatus(item){
  				var obj={
  					id:item.id,
  					status:item.enabled
  				}
				this.changeUserStatus(obj).then((response) => {
					const status = response.data.status
					if(status === 200){
						this.loadUsers()
						this.$notify.success({
							title: '成功',
							message: '操作成功！'
						});
					} else {
						this.$notify.error({
							title: '错误',
							message: '操作失败！'
						});
					}
				})
				},
				// 4.去删除
				deleteHandler(id) {
					this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
		          confirmButtonText: '确定',
		          cancelButtonText: '取消',
		          type: 'warning'
		        }).then(() => {
							this.deleteUserById(id)
							.then((response) => {
								const status = response.data.status
								if(status === 200){
									this.loadUsers()
									this.$notify.success({
										title: '成功',
										message: '删除成功！'
									});
								} else {
									this.$notify.error({
										title: '错误',
										message: '删除失败！'
									});
								}
							})
						})
				},
				// 5.去修改
				editHandler(item) {
					delete item.regtime
					this.userForm = item
					this.dialogVisible = true
				}
  		}
	}
</script>
<style type="text/css" scoped>
	.user{
		position: relative;
		padding: 0.5em;
		z-index: 1px;
	}
	.user-top{
		margin-bottom: 1em;
	}
	.el-card{
		margin-bottom: 1em;
	}
	.image {
		text-align: center;
	}
	.el-row{
		height: 550px;
		overflow-y: auto;
		width: 102.5%;
	}
	.image img{
		width: 100px;
		height: 100px;
		border-radius: 50%;
	}
	.info div{
		text-align: center;
		overflow: hidden;
		margin-top: 1em;
		color: #7E7F7F;
	}
	.info div>span:first-child{
		float: left;
		width: 80px;
		text-align: left;
		padding-right: 10px;
		margin: 0;
	}
	.info div>span:last-child{
		float: left;
		width: 50%;
		/* padding-left: 0.5em; */
		text-align: left;
		position: relative!important;
	}
	.el-switch {
		/*top:-5px;*/
		position: absolute!important;
		top:-15px;
	}
</style>