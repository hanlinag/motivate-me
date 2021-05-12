package com.motivation.team3.motivateme.model

class Contact {
    var id = 0
    var count = 0
    var title: String? = null
    var body: String? = null
    var time: String? = null
    var date: String? = null

    constructor() {}
    constructor(count: Int, title: String?, body: String?, time: String?, date: String?) {
        this.count = count
        this.title = title
        this.body = body
        this.time = time
        this.date = date
    }

    constructor(id: Int, count: Int, title: String?, body: String?, time: String?, date: String?) {
        this.id = id
        this.count = count
        this.title = title
        this.body = body
        this.time = time
        this.date = date
    }
}