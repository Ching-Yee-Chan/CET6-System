<template>
  <div class="app-container">
    <!--这个是查询按钮，我觉得我可以先不看这个-->
    <div class="filter-container">
      <!-- @keyup.enter.native="handleFilter" -->
      <el-input v-model="listQuery.examName" placeholder="请输入考试名称" style="width: 200px;" class="filter-item" @keyup.enter.native="handleQuery" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleQuery">
        查询
      </el-button>
    </div>
    <!--数据来源于examlist row key通过调用函数设置成了row.id-->
    <el-table v-loading="listLoading" :data="examList" :row-key="getRowKeys" element-loading-text="Loading" border fit highlight-current-row max-height="625">
      <el-table-column align="center" type="index" />
      <el-table-column align="center" label="考试名称" prop="title" />
      <el-table-column align="center" label="考试开始时间" prop="startTime">
        <template slot-scope="{row}">
          <span>
            <!--用管道解析和格式化时间数据            -->
            {{ row.startTime | parseTime }}
          </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="考试结束时间" prop="endTime">
        <template slot-scope="{row}">
          <span>
            {{ row.endTime | parseTime }}
          </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="状态" prop="status.msg">
        <template slot-scope="{row}">
          <!--根据状态不同选标签          -->
          <el-tag v-if="row.status && row.status.enumCode === 'NotStarted'">
            {{ row.status.msg }}
          </el-tag>
          <el-tag v-if="row.status &&row.status.enumCode === 'Start'" type="success">
            {{ row.status.msg }}
          </el-tag>
          <el-tag v-if="row.status &&row.status.enumCode === 'Stop'" type="warning">
            {{ row.status.msg }}
          </el-tag>
          <el-tag v-if="row.status &&row.status.enumCode === 'Score_Inquiry'" type="danger">
            {{ row.status.msg }}
          </el-tag>
        </template>
      </el-table-column>
      <!--点击阅卷按钮到达阅卷界面      -->
      <el-table-column align="center" label="操作" width="140">
        <template slot-scope="row">
          <el-button type="primary" size="mini" @click="judgeExam(row)">
            阅卷
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="getExamList" />
  </div>
</template>

<script>
import { mapState } from 'vuex'
import Pagination from '@/components/Pagination'
import { parseTime } from '@/utils/time'

export default {
  components: { Pagination },
  filters: {
    parseTime (val) {
      return parseTime(val, '{y}-{m}-{d} {h}:{i}') || '--'
    }
  },
  data () {
    return {
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        examName: ''
      },
      listLoading: true,
      total: 0
    }
  },
  computed: {
    ...mapState({
      examList: state => state.exam.examList,
      roleList: state => state.enumList.roleList,
      examTypeEnum: state => state.enumList.examTypeEnum,
      examStatusEnum: state => state.enumList.examStatusEnum
    })
  },
  mounted () {
    this.getExamList()
    this.$store.dispatch('getExamTypeEnum', 'ExamTypeEnum')
    this.$store.dispatch('getExamStatusEnum', 'ExamStatusEnum')
  },
  methods: {
    // 获取考试列表
    getExamList () {
      const params = {
        pageNum: this.listQuery.pageNum,
        pageSize: this.listQuery.pageSize
      }
      this.$store.dispatch('getExamList', params).then(res => {
        this.total = res.total
        this.listLoading = false
        console.log(this.examList)
      }).catch(err => {
        console.log(err)
      })
    },
    judgeExam(row) {
      console.log(row.row.id)
      this.$router.push('/judgepage/')
    },
    // 按条件查询考试
    handleQuery () {
      const params = {
        pageNum: this.listQuery.pageNum,
        pageSize: this.listQuery.pageSize,
        condition: { title: this.listQuery.examName }
      }
      console.log(params)
      console.log(params.condition)
      this.$store.dispatch('queryExam', params)
    },
    getRowKeys (row) {
      return row.id
    }
  }

}
</script>
<style lang="scss" scoped>
.content-item {
  display: flex;
  margin-bottom: 20px;
  .title {
    margin-right: 6px;
    font-weight: 600;
    font-size: 18px;
  }
  .words {
    display: flex;
    align-items: center;
    font-weight: 500;
    font-style: 16px;
  }
}
</style>
