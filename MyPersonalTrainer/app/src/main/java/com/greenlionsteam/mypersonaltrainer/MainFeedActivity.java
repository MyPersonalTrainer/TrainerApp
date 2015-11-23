package com.greenlionsteam.mypersonaltrainer;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.greenlionsteam.mypersonaltrainer.fragments.ExercisesFragment;
import com.greenlionsteam.mypersonaltrainer.fragments.ParametersFragment;
import com.greenlionsteam.mypersonaltrainer.Models.Exercise;
import com.greenlionsteam.mypersonaltrainer.Models.ExerciseType;

import java.util.ArrayList;
import java.util.Date;

public class MainFeedActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, AddNewExerciseCallback {

    public static ArrayList<Exercise> exercises;
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private int currentWindow;
    private ExercisesFragment exercisesFragment;

    private CharSequence mTitle;
    private ExerciseListFragment exerciseListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        exerciseListFragment = new ExerciseListFragment();

        createExercise();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment fragment = null;
        currentWindow = position;
        switch(position) {
            case 0: {
                if(exerciseListFragment == null)
                    exerciseListFragment = new ExerciseListFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, exerciseListFragment).commit();
                break;
            }
            case 2:
                fragment = ParametersFragment.newInstance();
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment)
                            .commit();
                break;
                }
            case 3:
                fragment = ExercisesFragment.newInstance();
                exercisesFragment = (ExercisesFragment)fragment;
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment)
                            .commit();
                    break;
                }

        }
    }

    public void createExercise() {
        ExerciseType.getAllTypes();
        Exercise e1 = new Exercise("asdf1", ExerciseType.getType(0), new Date(2015, 5, 11, 12, 05));
        Exercise e2 = new Exercise("asdf2", ExerciseType.getType(1), new Date(2015, 5, 11, 12, 05));
        Exercise e3 = new Exercise("asdf4", ExerciseType.getType(1), new Date(2015, 5, 12, 15, 05));
        Exercise e4 = new Exercise("asdf56", ExerciseType.getType(2), new Date(2015, 5, 13, 11, 05));

        exercises = new ArrayList<>();
        exercises.add(e1);
        exercises.add(e2);
        exercises.add(e3);
        exercises.add(e4);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public void onBackPressed() {
        if(currentWindow == 3)
        {
            if(exercisesFragment != null)
            {
                exercisesFragment.GetBack();
            }
        }
        else
         super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main_feed, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addNewExercise(Exercise e) {
        //todo add new item to list
        exercises.add(e);
        showExercises();
    }

    @Override
    public void showExercises() {
        //todo show all exercises list
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, exerciseListFragment).commit();
    }

    public void showAddingScreen() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new NewExerciseFragment()).commit();
    }

}