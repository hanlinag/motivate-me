package com.motivation.team3.motivateme.model

class Quote {
    var quote: String? = null
    var autor: String? = null

    constructor() {}
    constructor(quote: String?, autor: String?) {
        this.quote = quote
        this.autor = autor
    }
}