package ca.mcgill.ecse321.android_full_ftms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import controller.Controller;
import controller.InvalidInputException;
import model.FTMS;
import model.MenuItem;
import model.Supply;
import persistence.Persistence;

public class MenuActivity extends AppCompatActivity {

    private HashMap<Integer, Supply> supplies;
    private HashMap<Integer, MenuItem> menuItems;
    private FTMS ftms = FTMS.getInstance();
    TextInputLayout til;
    String error = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        til = (TextInputLayout) findViewById(R.id.fMenuItemLayout);
        til.setVisibility(View.GONE);

///////////////////////////////////////////////////////////////////////////////////////

        this.supplies = new HashMap<Integer, Supply>();
        int i = 0;
        for (Iterator<Supply> supplyList = ftms.getSupplies().iterator();
             supplyList.hasNext(); i++) {
            Supply s = supplyList.next();
            this.supplies.put(i, s);
        }

        ArrayAdapter<CharSequence> supplyAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item){
                    @Override
                    public boolean isEnabled(int position) {
                        // TODO Auto-generated method stub
                        if (position == supplies.size()) {
                            return false;
                        }
                        return true;
                    }
                };
        supplyAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        for(int j = 0; j < supplies.size(); j++){
            Supply s = supplies.get(j);
            supplyAdapter.add(s.getName());
        }
        supplyAdapter.add("--Choose Supply--");

        Spinner spinner, spinner2, spinner3, spinner4;

        spinner=(Spinner)findViewById(R.id.supply1spinner);
        spinner2=(Spinner)findViewById(R.id.supply2spinner);
        spinner3=(Spinner)findViewById(R.id.supply3spinner);
        spinner4=(Spinner)findViewById(R.id.supply4spinner);

        spinner.setAdapter(supplyAdapter);
        spinner2.setAdapter(supplyAdapter);
        spinner3.setAdapter(supplyAdapter);
        spinner4.setAdapter(supplyAdapter);

        spinner.setSelection(supplies.size());
        spinner2.setSelection(supplies.size());
        spinner3.setSelection(supplies.size());
        spinner4.setSelection(supplies.size());

///////////////////////////////////////////////////////////////////////////////////////
        refreshData();

    }

    public void refreshData(){

        TextView tv = (TextView) findViewById(R.id.newmenuitem_name);
        tv.setText("");

        error = "";
        til = (TextInputLayout) findViewById(R.id.fMenuItemLayout);

///////////////////////////////////////////////////////////////////////////////////////

        this.supplies = new HashMap<Integer, Supply>();
        int i = 0;
        for (Iterator<Supply> supplyList = ftms.getSupplies().iterator();
             supplyList.hasNext(); i++) {
            Supply s = supplyList.next();
            this.supplies.put(i, s);
        }

        ArrayAdapter<CharSequence> supplyAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item){
                    @Override
                    public boolean isEnabled(int position) {
                        // TODO Auto-generated method stub
                        if (position == supplies.size()) {
                            return false;
                        }
                        return true;
                    }
                };
        supplyAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        for(int j = 0; j < supplies.size(); j++){
            Supply s = supplies.get(j);
            supplyAdapter.add(s.getName());
        }
        supplyAdapter.add("--Choose Supply--");

        Spinner spinner, spinner2, spinner3, spinner4;

        spinner=(Spinner)findViewById(R.id.supply1spinner);
        spinner2=(Spinner)findViewById(R.id.supply2spinner);
        spinner3=(Spinner)findViewById(R.id.supply3spinner);
        spinner4=(Spinner)findViewById(R.id.supply4spinner);

        spinner.setAdapter(supplyAdapter);
        spinner2.setAdapter(supplyAdapter);
        spinner3.setAdapter(supplyAdapter);
        spinner4.setAdapter(supplyAdapter);

        spinner.setSelection(supplies.size());
        spinner2.setSelection(supplies.size());
        spinner3.setSelection(supplies.size());
        spinner4.setSelection(supplies.size());
///////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////
        this.menuItems = new HashMap<Integer, MenuItem>();
        ArrayAdapter menuItemAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);

        int j = 0;
        for (Iterator<MenuItem> items = ftms.getMenuItems().iterator();
             items.hasNext(); j++) {
            MenuItem mi = items.next();
            menuItemAdapter.add(mi.getName());
            this.menuItems.put(j, mi);
        }

        ListView list;
        list=(ListView)findViewById(R.id.menuitemslist);
        list.setAdapter(menuItemAdapter);
///////////////////////////////////////////////////////////////////////////////////////

    }

    public void addMenuItem(View v){

        TextView tv = (TextView) findViewById(R.id.newmenuitem_name);

        Spinner spinner = (Spinner) findViewById(R.id.supply1spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.supply2spinner);
        Spinner spinner3 = (Spinner) findViewById(R.id.supply3spinner);
        Spinner spinner4 = (Spinner) findViewById(R.id.supply4spinner);
        int index = spinner.getSelectedItemPosition();
        int index2 = spinner2.getSelectedItemPosition();
        int index3 = spinner3.getSelectedItemPosition();
        int index4 = spinner4.getSelectedItemPosition();

        ArrayList<Supply> ingredients = new ArrayList<>();

        if(supplies.get(index) != null){
            ingredients.add(supplies.get(index));
        }
        if(supplies.get(index2) != null){
            ingredients.add(supplies.get(index2));
        }
        if(supplies.get(index3) != null){
            ingredients.add(supplies.get(index3));
        }
        if(supplies.get(index4) != null){
            ingredients.add(supplies.get(index4));
        }

        Controller ftController = new Controller();
        try{
            ftController.createMenuItem(tv.getText().toString(), ingredients);
        } catch(InvalidInputException e){
            til = (TextInputLayout) findViewById(R.id.fMenuItemLayout);
            error += e.getMessage();
            til.setError(error);
            refreshData();
            til.setVisibility(View.VISIBLE);
            return;
        }
        til = (TextInputLayout) findViewById(R.id.fMenuItemLayout);
        til.setError("");
        til.setVisibility(View.GONE);
        refreshData();

    }

    public void goToDetailedMenuPage (View view){
        Intent intent = new Intent(this, DetailedMenuActivity.class);
        startActivity(intent);
    }

}
