package com.openclassrooms.realestatemanager.features.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.openclassrooms.realestatemanager.data.model.EstateViewModel;
import com.openclassrooms.realestatemanager.features.detail.DetailFragment;
import com.openclassrooms.realestatemanager.R;

public class MainActivity extends AppCompatActivity implements MainFragment.CallbackClick {

    public static final int ADD_ESTATE_REQUEST = 1;

    private MainFragment mainFragment;
    private DetailFragment detailFragment;
    private EstateViewModel estateViewModel;

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    // --------------
    // CALLBACK
    // --------------
    @Override
    public void onItemClicked(long id) {
        Log.i("testclick", "" + id);
        detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        detailFragment.setArguments(bundle);

        if (findViewById(R.id.frame_layout_detail) != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout_detail, detailFragment)
                    .commit();
        }
        else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout_main, detailFragment)
                    .commit();
        }
    }
}
