package com.gestor.model.entity;

import java.time.LocalDateTime;

/**
 * @class Book
 * @description Plain Old Java Object (POJO) model entity representing a data schema mapping 
 * for table reservations, tracking transactional associations between users and physical venue inventory.
 */
public class Book {
    private int bookId;
    private int userId;
    private int tableId;
    private LocalDateTime reservationDate; 
        
    /**
     * @constructor
     * @deprecated This constructor is deprecated because it forces an implicit fallback to the current system runtime timestamp via `LocalDateTime.now()`, which bypasses historical persistence mappings or explicit time zone parameter allocation.
     * @param {int} bookId - Unique primary key identifier for the reservation record.
     * @param {int} userId - Foreign key reference matching the authenticated user profile entity.
     * @param {int} tableId - Foreign key reference matching the allocated physical venue table asset.
     */
    @Deprecated
    public Book(int bookId, int userId, int tableId) {
        this.bookId = bookId;
        this.userId = userId;
        this.tableId = tableId;
        this.reservationDate = LocalDateTime.now();
    }


    /**
     * @constructor
     * @description Full structural constructor blueprint to initialize reservation records with an explicit historical or scheduled timestamp parameter mapping.
     * @param {int} bookId - Unique primary key identifier for the reservation record.
     * @param {int} userId - Foreign key reference matching the authenticated user profile entity.
     * @param {int} tableId - Foreign key reference matching the allocated physical venue table asset.
     * @param {LocalDateTime} reservationDate - Explicit target timestamp configuration for the scheduling payload context.
     */
    public Book(int bookId, int userId, int tableId, LocalDateTime reservationDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.tableId = tableId;
        this.reservationDate = reservationDate;
    }

    /**
     * @method getBookId
     * @description Retrieves the unique primary key identifier for the reservation record tracking wrapper.
     * @returns {int} The unique reservation entry index key value.
     */
    public int getBookId() {
    	return bookId; 
    }

    /**
     * @method setBookId
     * @description Sets the primary key index identifier for the destination reservation record tracking layer.
     * @param {int} bookId - The unique sequence index data value.
     */
    public void setBookId(int bookId) {
    	this.bookId = bookId; 
    }

    /**
     * @method getUserId
     * @description Extracts the internal foreign key mapping representing the scheduling account identity profile.
     * @returns {int} Relational user identity tracking index.
     */
    public int getUserId() {
    	return userId; 
    }
    
    /**
     * @method setUserId
     * @description Binds the structural foreign key mapping representing the scheduling account identity profile.
     * @param {int} userId - Relational target user structural primary identifier index.
     */
    public void setUserId(int userId) {
    	this.userId = userId; 
    }

    /**
     * @method getTableId
     * @description Extracts the underlying physical table inventory reference index bound to this contract.
     * @returns {int} Relational venue table asset data identifier index.
     */
    public int getTableId() {
    	return tableId; 
    }
    
    /**
     * @method setTableId
     * @description Binds the underlying physical table inventory reference index to this booking entity instance.
     * @param {int} tableId - Relational target venue table asset data primary identifier index.
     */
    public void setTableId(int tableId) {
    	this.tableId = tableId; 
    }

    /**
     * @method getReservationDate
     * @description Exposes the immutable timestamp tracking parameters representing the reservation schedule threshold.
     * @returns {LocalDateTime} Current explicit target timestamp mapping.
     */
    public LocalDateTime getReservationDate() {
    	return reservationDate; 
    }

    /**
     * @method setReservationDate
     * @description Configures the immutable timestamp tracking parameters representing the reservation schedule threshold.
     * @param {LocalDateTime} reservationDate - Target structural timestamp criteria mapping.
     */
    public void setReservationDate(LocalDateTime reservationDate) {
    	this.reservationDate = reservationDate; 
    }
}
