package com.openclassrooms.realestatemanager.features.add;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.openclassrooms.realestatemanager.R;

public class AddEstateActivity extends AppCompatActivity {

    public static final String EXTRA_TYPE = "com.openclassrooms.realestatemanager.EXTRA_TYPE";
    public static final String EXTRA_PRICE = "com.openclassrooms.realestatemanager.EXTRA_PRICE";
    public static final String EXTRA_ADDRESS = "com.openclassrooms.realestatemanager.EXTRA_ADDRESS";
    public static final String EXTRA_SURFACE = "com.openclassrooms.realestatemanager.EXTRA_SURFACE";
    public static final String EXTRA_INTEREST = "com.openclassrooms.realestatemanager.EXTRA_INTEREST";
    public static final String EXTRA_AGENT = "com.openclassrooms.realestatemanager.EXTRA_AGENT";
    public static final String EXTRA_DESCRIPTION = "com.openclassrooms.realestatemanager.EXTRA_DESCRIPTION";
    public static final String EXTRA_ROOMS = "com.openclassrooms.realestatemanager.EXTRA_ROOMS";

    private EditText editTextType;
    private EditText editTextPrice;
    private EditText editTextAddress;
    private EditText editTextSurface;
    private EditText editTextInterest;
    private EditText editTextAgent;
    private EditText editTextDescription;
    private NumberPicker numberPickerRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_estate);

        editTextType = findViewById(R.id.activity_add_type_edit);
        editTextPrice = findViewById(R.id.activity_add_price_edit);
        editTextAddress = findViewById(R.id.activity_add_address_edit);
        editTextSurface = findViewById(R.id.activity_add_surface_edit);
        editTextInterest = findViewById(R.id.activity_add_interest_edit);
        editTextAgent = findViewById(R.id.activity_add_agent_edit);
        editTextDescription = findViewById(R.id.activity_add_description_edit);
        numberPickerRooms = findViewById(R.id.activity_add_rooms_number_picker);

        numberPickerRooms.setMinValue(1);
        numberPickerRooms.setMaxValue(99);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Estate");
    }

    private void saveEstate() {
        String type = editTextType.getText().toString();
        String price = editTextPrice.getText().toString();
        String address = editTextAddress.getText().toString();
        String surface = editTextSurface.getText().toString();
        String interest = editTextInterest.getText().toString();
        String agent = editTextAgent.getText().toString();
        String description = editTextDescription.getText().toString();
        int rooms = numberPickerRooms.getValue();

        if (type.trim().isEmpty() || price.trim().isEmpty() || address.trim().isEmpty()
                || surface.trim().isEmpty() || agent.trim().isEmpty()) {
            Toast.makeText(this, "Please complete all fields with a red asterisk", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TYPE, type);
        data.putExtra(EXTRA_PRICE, price);
        data.putExtra(EXTRA_ADDRESS, address);
        data.putExtra(EXTRA_SURFACE, surface);
        data.putExtra(EXTRA_INTEREST, interest);
        data.putExtra(EXTRA_AGENT, agent);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_ROOMS, rooms);

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_estate_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_estate:
                saveEstate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

