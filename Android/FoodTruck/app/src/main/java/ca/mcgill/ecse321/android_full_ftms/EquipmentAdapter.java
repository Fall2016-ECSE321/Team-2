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

import model.Equipment;

import static android.R.attr.data;

/**
 * Created by aliel on 2016-11-24.
 */

public class EquipmentAdapter extends BaseAdapter {

    private Activity activity;
    private HashMap<Integer, Equipment> equipment;
    private static LayoutInflater inflater=null;


    public EquipmentAdapter(Activity a, HashMap<Integer, Equipment> equipment) {
        activity = a;
        this.equipment = equipment;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return equipment.size();
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

        Equipment equipmentPiece = equipment.get(position);

        // Setting all values in listview
        name.setText(equipmentPiece.getName());
        description.setText(equipmentPiece.getDescription());
        quantity.setText("" + equipmentPiece.getQuantity());

        return vi;
    }
}
