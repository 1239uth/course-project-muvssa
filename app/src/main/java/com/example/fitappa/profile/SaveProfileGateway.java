package com.example.fitappa.profile;

import io.realm.Realm;

/**
 * This is a gateway class to Firebase which lets a class save an object
 * <p>
 * Methods in this class help move profiles to the database
 * <p>
 * Documentation specifies what the methods do
 *
 * @author Uthman
 * @since 1.2
 */
public class SaveProfileGateway implements Saveable {
    private Profile profile;

    SaveProfileGateway() {
    }

    /**
     * Create a new profile with given parameters
     *
     * @param email    email of user
     * @param username username of user
     * @param uniqueID unique identifier representing user
     */
    public SaveProfileGateway(String email, String username, String uniqueID) {
        profile = new Profile(email, username, uniqueID);
    }

    /**
     * Save object into some database
     *
     * @param o object to be saved
     */
    @Override
    public void save(Object o) {
        if (o != null) {
            this.profile = (Profile) o;
        }

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(transaction -> transaction.insertOrUpdate(profile));
    }
}
