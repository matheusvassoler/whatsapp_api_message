package com.example.apimessage.repository

import com.example.apimessage.model.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MessageRepository : JpaRepository<Message, Long> {

    @Query(
        value = "SELECT * FROM message WHERE (receipt_id = :receiptNumber AND sender_id = :senderNumber) OR (receipt_id = :senderNumber AND sender_id = :receiptNumber) ORDER BY send_date DESC",
        nativeQuery = true
    )
    fun getMessagesBySenderAndReceipt(
        @Param("senderNumber") senderNumber: String,
        @Param("receiptNumber") receiptNumber: String
    ): List<Message>
}