package com.example.fitappa.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitappa.R;
import com.example.fitappa.constants.DatabaseConstants;
import com.example.fitappa.routine.StartWorkoutFromRoutinesActivity;
import com.example.fitappa.start.MainActivity;
import com.example.fitappa.workout.workout_log.WorkoutLogActivity;

import java.util.Objects;

import io.realm.mongodb.App;
import io.realm.mongodb.User;

/**
 * This class is a view class meant to open the activity_dashboard xml, a hub between other pages in the app
 * <p>
 * The method in the class allow the user to go to ViewProfileActivity, MainActivity, or StartWorkoutFromRoutinesActivity
 * <p>
 * The documentation in this class give a specification on what the methods do
 *
 * @author Abdullah
 * @since 0.6
 */

public class DashboardActivity extends AppCompatActivity implements DashboardPresenter.View {

    /**
     * This method is called when the activity starts.
     *
     * @param savedInstanceState contains the data it was most recently supplied with by onSaveInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        new DashboardPresenter(this);
    }

    @Override
    public void updateAppBarTitle(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    @Override
    public void setupButtons() {
        Button logoutBtn = findViewById(R.id.LogoutBtn);
        logoutBtn.setOnClickListener(v -> signOut());

        Button startWorkoutBtn = findViewById(R.id.startWorkoutNav);
        startWorkoutBtn.setOnClickListener(v -> openStartWorkout());

        Button openProfileBtn = findViewById(R.id.goToProfilesBtn);
        openProfileBtn.setOnClickListener(v -> openProfile());

        Button btn = findViewById(R.id.WorkoutLogsBtn);
        btn.setOnClickListener(v -> openWorkoutLog());
    }

    private void openWorkoutLog() {
        startActivity(new Intent(this, WorkoutLogActivity.class));
    }

    /**
     * Sign out the current user
     */
    private void signOut() {
        // Remote
//        FirebaseAuth.getInstance().signOut();

        // TODO: Separate this into a gateway
        DatabaseConstants constants = new DatabaseConstants();
        App app = new App(constants.getRealmAppID());

        User user = app.currentUser();
        if (user == null) return;

        user.logOutAsync(it -> {
            if (!it.isSuccess())
                Log.e("mongotest123: ", it.getError().getLocalizedMessage());
        });

        goBackToMain();
    }

    /**
     * This method opens the ProfileActivity View
     */
    private void openProfile() {
        Intent profile1 = new Intent(this, ViewProfileActivity.class);
        startActivity(profile1);
    }

    /**
     * This method opens the MainActivity View
     */
    private void goBackToMain() {
        finish();
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

    /**
     * This method opens the StartWorkout view
     */
    private void openStartWorkout() {
        Intent startWorkout = new Intent(this, StartWorkoutFromRoutinesActivity.class);
        startActivity(startWorkout);
    }

    /**
     * This method overrides the functionality of the android back button and does nothing so that the user
     * cannot go back to the MainActivity or login/signup activities. The only way they can return is through
     * the logout button.
     */
    @Override
    public void onBackPressed() {
        // Do nothing so you cannot go back from this activity
    }
}