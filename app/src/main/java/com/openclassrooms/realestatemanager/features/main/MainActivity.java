package com.openclassrooms.realestatemanager.features.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.openclassrooms.realestatemanager.data.model.Estate;
import com.openclassrooms.realestatemanager.data.model.EstateViewModel;
import com.openclassrooms.realestatemanager.features.add.AddEstateActivity;
import com.openclassrooms.realestatemanager.features.detail.DetailFragment;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.features.map.MapActivity;
import com.openclassrooms.realestatemanager.utils.Utils;

public class MainActivity extends AppCompatActivity implements MainFragment.CallbackClick {

    public static final int ADD_ESTATE_REQUEST = 1;

    private Toolbar toolbar;
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

        this.configureToolbar();
        this.configureMainFragment();
        this.configureDetailFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void configureToolbar() {
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));
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
    public void onBackPressed() {
        if (findViewById(R.id.frame_layout_detail) == null) {
            int count = getSupportFragmentManager().getBackStackEntryCount();
            if (count == 0) {
                super.onBackPressed();
            } else {
                getSupportFragmentManager().popBackStack();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_add:
                Intent addIntent = new Intent(this, AddEstateActivity.class);
                startActivityForResult(addIntent, ADD_ESTATE_REQUEST);
                return true;
            case R.id.toolbar_map:
                Intent mapIntent = new Intent(this, MapActivity.class);
                startActivity(mapIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ESTATE_REQUEST && resultCode == RESULT_OK) {
            String image = "";
            String type = data.getStringExtra(AddEstateActivity.EXTRA_TYPE);
            String price = data.getStringExtra(AddEstateActivity.EXTRA_PRICE);
            String address = data.getStringExtra(AddEstateActivity.EXTRA_ADDRESS);
            String surface = data.getStringExtra(AddEstateActivity.EXTRA_SURFACE);
            String rooms = data.getStringExtra(AddEstateActivity.EXTRA_ROOMS); // A AJOUTER
            String interest = data.getStringExtra(AddEstateActivity.EXTRA_INTEREST); // A AJOUTER
            String agent = data.getStringExtra(AddEstateActivity.EXTRA_AGENT);
            String description = data.getStringExtra(AddEstateActivity.EXTRA_DESCRIPTION); // A AJOUTER
            String date = Utils.getTodayDate();

            Estate estate = new Estate(image, type, price, address, surface, agent, date);
            estateViewModel.insert(estate);
        }
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
                    .addToBackStack("detail")
                    .commit();
        }
    }
}
