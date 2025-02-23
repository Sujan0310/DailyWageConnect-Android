package com.example.dailywageconnect;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dailywageconnect.database.DatabaseHelper;
import java.util.ArrayList;
import java.util.List;

public class JobListActivity extends AppCompatActivity {
    private TextView jobListTextView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        jobListTextView = findViewById(R.id.jobListTextView);
        dbHelper = new DatabaseHelper(this);

        List<String> jobs = fetchJobs();

        if (jobs.isEmpty()) {
            Toast.makeText(this, "No jobs available", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder jobDisplay = new StringBuilder();
            for (String job : jobs) {
                jobDisplay.append(job).append("\n\n");
            }
            jobListTextView.setText(jobDisplay.toString());
        }
    }

    private List<String> fetchJobs() {
        List<String> jobList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM jobs", null);

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                String wage = cursor.getString(3);
                jobList.add("Title: " + title + "\nDescription: " + description + "\nWage: " + wage);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return jobList;
    }
}
