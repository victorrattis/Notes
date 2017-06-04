
const express = require('express')

let app = express()
let port = process.env.PORT || 3000

let routers = require('./router/router')
app.use('/', routers)

app.listen(port, function() {
    console.log(`Server listening on port ${port}`)
})
