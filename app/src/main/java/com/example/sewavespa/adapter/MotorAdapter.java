package com.example.sewavespa.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sewavespa.R;
import com.example.sewavespa.SewaActivity;
import com.example.sewavespa.auth.login.LoginActivity;
import com.example.sewavespa.model.motor.MotorDataItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MotorAdapter extends RecyclerView.Adapter<MotorAdapter.ViewHolder> {

    private String baseimg_url = "https://nasywa-snack.store/storage/motor/";

    private List<MotorDataItem> dataItems;

    public MotorAdapter(List<MotorDataItem> motorDataItemList) {
        this.dataItems = motorDataItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_motor, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url = baseimg_url+dataItems.get(position).getImage();
        String name = dataItems.get(position).getNama();
        String judul = dataItems.get(position).getNama();
        String subjudul = dataItems.get(position).getHarga();
        Picasso.get()
                .load(""+baseimg_url+dataItems.get(position).getImage())
                .centerCrop()
                .fit()
                .into(holder.imageView);
        holder.title.setText(judul);
        holder.subtitle.setText(subjudul);
        holder.btnSewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("my-app", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.apply();

                if(sharedPreferences.getString("login", null).equals("")){
                    Intent i = new Intent(view.getContext(), LoginActivity.class);
                    view.getContext().startActivity(i);
                } else {
                    Intent i = new Intent(view.getContext(), SewaActivity.class);
                    i.putExtra("nama", name);
                    i.putExtra("harga", subjudul);
                    view.getContext().startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        ImageView imageView;
        TextView title, subtitle;
        Button btnSewa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card_vespa);
            imageView = itemView.findViewById(R.id.image_card);
            title = itemView.findViewById(R.id.title_card);
            subtitle = itemView.findViewById(R.id.price_card);
            btnSewa = itemView.findViewById(R.id.btn_pesan2);
        }
    }
}
