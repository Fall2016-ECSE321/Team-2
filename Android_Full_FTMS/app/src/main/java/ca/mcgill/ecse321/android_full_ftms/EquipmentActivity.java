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
import persistence.Persistence;
import persistence.PersistenceXStream;

public class EquipmentActivity extends AppCompatActivity {

    private HashMap<Integer, Equipment> equipmentList;
    private FTMS ftms = FTMS.getInstance();
    TextInputLayout til;
    String error = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        til = (TextInputLayout) findViewById(R.id.fNameLayout);
        til.setVisibility(View.GONE);

/////////////////////////////////////////////////////////////////////////////////////////////
        this.equipmentList = new HashMap<Integer, Equipment>();
        EquipmentAdapter equipmentAdapter = new EquipmentAdapter(this, equipmentList);
        ArrayAdapter<CharSequence> participantAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1);

        int i = 0;
        for (Iterator<Equipment> equipmentPieces = ftms.getEquipments().iterator();
             equipmentPieces.hasNext(); i++) {
            Equipment ep = equipmentPieces.next();
            this.equipmentList.put(i, ep);
        }
        ListView list;

        list=(ListView)findViewById(R.id.list);
        list.setAdapter(equipmentAdapter);
/////////////////////////////////////////////////////////////////////////////////////////////

        refreshData();
    }

    private void refreshData(){
        TextView tv = (TextView) findViewById(R.id.newequipment_name);
        TextView tv2 = (TextView) findViewById(R.id.newequipment_quantity);
        TextView tv3 = (TextView) findViewById(R.id.newequipment_description);
        tv.setText("");
        tv2.setText("");
        tv3.setText("");
        error = "";
        til = (TextInputLayout) findViewById(R.id.fNameLayout);

        /////////////////////////////////////////////////////////////////////////////////////////////
        this.equipmentList = new HashMap<Integer, Equipment>();
        EquipmentAdapter equipmentAdapter = new EquipmentAdapter(this, equipmentList);
        ArrayAdapter<CharSequence> participantAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1);

        int i = 0;
        for (Iterator<Equipment> equipmentPieces = ftms.getEquipments().iterator();
             equipmentPieces.hasNext(); i++) {
            Equipment ep = equipmentPieces.next();
            this.equipmentList.put(i, ep);
        }
        ListView list;

        list=(ListView)findViewById(R.id.list);
        list.setAdapter(equipmentAdapter);
        //list.setAdapter(participantAdapter);

/////////////////////////////////////////////////////////////////////////////////////////////

    }

    public void addEquipment(View v){

        TextView tv = (TextView) findViewById(R.id.newequipment_name);
        TextView tv2 = (TextView) findViewById(R.id.newequipment_quantity);
        TextView tv3 = (TextView) findViewById(R.id.newequipment_description);

        Controller ftController = new Controller();
        try{
            ftController.createEquipment(tv.getText().toString(), tv3.getText().toString(), tv2.getText().toString());
        } catch(InvalidInputException e){
            til = (TextInputLayout) findViewById(R.id.fNameLayout);
            error += e.getMessage();
            til.setError(error);
            refreshData();
            til.setVisibility(View.VISIBLE);
            return;
        }
        til = (TextInputLayout) findViewById(R.id.fNameLayout);
        til.setError("");
        til.setVisibility(View.GONE);
        refreshData();

    }

}
