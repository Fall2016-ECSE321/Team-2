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
import model.Supply;

/**
 * Created by aliel on 2016-11-25.
 */

public class SupplyAdapter extends BaseAdapter {

    private Activity activity;
    private HashMap<Integer, Supply> supplies;
    private static LayoutInflater inflater=null;


    public SupplyAdapter(Activity a, HashMap<Integer, Supply> supplies) {
        activity = a;
        this.supplies = supplies;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return supplies.size();
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
            vi = inflater.inflate(R.layout.list_row, null);

        TextView name = (TextView)vi.findViewById(R.id.name); // title
        TextView description = (TextView)vi.findViewById(R.id.description); // artist name
        TextView quantity = (TextView)vi.findViewById(R.id.quantity); // duration

        Supply supply = supplies.get(position);

        // Setting all values in listview
        name.setText(supply.getName());
        description.setText(supply.getDescription());
        quantity.setText("" + supply.getQuantity());

        return vi;
    }

}
