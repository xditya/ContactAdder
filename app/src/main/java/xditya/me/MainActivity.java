package xditya.me;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnCreate;
    TextView tvResultsMain;
    ImageView imgCall, imgBrowser;

    final int RECEIVER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = findViewById(R.id.btnCreate);
        tvResultsMain = findViewById(R.id.tvResultsMain);
        imgCall = findViewById(R.id.imgCall);
        imgBrowser = findViewById(R.id.imgBrowser);

        tvResultsMain.setVisibility(View.GONE);
        imgCall.setVisibility(View.GONE);
        imgBrowser.setVisibility(View.GONE);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, xditya.me.page2.class);
                //noinspection deprecation
                startActivityForResult(intent, RECEIVER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RECEIVER) {
            if (resultCode == RESULT_OK) {
                String cPhone = data.getStringExtra("contactPhone");
                String cName = data.getStringExtra("contactName");
                String cWebSite = data.getStringExtra("contactWebSite");
                String output = "Name: " + cName + "\nPhone: " + cPhone;
                String cWeb;

                imgCall.setVisibility(View.VISIBLE);
                imgCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + cPhone));
                        startActivity(intent);
                    }
                });

                if (cWebSite.length() > 0) {
                    if (!(cWebSite.startsWith("http")))
                        cWeb = "https://" + cWebSite;
                    else
                        cWeb = cWebSite;
                    imgBrowser.setVisibility(View.VISIBLE);
                    imgBrowser.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(cWeb));
                            startActivity(intent);
                        }
                    });
                }
            }
            else if (resultCode == RESULT_CANCELED) {
                tvResultsMain.setVisibility(View.VISIBLE);
                tvResultsMain.setText("Did not receive any data or was cancelled!");
            }
        }
        }
    }