
let notes = [
    {_id: 0, dbid: 2, title: "note 2.1", body: "This note was created in server", updated: 1496618622014},
    {_id: 1, dbid: 2, title: "note 2.2", body: "This note was created in server", updated: 1496618622014},
    {_id: 2, dbid: 2, title: "note 2.3", body: "This note was created in server", updated: 1496618622014},
    {_id: 3, dbid: 2, title: "note 2.4", body: "This note was created in server", updated: 1496618622014},
    {_id: 4, dbid: 2, title: "note 2.5", body: "This note was created in server", updated: 1496618622014},
    {_id: 5, dbid: 2, title: "note 2.6", body: "This note was created in server", updated: 1496618622014},
    {_id: 6, dbid: 2, title: "note 2.7", body: "This note was created in server", updated: 1496618622014},
    {_id: 7, dbid: 2, title: "note 2.8", body: "This note was created in server", updated: 1496618622014},
    {_id: 8, dbid: 2, title: "note 2.9", body: "This note was created in server", updated: 1496618622014},
    {_id: 9, dbid: 2, title: "note 2.10", body: "This note was created in server", updated: 1496618622014},
    {_id: 10, dbid: 2, title: "note 2.11", body: "This note was created in server", updated: 1496619759456},
    {_id: 11, dbid: 2, title: "note 2.12", body: "This note was created in server", updated: 1496619759456},
    {_id: 12, dbid: 2, title: "note 2.13", body: "This note was created in server", updated: 1496619759456},
    {_id: 13, dbid: 2, title: "note 2.14", body: "This note was created in server", updated: 1496628234476},
    {_id: 14, dbid: 2, title: "note 2.15", body: "This note was created in server", updated: 1496628234476}
]

module.exports.findNotes = (req, res, next) => {
    let date = req.query.date
    let result = date == undefined ?
        notes :
        notes.filter(item => date < item.updated)

    res.json({
        status: "ok",
        syncDate: Date.now(),
        payload: {
            notes: result
        }
    })
    next()
}

module.exports.findById = (req, res, next) => {
    let id = req.params.id
    let note = notes[id]
    if (note != undefined){
        res.json({
            status: "ok",
            payload: {
                note: note
            }
        })
    }
    else res.send('without note')
    next()
}

module.exports.add = (req, res, next) => {
    next()
}

module.exports.update = (req, res, next) => {
    next()
}

module.exports.delete = (req, res, next) => {
    next()
}
