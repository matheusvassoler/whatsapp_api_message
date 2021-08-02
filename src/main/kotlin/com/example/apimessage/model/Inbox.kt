package com.example.apimessage.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Inbox(
    @Id
    @GeneratedValue
    val id: Long = 0,
    val lastMessage: String = "",
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonBackReference()
    val owner: Contact = Contact(),
    val hashCode: String = "",
    val dateOfLastMessage: LocalDateTime = LocalDateTime.now(),
    val receiptName: String = "",
    val pictureInbox: String = "",
    val receiptNumber: String = "",
)
