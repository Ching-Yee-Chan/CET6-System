<template>
  <div class="main">
    <el-table :data="queryList.list" style="width: 100%">
      <el-table-column prop="examModel.title" label="名称" width="180">
      </el-table-column>
      <el-table-column prop="examModel.examType.msg" label="类型" width="180">
      </el-table-column>
      <el-table-column prop="examModel.applyEndTime" label="考试开始时间">
        <template slot-scope="{row}">
          {{row.examModel.startTime | parseTime}}
        </template>
      </el-table-column>
      <el-table-column prop="examModel.applyEndTime" label="考试结束时间">
        <template slot-scope="{row}">
          {{row.examModel.endTime | parseTime}}
        </template>
      </el-table-column>
      <el-table-column prop="status.enumCode" label="考试状态">
        <template slot-scope="{row}">
          <span v-if="row.status.enumCode === 'NoAnswer'">考试未完成</span>
          <span v-if="row.status.enumCode === 'FinishAnswer'">考试已完成</span>
        </template>
      </el-table-column>
      <el-table-column prop="examModel.status.enumCode" label="操作">
        <template slot-scope="{row}">
          <el-button type="primary" @click="toExam(row.examId, row.id)" v-if="row.status.enumCode === 'NoAnswer'">开始考试</el-button>
          <el-button type="primary" @click="toExam(row.examId, row.id)" v-if="row.status.enumCode === 'FinishAnswer'" disabled>开始考试</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import { parseTime } from '@/utils/time'
// import RegisterCard from './registerCard.vue'

export default {
  // components: { RegisterCard },
  mounted () {
    this.getQueryList()
  },
  data () {
    return {
      pageNum: 1,
      pageSize: 10
    }
  },
  filters: {
    parseTime (val) {
      return parseTime(val, '{y}-{m}-{d} {h}:{i}') || '--'
    }
  },
  computed: {
    ...mapState({
      queryList: state => state.registrationQuery.queryList,
      payDetails: state => state.registrationQuery.payDetails
    }),
    dealButton () {
      return function (row) {
        let status = ""
        if (row.examModel.status.enumCode === 'Start' && row.status.enumCode === 'Apply_NoPay') {
          status = 'noPay'
        }
        if (row.status.enumCode == 'Apply_Pay') {
          status = 'pay'
        }
        if (row.examModel.status.enumCode === 'Stop' || row.examModel.status.enumCode === 'Score_Inquiry') {
          status = 'ban'
        }
        return status
      }

    }
  },
  methods: {

    getQueryList () {
      let params = {
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        params: {}
      }
      this.$store.dispatch('getQueryList', params).then(res => {})
    },
    toExam (examId, id) {
      this.$router.push({name: 'ExamPage', params: {examId: examId, id: id}})
    }
  }
}
</script>
<style lang="scss" scoped>
.main {
  width: 1200px;
  margin: 0 auto;
  // height: 3000px;
  // min-height: 600px;
  min-height: calc(100vh - 240px);
  .content {
    padding: 20px;
  }
}
</style>
