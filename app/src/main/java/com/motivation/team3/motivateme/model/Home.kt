package com.motivation.team3.motivateme.model

class Home {
    var image = 0
    var functionName: String? = null
    var titleHome: String? = null

    constructor() {}
    constructor(image: Int) {
        this.image = image
    }

    constructor(titleHome: String?, image: Int) {
        this.titleHome = titleHome
        this.image = image
    }

    constructor(image: Int, functionName: String?, titleHome: String?) {
        this.image = image
        this.functionName = functionName
        this.titleHome = titleHome
    }

    constructor(titleHome: String?) {
        this.titleHome = titleHome
    }
}