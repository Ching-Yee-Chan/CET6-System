<!--考生答题界面-->
<template>
  <div id="answer">
    <!--顶部信息栏-->
    <div class="top">
      <ul class="item">
        <li>全国大学英语六级考试（CET-6）在线答题系统</li>
<!--        <li><i class="iconfont icon-time"></i>距离考试结束：<b>{{time}}</b>分钟</li>-->
        <li style="list-style-type: none"><i class="iconfont el-icon-menu icon20" ref="toggle" @click="slider_flag = !slider_flag"></i></li>
      </ul>
    </div>
    <div class="flexarea">
      <!--右边选择答题区-->
      <transition name="slider-fade">
        <div class="right">
          <div class="article">
            <vue-scroll :ops="scroll_ops">
              <p>{{questionList[index].material}}</p>
            </vue-scroll>
          </div>
        </div>
      </transition>

      <transition name="slider-fade">
        <div class="right">
          <div class="title">
            <i class="iconfont auto-left"></i>
            <span><b>Reading</b></span>
          </div>
          <div class="content">
            <p class="topic"><span class="number">{{index + 1}}</span>{{questionList[index].question}}</p>
            <div v-if="questionList[index].type === 1">
              <el-radio-group v-model="radio[index]" @change="getChangeLabel" >
                <el-radio :label="1">{{questionList[index].itema}}</el-radio>
                <el-radio :label="2">{{questionList[index].itemb}}</el-radio>
                <el-radio :label="3">{{questionList[index].itemc}}</el-radio>
                <el-radio :label="4">{{questionList[index].itemd}}</el-radio>
              </el-radio-group>
            </div>
            <div v-else>
              <el-input placeholder="Please write here"
                        v-model="fillAnswer[index]"
                        clearable
                        type="textarea"
                        :rows="15"
                        @change="getChangeLabel">
              </el-input>
            </div>
<!--            <div class="fill" v-if="questionList[index].type === 2 || questionList[index].type === 3">-->
<!--            </div>-->
          </div>
          <div class="operation">
            <ul class="end">
              <li @click="previous()"><i class="iconfont icon-previous"></i><span>上一题</span></li>
              <li @click="next()"><span>下一题</span><i class="iconfont icon-next"></i></li>
            </ul>
          </div>
        </div>

      </transition>
      <!--左边题目编号区-->
      <transition name="slider-fade">
        <div class="left" v-if="slider_flag">
          <div class="l-bottom">
            <div class="item">
              <p>客观题</p>
              <ul>
                <li v-for="(i) in 5" :key="i">
                  <a href="javascript:;"
                     @click="change(i-1)"
                       :class="{'border': index + 1 === i && questionList[index].type === 1,'bg': isAnswered[i - 1] === true}">
                    {{i}}
                  </a>
                </li>
              </ul>
              <ul>
                <li v-for="(i) in 5" :key="i">
                  <a href="javascript:;"
                     @click="change(i-1+5)"
                     :class="{'border': index + 1 === i + 5 && questionList[index].type === 1,'bg': isAnswered[i - 1 + 5] === true}">
                    {{i + 5}}
                  </a>
                </li>
              </ul>
            </div>
            <div class="item">
              <p>主观题</p>
              <ul>
                <li v-for="(i) in 2" :key="i">
                  <a href="javascript:;"
                     @click="change(i-1+10)"
                     :class="{'border': index + 1 === i + 10 && questionList[index].type === 1,'bg': isAnswered[i - 1 + 10] === true}">
                    {{i + 10}}
                  </a>
                </li>
              </ul>
            </div>
            <div class="final" @click="commit()">交卷</div>
          </div>
        </div>
      </transition>
    </div>
  </div>
</template>

<script>
import Vue from 'vue'
import vuescroll from 'vuescroll'
Vue.component('vue-scroll', vuescroll);
export default {
  data() {
    return {
      scroll_ops : {
        bar:{
          size:'5px',
          background: '#7d0c6d',
          keepShow:false,
        }
      },
      examId: '',
      userExamId: '',
      questionList: [],
      index: 0, //全局index
      isAnswered: [],
      temp: {
        answerid: '',
        answerexam: '',
        answerquestion: '',
        answeruser: '',
        answertype: '',
        answeroption: '',
        answercontent: ''
      },
      score: 0,
      slider_flag: true, //左侧显示隐藏标识符
      radio: [], //保存考生所有选择题的选项
      part: null, //填空题的空格数量
      fillAnswer: [], //二维数组保存所有填空题答案
    }
  },
  created() {
    this.userExamId = this.$route.params.id
    this.getUserId()
    this.getExamQuestions()
    this.showTime()
  },
  methods: {
    getTime(date) { //日期格式化
      let year = date.getFullYear()
      let month= date.getMonth()+ 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
      let day=date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
      let hours=date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
      let minutes=date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
      let seconds=date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      // 拼接
      return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
    },
    getUserId() {
      this.$store.dispatch('getUserId').then( res => {
        this.temp.answeruser = res.id
      })
    },
    //获取当前试卷所有信息
    getExamQuestions() {
      this.examId = this.$route.params.examId
      const param = {
        condition: {
          paperid: this.examId
        }
      }
      this.$store.dispatch('getQuestionList', param).then(res => {
        this.questionList = res
        for (let i = 0; i < res.length; i++) {
          this.isAnswered.push(false)
        }
      })
    },
    change(index) {
      this.index = index
    },
    getChangeLabel(val) {
      // 构造提交数据
      this.temp.answerexam = this.examId
      this.temp.answerquestion = this.questionList[this.index].questionid
      this.temp.answertype = this.questionList[this.index].type
      if (this.questionList[this.index].type === 1) {
        this.temp.answeroption = val
      }
      else {
        this.temp.answercontent = val
      }

      if (this.isAnswered[this.index] === true) {
        this.$store.dispatch('updateAnswer', this.temp).then( res => {
        }).catch( err => {
          console.log(err)
        })
      }
      else {
        this.$store.dispatch('addAnswer', this.temp).then( res => {
          this.isAnswered[this.index] = true
        }).catch( err => {
          console.log(err)
        })
      }
    },
    previous() {
      if(this.index > 0) {
        this.index--
      }
    },
    next() {
      if(this.index + 1 < this.questionList.length) {
        this.index++
      }
    },
    commit() { //答案提交计算分数
      this.$confirm("是否确认提交答案","提示", {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: "info"
      }).then(() => {
        for(let i = 0; i < this.questionList.length - 2; i++) {
          if(this.questionList[i].answer === 'A' && this.radio[i] === 1 ||
           this.questionList[i].answer === 'B' && this.radio[i] === 2 ||
           this.questionList[i].answer === 'C' && this.radio[i] === 3 ||
           this.questionList[i].answer === 'D' && this.radio[i] === 4) {
            this.score += 3
          }
        }
        const param = {
          id: this.userExamId,
          score: this.score + ''
        }
        this.$store.dispatch('finishExam', param).then(() => {
          this.$router.push('onlineExam')
        })
      }).catch(() => {
      })
    },
  },
}
</script>

<style lang="less">
.iconfont.icon-time {
  color: #ffffffdf;
  margin: 0px 6px 0px 20px;
}
.analysis {
  margin-top: 20px;
  .right {
    color: #2776df;
    font-size: 18px;
    border: 1px solid #2776df;
    padding: 0px 6px;
    border-radius: 4px;
    margin-left: 20px;
  }
  ul li:nth-child(2) {
    margin: 20px 0px;
    list-style-type: none;
  }
  ul li:nth-child(3) {
    padding: 10px;
    background-color: #d3c6c9;
    border-radius: 4px;
    list-style-type: none;
  }
}
.analysis span:nth-child(1) {
  font-size: 18px;
}
.mark {
  position: absolute;
  width: 4px;
  height: 4px;
  content: "";
  background-color: red;
  border-radius: 50%;
  top: 0px;
  left: 22px;
}
.border {
  position: relative;
  border: 1px solid #FF90AA !important;
}
.bg {
  background-color: #d22dba !important;
}
.fill .el-input {
  display: inline-flex;
  width: 150px;
  margin-left: 20px;
  .el-input__inner {
    border: 1px solid transparent;
    border-bottom: 1px solid #eee;
    padding-left: 20px;
  }
}
/* slider过渡效果 */
.slider-fade-enter-active {
  transition: all .3s ease;
}
.slider-fade-leave-active {
  transition: all .3s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.slider-fade-enter, .slider-fade-leave-to {
  transform: translateX(100px);
  opacity: 0;
}
.operation .end li {
  cursor: pointer;
  margin: 0 60px;
  list-style-type: none;
}
.operation {
  background-color: #fff;
  border-radius: 4px;
  padding: 10px 0px;
  margin-right: 10px;
}
.operation .end {
  display: flex;
  justify-content: center;
  align-items: center;
  color: rgb(126, 12, 110);
}
.content .number {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  width: 20px;
  height: 20px;
  background-color: rgb(210, 45, 186);
  border-radius: 4px;
  margin-right: 4px;
}
.content {
  padding: 0px 20px;
}
.content .topic {
  padding: 20px 0px;
  padding-top: 30px;
}
.right .content {
  background-color: #fff;
  margin: 10px;
  margin-left: 0px;
  height: 470px;
  text-align: justify;
}
.content .el-radio-group label {
  color: #000;
  margin: 5px 0px;
}
.content .el-radio-group {
  display: flex;
  flex-direction:column;
}
.right .title p {
  margin-right: 20px;
}
.flexarea {
  display: flex;
}
.flexarea .right {
  flex: 1;
}
.auto-right {
  margin-left: auto;
  color: #2776df;
  margin-right: 10px;
}
.right .title {
  margin-right: 10px;
  padding-left: 30px;
  display: flex;
  margin-top: 10px;
  background-color: #fff;
  height: 50px;
  line-height: 50px;
  text-align: justify-all;
}
.right .article {
  margin-right: 10px;
  padding-left: 30px;
  padding-right: 30px;
  display: flex;
  margin-top: 10px;
  padding-bottom: 20px;
  background-color: #fff;
  font-size: 18px;
  text-align: justify;
  height: 620px;
  //width: 600px;
  //line-height: 50px;
}
.clearfix {
  clear: both;
}
.l-bottom .final {
  cursor: pointer;
  display: inline-block;
  text-align: center;
  background-color: rgb(126, 12, 110);
  width: 240px;
  margin: 20px 0px 20px 10px;
  border-radius: 4px;
  height: 30px;
  line-height: 30px;
  color: #fff;
  margin-top: 22px;
}
#answer .left .item {
  padding: 0px;
  font-size: 16px;
}
.l-bottom {
  border-radius: 4px;
  background-color: #fff;
  text-align: left;
}
.l-bottom .item p {
  margin-bottom: 15px;
  margin-top: 10px;
  color: #000;
  margin-left: 10px;
  letter-spacing: 2px;
}
.l-bottom .item li {
  width: 15%;
  margin-left: 5px;
  margin-bottom: 10px;
  list-style-type: none;
}
.l-bottom .item {
  display: flex;
  flex-direction: column;
}
.l-bottom .item ul {
  width: 100%;
  margin-left: -20px;
  margin-bottom: -8px;
  display: flex;
  justify-content: left;
  flex-wrap: wrap;
}
.l-bottom .item ul li a {
  position: relative;
  justify-content: center;
  display: inline-flex;
  align-items: center;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  background-color: #fff;
  border: 1px solid #eee;
  text-align: center;
  color: #000;
  font-size: 16px;
}
.left .l-top {
  display: flex;
  justify-content: space-around;
  padding: 16px 0px;
  border: 1px solid #eee;
  border-radius: 4px;
  margin-bottom: 10px;
  background-color: #fff;
}
.left {
  width: 260px;
  height: 100%;
  margin: 10px 10px 0px 10px;
}
.left .l-top li:nth-child(2) a {
  border: 1px solid #eee;
}
.left .l-top li:nth-child(3) a {
  background-color: #500c7e;
  border: none;
}
.left .l-top li:nth-child(4) a {
  position: relative;
  border: 1px solid #eee;
}
.left .l-top li:nth-child(4) a::before {
  width: 4px;
  height: 4px;
  content: " ";
  position: absolute;
  background-color: red;
  border-radius: 50%;
  top: 0px;
  left: 16px;
}
.left .l-top li {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  list-style-type: none;
}
.left .l-top li a {
  display: inline-block;
  padding: 10px;
  border-radius: 50%;
  background-color: #fff;
  border: 1px solid #FF90AA;
}
#answer .top {
  background-color: rgb(126, 12, 110);
}
#answer .item {
  color: #fff;
  display: flex;
  padding: 20px;
  font-size: 20px;
}
#answer .top .item li:nth-child(1) {
  margin-right: 10px;
  list-style-type: none;
}
#answer .top .item li:nth-child(2) {
  position: relative;
  margin-left: auto;
  margin-right: 30px;
  list-style-type: none;
}
#answer {
  padding-bottom: 30px;
}
.icon20 {
  font-size: 20px;
  font-weight: bold;
}
.item .msg {
  padding: 10px 15px;
  border-radius: 4px;
  top: 47px;
  right: -30px;
  color: #6c757d;
  position: absolute;
  border: 1px solid rgba(0,0,0,.15);
  background-color: #fff;
}
.item .msg p {
  font-size: 16px;
  width: 200px;
  text-align: left;
}
</style>
