package com.example.fitappa.Exercise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitappa.Exercise.Exercise.ExerciseTemplate;
import com.example.fitappa.R;
import com.example.fitappa.Workout.CRUD.ViewWorkoutActivity;
import com.example.fitappa.Workout.Core.WorkoutTemplate;

import java.util.List;
import java.util.Objects;

/**
 * This class is a view class meant to open the activity_add_exercise xml, a GUI which allows users to add
 * new exercises
 *
 * The method in the class allow the user to interact and create new exercises
 *
 * The documentation in this class give a specification on what the methods do
 *
 * @author Abdullah
 * @since 0.6
 */


public class AddExerciseActivity extends AppCompatActivity implements AddExercisePresenter.View {
    private LinearLayout exerciseLayout;
    private AddExercisePresenter presenter;

    /**
     * This method is called when the activity starts.
     *
     * @param savedInstanceState contains the data it was most recently supplied with by onSaveInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        this.presenter = new AddExercisePresenter(this,
                getIntent().getSerializableExtra("workoutObj"),
                getIntent().getSerializableExtra("exercises"));
        this.exerciseLayout = findViewById(R.id.ExerciseLayout);

    }

    /**
     * Go to Create New Exercise Activity
     */
    private void openCreateNewExercise() {
        Intent addNewExercise = new Intent(this, CreateNewExerciseActivity.class);
        startActivity(addNewExercise);
    }

    /**
     * This method displays all the exercises and updates it in the
     * ExerciseLayout view component.
     */
    public void displayExercises(List<ExerciseTemplate> exerciseTemplates) {
        for (ExerciseTemplate exerciseTemplate : exerciseTemplates) {
            updateExerciseLayout(exerciseTemplate);
        }
    }

    /**
     * This method updates the ExerciseLayout view component on AddExerciseActivity
     * by adding the Exercise to the layout.
     *
     * @param exerciseTemplate represents the Exercise object to add to the ExerciseLayout
     */
    private void updateExerciseLayout(ExerciseTemplate exerciseTemplate) {
        Button button = new Button(this);
        button.setText(exerciseTemplate.getName());
        button.setOnClickListener(view -> goBackToWorkout(exerciseTemplate));

        exerciseLayout.addView(button);
    }

    /**
     * This method opens the ViewWorkoutActivity view.
     *
     * @param exerciseTemplate represents the exercise to be returned to ViewWorkoutActivity
     */
    private void goBackToWorkout(ExerciseTemplate exerciseTemplate) {
        Intent viewWorkout = new Intent(this, ViewWorkoutActivity.class);
        /*viewWorkout.putExtra("workoutObj", this.workoutTemplate);
        viewWorkout.putExtra("exercise", exerciseTemplate);
        setResult(RESULT_OK, viewWorkout);
        finish();*/
        startActivity(viewWorkout);
    }

    @Override
    public void updateAppBarTitle(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);

    }
}