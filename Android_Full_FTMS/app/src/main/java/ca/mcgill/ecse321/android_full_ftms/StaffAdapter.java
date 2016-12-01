package ca.mcgill.ecse321.android_full_ftms;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;

import model.Equipment;
import model.Staff;

/**
 * Created by aliel on 2016-11-26.
 */

public class StaffAdapter extends BaseAdapter {

    private Activity activity;
    private HashMap<Integer, Staff> staffList;
    private static LayoutInflater inflater=null;


    public StaffAdapter(Activity a, HashMap<Integer, Staff> staffList) {
        activity = a;
        this.staffList = staffList;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return staffList.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.staff_list_row, null);

        TextView employeeName = (TextView)vi.findViewById(R.id.employeename); // title
        TextView employeeRank = (TextView)vi.findViewById(R.id.employeerank); // artist name

        Staff employee = staffList.get(position);

        // Setting all values in listview
        employeeName.setText(employee.getName());
        employeeRank.setText(employee.getRole());

        return vi;
    }

}
