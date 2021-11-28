package com.example.fitappa.Profile;

import java.io.Serializable;

class User implements Serializable {

    private String username;
    private String email;
    private String uniqueID;

    /**
     * Constructor for a User that take an email and password as well as a unique identifier necessary
     * for database.
     *
     * @param email    The String email that is connected to the account
     * @param username The String username referring to the account
     * @param uniqueID The String unique identifier used to store the user in database and retrieve their info
     */
    User(String email, String username, String uniqueID) {
        this.username = username;
        this.email = email;
        this.uniqueID = uniqueID;
    }

    // Constructor necessary for Firebase
    @SuppressWarnings("unused")
    public User() {
    }

    /**
     * returns the User's username
     *
     * @return returns the String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the unique identifier for this user
     *
     * @return unique identifier for this user
     */
    public String getUniqueID() {
        return uniqueID;
    }

    /**
     * return a User's email
     *
     * @return return the String email
     */
    public String getEmail() {
        return email;
    }


}