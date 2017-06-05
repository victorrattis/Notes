const { Router } = require('express')
const notesController = require('./../controllers/notes')

const apiVersion = "1.0"

// resource URL: /<version>/<rest api>
// GET      for retrieving an object
// POST     for creating a new object
// PUT      for modifying or replacing an object
// DELETE   for removing an object
let router = Router();
router.use((req, res, next) => {
    console.log(`[${req.method}: ${req.url}]: ${Date()}`)
    next()
})

let notesRouters = Router();
notesRouters.route('/notes')
    .get(notesController.findNotes)

notesRouters.route('/notes/:id')
    .get(notesController.findById)

router.use(`/${apiVersion}/`, notesRouters);
module.exports = router
