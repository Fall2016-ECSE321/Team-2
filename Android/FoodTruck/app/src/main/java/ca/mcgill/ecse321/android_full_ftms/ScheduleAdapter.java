package ca.mcgill.ecse321.android_full_ftms;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import model.Order;
import model.TimeBlock;

/**
 * Created by aliel on 2016-11-27.
 */

public class ScheduleAdapter extends BaseAdapter {

    private Activity activity;
    private HashMap<Integer, TimeBlock> timeBlocks, sortedTimeBlocks;
    private static LayoutInflater inflater=null;
    private ArrayList<String> dayList;
    private ArrayList<TimeBlock> scheduleList;
    private ArrayList<TimeBlock> sortedScheduleList;

    public ScheduleAdapter(Activity a, HashMap<Integer, TimeBlock> timeBlocks, ArrayList<String> dayList) {
        activity = a;
        this.timeBlocks = timeBlocks;
        this.dayList = dayList;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.scheduleList = new ArrayList<>();

        for(int i = 0; i < this.timeBlocks.size(); i++){
            scheduleList.add(timeBlocks.get(i));
        }

        int key = 0;
        for(int i = 1; i < 7; i++){
            ArrayList<TimeBlock> temp = new ArrayList<>();
            for(int j = 0; j < scheduleList.size(); j++){
                if(scheduleList.get(j).getDayOfWeek() == i){
                    temp.add(scheduleList.get(j));
                }
            }
            ArrayList<TimeBlock> sortedTemp = new ArrayList<>();
            while(temp.size() > 0){
                TimeBlock firstTimeBlock = temp.get(0);
                int position = 0;
                for(int k = 1; k < temp.size(); k++){
                    if(temp.get(k).getStartTime().before(firstTimeBlock.getStartTime())){
                        firstTimeBlock = temp.get(k);
                        position = k;
                    }
                }
                sortedTemp.add(firstTimeBlock);
                temp.remove(position);
            }
            for(int m = 0; m < sortedTemp.size(); m++){
                this.sortedTimeBlocks.put(key, sortedTemp.get(m));
                key++;
            }
        }

        // for Sunday
        ArrayList<TimeBlock> temp = new ArrayList<>();
        for(int j = 0; j < scheduleList.size(); j++){
            if(scheduleList.get(j).getDayOfWeek() == 0){
                temp.add(scheduleList.get(j));
            }
        }
        ArrayList<TimeBlock> sortedTemp = new ArrayList<>();
        while(temp.size() > 0){
            TimeBlock firstTimeBlock = temp.get(0);
            int position = 0;
            for(int k = 1; k < temp.size(); k++){
                if(temp.get(k).getStartTime().before(firstTimeBlock.getStartTime())){
                    firstTimeBlock = temp.get(k);
                    position = k;
                }
            }
            sortedTemp.add(firstTimeBlock);
            temp.remove(position);
        }
        for(int m = 0; m < sortedTemp.size(); m++){
            this.sortedTimeBlocks.put(key, sortedTemp.get(m));
            key++;
        }

    }

    public int getCount() {
        return dayList.size();
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
            case 0:
                s += "Sunday";
                break;
            case 1:
                s += "Monday";
                break;
            case 2:
                s += "Tuesday";
                break;
            case 3:
                s += "Wednesday";
                break;
            case 4:
                s += "Thursday";
                break;
            case 5:
                s += "Friday";
                break;
            case 6:
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

        sortedScheduleList = new ArrayList<>();
        for(int i = 0; i < sortedTimeBlocks.size(); i++){
            sortedScheduleList.add(i, sortedTimeBlocks.get(i));
        }

        ScheduleDisplayer sd = new ScheduleDisplayer("", "");
        ArrayList<ScheduleDisplayer> schedule = new ArrayList<>();
        String fullSchedule = "";
        String dayString = "";
        for(int i = 1; i < 7; i++){
            for(int j = 0; j < sortedScheduleList.size(); j++){
                while (sortedScheduleList.get(j).getDayOfWeek() == i){
                    dayString = convertDayToString(i);
                    fullSchedule += sortedScheduleList.get(j).getStartTime().toString() + " - " + sortedScheduleList.get(j).getEndTime().toString() + '\n';
                }
                sd.setDayOfWeek(dayString);
                sd.setScheduleString(fullSchedule);
                fullSchedule = "";
            }
            schedule.add(sd);
        }
        for(int j = 0; j < sortedScheduleList.size(); j++){
            while (sortedScheduleList.get(j).getDayOfWeek() == 0){
                dayString = convertDayToString(0);
                fullSchedule += sortedScheduleList.get(j).getStartTime().toString() + " - " + sortedScheduleList.get(j).getEndTime().toString() + '\n';
            }
            sd.setDayOfWeek(dayString);
            sd.setScheduleString(fullSchedule);
            fullSchedule = "";
        }
        schedule.add(sd);

        ScheduleDisplayer specificDay = schedule.get(position);

        dayOfWeek.setText(specificDay.getDayOfWeek());
        times.setText(specificDay.getScheduleString());
        return vi;
    }

}
