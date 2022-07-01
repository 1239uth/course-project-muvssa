package com.example.fitappa.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitappa.R;
import com.example.fitappa.profile.DashboardActivity;

import java.util.Objects;

/**
 * This activity is activated when the user selects login from the MainActivity and wants to log in to an account
 * <p>
 * The class's methods allow the user to implement login info
 * <p>
 *
 * @author Uthman
 * @since 0.2
 */
public class LoginActivity extends AppCompatActivity implements OpensActivityWithProfile {
    private LoginPresenter presenter;

    /**
     * This method is called when the activity starts.
     *
     * @param savedInstanceState contains the data it was most recently supplied with by onSaveInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        presenter = new LoginPresenter(this);

        setup();
    }

    /**
     * Setup the text fields and buttons for this activity
     */
    private void setup() {
        EditText passwordField = findViewById(R.id.PasswordField);
        EditText emailField = findViewById(R.id.EmailField);
        TextView loginBtn = findViewById(R.id.LogInBtn);

        loginBtn.setOnClickListener(v -> presenter.tryLogin(emailField, passwordField));
    }

    /**
     * This method opens the DashboardActivity.
     */
    @Override
    public void openActivity() {
        finish();
        Intent dashboard = new Intent(this, DashboardActivity.class);
        startActivity(dashboard);
    }

    /**
     * Display an error message given a message
     *
     * @param message String message to be displayed as error on call
     */
    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}