<template>
  <div>
    <div style="margin-top: 1vh;margin-bottom: 1vh;font-weight: bold;">聊天内容:</div>
    <div style="background-color: #c8c8c8;width: 100%;height: 80vh;" id="chatDiv">

    </div>
    <div style="margin-top: 1vh;margin-bottom: 1vh;font-weight: bold;">聊天输入框:</div>
    <el-input v-model="text">

    </el-input>
    <el-button @click="sendMsg">点击发送</el-button>
  </div>
</template>

<script>
  import {getInfo} from '../../api/login.js';
  import {getToken} from '../../utils/auth.js'
  import msgApi from '../../api/msg.js'
  export default {
    mounted() {
      //
      this.token = getToken()
      getInfo(this.token).then((rep)=>{
        this.userName = rep.data.name
      }).catch((error)=>{
        console.log(error)
      })
    },
    data() {
      return {
        text: "",
        token:"",
        userName:"",
      }
    },
    methods: {
      sendMsg(){
        let msg = this.userName+"："+this.text
        msgApi.sendMsg(msg).then((rep)=>{

        }).catch((error)=>{

        })
        this.text = ""
      }
    }
  }
</script>

<style scoped="true">
  .selfMsg{
    float: right;
  }
</style>
