package com.example.apimessage.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonIgnoreType
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Lob
import javax.persistence.OneToMany

//@Entity
//data class Contact(
//    @Id
//    val phone: String = "",
//    val name: String = "",
//    @OneToMany(mappedBy = "sender")
//    @JsonManagedReference
//    @get:JsonIgnore
//    val senderMessage: List<Message> = listOf(),
//    @OneToMany(mappedBy = "receipt")
//    @JsonManagedReference
//    val receiptMessage: List<Message> = listOf(),
//    @OneToMany(mappedBy = "owner")
//    @JsonManagedReference
//    val inbox: List<Inbox> = listOf(),
//    val picture: String = ""
//)

@Entity
data class Contact(
    @Id
    val phone: String = "",
    val name: String = "",
    @OneToMany(mappedBy = "sender")
    //@JsonManagedReference
    @get:JsonIgnore
    val senderMessage: List<Message> = listOf(),
    @OneToMany(mappedBy = "receipt")
    //@JsonManagedReference
    @get:JsonIgnore
    val receiptMessage: List<Message> = listOf(),
    @OneToMany(mappedBy = "owner")
    @JsonManagedReference
    val inbox: List<Inbox> = listOf(),
    val picture: String = ""
)