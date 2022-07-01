package com.example.fitappa.start;

import com.example.fitappa.constants.DatabaseConstants;
import com.example.fitappa.start.MainActivity.ViewOpener;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.User;

/**
 * This class initializes all database requirements and checks authentication for the view
 */
class MainGateway {
    private final DatabaseConstants constants;

    MainGateway(MainActivity view) {
        Realm.init(view);
        constants = new DatabaseConstants();
    }

    /**
     * Check if there is an authenticated user already. If there is, proceed to the next view.
     *
     * @param view ViewOpener to proceed when a user is already authenticated
     */
    void checkAuth(ViewOpener view) {
//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        // If user exists, skip authentication and proceed
//        if (firebaseUser != null)
//            view.proceed();

        App app = new App(new AppConfiguration.Builder(constants.getRealmAppID()).build());

        User user = app.currentUser();
        if (user != null && user.isLoggedIn())
            view.proceed();
    }
}
