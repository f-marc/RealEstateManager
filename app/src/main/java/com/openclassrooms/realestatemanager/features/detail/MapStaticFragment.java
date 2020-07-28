package com.openclassrooms.realestatemanager.features.detail;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.openclassrooms.realestatemanager.R;

import java.io.IOException;
import java.util.List;

public class MapStaticFragment extends Fragment implements OnMapReadyCallback {

    private String address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_static, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_static);
        mapFragment.getMapAsync(this);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            address = bundle.getString("address");
        }

        Log.i("testadd", address);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng latLng = getLocationFromAddress(getContext(), address);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));
        googleMap.addMarker(new MarkerOptions().position(latLng).title(address));
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            try {
                // May throw an IndexOutOfBoundsException
                Address location = address.get(0);
                p1 = new LatLng(location.getLatitude(), location.getLongitude());
            } catch (IndexOutOfBoundsException ex) {
                // If strAddress isn't correct
                p1 = new LatLng(0.0, 0.0);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return p1;
    }
}
