	package com.gestor.model.entity;
	
	import java.time.LocalDateTime;
	
	public class SummaryBook {
		private int bookId;
	    private String tableName; 
	    private String userName;  
	    private LocalDateTime reservationDate;
	
	    public SummaryBook(int bookId, String tableName, String userName, LocalDateTime reservationDate) {
	        this.bookId = bookId;
	        this.tableName = tableName;
	        this.userName = userName;
	        this.reservationDate = reservationDate;
	    }
	
		public int getBookId() {
			return bookId;
		}
	
		public void setBookId(int bookId) {
			this.bookId = bookId;
		}
	
		public String getTableName() {
			return tableName;
		}
	
		public void setTableName(String tableName) {
			this.tableName = tableName;
		}
	
		public String getUserName() {
			return userName;
		}
	
		public void setUserName(String userName) {
			this.userName = userName;
		}
	
		public LocalDateTime getReservationDate() {
			return reservationDate;
		}
	
		public void setReservationDate(LocalDateTime reservationDate) {
			this.reservationDate = reservationDate;
		}
	    
	    
	}