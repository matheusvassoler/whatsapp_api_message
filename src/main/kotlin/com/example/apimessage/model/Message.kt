package com.example.apimessage.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

//@Entity
//data class Message(
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    val id: Long = 0,
//    val senderNumber: String = "",
//    val receiptNumber: String = "",
//    val content: String = "",
//    val sendDate: LocalDateTime = LocalDateTime.now(),
//    @ManyToMany(mappedBy = "message")
//    @JsonIgnore
//    @JsonBackReference
//    val inbox: Set<Inbox> = setOf()
//)

@Entity
data class Message(
    @Id
    @GeneratedValue
    val id: Long = 0,
    val content: String = "",
    val sendDate: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    @JoinColumn(name = "sender_id")
    //@JsonBackReference
    val sender: Contact = Contact(),
    @ManyToOne
    @JoinColumn(name = "receipt_id")
    //@JsonBackReference
    val receipt: Contact = Contact()
)