package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);

        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 設置要用哪個menu檔做為選單
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_message) {

            Toast.makeText(this, "Filter Message", Toast.LENGTH_SHORT).show();
            return true;
        }

        else if (id == R.id.action_settings) {

            Toast.makeText(this, "Resend", Toast.LENGTH_SHORT).show();
            return true;
        }

        else if (id == R.id.action_name) {
            Toast.makeText(this, "Notifications are sorted by APP's name.", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_time) {
            Toast.makeText(this, "Notifications are sorted by time.", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}