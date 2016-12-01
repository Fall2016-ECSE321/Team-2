package ca.mcgill.ecse321.android_full_ftms;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.util.HashMap;

import model.MenuItem;
import model.Order;

/**
 * Created by aliel on 2016-11-27.
 */

public class OrderAdapter extends BaseAdapter {

    private Activity activity;
    private HashMap<Integer, Order> orders;
    private static LayoutInflater inflater=null;

    public OrderAdapter(Activity a, HashMap<Integer, Order> orders/*, ArrayList<String> dateList*/) {
        activity = a;
        this.orders = orders;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return orders.size();
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
            vi = inflater.inflate(R.layout.order_history_row, null);

        TextView orderName = (TextView)vi.findViewById(R.id.orderdate); // title
        TextView orderedItems = (TextView)vi.findViewById(R.id.orderedmenuitems);

        Order order = orders.get((orders.size() - 1) - position);

        // Setting all values in listview
        String text = "";
        for(int i = 0; i < order.getMenuItems().size(); i++){
            text += order.getMenuItem(i).getName();
            text += '\n';
        }

        ArrayList<String> dateList = OrderActivity.getDateList();

        String currentTimeString = dateList.get((orders.size() - 1) - position);

        orderName.setText(currentTimeString);
        orderedItems.setText(text);
        return vi;
    }

}
