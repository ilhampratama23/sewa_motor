package com.example.sewavespa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.midtrans.sdk.corekit.callback.CardRegistrationCallback;
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.PaymentMethod;
import com.midtrans.sdk.corekit.core.TransactionRequest;
import com.midtrans.sdk.corekit.core.UIKitCustomSetting;
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.corekit.models.CardRegistrationResponse;
import com.midtrans.sdk.corekit.models.CustomerDetails;
import com.midtrans.sdk.corekit.models.snap.Gopay;
import com.midtrans.sdk.corekit.models.snap.Shopeepay;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;

public class MidtransActivity extends AppCompatActivity {

    private Button buttonUiKit, buttonDirectCreditCard, buttonDirectBcaVa, buttonDirectMandiriVa,
            buttonDirectBniVa, buttonDirectAtmBersamaVa, buttonDirectPermataVa, buttonPayWithSnapToken;
    private EditText editTextSnapToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midtrans);

        bindViews();
        initActionButtons();
        initMidtransSdk();
    }

    private void initMidtransSdk() {
        String client_key = "SB-Mid-client-aW7RAwbuEAmhzti8";
        String base_url = "https://promo-engine-sample-server.herokuapp.com/";
        SdkUIFlowBuilder sdkUIFlowBuilder = SdkUIFlowBuilder.init()
                .setClientKey(client_key) // client_key is mandatory
                .setContext(this) // context is mandatory
                .setMerchantBaseUrl(base_url)//set merchant url
                .setUIkitCustomSetting(uiKitCustomSetting())
                .enableLog(true) // enable sdk log
                .setColorTheme(new CustomColorTheme("#FFE51255", "#B61548", "#FFE51255")) // will replace theme on snap theme on MAP
                .setLanguage("en");
        sdkUIFlowBuilder.buildSDK();
    }

    private void initActionButtons() {
        buttonUiKit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MidtransSDK.getInstance().setTransactionRequest(initTransactionRequest());
                MidtransSDK.getInstance().startPaymentUiFlow(MidtransActivity.this);
            }
        });

        buttonDirectCreditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MidtransSDK.getInstance().setTransactionRequest(initTransactionRequest());
                MidtransSDK.getInstance().UiCardRegistration(MidtransActivity.this, new CardRegistrationCallback() {
                    @Override
                    public void onSuccess(CardRegistrationResponse cardRegistrationResponse) {
                        Toast.makeText(MidtransActivity.this, "register card token success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(CardRegistrationResponse cardRegistrationResponse, String s) {
                        Toast.makeText(MidtransActivity.this, "register card token Failed", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }
                });
            }
        });


        buttonDirectBcaVa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MidtransSDK.getInstance().setTransactionRequest(initTransactionRequest());
                MidtransSDK.getInstance().startPaymentUiFlow(MidtransActivity.this, PaymentMethod.BANK_TRANSFER_BCA);
            }
        });


        buttonDirectBniVa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MidtransSDK.getInstance().setTransactionRequest(initTransactionRequest());
                MidtransSDK.getInstance().startPaymentUiFlow(MidtransActivity.this, PaymentMethod.BANK_TRANSFER_BNI);
            }
        });

        buttonDirectMandiriVa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MidtransSDK.getInstance().setTransactionRequest(initTransactionRequest());
                MidtransSDK.getInstance().startPaymentUiFlow(MidtransActivity.this, PaymentMethod.BANK_TRANSFER_MANDIRI);
            }
        });


        buttonDirectPermataVa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MidtransSDK.getInstance().setTransactionRequest(initTransactionRequest());
                MidtransSDK.getInstance().startPaymentUiFlow(MidtransActivity.this, PaymentMethod.BANK_TRANSFER_PERMATA);
            }
        });

        buttonDirectAtmBersamaVa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MidtransSDK.getInstance().setTransactionRequest(initTransactionRequest());
                MidtransSDK.getInstance().startPaymentUiFlow(MidtransActivity.this, PaymentMethod.BCA_KLIKPAY);
            }
        });

        buttonPayWithSnapToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String snaptokenValue = editTextSnapToken.getEditableText().toString();
                if (snaptokenValue != null) {
                    MidtransSDK.getInstance().startPaymentUiFlow(MidtransActivity.this, snaptokenValue);
                }
            }
        });
    }

    private TransactionRequest initTransactionRequest() {
        // Create new Transaction Request
        TransactionRequest transactionRequestNew = new
                TransactionRequest(System.currentTimeMillis() + "", 36500.0);
        transactionRequestNew.setCustomerDetails(initCustomerDetails());
        transactionRequestNew.setGopay(new Gopay("mysamplesdk:://midtrans"));
        transactionRequestNew.setShopeepay(new Shopeepay("mysamplesdk:://midtrans"));
        return transactionRequestNew;
    }

    private CustomerDetails initCustomerDetails() {
        //define customer detail (mandatory for coreflow)
        CustomerDetails mCustomerDetails = new CustomerDetails();
        mCustomerDetails.setPhone("085310102020");
        mCustomerDetails.setFirstName("user fullname");
        mCustomerDetails.setEmail("mail@mail.com");
        mCustomerDetails.setCustomerIdentifier("mail@mail.com");
        return mCustomerDetails;
    }

    private UIKitCustomSetting uiKitCustomSetting() {
        UIKitCustomSetting uIKitCustomSetting = new UIKitCustomSetting();
        uIKitCustomSetting.setSkipCustomerDetailsPages(true);
        uIKitCustomSetting.setShowPaymentStatus(true);
        return uIKitCustomSetting;
    }

    private void bindViews() {
        buttonUiKit = (Button) findViewById(R.id.button_uikit);
        buttonDirectCreditCard = (Button) findViewById(R.id.button_direct_credit_card);
        buttonDirectBcaVa = (Button) findViewById(R.id.button_direct_bca_va);
        buttonDirectMandiriVa = (Button) findViewById(R.id.button_direct_mandiri_va);
        buttonDirectBniVa = (Button) findViewById(R.id.button_direct_bni_va);
        buttonDirectPermataVa = (Button) findViewById(R.id.button_direct_permata_va);
        buttonDirectAtmBersamaVa = (Button) findViewById(R.id.button_direct_atm_bersama_va);
        buttonPayWithSnapToken = (Button) findViewById(R.id.button_snap_pay);
        editTextSnapToken = (EditText) findViewById(R.id.edit_snaptoken);
    }
}