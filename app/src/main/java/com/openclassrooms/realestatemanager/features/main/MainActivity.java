package com.openclassrooms.realestatemanager.features.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.realestatemanager.data.model.Estate;
import com.openclassrooms.realestatemanager.data.model.EstateViewModel;
import com.openclassrooms.realestatemanager.features.add.AddEstateActivity;
import com.openclassrooms.realestatemanager.features.detail.DetailFragment;
import com.openclassrooms.realestatemanager.R;

public class MainActivity extends AppCompatActivity {

    private EstateViewModel estateViewModel;

    private MainFragment mainFragment;
    private DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        estateViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(this.getApplication()))
                .get(EstateViewModel.class);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*if (requestCode == ADD_ESTATE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1);

            Estate estate = new Estate(title, description, priority);
            estateViewModel.insert(estate);

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }*/
    }

}
