package com.example.apimessage.controller

import com.example.apimessage.model.Contact
import com.example.apimessage.repository.ContactRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/contact")
class ContactController {

    @Autowired
    private lateinit var contactRepository: ContactRepository

    @GetMapping
    fun getAllContacts(): List<Contact> {
        return contactRepository.findAll()
    }

    @GetMapping(params = ["sort"])
    fun getAllContactsBySort(@RequestParam sort: String): List<Contact> {
        return when (sort.toUpperCase()) {
            "ASC" -> contactRepository.findAllByOrderByNameAsc()
            else -> contactRepository.findAll()
        }
    }

    @GetMapping("/{phone}")
    fun getContact(@PathVariable phone: String): ResponseEntity<Contact> {
        val contact = contactRepository.findById(phone)
        if (contact.isPresent) {
            return ResponseEntity.ok(contact.get())
        }

        return ResponseEntity.notFound().build()
    }
}