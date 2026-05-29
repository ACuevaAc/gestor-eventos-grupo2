package com.gestor.model.entity;

/**
 * @class User
 * @description Plain Old Java Object (POJO) model entity representing a data schema mapping 
 * for system accounts, managing unique identity profile criteria, authorization credential strings, 
 * access management role tokens, and basic demographic attributes.
 */
public class User {
    
    /**
     * @private
     * @type {int}
     */
    private int id;
    
    /**
     * @private
     * @type {String}
     */
    private String name;
    
    /**
     * @private
     * @type {String}
     */
    private String email;
    
    /**
     * @private
     * @type {String}
     */
    private String password;
    
    /**
     * @private
     * @type {String}
     */
    private String role;
    
    /**
     * @private
     * @type {int}
     */
    private int age;
    
    /**
     * @constructor
     * @description Full structural constructor blueprint to initialize complete identity tracking 
     * configurations matching ongoing write or update transactional database entity rows.
     * @param {int} id - Unique primary key identifier for the account tracking index.
     * @param {String} name - The descriptive naming parameter assigned to the profile owner.
     * @param {String} email - The primary administrative contact index address configuration.
     * @param {int} age - Primitive integer numeric metric tracking current demographic rules.
     * @param {String} password - Secure cryptographic hash string token containing authentication keys.
     * @param {String} role - Structural string keyword matching identity permission access models (e.g., ADMIN, USER).
     */
    public User(int id, String name, String email, int age, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.password = password;
        this.role = role;
    }
    
    /**
     * @constructor
     * @description Partial historical constructor blueprint used exclusively during query retrieval pipelines 
     * to instantiate localized authentication verification checkpoints.
     * @param {String} email - The target administrative contact identity mapping tracking identifier.
     * @param {String} password - Secure cryptographic validation hash token representation.
     * @param {String} role - The explicit authorization authorization group scope tracking parameter.
     */
    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * @constructor
     * @description Default structural constructor blueprint fallback context to instantiate unallocated data states.
     */
    public User() {}

    /**
     * @method getId
     * @description Retrieves the unique primary key tracker index assigned to this account data frame.
     * @returns {int} The source record sequence identity key value.
     */
    public int getId() {
        return id;
    }

    /**
     * @method setId
     * @description Assigns the operational primary key tracker reference to the structural identifier mapping layers.
     * @param {int} id - Target unique sequence index parameter configuration.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @method getName
     * @description Exposes the text naming property representing this account entity profile.
     * @returns {String} The structural identity profile name description.
     */
    public String getName() {
        return name;
    }

    /**
     * @method setName
     * @description Binds the target descriptive sequence string to the entity profile metadata parameters.
     * @param {String} name - Absolute naming string assigned to the interface tracking structure.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @method getEmail
     * @description Extracts the primary contact identity index mapping address bound to the user.
     * @returns {String} The unique account tracking email mapping.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @method setEmail
     * @description Injects the unique messaging contact tracker address variable into persistent layers.
     * @param {String} email - Target unique contact index data parameter configuration.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @method getPassword
     * @description Exposes the persistent cryptographic verification token containing authentication context keys.
     * @returns {String} The current structural pass key hash variable.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @method setPassword
     * @description Binds the target processed cryptographic verification token string to credential tracking parameters.
     * @param {String} password - Secure calculated encryption sequence key data asset.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @method getRole
     * @description Resolves the access management tracking keyword designating validation scopes.
     * @returns {String} Absolute authorization permission group token identifier.
     */
    public String getRole() {
        return role;
    }

    /**
     * @method setRole
     * @description Standardizes and applies the administrative structural clearance security token.
     * @param {String} role - Target permission tier string parameter constraint.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @method getAge
     * @description Extracts the primitive metric tracking threshold age properties assigned to this entity.
     * @returns {int} Demographic validation threshold value.
     */
    public int getAge() {
        return age;
    }

    /**
     * @method setAge
     * @description Configures structural demographic variables tracking absolute user age parameters.
     * @param {int} age - Target physical validation metric constraint configuration.
     */
    public void setAge(int age) {
        this.age = age;
    }
}