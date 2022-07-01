package com.example.fitappa.authentication;

import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * This is a presenter for the LoginActivity.
 * <p>
 * This class is an authentication presenter since it is a presenter for an activity that
 * authenticates the user.
 * <p>
 * It also determines what error message to display to the user.
 * <p>
 *
 * @author Uthman
 * @since 0.2
 */
class LoginPresenter extends AuthenticationPresenter {
    private final LoginGateway gateway;

    /**
     * Constructor that takes an AuthenticationActivity interface and initializes it
     *
     * @param view AuthenticationActivity interface which is implemented by an Activity
     */
    LoginPresenter(AuthenticationActivity view) {
        super(view);
        this.gateway = new LoginGateway(this);
    }

    /**
     * Try to log in the user given an email and password by checking if they match a criteria. If they match, then
     * pass the email and password to a gateway to log in.
     *
     * @param emailText    EditText representing the text for email that the user entered
     * @param passwordText EditText representing the text for password that the user entered
     */
    void tryLogin(EditText emailText, EditText passwordText) {
        // Convert EditText to String
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        // build verification regex to verify email
        String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pat = Pattern.compile(emailRegex);

        // Set error if email or password are empty or invalid
        if (email.isEmpty()) {
            emailText.setError("Please fill out email");
            emailText.requestFocus();
            return;
        } else if (!pat.matcher(email).matches()) {
            emailText.setError("Please enter a valid email");
            emailText.requestFocus();
            return;
        } else if (password.isEmpty()) {
            passwordText.setError("Please fill out password");
            passwordText.requestFocus();
            return;
        }

        gateway.login(email, password);
    }

    /**
     * Set an error when the database fails to retrieve the profile
     */
    @Override
    public void setError(String message) {
        view.showErrorMessage(message);
    }

}
