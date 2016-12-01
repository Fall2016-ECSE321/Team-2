package ca.mcgill.ecse321.android_full_ftms;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

import model.Equipment;
import model.MenuItem;

/**
 * Created by aliel on 2016-11-26.
 */

public class DetailedMenuAdapter extends BaseAdapter {

    private Activity activity;
    private HashMap<Integer, MenuItem> menuItems;
    private static LayoutInflater inflater=null;


    public DetailedMenuAdapter(Activity a, HashMap<Integer, MenuItem> menuItems) {
        activity = a;
        this.menuItems = menuItems;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return menuItems.size();
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
            vi = inflater.inflate(R.layout.detailed_menu_item_list_row, null);

        TextView menuItemName = (TextView)vi.findViewById(R.id.menuitemname); // title
        TextView menuItemIngredients = (TextView)vi.findViewById(R.id.menuitemingredients);

        MenuItem menuItem = menuItems.get(position);

        // Setting all values in listview
        String text = "";
        for(int i = 0; i < menuItem.getSupplies().size(); i++){
            text += menuItem.getSupply(i).getName();
            text += '\n';
        }
        menuItemName.setText(menuItem.getName());
        menuItemIngredients.setText(text);
        return vi;
    }

}
