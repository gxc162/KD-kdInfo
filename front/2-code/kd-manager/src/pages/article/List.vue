<template>
	<div class="article">
		<div class="article_top">
			<el-row :gutter="0">
				<el-col :span='5'>
					<el-button type="primary" size='small' @click='toAddHandler'>新增</el-button>
					<el-button type="primary" size='small' @click='batchDelect'>批量删除</el-button>
				</el-col>
				<el-col :span="3">
					<el-button size="medium" class="reset" :offset="2" @click="resetData">重置</el-button>
				</el-col>
				<el-col :span='8'>
						<div class="block" offset="10">
							<el-date-picker
								unlink-panels
								v-model="time"
								type="daterange"
								range-separator="至"
								start-placeholder="开始日期"
								end-placeholder="结束日期"
								format="yyyy 年 MM 月 dd 日"
      					value-format="yyyy-MM-dd">
							</el-date-picker>
						</div>
				</el-col>
				<el-col :span='4'>
					<el-input v-model="keywords" placeholder='请输入标题关键字'></el-input>
				</el-col>	
				<el-col :span='4'>
					<el-select v-model="categoryId" placeholder="请选择栏目" clearable class='select'>
						<el-option
							v-for="item in categories"
							:key="item.id"
							:label="item.name"
							:value="item.id">
						</el-option>
					</el-select>
				</el-col>	
			</el-row>	
		</div>
		<div class="article_content">
			<!-- 表格开始 -->
			<el-table :data="article" style="width: 100%" size='small'
			@selection-change="selectionChangeHandler">
	     	<el-table-column
		      type="selection"
		      width="50" 
					align='center'>
		    </el-table-column>
	      <el-table-column
	        prop="title"
	        label="文章标题"
					align='center'>
	      </el-table-column>
	      <el-table-column
	        prop="category.name"
	        label="所属栏目"
	        width="150"
					align='center'>
	      </el-table-column>
				<el-table-column
	        prop="author.nickname"
	        label="作者"
	        width="150"
					align='center'>
	      </el-table-column>
	      <el-table-column
	        prop="publishtime"
	        label="发布时间"
					width="150"
					align='center'>
	      </el-table-column>
	      <el-table-column
	        prop="readtimes"
	        label="阅读次数"
	        width="100"
					align='center'>
	      </el-table-column>
	      <el-table-column
	        prop="status"
	        label="状态"
	        width="100" 
					align='center'>
	      </el-table-column>
				<el-table-column
	        label="详情"
	        width="100" 
					align='center'>
					<template slot-scope='{row}'>
						<el-button type="primary" size="mini" @click="toArticleDetailsHandler(row.id)">查看</el-button>
					</template>
	      </el-table-column>
	      <el-table-column label="操作" width="100" align='center'>
	      	<template slot-scope='{row}'>
	      		<i class="fa fa-trash" @click='deleteHandler(row.id)'></i>&emsp;
	      		<i class="fa fa-pencil" @click='toEditHandler(row)'></i>
	      	</template>
	      </el-table-column>
	    </el-table>
	   	<!-- 表格结束 -->
			<!-- 模态框开始 -->
			<el-dialog
				:title=title
				:visible.sync="dialogVisible"
				width="85%"
				>
				<!-- 表单开始 -->
			<el-form label-position="right" label-width="80px" :model="articleForm">
				<el-form-item label="文章标题">
					<el-input v-model="articleForm.title"></el-input>
				</el-form-item>
			<el-form-item label="所属栏目">
				<el-select v-model="articleForm.categoryId" placeholder="请选择所属栏目">
					<el-option :key='c.id' v-for='c in categories' :label="c.name" :value="c.id"></el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="文章作者">
				<el-select v-model="articleForm.userId" placeholder="请选择文章作者">
					<el-option :key='c.id' v-for='c in editors' :label="c.nickname" :value="c.id"></el-option>
				</el-select>
			</el-form-item>
			<!-- <el-form-item label="列表样式" >
				<ul class="list_style">
					<li class="style_one" :class="{current:articleForm.liststyle=='style-one'}" @click="articleForm.liststyle = 'style-one'">
						<img src="@/assets/form_images/1.jpg" alt="图片丢了">
					</li>
					<li class="style_two" :class="{current:articleForm.liststyle=='style-two'}"  @click="articleForm.liststyle = 'style-two'">
						<img src="@/assets/form_images/2.jpg" alt="图片丢了">
					</li>
				</ul>
			</el-form-item> -->
			<el-form-item label="正文">
				<!-- 富文本编辑器 -->
		    <mavon-editor ref="articleContent" v-model="articleForm.content"/>
			</el-form-item>
			</el-form>
				<!-- 表单结束 -->
				<span slot="footer" class="dialog-footer">
					<el-button size='small' @click="dialogVisible = false">取 消</el-button>
					<el-button size='small' type="primary" @click="toSaveHandler">确 定</el-button>
				</span>
			</el-dialog>
			<!-- 模态框结束 -->
			<!-- 详情模态框开始 -->
			<el-dialog title="文章详情" width="50%" :visible.sync="detailsVisible">
				<el-form :data="articleDetails">
					<span class="none">{{articleDetails}}</span>
					<el-row>
						<el-form-item label="文章标题：">
							<span>{{articleDetails.title}}</span>
						</el-form-item>
					</el-row>
					<el-row>
						<el-col :span="8">
							<el-form-item label="文章作者：">
								<span v-if="articleDetails.author">{{articleDetails.author.nickname}}</span>
							</el-form-item>
						</el-col>
						<el-col :span="6">
							<el-form-item label="所属栏目：">
								<span v-if="articleDetails.category">{{articleDetails.category.name}}</span>
							</el-form-item>
						</el-col>
						<el-col :span="5">
							<el-form-item label="阅读次数：">
								<span>{{articleDetails.readtimes}}</span>
							</el-form-item>
						</el-col>
						<el-col :span="5">
							<el-form-item label="状态：">
								<span>{{articleDetails.status}}</span>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="14">
							<el-form-item label="发布时间：">
								<span>{{articleDetails.publishtime}}</span>
							</el-form-item>
						</el-col>
						<el-col :span="5">
							<el-form-item label="点赞次数：">
								<span>{{articleDetails.thumbup}}</span>
							</el-form-item>
						</el-col>
						<el-col :span="5">
							<el-form-item label="被踩次数：">
								<span>{{articleDetails.thumbdown}}</span>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-form-item label="评论状态：">
							<el-switch
								v-model="commentStatus"
								active-color="#1296db"
								inactive-color="#cdcdcd"
								@change='changeCommentStatus(articleDetails)'>
							</el-switch>
						</el-form-item>
					</el-row>
					<el-form-item label="文章正文：">
						<span>{{articleDetails.content}}</span>
					</el-form-item>
				</el-form>
				<div slot="footer" class="dialog-footer">
					<el-button @click="auditHandler(articleDetails.id)">通过审核</el-button>
					<el-button type="primary" @click="detailsVisible = false">返 回</el-button>
				</div>
			</el-dialog>
			<!-- 详情模态框结束 -->
			<!-- 分页开始 -->
			<div class="page">
				<el-pagination
					layout="prev, pager, next"
					:page-size='this.pageSize'
					@current-change='handleCurrentChange'
					:total="this.total">
				</el-pagination>
			</div>
			<!-- 分页结束 -->
		</div>
	</div>
</template>
<script type="text/javascript">
import {mapActions,mapState,mapMutations,mapGetters} from 'vuex';
	export default {
		data(){
			return {
				page: 0,
				pageSize: 9,
				categoryId: null,
				multipleSelection:[],
				dialogVisible: false ,
				detailsVisible: false ,
				title:'',
				articleForm:{
					liststyle:'true'
				},
				time:[],
				keywords:'',
				commentStatus: true
			}
		},
			created() {
				let payload = {
					page:this.page,
					pageSize: this.pageSize,
					categoryId: this.categoryId
				}
				this.loadArticle(payload)
				this.loadCategories()
				this.findAllEditor()
			},
		  computed: {
				...mapState('Article',['article','total','categories','articleDetails','editors']),
			},
			methods: {
				...mapActions('Article',['loadArticle','saveOrUpDateArticle','deleteArticleById','batchDelectArticle','loadCategories','findArticleById','checkArticle','findAllEditor']),
				// 1.分页page处理
				handleCurrentChange (page) {
					this.page = page -1
					const beginTime = this.time[0]
					const endTime = this.time[1]
					let payload = {
						page:this.page,
						pageSize: this.pageSize,
						beginTime: beginTime,
						endTime: endTime,
						keywords: this.keywords,
						categoryId: this.categoryId
					}
					this.loadArticle(payload)
				},
				// 2.打开添加模态框
				toAddHandler() {
					this.dialogVisible=true
					this.title='添加文章'
					this.articleForm={
						liststyle:'style-one'
					}
				},
				// 3.打开修改模态框
				toEditHandler (row) {
					let article = {
						id:row.id,
						title:row.title,
						userId:row.author.id,
						categoryId:row.category.id,
						liststyle:row.liststyle,
						publishtime:row.publishtime,
						readtimes:row.readtimes,
						status:row.status,
						thumbup:row.thumbup,
						thumbdown:row.thumbdown,
						content:row.content,
						music:row.music,
						video:row.video,
						source:row.source
					}
					this.title='修改文章'
					this.articleForm = article
					this.dialogVisible=true
				},
				// 4.提交表单
				toSaveHandler() {
					this.dialogVisible=false
					this.articleForm.music = 'http://134.175.154.93:8888/group1/M00/00/2E/rBAACV5h0uOAf-hLAACHCNedh0E686.jpg'
					this.articleForm.video = 'http://134.175.154.93:8888/group1/M00/00/2E/rBAACV5h0sGAWbrkAAA9XpTHPSQ68.jpeg'
					this.articleForm.source = 'http://134.175.154.93:8888/group1/M00/00/2E/rBAACV5h0piARqc4AACIwnbdk7Y494.jpg'
					this.saveOrUpDateArticle(this.articleForm)
					.then((response) => {
						const status = response.data.status
						if(status === 200){
							this.$notify.success({
									title: '成功',
									message: '操作成功！'
								});
							let payload = {
								page:this.page,
								pageSize: this.pageSize,
								categoryId: this.categoryId
							}
							this.loadArticle(payload)
						} else {
							this.$notify.error({
								title: '错误',
								message: '操作失败！'
							});
						}
					})
				},
				// 5.通过ID删除
				deleteHandler(id) {
					this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
		          confirmButtonText: '确定',
		          cancelButtonText: '取消',
		          type: 'warning'
		        }).then(()=>{
		        	this.deleteArticleById(id)
							.then((response)=>{
								const status = response.data.status
								if(status === 200){
									this.$notify.success({
											title: '成功',
											message: '删除成功！'
										});
									let payload = {
										page:this.page,
										pageSize: this.pageSize,
										categoryId: this.categoryId
									}
									this.loadArticle(payload)
								} else {
									this.$notify.error({
										title: '错误',
										message: '删除失败！'
									});
								}
							})
		        })
				},
				// 6.批量删除
				batchDelect() {
					this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
		          confirmButtonText: '确定',
		          cancelButtonText: '取消',
		          type: 'warning'
		        }).then(() => {
							let ids = this.multipleSelection.map((item) => {
								return item.id
							})
							this.batchDelectArticle({ids})
							.then((response)=>{
								const status = response.data.status
								if(status === 200){
									this.$notify.success({
											title: '成功',
											message: '删除成功！'
										});
									let payload = {
										page:this.page,
										pageSize: this.pageSize,
										categoryId: this.categoryId
									}
									this.loadArticle(payload)
								} else {
									this.$notify.error({
										title: '错误',
										message: response.data.message
									});
								}
							})
						})
				},
				// 7.多选框集合
				selectionChangeHandler(val) {
					this.multipleSelection = val
				},
				// 8.重置按钮，刷新数据
				resetData() {
					this.categoryId = null
					this.keywords = ''
					this.time = []
					let payload = {
						page: 0,
						pageSize: this.pageSize,
					}
					this.loadArticle(payload)
				},
				// 9.点击查看详情
				toArticleDetailsHandler(id){
					this.findArticleById(id)
					this.detailsVisible = true
				},
				// 10.审核文章
				auditHandler(id) {
					const params = {
						id:id,
						status:'审核通过'
					}
					this.checkArticle(params)
					.then((response) => {
						const status = response.data.status
						if(status === 200){
							this.findArticleById(id)
							let payload = {
								page:this.page,
								pageSize: this.pageSize,
								categoryId: this.categoryId
							}
							this.loadArticle(payload)
							this.$notify.success({
								title: '成功',
								message: '审核成功！'
							});
						} else {
							this.$notify.warning({
								title: '警告',
								message: '已审核通过！'
							});
						}
						
					})
				},
				// 11.文章评论区状态改变
				changeCommentStatus(articleDetails) {
					const article = {
						id:articleDetails.id,
						title:articleDetails.title,
						userId:articleDetails.author.id,
						categoryId:articleDetails.category.id,
						liststyle:this.commentStatus,
						publishtime:articleDetails.publishtime,
						readtimes:articleDetails.readtimes,
						status:articleDetails.status,
						thumbup:articleDetails.thumbup,
						thumbdown:articleDetails.thumbdown,
						content:articleDetails.content
					}
					this.saveOrUpDateArticle(article)
					.then((response) => {
						const status = response.data.status
						if(status === 200){
							this.findArticleById(articleDetails.id)
							let payload = {
								page:this.page,
								pageSize: this.pageSize,
							}
							this.loadArticle(payload)
							this.$notify.success({
								title: '成功',
								message: '更改成功！'
							});
						} else {
							this.$notify.error({
								title: '错误',
								message: '更改失败！'
							});
						}
					})
				}
			},
			// 监听数据变化，做数据重载
			watch: {
				categoryId:function(now,old) {
					const beginTime = this.time[0]
					const endTime = this.time[1]
					const payload = {
						page: 0,
						pageSize: 5,
						beginTime: beginTime,
						endTime: endTime,
						keywords: this.keywords,
						categoryId: this.categoryId
					}
					console.log(payload)
					this.loadArticle(payload)
				},
				time:function(now,old) {
					const beginTime = this.time[0]
					const endTime = this.time[1]
					const payload = {
						page: 0,
						pageSize: 5,
						beginTime: beginTime,
						endTime: endTime,
						keywords: this.keywords,
						categoryId: this.categoryId
					}
					this.loadArticle(payload)
				},
				keywords:function(now,old) {
						const beginTime = this.time[0]
						const endTime = this.time[1]
						const payload = {
							page: 0,
							pageSize: 5,
							beginTime: beginTime,
							endTime: endTime,
							keywords: this.keywords,
							categoryId: this.categoryId
						}
						this.loadArticle(payload)
					},
			}
	}
</script>
<style type="text/css">
	.article{
		padding: 0.5em;
	}
	.article_top{
		margin-bottom: 1em;
	}
	.list_style >li {
		list-style: none;
		border: 1px solid #ededed;
		border-radius: 3px;
		padding: .5em;
	}
	.list_style >li.current {
		border-color: #409eff;
	}
	.list_style >li.style_one {
		float: left;
		margin-left: -40px;
	}
	.list_style >li.style_two {
		margin-left: 220px;
		width: 220px;
	}
	.select {
		margin-left: 20px;
	}
	.reset {
		margin-left: 50px;
		margin-right: 10px;
	}
	.none {
		display: none;
	}
</style>
