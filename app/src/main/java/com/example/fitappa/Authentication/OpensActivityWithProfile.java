package com.example.fitappa.Authentication;

import com.example.fitappa.Profile.Profile;

/**
 * Interface to be implemented by a class that contains a method that opens DashboardActivity
 * <p>
 * To be initialized by a class that needs to pass in a Profile and navigate to the DashboardActivity
 */
interface OpensActivityWithProfile {

    /**
     * Open the DashboardActivity and pass a profile to it
     *
     * @param profile Profile to be passed to Dashboard
     */
    void openActivityWith(Profile profile);
}