<template>
  <div class="app-container">
    <div class="filter-container">
      <!-- @keyup.enter.native="handleFilter" -->
      <el-input v-model="listQuery.questionid" placeholder="输入试题ID以检索" style="width: 200px;" class="filter-item" @keyup.enter.native="handleQuery" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleQuery">
        查询
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="addQuestion">
        Add
      </el-button>
    </div>
    <el-table v-loading="listLoading" :data="questionList" element-loading-text="Loading" max-height="625" border fit highlight-current-row :expand-row-keys="expands" :row-key="getRowKeys" @expand-change="expandChange">
      <el-table-column fixed="left" prop="questionid" label="题目ID" width="100"></el-table-column>
      <el-table-column prop="type" label="类型" width="120">
        <template slot-scope="{row}">
          <el-tag v-if="row.type === 1">
            选择题
          </el-tag>
          <el-tag v-if="row.type === 2" type="success">
            解答题
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="question" label="题目" width="800"></el-table-column>
      <el-table-column prop="score" label="分值" width="120"></el-table-column>
      <el-table-column fixed="right" label="操作" width="150">
        <template slot-scope="row">
<!--          <el-button @click="add(scope.row.paperId,scope.row.source)" type="success" size="small" @click="addQuestion">查看</el-button>-->
          <el-button type="primary" size="small" @click="updateQuestion (row)">修改</el-button>
          <el-popconfirm title="确定要删除此条数据吗？" @confirm="handleDelete(row)">
            <el-button slot="reference" size="small" type="danger">
              删除
            </el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
<!--    <el-pagination-->
<!--      @size-change="handleSizeChange"-->
<!--      @current-change="handleCurrentChange"-->
<!--      :current-page="pagination.current"-->
<!--      :page-sizes="[4, 8, 10, 20]"-->
<!--      :page-size="pagination.size"-->
<!--      layout="total, sizes, prev, pager, next, jumper"-->
<!--      :total="pagination.total"-->
<!--      class="page">-->
<!--    </el-pagination>-->
    <!-- 弹框 -->
    <el-dialog :visible.sync="dialogFormVisible" :title="type === 'add' ? '新增试题' : '修改试题'">
      <el-form ref="dataForm" :model="temp" label-position="left" label-width="70px" style="width: 80%; margin-left:50px;">
        <el-form-item label="类型" prop="examType">
          <el-radio-group v-model="temp.type">
            <el-radio :label="1">客观题</el-radio>
            <el-radio :label="2">主观题</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="题目" prop="title">
          <el-input v-model="temp.question" placeholder="请输入" :autosize="{ minRows: 5}" type="textarea"/>
        </el-form-item>
        <el-form-item label="阅读材料" prop="description">
          <el-input v-model="temp.material" placeholder="（选填）" :autosize="{ minRows: 5}" type="textarea"/>
        </el-form-item>
        <el-form-item label="分值" prop="score">
          <el-input v-model="temp.score" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="A选项" prop="A" v-model="temp.type" v-if="temp.type == '1' ">
          <el-input v-model="temp.itema" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="B选项" prop="B" v-model="temp.type" v-if="temp.type == '1' ">
          <el-input v-model="temp.itemb" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="C选项" prop="C" v-model="temp.type" v-if="temp.type == '1' ">
          <el-input v-model="temp.itemc" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="D选项" prop="D" v-model="temp.type" v-if="temp.type == '1' ">
          <el-input v-model="temp.itemd" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="答案" prop="answer" v-model="temp.type" v-if="temp.type == '1' ">
          <el-input v-model="temp.answer" placeholder="请输入" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?addData():updateData()">
          确定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  data() {
    return {
      QL: [],
      type: 'add',
      expands: [],
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        title: ''
      },
      listLoading: true,
      ExamName: '',
      dialogFormVisible: false,
      dialogStatus: '',
      temp: {
        questionid: '',
        question: '',
        material: '',
        type: '',
        answer: '',
        score: '',
        itema: '',
        itemb: '',
        itemc: '',
        itemd: '',
        paperid: this.$route.params.examId
      },
      visible: false,
      total: 0
    }
  },
  computed: {
    ...mapState({
      questionList: state => state.question.questionList
      // examTypeEnum: state => state.enumList.examTypeEnum
    })
  },
  mounted() {
    this.getQuestionList()
  },
  methods: {
    getRowKeys (row) {
      return row.id
    },
    expandChange (row, expandedRows) {
      const that = this
      console.log(expandedRows)
      if (expandedRows.length) { // 说明展开了
        that.expands = []
        if (row) {
          that.expands.push(row.id)// 只展开当前行id
        }
      } else { // 说明收起了
        that.expands = []
      }
    },
    getQuestionList () {
      console.log(this.$route.params.examId)
      const params = {
        pageNum: this.listQuery.pageNum,
        pageSize: this.listQuery.pageSize,
        condition: {
          paperid: this.$route.params.examId
        }
      }
      this.$store.dispatch('queryQuestion', params).then(res => {
        this.listLoading = false
        this.total = res.total
      }).catch(err => {
        console.log(err)
      })
    },
    // 清空form表单
    resetForm () {
      this.temp = {
        questionid: '',
        question: '',
        material: '',
        type: '',
        answer: '',
        score: '',
        itema: '',
        itemb: '',
        itemc: '',
        itemd: '',
        paperid: this.$route.params.examId
      }
    },
    handleQuery () {
      console.log(this.listQuery.question)
      const params = {
        pageNum: this.listQuery.pageNum,
        pageSize: this.listQuery.pageSize,
        condition: {
          questionid: this.listQuery.questionid,
          paperid: this.$route.params.examId
        }
      }
      this.$store.dispatch('queryQuestion', params)
    },
    // 添加用户弹窗
    addQuestion () {
      this.type = 'add'
      this.resetForm()
      this.dialogFormVisible = true
      this.dialogStatus = 'create'
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 修改弹窗
    updateQuestion (row) {
      console.log(row)
      this.type = 'modify'
      this.temp = row.row
      this.dialogFormVisible = true
      this.dialogStatus = 'update'
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 添加用户
    addData () {
      // console.log(this.temp)
      this.$store.dispatch('addQuestion', this.temp).then(res => {
        this.dialogFormVisible = false
        this.getQuestionList()
        this.$notify({
          title: 'Success',
          message: '添加成功',
          type: 'success',
          duration: 2000
        })
      }).catch(err => {
        // this.$message.error(err)
        console.log(err)
      })
    },
    // 修改用户
    updateData () {
      // console.log(this.temp)
      this.$store.dispatch('updateQuestion', this.temp).then(res => {
        this.getQuestionList()
        this.$notify({
          title: 'Success',
          message: '修改成功',
          type: 'success',
          duration: 2000
        })
        this.dialogFormVisible = false
      }).catch(err => {
        this.$message.error(err)
      })
    },
    // 删除用户
    handleDelete (row) {
      // console.log(row.row.questionid)
      this.$store.dispatch('deleteQuestion', row.row.questionid).then(res => {
        this.getQuestionList()
        this.$notify({
          title: 'Success',
          message: '删除成功',
          type: 'success',
          duration: 2000
        })
      }).catch(err => {
        this.$message.error(err)
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.exam {
  padding: 0px 40px;
  .page {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .edit{
    margin-left: 20px;
  }
}
</style>
