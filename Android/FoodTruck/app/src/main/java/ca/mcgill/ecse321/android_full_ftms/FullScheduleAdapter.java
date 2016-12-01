package ca.mcgill.ecse321.android_full_ftms;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import model.Order;
import model.Staff;
import model.TimeBlock;

/**
 * Created by aliel on 2016-11-28.
 */

public class FullScheduleAdapter extends BaseAdapter {

    private Activity activity;
    private HashMap<Integer, Staff> employees;
    private Staff employee;
    private static LayoutInflater inflater=null;
    //private ArrayList<String> dateList;

    public FullScheduleAdapter(Activity a, Staff employee) {
        activity = a;
        this.employee = employee;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return 7;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public String convertDayToString(int day){
        String s = "";
        switch (day){
            case 6:
                s += "Sunday";
                break;
            case 0:
                s += "Monday";
                break;
            case 1:
                s += "Tuesday";
                break;
            case 2:
                s += "Wednesday";
                break;
            case 3:
                s += "Thursday";
                break;
            case 4:
                s += "Friday";
                break;
            case 5:
                s += "Saturday";
                break;
            default:
                break;
        }
        return s;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.schedule_row, null);

        TextView dayOfWeek = (TextView)vi.findViewById(R.id.dayofweek); // title
        TextView times = (TextView)vi.findViewById(R.id.times);

        ArrayList<TimeBlock> timeBlocks = new ArrayList<>(employee.getTimeBlocks());

        int counter = 0;
        ArrayList<TimeBlock> temp = new ArrayList<>();
        while(counter < timeBlocks.size()){
            if(timeBlocks.get(counter).getDayOfWeek() - 1 == position){
                temp.add(timeBlocks.get(counter));
            }
            counter++;
        }

        // Setting all values in listview
        String text = "";
        for(int i = 0; i < temp.size(); i++){
            text += temp.get(i).getStartTime().toString() + " - " + temp.get(i).getEndTime().toString();
            text += '\n';
        }

        String day = convertDayToString(position);
        dayOfWeek.setText(day);
        times.setText(text);
        return vi;
    }

}
