<template>
  <div class="app-container">
    <!--    {{answerList}}-->
    <!--数据来源于examlist row key通过调用函数设置成了row.id-->
    <el-table v-loading="listLoading" :data="answerList" :row-key="getRowKeys" element-loading-text="Loading" border fit highlight-current-row max-height="625">
      <el-table-column align="center" type="index" />
      <!--      <el-table-column align="center" label="学生编号" prop="answeruser" />-->
      <!--      <el-table-column align="center" label="考试题目" prop="answerquestion">-->
      <!--        <template slot-scope="{row}">-->
      <!--          <span>-->
      <!--            &lt;!&ndash;用管道解析和格式化时间数据            &ndash;&gt;-->
      <!--            {{ row.startTime | parseTime }}-->
      <!--          </span>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <el-table-column align="left" label="学生作答情况" prop="answercontent">
        <!--        <template slot-scope="{row}">-->
        <!--          <span>-->
        <!--            {{ row.endTime | parseTime }}-->
        <!--          </span>-->
        <!--        </template>-->
      </el-table-column>
      <el-table-column align="center" label="教师打分">
        <template slot-scope="{ row }">
          <el-input v-model="row.score" placeholder="请输入分数" />
        </template>
      </el-table-column>
      <!--点击提交教师打分分数      -->
      <el-table-column align="center" label="操作" width="140">
        <template slot-scope="row">
          <el-button type="primary" size="mini" @click.once="submitScore(row.row)">
            提交分数
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :page-sizes="[1,5,10]" :total="total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="getAnswerList" />
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
        pageSize: 1
      },
      listLoading: true,
      total: 2
    }
  },
  computed: {
    ...mapState({
      // examList: state => state.exam.examList
      answerList: state => state.answer.answerList
    })
  },
  mounted () {
    this.getAnswerList()
  },
  methods: {
    // 获取考试列表
    getAnswerList () {
      console.log('AAA!')
      const params = {
        pageNum: this.listQuery.pageNum,
        pageSize: this.listQuery.pageSize
      }
      this.$store.dispatch('getAnswerList', params).then(res => {
        this.total = res.total
        this.listLoading = false
        console.log(this.answerList)
      }).catch(err => {
        console.log('HHH')
        console.log(err)
      })
    },
    submitScore(row) {
      console.log(row)
      const score = row.score
      const params = {
        examid: row.examid,
        score: score
      }
      this.$store.dispatch('addScore2', params).then(
        this.$message.success(`成功打分为 ${score} 分`)
      ).catch(err => {
        console.log(err)
      })
    },
    // 按条件查询考试
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
