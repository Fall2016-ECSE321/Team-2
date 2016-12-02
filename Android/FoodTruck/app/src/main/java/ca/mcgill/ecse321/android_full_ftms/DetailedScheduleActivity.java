package ca.mcgill.ecse321.android_full_ftms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import model.FTMS;
import model.MenuItem;
import model.Staff;
import model.TimeBlock;

public class DetailedScheduleActivity extends AppCompatActivity {

    private HashMap<Integer, TimeBlock> timeBlocks;
    private FTMS ftms = FTMS.getInstance();
    private HashMap<Integer, String> daysOfWeek;
    private ArrayList<String> daysList;
    private HashMap<Integer, Staff> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.employees = new HashMap<Integer, Staff>();
        int i = 0;
        for (Iterator<Staff> staffList = ftms.getStaffs().iterator();
             staffList.hasNext(); i++) {
            Staff employee = staffList.next();
            this.employees.put(i, employee);
        }

        ArrayAdapter<CharSequence> staffAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item){
                    @Override
                    public boolean isEnabled(int position) {
                        // TODO Auto-generated method stub
                        if (position == employees.size()) {
                            return false;
                        }
                        return true;
                    }
                };
        staffAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        for(int j = 0; j < employees.size(); j++){
            Staff e = employees.get(j);
            staffAdapter.add(e.getName());
        }
        Spinner spinner = (Spinner) findViewById(R.id.employeespinner);
        staffAdapter.add("--Choose Employee--");
        spinner.setAdapter(staffAdapter);
        spinner.setSelection(employees.size());

        // Initialize the data in the day spinner
        Spinner spinner2 = (Spinner) findViewById(R.id.dayspinner);
        ArrayAdapter<CharSequence> dayAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item){
                    @Override
                    public boolean isEnabled(int position) {
                        // TODO Auto-generated method stub
                        if (position > 6) {
                            return false;
                        }
                        return true;
                    }
                };
        dayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

    }

    public void refreshDataSchedule(View v){
        // Initialize the data in the day spinner
        Spinner spinner = (Spinner) findViewById(R.id.employeespinner);
        ArrayAdapter<CharSequence> dayAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);


        ///////////////////////////////////////////////////////////////////////////////////////

        this.employees = new HashMap<Integer, Staff>();
        int index = spinner.getSelectedItemPosition();

        int i = 0;
        for (Iterator<Staff> s = ftms.getStaffs().iterator();
             s.hasNext(); i++) {
            Staff staff = s.next();
            this.employees.put(i, staff);
        }

        Staff employee = employees.get(index);
        FullScheduleAdapter fullScheduleAdapter = new FullScheduleAdapter(this, employee);

        ListView list;

        list=(ListView)findViewById(R.id.detailedschedulelist);
        list.setAdapter(fullScheduleAdapter);
///////////////////////////////////////////////////////////////////////////////////////

    }

}
