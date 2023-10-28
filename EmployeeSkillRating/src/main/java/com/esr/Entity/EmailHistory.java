package com.esr.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class EmailHistory {
	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String recipientEmail;
	    private LocalDateTime sentDate;
	    private LocalDate lastEmailSentDate;

	    
		public LocalDate getLastEmailSentDate() {
			return lastEmailSentDate;
		}
		public void setLastEmailSentDate(LocalDate lastEmailSentDate) {
			this.lastEmailSentDate = lastEmailSentDate;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getRecipientEmail() {
			return recipientEmail;
		}
		public void setRecipientEmail(String recipientEmail) {
			this.recipientEmail = recipientEmail;
		}
		public LocalDateTime getSentDate() {
			return sentDate;
		}
		public void setSentDate(LocalDateTime sentDate) {
			this.sentDate = sentDate;
		}
		@Override
		public String toString() {
			return "EmailHistory [id=" + id + ", recipientEmail=" + recipientEmail + ", sentDate=" + sentDate
					+ ", lastEmailSentDate=" + lastEmailSentDate + "]";
		}
	    
	    // Getters and setters
	


}
