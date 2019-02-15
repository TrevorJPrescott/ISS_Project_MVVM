package com.trevorpc.newstart.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.trevorpc.newstart.R;
import com.trevorpc.newstart.viewmodel.ResponseViewModel;

public class MainActivity extends AppCompatActivity {

    private ResponseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // importing the recyclerView into the ViewModel is probably not best practice
        viewModel = new ResponseViewModel(this.getApplication(),this,recyclerView);
        viewModel.fetchLocation();
    }
}
