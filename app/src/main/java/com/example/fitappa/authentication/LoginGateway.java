package com.example.fitappa.authentication;

import android.util.Log;

import com.example.fitappa.constants.DatabaseConstants;

import java.util.concurrent.atomic.AtomicReference;

import io.realm.mongodb.App;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

/**
 * This class is a gateway that accesses the database and signs in the user given their email and password
 * as credentials.
 * <p>
 * The class's methods allow users to fetch information from the database
 * <p>
 *
 * @author Uthman
 * @since 0.2
 */
class LoginGateway {
    private final AuthenticationPresenter presenter;
    private final DatabaseConstants constants;

    /**
     * Constructor that takes in an AuthenticationPresenter abstract class and initializes it
     *
     * @param presenter AuthenticationPresenter abstract class to be used to access presenter methods
     */
    LoginGateway(AuthenticationPresenter presenter) {
        this.presenter = presenter;
        constants = new DatabaseConstants();
    }

    /**
     * Log in and authenticate user given credentials with MongoDB
     *
     * @param email    email to log in with
     * @param password password to log in with
     */
    void login(String email, String password) {
        App app = new App(constants.getRealmAppID());

        Credentials emailPasswordCredentials = Credentials.emailPassword(email, password);
        AtomicReference<User> user = new AtomicReference<>();

        app.loginAsync(emailPasswordCredentials, it -> {
            if (it.isSuccess()) {
                user.set(app.currentUser());
                presenter.proceed();
            } else {
                Log.e("mongotest123: ", it.getError().getLocalizedMessage());
                presenter.setError(it.getError().getLocalizedMessage());
            }
        });

    }
}
