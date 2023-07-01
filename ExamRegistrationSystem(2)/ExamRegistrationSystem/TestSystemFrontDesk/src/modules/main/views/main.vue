<template>
  <div>
    <div class="top">
      <!-- <img src="/static/images/main.png" alt=""> -->
      <div class="introduction">
        <div class="in">
          <div class="title">英语六级考试报名系统介绍</div>
          <div class="content">本系统用于大学英语四六级考试报名，考生应确认个人信息无误后根据大学所在城市选择考点进行报名。学生应在考试时间内登录系统进行作答。
            考试结束后学生可以在系统中查询成绩</div>
        </div>
      </div>
    </div>
    <div class="main">
      <div v-for="item in bulletin" :key="item.value">
        <div class="bulletin-board" @click="toExamInfo(item.value)">
          <div class="board-item">
            <div class="title">
              {{item.title}}
            </div>
            <div class="content">
              {{item.content}}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  filters: {
  },
  components: {
  },
  data () {
    return {
      bulletin: [
        {
          title: '报名时间公告',
          content: '全国大学英语四、六级考试(CET)报名时间公告',
          value: 'register'
        },
        {
          title: '考试信息说明公告',
          content: '全国大学英语四、六级考试(CET)考试信息说明公告',
          value: 'admissionTicket'
        },
        {
          title: '成绩查询公告',
          content: '全国大学英语四、六级考试(CET)成绩查询公告',
          value: 'grade'
        }
      ]
    }
  },
  computed: {
  },
  mounted () {
  },
  methods: {
    /* 跳转到公告页 */
    toExamInfo (bulletinType) {
      this.$router.push({ path: '/examInfo', query: { bulletinType: bulletinType } })
    }
  }
}
</script>
<style lang="scss" scoped>
.top {
  width: 100%;
  height: 400px;
  background: url('https://resource.neea.edu.cn/project/CET/images/lanrenzhijia01.png') no-repeat;
  background-size: 100% 400px;
  .introduction {
    width: 1200px;
    margin: 0 auto;
    .in {
      width: 600px;
      height: 400px;
      text-align: left;
      // background-color: #fff;
      padding-top: 60px;
      .title {
        color: #1976d2;
        margin-bottom: 20px;
        margin-left: 20px;
        font-size: 30px;
        // mt20
      }
      .content {
        padding-left: 20px;
        line-height: 3rem;
        font-size: 22px;
        font-weight: bold;
      }
    }
  }
}
.main {
  display: flex;
  justify-content: space-between;
  width: 1200px;
  margin: 0 auto;
  //background: #800080;
  // background-color: #fff;
  .bulletin-board {
    display: flex;
    justify-content: center;
    margin-top: 70px;
    padding-bottom: 70px;
    cursor: pointer;
    //background: #800080;
    .board-item {
      background: #D8BFD8;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      width: 300px;
      height: 200px;
      border: 1px solid #ddd;
      //background-color: #fff;
      border-radius: 4px;
      .title {
        height: 80px;
        line-height: 80px;
        width: 90%;
        font-weight: 400;
        font-size: 26px;
        border-bottom: 1px solid #ddd;
      }
      .content {
        // flex: 1;
        padding: 16px;
        color: #777;
        line-height: 2rem;
        font-weight: bold;
      }
    }
  }
}
</style>
