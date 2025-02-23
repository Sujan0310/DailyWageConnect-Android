package com.example.dailywageconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class WorkerDashboard extends AppCompatActivity {
    private Button viewJobsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_dashboard);

        viewJobsBtn = findViewById(R.id.viewJobsBtn);

        viewJobsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkerDashboard.this, JobListActivity.class);
                startActivity(intent);
            }
        });
    }
}
