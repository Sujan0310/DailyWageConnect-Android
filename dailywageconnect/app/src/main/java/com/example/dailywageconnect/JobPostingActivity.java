package com.example.dailywageconnect;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dailywageconnect.database.DatabaseHelper;

public class JobPostingActivity extends AppCompatActivity {
    private EditText jobTitle, jobDescription, jobWage;
    private Button postJobBtn;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_posting);

        dbHelper = new DatabaseHelper(this);

        jobTitle = findViewById(R.id.jobTitle);
        jobDescription = findViewById(R.id.jobDescription);
        jobWage = findViewById(R.id.jobWage);
        postJobBtn = findViewById(R.id.postJobBtn);

        postJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = jobTitle.getText().toString().trim();
                String description = jobDescription.getText().toString().trim();
                String wage = jobWage.getText().toString().trim();

                if (title.isEmpty() || description.isEmpty() || wage.isEmpty()) {
                    Toast.makeText(JobPostingActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    saveJobToDatabase(title, description, wage);
                    Toast.makeText(JobPostingActivity.this, "Job posted successfully!", Toast.LENGTH_SHORT).show();
                    jobTitle.setText("");
                    jobDescription.setText("");
                    jobWage.setText("");
                }
            }
        });
    }

    private void saveJobToDatabase(String title, String description, String wage) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("description", description);
        values.put("wage", wage);
        db.insert("jobs", null, values);
        db.close();
    }
}
