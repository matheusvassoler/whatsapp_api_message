package com.example.apimessage.controller

import com.example.apimessage.controller.form.MessageForm
import com.example.apimessage.model.Inbox
import com.example.apimessage.model.Message
import com.example.apimessage.repository.ContactRepository
import com.example.apimessage.repository.InboxRepository
import com.example.apimessage.repository.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriBuilder
import org.springframework.web.util.UriComponentsBuilder
import java.time.LocalDateTime

@RestController
@RequestMapping("message")
class MessageController {

    @Autowired
    private lateinit var messageRepository: MessageRepository

    @Autowired
    private lateinit var inboxRepository: InboxRepository

    @Autowired
    private lateinit var contactRepository: ContactRepository

    @Autowired
    private lateinit var simpMessagingTemplate: SimpMessagingTemplate

    @GetMapping
    fun getAllMessages(): List<Message> {
        return messageRepository.findAll()
    }

    @GetMapping(params = ["senderNumber", "receiptNumber"])
    fun getMessagesBetweenTwoContacts(
        @RequestParam senderNumber: String,
        @RequestParam receiptNumber: String
    ): List<Message> {
        return messageRepository.getMessagesBySenderAndReceipt(senderNumber, receiptNumber)
    }

//    @PostMapping
//    fun sendMessage(@RequestBody form: MessageForm , uriBuilder: UriComponentsBuilder): ResponseEntity<Message> {
//        val senderContact = contactRepository.findById(form.senderNumber)
//        val receiptContact = contactRepository.findById(form.receiptNumber)
//        val receiptInbox = inboxRepository.findByHashCode("${receiptContact.get().phone}${senderContact.get().phone}")
//        val senderInbox = inboxRepository.findByHashCode("${senderContact.get().phone}${receiptContact.get().phone}")
//        val actualDate = LocalDateTime.now()
//
//        if (senderInbox == null) {
//            inboxRepository.save(Inbox(lastMessage = form.content, owner = senderContact.get(), hashCode = "${senderContact.get().phone}${receiptContact.get().phone}", dateOfLastMessage = actualDate, receiptName = receiptContact.get().name, pictureInbox = receiptContact.get().picture, receiptNumber = receiptContact.get().phone))
//        } else {
//            inboxRepository.save(Inbox(id = senderInbox.id, lastMessage = form.content, owner = senderInbox.owner, hashCode = senderInbox.hashCode, dateOfLastMessage = actualDate, receiptName = senderInbox.receiptName, pictureInbox = senderInbox.pictureInbox, receiptNumber = senderInbox.receiptNumber))
//        }
//
//        if (receiptInbox == null) {
//            inboxRepository.save(Inbox(lastMessage = form.content, owner = receiptContact.get(), hashCode = "${receiptContact.get().phone}${senderContact.get().phone}", dateOfLastMessage = actualDate, receiptName = senderContact.get().name, pictureInbox = senderContact.get().picture, receiptNumber = senderContact.get().phone))
//        } else {
//            inboxRepository.save(Inbox(id = receiptInbox.id, lastMessage = form.content, owner = receiptInbox.owner, hashCode = receiptInbox.hashCode, dateOfLastMessage = actualDate, receiptName = receiptInbox.receiptName, pictureInbox = receiptInbox.pictureInbox, receiptNumber = receiptInbox.receiptNumber))
//        }
//
//        val message = Message(
//            content = form.content,
//            sender = senderContact.get(),
//            receipt = receiptContact.get(),
//            sendDate = actualDate
//        )
//
//        messageRepository.save(message)
//
//        val uri = uriBuilder.path("/message/{id}").buildAndExpand(message.id).toUri()
//
//        return ResponseEntity.created(uri).body(message)
//    }

    @MessageMapping("/websocket/{to}")
    @SendTo("/response/message")
    fun sendMessage(@DestinationVariable to: String, messageForm: MessageForm) {
        print("handling send message: $messageForm to: $to")
        simpMessagingTemplate.convertAndSend("/response/messages$to", messageForm)
    }

//    @PostMapping
//    fun sendMessage(@RequestBody form: MessageForm, uriBuilder: UriComponentsBuilder): ResponseEntity<Message> {
//        val sender = contactRepository.findById(form.senderNumber)
//        val receipt = contactRepository.findById(form.receiptNumber)
//        val senderInbox = Inbox(
//            lastMessage = form.content,
//            owner = sender.get()
//        )
//        val receiptInbox = Inbox(
//            lastMessage = form.content,
//            owner = receipt.get()
//        )
//        inboxRepository.save(senderInbox)
//        inboxRepository.save(receiptInbox)
//        val message = Message(
//            senderNumber = form.senderNumber,
//            receiptNumber = form.receiptNumber,
//            content = form.content,
//            inbox = setOf(senderInbox, receiptInbox)
//        )
//        messageRepository.save(message)
//
//        val uri = uriBuilder.path("/message/{id}").buildAndExpand(message.id).toUri()
//
//        return ResponseEntity.created(uri).body(message)
//    }
}