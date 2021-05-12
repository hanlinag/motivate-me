package com.motivation.team3.motivateme.model

class Note {
    var id = 0
    var title: String? = null
    var body: String? = null
    var time: String? = null
    var date: String? = null

    constructor() {}
    constructor(title: String?, body: String?, time: String?, date: String?) {
        this.title = title
        this.body = body
        this.time = time
        this.date = date
    }

    constructor(id: Int, title: String?, body: String?, time: String?, date: String?) {
        this.id = id
        this.title = title
        this.body = body
        this.time = time
        this.date = date
    }
}