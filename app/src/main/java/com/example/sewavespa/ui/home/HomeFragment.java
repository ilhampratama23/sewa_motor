package com.example.sewavespa.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.example.sewavespa.R;
import com.example.sewavespa.adapter.MotorAdapter;
import com.example.sewavespa.api.ApiClient;
import com.example.sewavespa.api.ApiInterface;
import com.example.sewavespa.model.motor.Motor;
import com.example.sewavespa.model.motor.MotorDataItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    public static final String token = "token";

    ImageSlider imageSlider;
    RecyclerView listMotor;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        listMotor = view.findViewById(R.id.motor_list);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(view.getContext(), 2);
        listMotor.setHasFixedSize(true);
        listMotor.setLayoutManager(mLayoutManager);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Motor> motorDataCall = apiInterface.motorShowResponse();
        motorDataCall.enqueue(new Callback<Motor>() {
            @Override
            public void onResponse(Call<Motor> call, Response<Motor> response) {
                List<MotorDataItem> dataItems = response.body().getData();
                listMotor.setAdapter(new MotorAdapter(dataItems));
                Log.d("size", ""+dataItems.size());
            }

            @Override
            public void onFailure(Call<Motor> call, Throwable t) {

            }
        });

        return view;
    }
}