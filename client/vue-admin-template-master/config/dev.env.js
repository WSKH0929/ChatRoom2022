'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_API: '"http://localhost:8002"',
  SS_PATH: '"https://eduguli-13184.oss-cn-beijing.aliyuncs.com/wallhaven-43lg6d.jpg"'//默认头像
})
