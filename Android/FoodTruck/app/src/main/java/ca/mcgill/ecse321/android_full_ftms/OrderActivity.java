package ca.mcgill.ecse321.android_full_ftms;

import android.content.Intent;
import android.content.SharedPreferences;
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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import controller.Controller;
import controller.InvalidInputException;
import model.FTMS;
import model.MenuItem;
import model.Order;
import model.Staff;
import model.Supply;

public class OrderActivity extends AppCompatActivity {

    private HashMap<Integer, Order> orders;
    private HashMap<Integer, MenuItem> menuItems;
    private static ArrayList<String> dateList = new ArrayList<>();
    private FTMS ftms = FTMS.getInstance();
    TextInputLayout til;
    String error = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        til = (TextInputLayout) findViewById(R.id.fOrderLayout);
        til.setVisibility(View.GONE);
        dateList = new ArrayList<>();


        FileInputStream input = null; // Open input stream
        try {
            input = openFileInput("lines.txt");
            DataInputStream din = new DataInputStream(input);
            int sz = din.readInt(); // Read line count
            for (int i=0;i<sz;i++) { // Read lines
                String line = din.readUTF();
                dateList.add(line);
            }
            din.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

///////////////////////////////////////////////////////////////////////////////////////

        this.menuItems = new HashMap<Integer, MenuItem>();
        int i = 0;
        for (Iterator<MenuItem> items = ftms.getMenuItems().iterator();
             items.hasNext(); i++) {
            MenuItem mi = items.next();
            this.menuItems.put(i, mi);
        }

        ArrayAdapter<CharSequence> menuAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item){
                    @Override
                    public boolean isEnabled(int position) {
                        // TODO Auto-generated method stub
                        if (position == menuItems.size()) {
                            return false;
                        }
                        return true;
                    }
                };
        menuAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        for(int j = 0; j < menuItems.size(); j++){
            MenuItem mi = menuItems.get(j);
            menuAdapter.add(mi.getName());
        }
        menuAdapter.add("--Choose Menu Item--");

        Spinner spinner, spinner2, spinner3, spinner4;

        spinner=(Spinner)findViewById(R.id.menuitem1spinner);
        spinner2=(Spinner)findViewById(R.id.menuitem2spinner);
        spinner3=(Spinner)findViewById(R.id.menuitem3spinner);
        spinner4=(Spinner)findViewById(R.id.menuitem4spinner);

        spinner.setAdapter(menuAdapter);
        spinner2.setAdapter(menuAdapter);
        spinner3.setAdapter(menuAdapter);
        spinner4.setAdapter(menuAdapter);

        spinner.setSelection(menuItems.size());
        spinner2.setSelection(menuItems.size());
        spinner3.setSelection(menuItems.size());
        spinner4.setSelection(menuItems.size());

///////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////
        this.orders = new HashMap<Integer, Order>();

        int m = 0;
        for (Iterator<Order> orderIterator = ftms.getOrders().iterator();
             orderIterator.hasNext(); m++) {
            Order o = orderIterator.next();
            this.orders.put(m, o);
        }

        if(orders.size() != 0){
            Order order;
            HashMap<Integer, MenuItem> mi;
            MenuItem mostPopular, current;
            mostPopular = orders.get(0).getMenuItem(0);
            for(int j = 0; j < orders.size(); j++){
                order = orders.get(j);
                for(int k = 0; k < order.getMenuItems().size(); k++){
                    current = order.getMenuItem(k);
                    if(current.getPopularity() > mostPopular.getPopularity()){
                        mostPopular = current;
                    }
                }
            }

            TextView tv = (TextView) findViewById(R.id.mostpopularitem);
            tv.setText(mostPopular.getName());
        }
///////////////////////////////////////////////////////////////////////////////////////

        refreshData();

    }

    public void refreshData(){

        error = "";
        til = (TextInputLayout) findViewById(R.id.fOrderLayout);

///////////////////////////////////////////////////////////////////////////////////////

        this.menuItems = new HashMap<Integer, MenuItem>();
        int i = 0;
        for (Iterator<MenuItem> items = ftms.getMenuItems().iterator();
             items.hasNext(); i++) {
            MenuItem mi = items.next();
            this.menuItems.put(i, mi);
        }

        ArrayAdapter<CharSequence> menuAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item){
                    @Override
                    public boolean isEnabled(int position) {
                        // TODO Auto-generated method stub
                        if (position == menuItems.size()) {
                            return false;
                        }
                        return true;
                    }
                };
        menuAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        for(int j = 0; j < menuItems.size(); j++){
            MenuItem mi = menuItems.get(j);
            menuAdapter.add(mi.getName());
        }
        menuAdapter.add("--Choose Menu Item--");

        Spinner spinner, spinner2, spinner3, spinner4;

        spinner=(Spinner)findViewById(R.id.menuitem1spinner);
        spinner2=(Spinner)findViewById(R.id.menuitem2spinner);
        spinner3=(Spinner)findViewById(R.id.menuitem3spinner);
        spinner4=(Spinner)findViewById(R.id.menuitem4spinner);

        spinner.setAdapter(menuAdapter);
        spinner2.setAdapter(menuAdapter);
        spinner3.setAdapter(menuAdapter);
        spinner4.setAdapter(menuAdapter);

        spinner.setSelection(menuItems.size());
        spinner2.setSelection(menuItems.size());
        spinner3.setSelection(menuItems.size());
        spinner4.setSelection(menuItems.size());

///////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////
        this.orders = new HashMap<Integer, Order>();

        int m = 0;
        for (Iterator<Order> orderIterator = ftms.getOrders().iterator();
             orderIterator.hasNext(); m++) {
            Order o = orderIterator.next();
            this.orders.put(m, o);
        }

        if(orders.size() != 0){
            Order order;
            HashMap<Integer, MenuItem> mi;
            MenuItem mostPopular, current;
            mostPopular = orders.get(0).getMenuItem(0);
            for(int j = 0; j < orders.size(); j++){
                order = orders.get(j);
                for(int k = 0; k < order.getMenuItems().size(); k++){
                    current = order.getMenuItem(k);
                    if(current.getPopularity() > mostPopular.getPopularity()){
                        mostPopular = current;
                    }
                }
            }

            TextView tv = (TextView) findViewById(R.id.mostpopularitem);
            tv.setText(mostPopular.getName());
        }
///////////////////////////////////////////////////////////////////////////////////////

    }

    public void addOrder(View v){

        Spinner spinner = (Spinner) findViewById(R.id.menuitem1spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.menuitem2spinner);
        Spinner spinner3 = (Spinner) findViewById(R.id.menuitem3spinner);
        Spinner spinner4 = (Spinner) findViewById(R.id.menuitem4spinner);
        int index = spinner.getSelectedItemPosition();
        int index2 = spinner2.getSelectedItemPosition();
        int index3 = spinner3.getSelectedItemPosition();
        int index4 = spinner4.getSelectedItemPosition();
        String currentTimeString = "";

        ArrayList<MenuItem> items = new ArrayList<>();

        if(menuItems.get(index) != null){
            items.add(menuItems.get(index));
        }
        if(menuItems.get(index2) != null){
            items.add(menuItems.get(index2));
        }
        if(menuItems.get(index3) != null){
            items.add(menuItems.get(index3));
        }
        if(menuItems.get(index4) != null){
            items.add(menuItems.get(index4));
        }

        Controller ftController = new Controller();
        try{
            ftController.createOrder(items);
            //currentTimeString += new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date());
            currentTimeString += new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z").format(new Date());
            dateList.add(currentTimeString);
        } catch(InvalidInputException e){
            til = (TextInputLayout) findViewById(R.id.fOrderLayout);
            error += e.getMessage();
            til.setError(error);
            refreshData();
            til.setVisibility(View.VISIBLE);
            return;
        }
        til = (TextInputLayout) findViewById(R.id.fOrderLayout);
        til.setError("");
        til.setVisibility(View.GONE);
        refreshData();

        try {
            //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
            FileOutputStream output = openFileOutput("lines.txt",MODE_WORLD_READABLE);
            DataOutputStream dout = new DataOutputStream(output);
            dout.writeInt(dateList.size()); // Save line count
            for(String line : dateList) // Save lines
                dout.writeUTF(line);
            dout.flush(); // Flush stream ...
            dout.close(); // ... and close.
        }
        catch (IOException exc) { exc.printStackTrace(); }

    }

    public void goToHistory (View view){
        Intent intent = new Intent(this, OrderHistoryActivity.class);
        startActivity(intent);
    }

    public static ArrayList<String> getDateList(){return dateList;}

}
