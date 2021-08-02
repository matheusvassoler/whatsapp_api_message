package com.example.apimessage.controller

import com.example.apimessage.model.Inbox
import com.example.apimessage.repository.ContactRepository
import com.example.apimessage.repository.InboxRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/inbox")
class InboxController {

    @Autowired
    private lateinit var inboxRepository: InboxRepository

    @Autowired
    private lateinit var contactRepository: ContactRepository

    @GetMapping
    fun getAllInboxes(): List<Inbox> {
        return inboxRepository.findAll()
    }

    @GetMapping(params = ["contactId"])
    fun getAllInboxForOwner(@RequestParam contactId: String): List<Inbox> {
        return inboxRepository.findByOwner(contactId)
    }
}