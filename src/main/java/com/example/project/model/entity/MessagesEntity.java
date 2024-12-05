package com.example.project.model.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "MESSAGES", schema = "SYSTEM", catalog = "")
public class MessagesEntity {
    private long messageId;
    private String content;
    private LocalDateTime createdAt;
    private Long senderId;
    private Long receiverId;

    @Id
    @Column(name = "MESSAGE_ID")
    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "CREATED_AT")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessagesEntity that = (MessagesEntity) o;
        return messageId == that.messageId && Objects.equals(content, that.content) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, content, createdAt);
    }

    @Basic
    @Column(name = "SENDER_ID")
    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    @Basic
    @Column(name = "RECEIVER_ID")
    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
}
