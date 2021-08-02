package com.example.apimessage.controller.form

data class MessageForm(
    val content: String,
    val senderNumber: String,
    val receiptNumber: String
)
