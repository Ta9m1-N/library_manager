package com.example.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LOGS")
public class Log {
	@Id
	@SequenceGenerator(name = "LOG_ID_GENERATOR", sequenceName = "LOG_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOG_ID_GENERATOR")
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "LIBRARY_ID")
	private Integer libraryId;
	
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "RENT_DATE")
	private LocalDateTime rentDate;
	
	@Column(name = "RETURN_DATE")
	private LocalDateTime returnDate;
	
	@Column(name = "RETURN_DUE_DATE")
	private LocalDateTime returnDueDate;
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getLibraryId() {
		return this.libraryId;
	}
	public void setLibraryId(Integer libraryId) {
		this.libraryId = libraryId;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public LocalDateTime getRentDate() {
		return this.rentDate;
	}
	public void setRentDate(LocalDateTime rentDate) {
		this.rentDate = rentDate;
	}
	
	public LocalDateTime getReturnDate() {
		return this.returnDate;
	}
	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}
	
	public LocalDateTime getReturnDueDate() {
		return returnDueDate;
	}
	public void setReturnDueDate(LocalDateTime returnDueDate) {
		this.returnDueDate = returnDueDate;
	}
	
	@ManyToOne
	@JoinColumn(name = "user_id",insertable = false,updatable = false)
	private User user;
	
	public User getUser(){
		return this.user;
	}
	
	@ManyToOne
	@JoinColumn(name = "library_id",insertable = false,updatable = false)
	private Library library;
	
	public Library getLibrary(){
		return this.library;
	}
}
