package ca.mcgill.ecse321.android_full_ftms;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.Iterator;

import controller.Controller;
import controller.InvalidInputException;
import model.FTMS;
import model.Staff;
import model.TimeBlock;

public class ScheduleActivity extends AppCompatActivity {

    private FTMS ftms = FTMS.getInstance();

    private TextView dateView;
    private TextView startTimeView, endTimeView;
    private static final int STIME_DIALOG_ID = 1, ETIME_DIALOG_ID = 2;
    private int year, month, day, hour, minute, hour2, minute2;
    private Date date;
    private Time startTime, endTime;
    private String error = "";
    private TextInputLayout til;

    private HashMap<Integer, TimeBlock> timeBlocks;
    private HashMap<Integer, String> daysOfWeek;
    private HashMap<Integer, Staff> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showStartTimePickerDialog();
        showEndTimePickerDialog();

        daysOfWeek = new HashMap<>();

        daysOfWeek.put(0, "Monday");
        daysOfWeek.put(1, "Tuesday");
        daysOfWeek.put(2, "Wednesday");
        daysOfWeek.put(3, "Thursday");
        daysOfWeek.put(4, "Friday");
        daysOfWeek.put(5, "Saturday");
        daysOfWeek.put(6, "Sunday");
        daysOfWeek.put(7, "--Choose Day--");

        til = (TextInputLayout) findViewById(R.id.fScheduleLayout);
        til.setVisibility(View.GONE);

        refreshData();
    }

    private void refreshData(){

        TextView tv4 = (TextView) findViewById(R.id.newschedule_starttime);
        TextView tv5 = (TextView) findViewById(R.id.newschedule_endtime);

        tv4.setText("Choose Start Time");
        tv5.setText("Choose End Time");

        date = null;
        startTime = null;
        endTime = null;
        error = "";
        til = (TextInputLayout) findViewById(R.id.fScheduleLayout);

        // Initialize the data in the staff spinner
        Spinner spinner = (Spinner) findViewById(R.id.staffspinner);

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

        for (int j = 0; j < daysOfWeek.size(); j++) {
            String day = daysOfWeek.get(j);
            dayAdapter.add(day);
        }

        spinner2.setAdapter(dayAdapter);
        spinner2.setSelection(7);

    }

    public void showStartTimePickerDialog(){
        startTimeView = (TextView)findViewById(R.id.newschedule_starttime);
        startTimeView.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        showDialog(STIME_DIALOG_ID);
                    }
                }
        );
    }

    public void showEndTimePickerDialog(){
        endTimeView = (TextView)findViewById(R.id.newschedule_endtime);
        endTimeView.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        showDialog(ETIME_DIALOG_ID);
                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(id == STIME_DIALOG_ID){
            return new TimePickerDialog(this, STimePickerListener, hour, minute, true);
        }
        else if(id == ETIME_DIALOG_ID){
            return new TimePickerDialog(this, ETimePickerListener, hour2, minute2, true);
        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener STimePickerListener = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourTime, int minuteTime){

            hour = hourTime;
            minute = minuteTime;
            String hourString = "";
            String minuteString = "";

            TextView tv = (TextView) findViewById(R.id.newschedule_starttime);

            if(hour < 10) hourString = "0" + hour;
            else hourString += hour;
            if(minute < 10) minuteString = "0" + minute;
            else minuteString += minute;

            tv.setText(hourString + ":" + minuteString);

            startTime = new Time(hour, minute, 0);
        }
    };

    private TimePickerDialog.OnTimeSetListener ETimePickerListener = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourTime, int minuteTime){

            hour2 = hourTime;
            minute2 = minuteTime;
            String hourString = "";
            String minuteString = "";

            TextView tv = (TextView) findViewById(R.id.newschedule_endtime);

            if(hour2 < 10) hourString = "0" + hour2;
            else hourString += hour2;
            if(minute2 < 10) minuteString = "0" + minute2;
            else minuteString += minute2;

            tv.setText(hourString + ":" + minuteString);

            endTime = new Time(hour2, minute2, 0);
        }
    };

    public void addTimeBlock(View v){

        Spinner spinner = (Spinner) findViewById(R.id.dayspinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.staffspinner);
        int index = spinner2.getSelectedItemPosition();
        int index2 = spinner.getSelectedItemPosition();
        Staff selectedEmployee = employees.get(index);

        Controller controller = new Controller();

        try {
            controller.createTimeBlock(startTime, endTime, index2 + 1, selectedEmployee);
        } catch(InvalidInputException e){
            til = (TextInputLayout) findViewById(R.id.fScheduleLayout);
            error += e.getMessage();
            til.setError(error);
            refreshData();
            til.setVisibility(View.VISIBLE);
            return;
        }
        til = (TextInputLayout) findViewById(R.id.fScheduleLayout);
        til.setError("");
        til.setVisibility(View.GONE);
        refreshData();
    }

    public void goToDetailedSchedule (View view){
        Intent intent = new Intent(this, DetailedScheduleActivity.class);
        startActivity(intent);
    }

}
