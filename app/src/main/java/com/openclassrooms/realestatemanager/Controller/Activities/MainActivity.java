package com.openclassrooms.realestatemanager.Controller.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.openclassrooms.realestatemanager.Controller.Fragments.DetailFragment;
import com.openclassrooms.realestatemanager.Controller.Fragments.MainFragment;
import com.openclassrooms.realestatemanager.R;

public class MainActivity extends AppCompatActivity {

    private MainFragment mainFragment;
    private DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureMainFragment();
        this.configureDetailFragment();
    }

    // --------------
    // FRAGMENTS
    // --------------

    private void configureMainFragment(){
        // Get FragmentManager and Try to find existing instance of fragment in FrameLayout container
        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_main);

        if (mainFragment == null) {
            // Create new main fragment
            mainFragment = new MainFragment();
            // Add it to FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_main, mainFragment)
                    .commit();
        }
    }
    private void configureDetailFragment(){
        detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_detail);

        // We only add DetailFragment in Tablet mode (If found frame_layout_detail)
        if (detailFragment == null && findViewById(R.id.frame_layout_detail) != null) {
            detailFragment = new DetailFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_detail, detailFragment)
                    .commit();
        }
    }

}
