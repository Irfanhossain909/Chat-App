package com.example.chatapp.model

class Message {


  var messegeId :String?= null
  var messege :String?= null
  var senderId :String?= null
  var imageurl :String?= null
  var timeStamp :Long = 0

    constructor()

    constructor(
        messege :String?,
        senderId :String?,
        timeStamp :Long
    ){
        this.messege = messege
        this.senderId = senderId
        this.timeStamp = timeStamp
    }
}