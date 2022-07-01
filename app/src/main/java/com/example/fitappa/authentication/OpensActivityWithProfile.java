package com.example.fitappa.authentication;

/**
 * Interface to be implemented by a class that contains a method that opens DashboardActivity
 * <p>
 * To be initialized by a class that needs to pass in a profile and navigate to the DashboardActivity
 */
interface OpensActivityWithProfile {

    /**
     * Open the DashboardActivity
     */
    void openActivity();

    /**
     * Display an error message given a message
     *
     * @param message String message to be displayed as error on call
     */
    void showErrorMessage(String message);
}
