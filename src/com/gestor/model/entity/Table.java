package com.gestor.model.entity;

/**
 * @class Table
 * @description Plain Old Java Object (POJO) model entity representing a data schema mapping 
 * for physical venue table assets, managing spatial capacity thresholds, descriptive naming identifiers, 
 * and operational allocation tracking flags.
 */
public class Table {
    
    /**
     * @private
     * @type {int}
     */
    private int id;
    
    /**
     * @private
     * @type {int}
     */
    private int max;
    
    /**
     * @private
     * @type {String}
     */
    private String name;
    
    /**
     * @private
     * @type {boolean}
     */
    private boolean bookedTable;
    
    /**
     * @constructor
     * @description Full structural constructor blueprint to initialize physical inventory tracking states 
     * matching ongoing database entity schemas.
     * @param {int} id - Unique primary key identifier for the venue table tracking row index.
     * @param {int} max - The structural maximum capacity seating constraint allowed for the physical resource.
     * @param {String} name - The descriptive layout title assigned to the venue asset.
     * @param {boolean} booked - Boundary flag configuration indicating the current operational allocation state.
     */
    public Table (int id, int max, String name, boolean booked) {
        this.id = id;
        this.max = max;
        this.name = name;
        this.bookedTable = booked;
    }

    /**
     * @constructor
     * @description Default structural constructor blueprint fallback context to instantiate unallocated data states.
     */
    public Table () {}

    /**
     * @method getId
     * @description Retrieves the unique primary key tracker index assigned to this table instance.
     * @returns {int} The source record identity sequence number data value.
     */
    public int getId () {
        return id;
    }

    /**
     * @method setId
     * @description Assigns the primary key tracker reference to the structural identifier mapping layers.
     * @param {int} id - Target unique sequence index data parameter configuration.
     */
    public void setId (int id) {
        this.id = id;
    }

    /**
     * @method getMax
     * @description Extracts the spatial volumetric seat mapping constraint assigned to the layout asset.
     * @returns {int} The maximum capacity threshold allocation metric.
     */
    public int getMax () {
        return max;
    }

    /**
     * @method setMax
     * @description Establishes the operational spatial boundary constraint parameter defining peak seating.
     * @param {int} max - Maximum capacity threshold limit variable configuration.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @method getName
     * @description Exposes the descriptive layout title string representing this table asset.
     * @returns {String} The structural component text description mapping.
     */
    public String getName () {
        return name;
    }

    /**
     * @method setName
     * @description Binds the target descriptive sequence string to the entity configuration profile.
     * @param {String} name - Absolute naming parameter assigned to the interface text model.
     */
    public void setName (String name) {
        this.name = name;
    }

    /**
     * @method isBooked
     * @description Evaluates ongoing allocation parameters to verify whether the asset is currently claimed.
     * @returns {boolean} True if matching occupancy checks block allocations, false if resources remain empty.
     */
    public boolean isBooked () {
        return bookedTable;
    }

    /**
     * @method setBooked
     * @description Sets the underlying allocation validation flag state across the tracking resource maps.
     * @param {boolean} booked - Target flag variable tracking allocation collision parameters.
     */
    public void setBooked(boolean booked) {
        this.bookedTable = booked;
    }
}