package com.motivation.team3.motivateme.model

class Song {
    var name: String? = null
    var artist: String? = null
    var image = 0

    constructor() {}
    constructor(name: String?, artist: String?, image: Int) {
        this.name = name
        this.artist = artist
        this.image = image
    }
}