<template>
	<div class="article">
		<div class="btns">
		
		<el-select v-model="categoryId" placeholder="所有栏目">
			    <el-option
			      v-for="item in categories"
			      :key="item.id"
			      :label="item.name"
			      :value="item.id">
			    </el-option>
		</el-select>

		 <el-button class='btn' size='small' type="info" plain @click='addarticle'>添加栏目</el-button>
		 <el-button class='btn' size='small' type="info" plain @click='deleteAll'>批量删除</el-button>
		</div>


		<el-table :data="article" style="width: 100%" size='small' :border='true'
			@selection-change="handleSelectionChange">
	     	<el-table-column
		      type="selection"
		      width="50" align='center'>
		    </el-table-column>
		    <el-table-column
	        prop="id"
	        label="编号"
	        width="70">
	      </el-table-column>
	      <el-table-column
	        prop="title"
	        label="文章标题"
	        width="120">
	      </el-table-column>
	      <el-table-column
	        prop="category.name"
	        label="所属栏目"
	        width="120">
	      </el-table-column>
	     
	      <el-table-column
	        prop="publishtime"
	        label="发布时间">
	      </el-table-column>
	      <el-table-column
	        prop="readtimes"
	        label="阅读次数"
	        width="120">
	      </el-table-column>
	      <el-table-column
	        prop="liststyle"
	        label="列表样式"
	        width="120" align='center'>
	      </el-table-column>
	     
	      <el-table-column label="操作" align='center'>
	      	<template slot-scope='{row}'>
	      		<i class="fa fa-trash" @click='deleteById(row.id)'></i>
	      		<i class="fa fa-pencil" @click='toUpdateArticle(row)'></i>
	      	</template>
	      </el-table-column>
	    </el-table>

	<!-- 模态框 -->
		<el-dialog fullscreen :title="articleModal.title" :visible.sync="articleModal.visible">
		  <el-form :model="articleModal.form" size="small" label-position="left" >
		    <el-form-item label="标题名称" label-width="6em">
		      <el-input v-model="articleModal.form.title" autocomplete="off"></el-input>
		    </el-form-item>
		    <el-form-item label="所属栏目" label-width="6em">
		      <el-select v-model="articleModal.form.categoryId" placeholder="默认栏目">
		        <el-option :key='c.id' v-for='c in categories' :label="c.name" :value="c.id"></el-option>
		      </el-select>
		    </el-form-item>
		  <el-form-item label="列表样式" label-width="6em">
				<ul class="list_style">
					<li class="style_one" :class="{current:articleModal.form.liststyle=='style-one'}" @click="articleModal.form.liststyle = 'style-one'">
						<img src="@/assets/style_one.jpg" alt="">
					</li>
					<li class="style_two" :class="{current:articleModal.form.liststyle=='style-two'}"  @click="articleModal.form.liststyle = 'style-two'">
						<img src="@/assets/style_two.jpg" alt="">
					</li>
				</ul>
			</el-form-item>

			 <!--  <el-form-item label="缩略图" label-width="6em">

				<el-upload
				  action="http://120.78.164.247:8099/manager/file/upload"
				   :on-success='handleUploadSuccess'
				  list-type="picture">
				  <el-button size="small" type="primary">点击上传</el-button>
				  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
				</el-upload>

		    </el-form-item> -->

		    <el-form-item label="正文" label-width="6em">
		      <!-- <el-input v-model="articleModal.form.content" type="textarea" :rows="6"></el-input> -->
		      <!-- 富文本编辑器 -->
		       <mavon-editor ref="articleContent" v-model="articleModal.form.content"/>
		    </el-form-item>
		  </el-form>
		  <div slot="footer" class="dialog-footer">
		    <el-button size='small' @click='close'>取 消</el-button>
		    <el-button type="primary" size='small' @click='addOrUpdate'>确 定</el-button>
		  </div>
		</el-dialog>
		<!-- 模态框结束 -->
		<!-- 分页 -->
		<div class="page">
			<el-pagination
		    layout="prev, pager, next"
		    :page-size='pageSize'
		    @current-change='handleCurrentChange'
		    :total="total">
		  </el-pagination>
		</div>
	</div>
</template>
<script>
import axios from '@/http/axios'
	export default{
		data(){
			return {
				categories:[],
				article:[],
				multipleSelection:[],
				page:0,
				total:10,
				pageSize:5,
				categoryId:null,
				
				fileIds:[],
				articleModal:{
					title:'',
					visible:false,
					form:{
					liststyle:'style-one'
					}
				}
			}
		},
		methods:{
			// handleUploadSuccess(response, file, fileList){
			// 	this.fileIds.push(response.data.id);
			// },
			//分页处理
			handleCurrentChange(page){
				this.page = page -1;
			},
			
			//修改
			toUpdateArticle(row){
				console.log('row------',row)
				let article = _.clone(row);
				article.categoryId = article.category.id;
				//1. 删除category,添加categoryId
				delete article.category;

				//删除为空的
				for(let key in article){
					let val=article[key]
					if(!val){
						delete article[key];
					}
				};
				this.articleModal.title='修改文章';		
				this.articleModal.form=article;
				this.articleModal.visible = true;
			},
			//批量删除
			deleteAll(){
				this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
		          confirmButtonText: '确定',
		          cancelButtonText: '取消',
		          type: 'warning'
		        })
		        .then(()=>{
		        	let ids=this.multipleSelection.map((item)=>{
					return item.id
				})
				axios.post('/manager/article/batchDeleteArticle',{ids})
				.then(()=>{
					this.$notify.success({
			          title: '成功',
			          message: '删除成功！'
			        });
			          this.loadArticle();
				})
				.catch(()=>{
					this.$notify.error({
			          title: '错误',
			          message: '删除失败！'
			        });
				})
			})
		        .catch(()=>{
		        	this.$message({
		            type: 'info',
		            message: '已取消删除'
		          });   
		        })	
			},
			//复选框
			handleSelectionChange(val){
				this.multipleSelection = val;
			},
			//单个删除
				deleteById(oId){
				this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
		          confirmButtonText: '确定',
		          cancelButtonText: '取消',
		          type: 'warning'
		        }).then(()=>{
		        	axios.get('/manager/article/deleteArticleById?id='+oId)
					.then(()=>{
						this.$notify.success({
						          title: '成功',
						          message: '删除成功！'
						        });
						  this.loadArticle();
					})
					.catch(()=>{
						this.$notify.error({
						          title: '错误',
						          message: '删除失败！'
					 });
					})
		        })
				},

				//保存更新按钮

				addOrUpdate(){
					// 将html代码绑定到form.source上,refs拿到所有引用

					this.articleModal.form.source = _.clone(this.$refs.articleContent.d_render);
					axios.post('/manager/article/saveOrUpdateArticle',this.articleModal.form)
					.then(()=>{
					this.$notify.success({
			          title: '成功',
			          message: '提交成功！'
			        });
			        this.close();
			        this.loadArticle();
						})
				.catch(()=>{
					this.$notify.error({
			          title: '错误',
			          message: '提交失败！'
			        });
				})
				},
				//加载栏目
				loadCategory(){
				axios.get('/manager/category/findAllCategory')
				.then(({data})=>{
					this.categories = data.data;
				})
				.catch(()=>{
					this.$notify.error({
			          title: '错误',
			          message: '网络异常!'
			        });
				})
			},
			//加载文章
			loadArticle(){
				axios.get('/manager/article/findArticle',{
					params:{
						page:this.page,
						pageSize:this.pageSize,
						categoryId:this.categoryId
					}
				})
				.then((res)=>{
				console.log('this.article  ',res.data.data.list)
				this.total = res.data.data.total;
				this.article = res.data.data.list;
				// console.log('this.article  ',this.article)

				})
				.catch(()=>{
					this.$notify.error({
			          title: '错误',
			          message: '网络异常!'
			        });
				})
			},
			//添加文章
			addarticle(){
				this.articleModal.visible=true;
				this.articleModal.title='添加文章';
				this.articleModal.form={
					liststyle:'style-one'
				}
			},
			//关闭模态框
			close(){
				this.articleModal.form={};
				this.articleModal.visible=false;
			}
		},
		//监听categoryId的变化,做相应刷新
		watch:{
			categoryId:function(now,old){
					this.page=0;
					this.loadArticle();
				},
				page:function(now,old){
					this.loadArticle();
				}
				
			
		},
		created(){
			this.loadCategory();
			this.loadArticle();
		}
	}
</script>
<style type="text/css">
	.btn{
	margin: .5em;
	font-size:15px;
}
i.fa {
		margin: 0 3em;
		cursor: pointer;
	}
	i.fa.fa-trash {
		color: #F56C6C;
	}
	.list_style >li {
		width: 200px;
		height: 80px;
		border: 1px solid #ededed;
		border-radius: 3px;
		padding: .5em;
	}
	.list_style >li.current {
		border-color: #409eff;
	}
	.list_style >li img {
		width: 100%;
	}
	.list_style >li.style_one {
		float: left;
	}
	.list_style >li.style_two {
		margin-left: 220px;
	}
</style>