package com.example.fitappa.authentication;

import android.util.Log;

import com.example.fitappa.constants.DatabaseConstants;
import com.example.fitappa.profile.SaveProfileGateway;
import com.example.fitappa.profile.Saveable;

import java.util.concurrent.atomic.AtomicReference;

import io.realm.mongodb.App;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

/**
 * This class is a gateway that deals with signing up a user with the database given an email, username, and password
 * <p>
 * Methods in this class move information to the database
 *
 * @author Uthman
 * @version 1.1
 */
class SignUpGateway {
    private final AuthenticationPresenter presenter;
    private final DatabaseConstants constants;

    /**
     * Constructor that takes an AuthenticationPresenter abstract class which represents a presenter for sign up
     *
     * @param presenter AuthenticationPresenter abstract class which represents a presenter for sign up
     */
    SignUpGateway(AuthenticationPresenter presenter) {
        this.presenter = presenter;
        constants = new DatabaseConstants();
    }

    /**
     * Sign up and authenticate user given credentials using Firebase
     *
     * @param email    email to sign up with
     * @param username username to create new profile
     * @param password password to sign up with
     */
    void signUp(String email, String username, String password) {
        App app = new App(constants.getRealmAppID());
        app.getEmailPassword().registerUserAsync(email, password, it -> {
            if (it.isSuccess()) {
                login(email, username, password, app);
            } else {
                Log.e("mongotest123: ", it.getError().getLocalizedMessage());
                presenter.setError();
            }
        });
    }

    /**
     * Login to app after signing up successfully
     *
     * @param email    email to sign in with
     * @param username username to assign to profile after logging in
     * @param password password to sign in with
     * @param app      App to sign in to
     */
    private void login(String email, String username, String password, App app) {
        Credentials emailPasswordCredentials = Credentials.emailPassword(email, password);
        AtomicReference<User> user = new AtomicReference<>();

        app.loginAsync(emailPasswordCredentials, callback -> {
            if (callback.isSuccess()) {
                User currentUser = app.currentUser();
                if (currentUser == null) {
                    Log.e("mongotest123: ", "current user null: " + callback.getError().getLocalizedMessage());
                    return;
                }
                user.set(currentUser);

                // Save the data of the profile to the database
                Saveable gateway = new SaveProfileGateway(email, username, currentUser.getId());
                // pass null since we don't have access to the full profile object
                gateway.save(null);
                // proceed to update activity

                presenter.proceed();
            } else {
                Log.e("mongotest123: ", callback.getError().getLocalizedMessage());
            }
        });
    }
}
