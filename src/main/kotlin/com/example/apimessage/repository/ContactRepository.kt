package com.example.apimessage.repository

import com.example.apimessage.model.Contact
import org.springframework.data.jpa.repository.JpaRepository

interface ContactRepository : JpaRepository<Contact, String> {
    fun findAllByOrderByNameAsc(): List<Contact>
}