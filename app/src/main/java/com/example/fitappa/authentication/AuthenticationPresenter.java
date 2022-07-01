package com.example.fitappa.authentication;

/**
 * This class is an abstract class extended by presenters that update an activity after retrieving a profile
 * from the database.
 * <p>
 * The class's methods use an interface which represents a view that allows this class to call its method and open
 * and activity with the profile.
 * <p>
 *
 * @author Uthman
 * @since 0.2
 */
abstract class AuthenticationPresenter {
    /**
     * An interface which contains a method to open an activity given a profile and display an error given a message
     */
    protected final OpensActivityWithProfile view;

    /**
     * Constructor that takes in an OpensActivityWithProfile interface and initializes it
     *
     * @param view OpensActivityWithProfile interface that contains methods to set error and open activity with profile
     */
    AuthenticationPresenter(OpensActivityWithProfile view) {
        this.view = view;
    }

    /**
     * Proceed to next activity when authenticated
     */
    void proceed() {
        view.openActivity();
    }

    /**
     * Set an error when the database fails to retrieve the profile
     */
    abstract void setError();
}
