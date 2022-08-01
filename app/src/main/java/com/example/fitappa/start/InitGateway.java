package com.example.fitappa.start;

import android.content.Context;

import com.example.fitappa.constants.DatabaseConstants;
import com.example.fitappa.start.MainActivity.ViewOpener;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.User;

/**
 * This class initializes all database requirements and checks authentication for the view
 */
class InitGateway {
    private final DatabaseConstants constants;

    /**
     * Constructor that takes the main view's context to initialize database
     *
     * @param context Context of the main view
     */
    InitGateway(Context context) {
        initializeRealm(context);
        constants = new DatabaseConstants();
    }

    /**
     * Initialize and set configurations necessary for MongoDB Realm
     *
     * @param context Context of initial view in app
     */
    private void initializeRealm(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .build();
        Realm.setDefaultConfiguration(config);
    }

    /**
     * Check if there is an authenticated user already. If there is, proceed to the next view.
     *
     * @param view ViewOpener to proceed when a user is already authenticated
     */
    void checkAuth(ViewOpener view) {
        App app = new App(new AppConfiguration.Builder(constants.getRealmAppID()).build());

        User user = app.currentUser();
        if (user != null && user.isLoggedIn())
            view.proceed();
    }
}
