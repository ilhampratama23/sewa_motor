package com.example.sewavespa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sewavespa.auth.login.LoginActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SewaActivity extends AppCompatActivity {

    public static final String login = "";

    String nama, no_hp, antar, kembali, tgl_pinjam, tgl_kembali, motor, bayar;
    EditText namaSewa, hpSewa, lokAntar, lokBalik, tglPinjam, tglKembali;
    Spinner pilihBayar;
    TextView namaMotor;
    Button btnSewa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sewa);

        Intent intent = getIntent();
        String motor_name = intent.getStringExtra("nama");
        String harga = intent.getStringExtra("harga");

        Toast.makeText(this, ""+harga, Toast.LENGTH_SHORT).show();

        namaSewa = findViewById(R.id.input_nama);
        hpSewa = findViewById(R.id.input_telphone);
        lokAntar = findViewById(R.id.input_pengantaran);
        lokBalik = findViewById(R.id.input_pengembalian);
        tglPinjam = findViewById(R.id.tanggal_pinjam);
        tglKembali = findViewById(R.id.tanggal_pengembalian);
        namaMotor = findViewById(R.id.nama_motor);
        pilihBayar = findViewById(R.id.spinner);
        btnSewa = findViewById(R.id.btn_sewa);

        namaMotor.setText(motor_name);

        final Calendar currentdate = Calendar.getInstance();

        tglPinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SewaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String datePinjam = ""+i2+"-"+(i1+1)+"-"+i;
                        tglPinjam.setText(datePinjam);
                    }
                }, currentdate.get(Calendar.YEAR), currentdate.get(Calendar.MONTH), currentdate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        tglKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SewaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String dateKembali = ""+i2+"-"+(i1+1)+"-"+i;
                        tglKembali.setText(dateKembali);
                    }
                }, currentdate.get(Calendar.YEAR), currentdate.get(Calendar.MONTH), currentdate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        btnSewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = namaSewa.getText().toString();
                no_hp = hpSewa.getText().toString();
                antar = lokAntar.getText().toString();
                kembali = lokBalik.getText().toString();
                tgl_pinjam = tglPinjam.getText().toString();
                tgl_kembali = tglKembali.getText().toString();
                motor = motor_name;
                bayar = pilihBayar.getSelectedItem().toString();
                sewa(nama, no_hp, antar, kembali, tgl_pinjam, tgl_kembali, motor, bayar, harga);
            }
        });
    }

    private void sewa(String nama, String no_hp, String antar, String kembali, String tgl_pinjam, String tgl_kembali, String motor, String bayar, String harga) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date dateBefore = simpleDateFormat.parse(tgl_pinjam);
            Date dateAfter = simpleDateFormat.parse(tgl_kembali);
            long difference = dateAfter.getTime() - dateBefore.getTime();
            float daysBetween = (difference / (1000*60*60*24));
            int days = (int) daysBetween;
            String hari = String.valueOf(days);

            Intent i = new Intent(SewaActivity.this, KonfirmasiActivity.class);
            i.putExtra("nama", nama);
            i.putExtra("no_hp", no_hp);
            i.putExtra("antar", antar);
            i.putExtra("kembali", kembali);
            i.putExtra("tgl_pinjam", tgl_pinjam);
            i.putExtra("tgl_kembali", tgl_kembali);
            i.putExtra("motor", motor);
            i.putExtra("bayar", bayar);
            i.putExtra("hari", hari);
            i.putExtra("harga", harga);
            startActivity(i);
        } catch (ParseException except) {
            except.printStackTrace();
        }

    }
}