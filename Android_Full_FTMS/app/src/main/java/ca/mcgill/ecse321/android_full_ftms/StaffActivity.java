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
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;

import controller.Controller;
import controller.InvalidInputException;
import model.Equipment;
import model.FTMS;
import model.Staff;

public class StaffActivity extends AppCompatActivity {

    private HashMap<Integer, Staff> staffList;
    private FTMS ftms = FTMS.getInstance();
    TextInputLayout til;
    String error = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        til = (TextInputLayout) findViewById(R.id.fStaffLayout);
        til.setVisibility(View.GONE);

/////////////////////////////////////////////////////////////////////////////////////////////
        this.staffList = new HashMap<Integer, Staff>();
        StaffAdapter staffAdapter = new StaffAdapter(this, staffList);

        int i = 0;
        for (Iterator<Staff> employees = ftms.getStaffs().iterator();
             employees.hasNext(); i++) {
            Staff employee = employees.next();
            this.staffList.put(i, employee);
        }
        ListView list;

        list=(ListView)findViewById(R.id.staff_list);
        list.setAdapter(staffAdapter);
/////////////////////////////////////////////////////////////////////////////////////////////

        refreshData();

    }

    private void refreshData(){
        TextView tv = (TextView) findViewById(R.id.newstaff_name);
        TextView tv2 = (TextView) findViewById(R.id.newstaff_position);
        tv.setText("");
        tv2.setText("");
        error = "";
        til = (TextInputLayout) findViewById(R.id.fStaffLayout);

/////////////////////////////////////////////////////////////////////////////////////////////
        this.staffList = new HashMap<Integer, Staff>();
        StaffAdapter staffAdapter = new StaffAdapter(this, staffList);

        int i = 0;
        for (Iterator<Staff> employees = ftms.getStaffs().iterator();
             employees.hasNext(); i++) {
            Staff employee = employees.next();
            this.staffList.put(i, employee);
        }
        ListView list;

        list=(ListView)findViewById(R.id.staff_list);
        list.setAdapter(staffAdapter);
/////////////////////////////////////////////////////////////////////////////////////////////

    }

    public void addStaff(View v){

        TextView tv = (TextView) findViewById(R.id.newstaff_name);
        TextView tv2 = (TextView) findViewById(R.id.newstaff_position);

        Controller ftController = new Controller();
        try{
            ftController.createStaff(tv.getText().toString(), tv2.getText().toString());
        } catch(InvalidInputException e){
            til = (TextInputLayout) findViewById(R.id.fStaffLayout);
            error += e.getMessage();
            til.setError(error);
            refreshData();
            til.setVisibility(View.VISIBLE);
            return;
        }
        til = (TextInputLayout) findViewById(R.id.fStaffLayout);
        til.setError("");
        til.setVisibility(View.GONE);
        refreshData();

    }

}
