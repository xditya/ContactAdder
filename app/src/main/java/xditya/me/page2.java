package xditya.me;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class page2 extends AppCompatActivity {

    EditText contactName, contactPhone, contactWebSite;
    TextView tvView, tvWarn;
    Button btnSubmit, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        contactName = findViewById(R.id.contactName);
        contactPhone = findViewById(R.id.contactPhone);
        contactWebSite = findViewById(R.id.contactWebSite);
        tvView = findViewById(R.id.tvView);
        tvWarn = findViewById(R.id.tvWarn);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String cName = contactName.getText().toString();
                String cPhone = contactPhone.getText().toString();
                String cWebSite = contactWebSite.getText().toString();

                if (cName.isEmpty() || cPhone.isEmpty()) {
                    Toast.makeText(page2.this, "Please fill in the values marked with \"*\"!", Toast.LENGTH_SHORT).show();
                    return;
                }

                tvWarn.setVisibility(View.GONE);
                Intent intent = new Intent();
                intent.putExtra("contactName", cName);
                intent.putExtra("contactPhone", cPhone);
                intent.putExtra("contactWebSite", cWebSite);
                setResult(RESULT_OK, intent);
                page2.this.finish();

                tvView.setText("Finished!");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                page2.this.finish();
            }
        });
    }
}