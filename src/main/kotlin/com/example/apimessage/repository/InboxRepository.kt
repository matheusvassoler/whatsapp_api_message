package com.example.apimessage.repository

import com.example.apimessage.model.Contact
import com.example.apimessage.model.Inbox
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface InboxRepository : JpaRepository<Inbox, Long> {
    @Query(value = "SELECT * FROM inbox WHERE owner_id = :contact_id ORDER BY date_of_last_message DESC", nativeQuery = true)
    fun findByOwner(@Param("contact_id") contactId: String): List<Inbox>
    fun findByHashCode(hashCode: String): Inbox?
}