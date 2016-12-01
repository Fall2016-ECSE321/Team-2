package ca.mcgill.ecse321.android_full_ftms;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;

import controller.Controller;
import controller.InvalidInputException;
import model.Equipment;
import model.FTMS;
import model.Supply;
import persistence.Persistence;

public class SupplyActivity extends AppCompatActivity {

    private HashMap<Integer, Supply> suppliesList;
    private FTMS ftms = FTMS.getInstance();
    TextInputLayout til;
    String error = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        til = (TextInputLayout) findViewById(R.id.fSuppliesNameLayout);
        til.setVisibility(View.GONE);

        //String location = getFilesDir() + "data.xml";
        //Persistence.loadModel(location);

        /////////////////////////////////////////////////////////////////////////////////////////////
        this.suppliesList = new HashMap<Integer, Supply>();
        SupplyAdapter supplyAdapter = new SupplyAdapter(this, suppliesList);

        int i = 0;
        for (Iterator<Supply> supplies = ftms.getSupplies().iterator();
             supplies.hasNext(); i++) {
            Supply sp = supplies.next();
            this.suppliesList.put(i, sp);
        }

        ListView list;
        list=(ListView)findViewById(R.id.supplieslist);
        list.setAdapter(supplyAdapter);
        /////////////////////////////////////////////////////////////////////////////////////////////

        refreshData();
    }

    private void refreshData(){
        TextView tv = (TextView) findViewById(R.id.newsupply_name);
        TextView tv2 = (TextView) findViewById(R.id.newsupply_quantity);
        TextView tv3 = (TextView) findViewById(R.id.newsupply_description);
        tv.setText("");
        tv2.setText("");
        tv3.setText("");
        error = "";
        til = (TextInputLayout) findViewById(R.id.fSuppliesNameLayout);

        /////////////////////////////////////////////////////////////////////////////////////////////
        this.suppliesList = new HashMap<Integer, Supply>();
        SupplyAdapter supplyAdapter = new SupplyAdapter(this, suppliesList);

        int i = 0;
        for (Iterator<Supply> supplies = ftms.getSupplies().iterator();
             supplies.hasNext(); i++) {
            Supply sp = supplies.next();
            this.suppliesList.put(i, sp);
        }

        ListView list;
        list=(ListView)findViewById(R.id.supplieslist);
        list.setAdapter(supplyAdapter);
        /////////////////////////////////////////////////////////////////////////////////////////////

    }

    public void addSupply(View v){

        TextView tv = (TextView) findViewById(R.id.newsupply_name);
        TextView tv2 = (TextView) findViewById(R.id.newsupply_quantity);
        TextView tv3 = (TextView) findViewById(R.id.newsupply_description);

        Controller ftController = new Controller();
        try{
            ftController.createSupply(tv.getText().toString(), tv3.getText().toString(), tv2.getText().toString());
        } catch(InvalidInputException e){
            til = (TextInputLayout) findViewById(R.id.fSuppliesNameLayout);
            error += e.getMessage();
            til.setError(error);
            refreshData();
            til.setVisibility(View.VISIBLE);
            return;
        }
        til = (TextInputLayout) findViewById(R.id.fSuppliesNameLayout);
        til.setError("");
        til.setVisibility(View.GONE);
        refreshData();

    }


}
