package com.openclassrooms.realestatemanager.features.add;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.realestatemanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class AddEstateActivity extends AppCompatActivity {

    private static final String PERMS = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final int RC_IMAGE_PERMS = 100;
    private static final int RC_CHOOSE_PHOTO = 200;
    private Uri uriImageSelected;

    public static final String EXTRA_TYPE = "com.openclassrooms.realestatemanager.EXTRA_TYPE";
    public static final String EXTRA_PRICE = "com.openclassrooms.realestatemanager.EXTRA_PRICE";
    public static final String EXTRA_ADDRESS = "com.openclassrooms.realestatemanager.EXTRA_ADDRESS";
    public static final String EXTRA_SURFACE = "com.openclassrooms.realestatemanager.EXTRA_SURFACE";
    public static final String EXTRA_INTEREST = "com.openclassrooms.realestatemanager.EXTRA_INTEREST";
    public static final String EXTRA_AGENT = "com.openclassrooms.realestatemanager.EXTRA_AGENT";
    public static final String EXTRA_DESCRIPTION = "com.openclassrooms.realestatemanager.EXTRA_DESCRIPTION";
    public static final String EXTRA_ROOMS = "com.openclassrooms.realestatemanager.EXTRA_ROOMS";

    @BindView(R.id.activity_add_type_edit) EditText editTextType;
    @BindView(R.id.activity_add_price_edit) EditText editTextPrice;
    @BindView(R.id.activity_add_address_edit) EditText editTextAddress;
    @BindView(R.id.activity_add_surface_edit) EditText editTextSurface;
    @BindView(R.id.activity_add_rooms_number_edit) EditText editTextRooms;
    @BindView(R.id.activity_add_interest_edit) EditText editTextInterest;
    @BindView(R.id.activity_add_agent_edit) EditText editTextAgent;
    @BindView(R.id.activity_add_description_edit) EditText editTextDescription;
    @BindView(R.id.activity_add_estate_button) Button addEstate;
    @BindView(R.id.activity_add_thumbnail_image) ImageView addImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_estate);
        ButterKnife.bind(this);

        addEstate.setOnClickListener(v -> saveEstate());
        addImage.setOnClickListener(v -> onClickAddFile());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Calling the appropriate method after activity result
        this.handleResponse(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(RC_IMAGE_PERMS)
    public void onClickAddFile() {
        if (!EasyPermissions.hasPermissions(this, PERMS)) {
            EasyPermissions.requestPermissions(this, getString(R.string.popup_title_permission_files_access), RC_IMAGE_PERMS, PERMS);
            return;
        }
        // Launch an "Selection Image" Activity
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RC_CHOOSE_PHOTO);
    }

    // Handle activity response (after user has chosen or not a picture)
    private void handleResponse(int requestCode, int resultCode, Intent data){
        if (requestCode == RC_CHOOSE_PHOTO) {
            if (resultCode == RESULT_OK) { // SUCCESS
                this.uriImageSelected = data.getData();
                Glide.with(this) // SHOWING PREVIEW OF IMAGE
                        .load(this.uriImageSelected)
                        .centerCrop()
                        .into(this.addImage);
            } else {
                Toast.makeText(this, getString(R.string.toast_title_no_image_chosen), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveEstate() {
        String type = editTextType.getText().toString();
        String price = editTextPrice.getText().toString();
        String address = editTextAddress.getText().toString();
        String surface = editTextSurface.getText().toString();
        String interest = editTextInterest.getText().toString();
        String agent = editTextAgent.getText().toString();
        String description = editTextDescription.getText().toString();
        String rooms = editTextRooms.getText().toString();

        if (type.trim().isEmpty() || price.trim().isEmpty() || address.trim().isEmpty()
                || surface.trim().isEmpty() || agent.trim().isEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.complete_fields), Toast.LENGTH_SHORT).show();
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

}

