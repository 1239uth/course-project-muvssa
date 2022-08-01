package com.example.fitappa.profile;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * This class is the central storage for all relevant entities and use cases for the third and fourth layers of
 * clean architecture. It contains a User, FollowManager, list of Routines, Saveable gateway, and DefaultExercises.
 * <p>
 * It implements Serializable so that it may be saved into a database or used to start a new intent and pass its data.
 *
 * @author Souren
 * @author Uthman
 * @author Abdullah
 * <p>
 * Since 2.6
 */
public class Profile extends RealmObject {

    @PrimaryKey
    private String id;
    @Required
    private String email;
    @Required
    private String username;

    private String firstName;
    private String lastName;
    private String height;
    private String weight;

    /**
     * Constructor that creates a new profile given a user's information with a unique ID
     *
     * @param email    Email address for user
     * @param username Username for the user
     * @param id Unique identifier representing the User (necessary for database query)
     */
    Profile(String email, String username, String id) {
        this.email = email;
        this.username = username;
        this.id = id;
    }

    // empty constructor necessary for Database
    @SuppressWarnings("unused")
    public Profile() {
    }

    /**
     * Get the email of this user
     *
     * @return String representing this users email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the username of the user for this profile
     *
     * @return String representing the user's username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Get the unique ID of the user for this profile
     *
     * @return String representing the user's unique identifier
     */
    public String getId() {
        return this.id;
    }

    /**
     * gets a string of the users weight
     *
     * @return returns string of their weight
     */
    public String getWeight() {
        return this.weight;
    }

    /**
     * Set the weight of this profile
     *
     * @param weight String representing the weight of this profile
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * gets a string of the users height
     *
     * @return returns string of their height
     */
    public String getHeight() {
        return this.height;
    }

    /**
     * Set the height of this profile
     *
     * @param height String representing the height of this profile
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * gets a string of the users first name
     *
     * @return returns string of their first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Set the first name of this profile
     *
     * @param firstName String representing the first name for this profile
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * gets a string of the users last name
     *
     * @return returns string of their last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Set the last name of this profile
     *
     * @param lastName String representing the last name of this profile
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
