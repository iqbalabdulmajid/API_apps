package com.iqbal_abdul_majid.myapplication

class Model {
    lateinit var id: String
    lateinit var name: String
    lateinit var email : String

    constructor(id: String, name: String, email: String) {
        this.id = id
        this.name = name
        this.email = email
    }
    constructor()
}