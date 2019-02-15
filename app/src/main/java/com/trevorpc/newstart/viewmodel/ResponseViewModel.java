package com.trevorpc.newstart.viewmodel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.trevorpc.newstart.adapters.ResponseAdapter;
import com.trevorpc.newstart.model.ResponseRepository;
import com.trevorpc.newstart.model.object.FullResponse;
import com.trevorpc.newstart.model.object.Response;

import java.util.List;

public class ResponseViewModel extends AndroidViewModel {
    private ResponseRepository repository;
    private FullResponse listResponse;

    private Context context;

    private LocationManager manager;
    private LocationListener listener;
    private Double longitude =45.5;
    private Double latitude = 45.5;
    private Activity activity;

    private List<Response> responseList;
    private ResponseAdapter adapter;

    RecyclerView recyclerView;

    public ResponseAdapter getAdapter() {
        return adapter;
    }

    private static final String TAG = "TAG";

    public ResponseViewModel(@NonNull Application application, Activity activity, RecyclerView rv) {
        super(application);
        repository = new ResponseRepository(application);
        this.activity = activity;

        context = application.getApplicationContext();
        Log.d(TAG, "ResponseViewModel: created ");
        manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        recyclerView = rv;
        // dagger 2 potential?
        ResponseAdapter adapter = new ResponseAdapter(context,responseList);

    }


    @SuppressLint("MissingPermission")
    public void fetchLocation() {
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                Log.d(TAG, "Location: " + latitude + " " + longitude);
                repository.fetchData(latitude,longitude);
                responseList = repository.getResponses();

                adapter = new ResponseAdapter(context,responseList);
                recyclerView.setAdapter(adapter);

            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d(TAG, "onStatusChanged: ");
            }
            @Override
            public void onProviderEnabled(String provider) {

            }
            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        // might be able to reduce this to only fine location
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        if(ActivityCompat.checkSelfPermission(context,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_DENIED)
            manager.requestLocationUpdates("gps",5000,0,listener);
        } else {
            manager.requestLocationUpdates("gps", 5000, 0, listener);
        }
    }

    public List<Response> getResponseList() {
        Log.d(TAG, "getResponseList: " + responseList.get(1).getDuration().toString());
        return responseList;
    }


}
