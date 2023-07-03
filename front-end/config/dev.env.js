'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_API: '""',
  WS_BASE_API: '"//sfcc.edu.koobietech.com/ws/"',
  IMAGE_API: '"//sfcc.edu.koobietech.com/file-api/"',
})
