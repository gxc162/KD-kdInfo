<template>
  <div class="comment">
    <div class="comment_content">
      <!-- 表格开始 -->
			<el-table :data="comments" style="width: 100%" size='small'>
		    <el-table-column
	        label="序号"
					type="index" 
					:index="1"
	        width="50"
					align='center'>
	      </el-table-column>
	      <el-table-column
	        prop="article.title"
	        label="所属文章"
	        width="200"
					align='center'>
	      </el-table-column>
	      <el-table-column
	        prop="content"
	        label="评论内容"
	        width="300"
					align='center'>
	      </el-table-column>
				<el-table-column
	        prop="customer.nickname"
	        label="评论者"
	        width="100"
					align='center'>
	      </el-table-column>
	      <el-table-column
	        prop="commenttime"
	        label="评论时间"
					width="150"
					align='center'>
	      </el-table-column>
	      <el-table-column
	        prop="status"
	        label="状态"
	        width="100" 
					align='center'>
	      </el-table-column>
				<el-table-column
	        label="审核"
	        width="100" 
					align='center'>
					<template slot-scope='{row}'>
						<el-button type="primary" size="mini" @click="auditHandler(row.id)">通过</el-button>
					</template>
	      </el-table-column>
	      <el-table-column label="操作" align='center'>
	      	<template slot-scope='{row}'>
	      		<i class="fa fa-trash" @click='deleteHandler(row.id)'></i>&emsp;
	      	</template>
	      </el-table-column>
	    </el-table>
	   	<!-- 表格结束 -->
      <!-- 分页开始 -->
			<div class="page">
				<el-pagination
					layout="prev, pager, next"
					:page-size='this.pageSize'
					:total="this.total"
					@current-change="handleCurrentChange">
				</el-pagination>
			</div>
			<!-- 分页结束 -->
    </div>
  </div>
</template>
<script>
import {mapActions,mapState,mapMutations,mapGetters} from 'vuex'
export default {
  data() {
    return {
      page: 0,
			pageSize: 10
    }
  },
  created() {
    const payload = {
      page:this.page,
      pageSize: this.pageSize,
    }
    this.loadComments(payload)
  },
  computed: {
    ...mapState('Comment',['comments','total'])
  },
  methods: {
    ...mapActions('Comment',['loadComments','auditComment','deleteCommentById']),
    // 1.分页page处理
    handleCurrentChange (page) {
      this.page = page -1
      let payload = {
        page:this.page,
        pageSize: this.pageSize,
			}
			this.loadComments(payload)
		},
		// 2.审核事件
		auditHandler(id) {
			this.$confirm('此操作将使该文章通过审核, 是否继续?', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			})
			.then(() => {
				const payload = {
					id:id,
					status:'审核通过'
				}
				this.auditComment(payload)
				.then((response)=>{
					const status = response.data.status
					if(status === 200){
						this.$notify.success({
							title: '成功',
							message: '审核成功！'
						});
						const payload = {
							page:this.page,
							pageSize: this.pageSize,
						}
						this.loadComments(payload)
					} else {
							this.$notify.warning({
								title: '警告',
								message: '已审核通过！'
							});
						}
				})
			})
		},
		// 3.根据ID删除评论
		deleteHandler(id) {
			this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			})
			.then(()=>{
				this.deleteCommentById(id)
				.then((response)=>{
					const status = response.data.status
					if(status === 200) {
						this.$notify.success({
							title: '成功',
							message: '删除成功！'
						});
						const payload = {
							page:this.page,
							pageSize: this.pageSize,
						}
						this.loadComments(payload)
					} else {
						this.$notify.error({
							title: '错误',
							message: '删除失败！'
						});
					}
				})
			})
			
			
		}
  }
}
</script>
<style type="text/css">
  .comment {
    padding:1em;
  }
</style>