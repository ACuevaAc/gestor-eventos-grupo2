package com.gestor.model.entity;

import java.time.LocalDateTime;

public class Book {
    private int bookId;
    private int userId;
    private int tableId;
    private LocalDateTime reservationDate; 
    
    
    // Este constructor sobra
    // what should we do with this constructor so?
    /**
     * @deprecated
     */
    public Book(int bookId, int userId, int tableId) {
        this.bookId = bookId;
        this.userId = userId;
        this.tableId = tableId;
        this.reservationDate = LocalDateTime.now();
    }


    public Book(int bookId, int userId, int tableId, LocalDateTime reservationDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.tableId = tableId;
        this.reservationDate = reservationDate;
    }
    public int getBookId() {
    	return bookId; 
    }

    public void setBookId(int bookId) {
    	this.bookId = bookId; 
    }

    public int getUserId() {
    	return userId; 
    }
    
    public void setUserId(int userId) {
    	this.userId = userId; 
    }

    public int getTableId() {
    	return tableId; 
    }
    
    public void setTableId(int tableId) {
    	this.tableId = tableId; 
    }

    public LocalDateTime getReservationDate() {
    	return reservationDate; 
    }

    public void setReservationDate(LocalDateTime reservationDate) {
    	this.reservationDate = reservationDate; 
    }
}
