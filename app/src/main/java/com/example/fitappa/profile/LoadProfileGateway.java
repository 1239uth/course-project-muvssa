package com.example.fitappa.profile;

import com.example.fitappa.constants.DatabaseConstants;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.User;

/**
 * This class is used for retrieving a profile from the database given a username, and updating
 * the presenter with the new profile if it was found
 * <p>
 * The methods in this class work with the database to retrieve information
 * <p>
 * The Documentation explains the specification of each method
 *
 * @author Uthman
 * @since 0.3
 */
class LoadProfileGateway implements Loadable {
    private final LoadsProfile presenter;

    /**
     * Constructor that takes in an interface that allows this class to update the presenter with
     * a retrieved profile
     *
     * @param presenter Interface that is used to update the presenter
     */
    LoadProfileGateway(LoadsProfile presenter) {
        this.presenter = presenter;
    }

    /**
     * Get a profile from the database and return it
     */
    @Override
    public void load() {
        DatabaseConstants constants = new DatabaseConstants();

        App app = new App(constants.getRealmAppID());
        User user = app.currentUser();
        if (user == null) return;

        Realm realm = Realm.getDefaultInstance();
        Profile profile = realm.where(Profile.class).equalTo("id", user.getId()).findFirstAsync();
        profile.addChangeListener(initializeListener -> {
            Profile returnProfile = new Profile(profile.getEmail(), profile.getUsername(), profile.getId());
            returnProfile.setFirstName(profile.getFirstName());
            returnProfile.setLastName(profile.getLastName());
            returnProfile.setHeight(profile.getHeight());
            returnProfile.setWeight(profile.getWeight());
            profile.removeAllChangeListeners();

            presenter.loadProfile(returnProfile);
        });
    }
}
