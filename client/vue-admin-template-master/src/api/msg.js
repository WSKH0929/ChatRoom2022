import request from '@/utils/request'

export default {
  sendMsg(msg) {
    return request({
      url: '/chatroom/msg/sendMsg',
      method: 'post',
      params: {
        msg
      }
    })
  },
}
