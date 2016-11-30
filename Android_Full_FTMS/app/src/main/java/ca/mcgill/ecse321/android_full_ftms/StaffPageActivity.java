package ca.mcgill.ecse321.android_full_ftms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class StaffPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void goToEquipment (View view){
        Intent intent = new Intent(this, EquipmentActivity.class);
        startActivity(intent);
    }

    public void goToSupply (View view){
        Intent intent = new Intent(this, SupplyActivity.class);
        startActivity(intent);
    }

    public void goToMenu (View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void goToOrder (View view){
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }

}
