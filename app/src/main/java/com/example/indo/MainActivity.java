package com.example.indo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private SharedPreferences sharedPreferences;
    private static final String TAG = "kamlans2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);

        sharedPreferences = getSharedPreferences("indo" , Context.MODE_PRIVATE);
        Log.d(TAG, "onCreate: token ::"+  sharedPreferences.contains("token"));
        Log.d(TAG, "onCreate: token value is ::::::"+sharedPreferences.getString("token", "def value"));

        viewPager2.setAdapter( new FragmentAdapter( getSupportFragmentManager() , getLifecycle()));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int selectedTab = tab.getId();

                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.AboutUsMenuItem:
                startActivity( new Intent(getApplicationContext() , AboutUsActivity.class));
                break;

            case R.id.LogutBtnInMenuitem:
                startActivity(new Intent(getApplicationContext() , LoginActivity.class));
        }
        return true;
    }
}