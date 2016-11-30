package ca.mcgill.ecse321.android_full_ftms;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import model.Equipment;
import model.FTMS;
import model.MenuItem;
import model.Supply;
import persistence.Persistence;

public class DetailedMenuActivity extends AppCompatActivity {

    private HashMap<Integer, MenuItem> menuItems;
    private FTMS ftms = FTMS.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

///////////////////////////////////////////////////////////////////////////////////////
        this.menuItems = new HashMap<Integer, MenuItem>();
        DetailedMenuAdapter dmAdapter = new DetailedMenuAdapter(this, menuItems);

        int i = 0;
        for (Iterator<MenuItem> items = ftms.getMenuItems().iterator();
             items.hasNext(); i++) {
            MenuItem mi = items.next();
            this.menuItems.put(i, mi);
        }
        ListView list;

        list=(ListView)findViewById(R.id.detailedmenuitemslist);
        list.setAdapter(dmAdapter);
///////////////////////////////////////////////////////////////////////////////////////

    }

}
