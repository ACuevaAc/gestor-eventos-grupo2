package com.gestor.model.entity;

import java.time.LocalDateTime;

/**
 * @class SummaryBook
 * @description Plain Old Java Object (POJO) model entity representing a data projection schema,
 * aggregating normalized relational information across reservation, user, and table records 
 * into a single unified read-only transaction log data mapping wrapper for structural interface tables.
 */
public class SummaryBook {
    
    /**
     * @private
     * @type {int}
     */
    private int bookId;
    
    /**
     * @private
     * @type {String}
     */
    private String tableName; 
    
    /**
     * @private
     * @type {String}
     */
    private String userName;  
    
    /**
     * @private
     * @type {LocalDateTime}
     */
    private LocalDateTime reservationDate;

    /**
     * @constructor
     * @description Full structural constructor blueprint to initialize flattened read-only analytics logs 
     * matching custom database relational query join projection schemas.
     * @param {int} bookId - Unique primary key mapping identifier for the source reservation tracking tuple.
     * @param {String} tableName - The descriptive naming parameter extracted from the resolved venue table asset.
     * @param {String} userName - The identity naming profile parameter extracted from the resolved account owner record.
     * @param {LocalDateTime} reservationDate - Absolute historical timestamp tracking threshold configuration.
     */
    public SummaryBook(int bookId, String tableName, String userName, LocalDateTime reservationDate) {
        this.bookId = bookId;
        this.tableName = tableName;
        this.userName = userName;
        this.reservationDate = reservationDate;
    }

    /**
     * @method getBookId
     * @description Retrieves the unique primary key tracking reference index for the root booking record.
     * @returns {int} The source reservation entry index sequence number.
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * @method setBookId
     * @description Assigns the primary key tracker tracking reference index to this data projection layer wrapper.
     * @param {int} bookId - Target unique sequence index parameter value.
     */
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    /**
     * @method getTableName
     * @description Extracts the de-normalized string description representing the target allocated venue table asset name.
     * @returns {String} The structural layout table identifier text mapping.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @method setTableName
     * @description Binds the de-normalized text description tracking value for the target venue table mapping layers.
     * @param {String} tableName - Absolute descriptive naming property assigned to the interface component.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @method getUserName
     * @description Extracts the de-normalized identity user name attribute representing the scheduling profile owner.
     * @returns {String} Account profile descriptive identity string tracker.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @method setUserName
     * @description Binds the de-normalized identity user name text criteria to this reporting entity blueprint layer.
     * @param {String} userName - Absolute account identification text mapping tracking value.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @method getReservationDate
     * @description Exposes the explicit chronological timestamp tracking state parameters representing the reservation threshold.
     * @returns {LocalDateTime} Current target immutable timestamp data object mapping.
     */
    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    /**
     * @method setReservationDate
     * @description Configures the explicit chronological timestamp tracking state parameters representing the reservation threshold.
     * @param {LocalDateTime} reservationDate - Target structural timestamp boundary parameter configuration.
     */
    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }
}