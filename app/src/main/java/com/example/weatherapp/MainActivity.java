package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String THEME = "THEME";
    private int themeNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){

            themeNumber = savedInstanceState.getInt(THEME);

            switch (themeNumber){
                case 0:
                    setTheme(R.style.AppTheme_NoActionBar);
                    Toast.makeText(this, "Theme AppTheme_NoActionBar" , Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    setTheme(R.style.AppTheme_NoActionBar_Theme1);
                    Toast.makeText(this, "Theme AppTheme_NoActionBar_Theme1" , Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    setTheme(R.style.AppTheme_NoActionBar_Theme2);
                    Toast.makeText(this, "Theme AppTheme_NoActionBar_Theme2" , Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int countOfFragmentInManager = getSupportFragmentManager().getBackStackEntryCount();
        if (countOfFragmentInManager > 0) {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            themeNumber++;
            themeNumber = themeNumber%3;

            Toast toast = Toast.makeText(this, "change theme", Toast.LENGTH_SHORT);
            toast.show();
            recreate();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(THEME, themeNumber);
    }
}
