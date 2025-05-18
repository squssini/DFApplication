package com.example.dfapplication.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dfapplication.Fragments.AllFurnitureFragment;
import com.example.dfapplication.Fragments.LoginFragment;
import com.example.dfapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (savedInstanceState == null) {
            String loadFragment = getIntent().getStringExtra("loadFragment");
            if ("allFurniture".equals(loadFragment)) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainFragmentContainer, new AllFurnitureFragment())
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainFragmentContainer, new LoginFragment())
                        .commit();
            }
        }
    }


}
