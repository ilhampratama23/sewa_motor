package com.example.sewavespa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.midtrans.sdk.corekit.BuildConfig;
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.TransactionRequest;
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.corekit.models.ItemDetails;
import com.midtrans.sdk.corekit.models.snap.CreditCard;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;

import java.util.ArrayList;

public class KonfirmasiActivity extends AppCompatActivity implements TransactionFinishedCallback{

    TextView textNama, textHp, textAntar, textBalek, textTglSewa, textTglBalek, textTypeMotor, textTotal, textBayar;
    Button btnBayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);

        textNama = findViewById(R.id.textNama);
        textHp = findViewById(R.id.textHp);
        textAntar = findViewById(R.id.textAntar);
        textBalek = findViewById(R.id.textBalek);
        textTglSewa = findViewById(R.id.textTglSewa);
        textTglBalek = findViewById(R.id.textTglBalek);
        textTypeMotor = findViewById(R.id.textNamaMotor);
        textTotal = findViewById(R.id.textTotal);
        textBayar = findViewById(R.id.textBayar);
        btnBayar = findViewById(R.id.btn_bayar);

        Intent intent = getIntent();
        int harga = Integer.parseInt(intent.getStringExtra("harga"));
        int durasi = Integer.parseInt(intent.getStringExtra("hari"));
        String total = String.valueOf(harga * durasi);

        textNama.setText(intent.getStringExtra("nama"));
        textHp.setText(intent.getStringExtra("no_hp"));
        textAntar.setText(intent.getStringExtra("antar"));
        textBalek.setText(intent.getStringExtra("kembali"));
        textTglSewa.setText(intent.getStringExtra("tgl_pinjam"));
        textTglBalek.setText(intent.getStringExtra("tgl_kembali"));
        textTypeMotor.setText(intent.getStringExtra("motor"));
        textBayar.setText(intent.getStringExtra("bayar"));
        textTotal.setText(total);

        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(intent.getStringExtra("bayar").equals("Tunai")){
                    Intent i = new Intent(KonfirmasiActivity.this, NotaActivity.class);
                    i.putExtra("total", total);
                    startActivity(i);
                } else {
                    pay();
                }
            }
        });
        
        makePyament();
    }

    private void pay() {
        MidtransSDK.getInstance().setTransactionRequest(transactionRequest("101",2000, 1, "John"));
        MidtransSDK.getInstance().startPaymentUiFlow(KonfirmasiActivity.this );
    }

    public static TransactionRequest transactionRequest(String id, int price, int qty, String name){
        TransactionRequest request =  new TransactionRequest(System.currentTimeMillis() + " " , 2000 );
//        request.setCustomerDetails(customerDetails());
        ItemDetails details = new ItemDetails(id, price, qty, name);

        ArrayList<ItemDetails> itemDetails = new ArrayList<>();
        itemDetails.add(details);
        request.setItemDetails(itemDetails);
        return request;
    }

    private void makePyament() {
        SdkUIFlowBuilder.init()
                .setContext(this)
                .setMerchantBaseUrl("\"https://https://nasywa-snack.store/api/bayar/\"")
                .setClientKey("\"SB-Mid-client-aW7RAwbuEAmhzti8\"")
                .setTransactionFinishedCallback((TransactionFinishedCallback) this)
                .enableLog(true)
                .setColorTheme(new CustomColorTheme("#777777","#f77474" , "#3f0d0d"))
                .buildSDK();
    }

    @Override
    public void onTransactionFinished(TransactionResult result) {
        if(result.getResponse() != null){
            switch (result.getStatus()){
                case TransactionResult.STATUS_SUCCESS:
                    Toast.makeText(this, "Transaction Sukses " + result.getResponse().getTransactionId(), Toast.LENGTH_LONG).show();
                    break;
                case TransactionResult.STATUS_PENDING:
                    Toast.makeText(this, "Transaction Pending " + result.getResponse().getTransactionId(), Toast.LENGTH_LONG).show();
                    break;
                case TransactionResult.STATUS_FAILED:
                    Toast.makeText(this, "Transaction Failed" + result.getResponse().getTransactionId(), Toast.LENGTH_LONG).show();
                    break;
            }
            result.getResponse().getValidationMessages();
        }else if(result.isTransactionCanceled()){
            Toast.makeText(this, "Transaction Failed", Toast.LENGTH_LONG).show();
        }else{
            if(result.getStatus().equalsIgnoreCase((TransactionResult.STATUS_INVALID))){
                Toast.makeText(this, "Transaction Invalid" + result.getResponse().getTransactionId(), Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Something Wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
}