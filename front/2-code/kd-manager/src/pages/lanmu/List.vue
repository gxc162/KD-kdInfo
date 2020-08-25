<template>
	<div class="lanmu">
		<div>
			<el-tabs v-model="editableTabsValue" type="card" editable @edit="handleTabsEdit" @tab-click='handlerRelod2_categories'>
				<el-tab-pane
					:value="item.id"
					:key="item.id"
					v-for="item in categories"
					:label="item.name"
					:name="item.name"
				>
					<!-- 表格开始 -->
					<div class="lanmu_content">
						<el-table :data="children" style="width: 100%" size='small'>
							<el-table-column
								label="序号"
								type="index" 
								:index="1"
								width="100"
								align='center'>
							</el-table-column>
							<el-table-column
								prop="name"
								label="栏目名称"
								width="300"
								align='center'>
							</el-table-column>
							<el-table-column prop="comment" label="描述" align='center'>
							</el-table-column>
							<el-table-column width="150" label="操作" align='center'>
								<template slot-scope='{row}'>
									<i class="fa fa-trash" style="cursor:hand" @click='deleteLanmu(row.id)'></i>&emsp;
									<i class="fa fa-pencil" @click='updata(row)'></i>
								</template>
							</el-table-column>
						</el-table>
					</div>
					<!-- 表格结束 -->
				</el-tab-pane>
			</el-tabs>
		</div>
		<!-- 新增栏目模态框开始 -->
		<el-dialog
		  :title=title
		  :visible.sync="dialogVisible"
		  width="30%"
		  >
		  <!-- 表单开始 -->
		 	<el-form label-position="right" label-width="80px" :model="categoriesForm">
		  <el-form-item label="栏目名称">
		    <el-input v-model="categoriesForm.name"></el-input>
		  </el-form-item>
		 	<el-form-item label="父栏目">
		    <el-select v-model="categoriesForm.parentId" placeholder="请选择父栏目">
		       <el-option :key='c.id' v-for='c in categories' :label="c.name" :value="c.id"></el-option>
		    </el-select>
		  </el-form-item>
		 	<el-form-item label="描述">
		    <el-input type="textarea" v-model="categoriesForm.comment"></el-input>
		  </el-form-item>
		</el-form>
			<!-- 表单结束 -->
		  <span slot="footer" class="dialog-footer">
		    <el-button size='small' @click="dialogVisible = false">取 消</el-button>
		    <el-button size='small' type="primary" @click="save">确 定</el-button>
		  </span>
		</el-dialog>
		<!-- 新增栏目模态框结束 -->
	</div>
</template>
<script type="text/javascript">
import {mapActions,mapGetters,mapMutations,mapState} from 'vuex';
	export default {
		data(){
			return {
				multipleSelection:[],
				dialogVisible: false,
				categoriesForm:{parentId:''},
				title:'',
				editableTabsValue: '头条',
				id: '1'
			}
		},
		 computed: {
				...mapGetters('Lanmu',['categories']),
				...mapState('Lanmu',['children'])
  		},
		created(){
			this.loadCategories(),
			this.findCategoryByParentId(this.id)
		},
  		methods:{
				...mapActions('Lanmu',['loadCategories','saveCategories','deleteLm','deleteAllLm','findCategoryByParentId','deleteCategoryByName']),
				// 1.标签页增加删除方法
				handleTabsEdit(targetName, action) {
					if (action === 'add') {
						this.dialogVisible = true
						this.title = '新增栏目'
						this.categoriesForm = {}
					}
					if (action === 'remove') {
						this.$confirm('此操作将永久删除该栏目, 是否继续?', '提示', {
		          confirmButtonText: '确定',
		          cancelButtonText: '取消',
		          type: 'warning'
		        }).then(() => {
							this.deleteCategoryByName(targetName).then((response) => {
								const status = response.data.status
								if (status === 200){
									this.loadCategories()
									this.$notify.success({
										title: '成功',
										message: '删除成功！'
									});
								} else {
									this.$notify.error({
										title: '错误',
										message: '不允许删除！'
									});
								}
							})
						})
					}
				},
				// 2.标签页改变函数
				handlerRelod2_categories(el){
					localStorage.setItem('parentId',el.$attrs.value)
					this.findCategoryByParentId(el.$attrs.value)
				},
  			 // 3.删除栏目
  			 deleteLanmu(id){
  			 	this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
		          confirmButtonText: '确定',
		          cancelButtonText: '取消',
		          type: 'warning'
		        }).then(()=>{
		        	this.deleteLm(id)
							.then((response)=>{
								const status = response.data.status
								if(status === 200) {
									this.$notify.success({
										title: '成功',
										message: '删除成功！'
									});
									const id = localStorage.getItem('parentId')
									this.findCategoryByParentId(id)
								} else {
									this.$notify.error({
										title: '错误',
										message: '删除失败！'
									});
								}
							})
		        })
  			 },
  			 // 4.修改栏目信息
  			 updata(data){
					this.categoriesForm = data 
  			 	this.dialogVisible = true
					this.title = '修改栏目'
  			 },
  			 // 点击新增按钮
  			 toAdd(){
  			 	this.dialogVisible = true
  			 	this.title = '新增栏目'
  			 	this.categoriesForm = {}
  			 },
  			 // 5.保存栏目
				save(){
					this.dialogVisible = false
					this.saveCategories(this.categoriesForm).then((response) => {
						const status = response.data.status
						if(status === 200){
							let id = localStorage.getItem('parentId')
							this.findCategoryByParentId(id)
							this.loadCategories()
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
				}
  		}
	}
</script>
<style type="text/css">
	.lanmu{
		padding: 0.5em;
	}
	.lanmu_top{
		margin-bottom: 1em;
	}
</style>