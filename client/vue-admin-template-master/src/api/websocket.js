//websocket.js
import Vue from 'vue'

// 1、用于保存WebSocket 实例对象
export const WebSocketHandle = undefined

// 2、外部根据具体登录地址实例化WebSocket 然后回传保存WebSocket
export const WebsocketINI = function(websocketinstance) {
  this.WebSocketHandle = websocketinstance
  this.WebSocketHandle.onmessage = OnMessage
}

// 3、为实例化的WebSocket绑定消息接收事件：同时用于回调外部各个vue页面绑定的消息事件
// 主要使用WebSocket.WebSocketOnMsgEvent_CallBack才能访问  this.WebSocketOnMsgEvent_CallBack 无法访问很诡异
const OnMessage = function(msg) {
  // 1、消息打印
  // console.log('收到消息：', msg)

  // 2、如果外部回调函数未绑定 结束操作
  if (!WebSocket.WebSocketOnMsgEvent_CallBack) {
    console.log(WebSocket.WebSocketOnMsgEvent_CallBack)
    return
  }

  // 3、调用外部函数
  WebSocket.WebSocketOnMsgEvent_CallBack(msg)
}

// 4、全局存放外部页面绑定onmessage消息回调函数:注意使用的是var
export const WebSocketOnMsgEvent_CallBack = undefined

// 5、外部通过此绑定方法 来传入的onmessage消息回调函数
export const WebSocketBandMsgReceivedEvent = function(receiveevent) {
  WebSocket.WebSocketOnMsgEvent_CallBack = receiveevent
}

// 6、封装一个直接发送消息的方法：
export const Send = function(msg) {
  if (!this.WebSocketHandle || this.WebSocketHandle.readyState !== 1) {
    // 未创建连接 或者连接断开 无法发送消息
    return
  }
  this.WebSocketHandle.send(msg)// 发送消息
}

// 7、导出配置
const WebSocket = {
  WebSocketHandle,
  WebsocketINI,
  WebSocketBandMsgReceivedEvent,
  Send,
  WebSocketOnMsgEvent_CallBack
}

// 8、全局绑定WebSocket
Vue.prototype.$WebSocket = WebSocket