
const express = require('express')

const port = process.env.PORT || 3000

let app = express()
let routers = require('./router/router')
app.use('/', routers)

app.listen(port, function() {
    console.log(`Server listening on port ${port}`)
})
