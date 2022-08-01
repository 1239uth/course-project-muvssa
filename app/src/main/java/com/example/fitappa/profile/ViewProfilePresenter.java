package com.example.fitappa.profile;

import com.example.fitappa.constants.DatabaseConstants;

import java.util.Objects;

import io.realm.mongodb.App;

/**
 * This activity class represents a users profile on the UI
 * <p>
 * Methods in this class load information from the presenter and send information users input to the backend
 * <p>
 * Documentation specifies what the methods do
 *
 * @author Souren
 * @author Uthman
 * @author Abdulah
 * @since 0.6
 */

class ViewProfilePresenter implements LoadsProfile {
    private final View view;
    private Profile profile;

    /**
     * Constructor that sets up the view and loads the profile
     *
     * @param view View to setup and load profile for
     */
    ViewProfilePresenter(View view) {
        this.view = view;
        final String PAGE_TITLE = "profile";
        this.view.updateAppBarTitle(PAGE_TITLE);
        this.view.setupElements();

        Loadable gateway = new LoadProfileGateway(this);
        gateway.load();
    }

    /**
     * This method should be called when the save button is pressed by the user for their settings.
     * Saves all data for the profile and returns to the dashboard view.
     *
     * @param firstName profile's first name
     * @param lastName  profile's last name
     * @param weight    profile's weight
     * @param height    profile's height
     */
    void saveSettings(String firstName, String lastName, String weight, String height) {
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setHeight(height);
        profile.setWeight(weight);

        Saveable gateway = new SaveProfileGateway();
        gateway.save(profile);

        view.backToDashboard();
    }

    /**
     * Log out the current user/profile and return to the starting main view
     */
    void logout() {
        App app = new App(new DatabaseConstants().getRealmAppID());
        Objects.requireNonNull(app.currentUser()).logOutAsync(callback -> {
            if (callback.isSuccess())
                view.goToMain();
        });
    }


    /**
     * Receive the profile data from the database and send it to the view to load in the UI.
     *
     * @param profile Profile object received from the database
     */
    @Override
    public void loadProfile(Profile profile) {
        this.profile = profile;
        view.setup(profile.getUsername(), profile.getFirstName(), profile.getLastName(), profile.getWeight(), profile.getHeight());
    }

    interface View {
        void updateAppBarTitle(String title);

        void setup(String username, String firstName, String lastName, String weight, String height);

        void setupElements();

        void goToMain();

        void backToDashboard();
    }
}