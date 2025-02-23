package com.example.dailywageconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HostDashboard extends AppCompatActivity {
    private Button postJobBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_dashboard);

        postJobBtn = findViewById(R.id.postJobBtn);

        postJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HostDashboard.this, JobPostingActivity.class);
                startActivity(intent);
            }
        });
    }
}
