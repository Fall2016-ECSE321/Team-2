package ca.mcgill.ecse321.android_full_ftms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import model.FTMS;
import model.MenuItem;
import model.Order;

public class OrderHistoryActivity extends AppCompatActivity {

    private HashMap<Integer, Order> orders;
    private FTMS ftms = FTMS.getInstance();
    private ArrayList<String> dateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

///////////////////////////////////////////////////////////////////////////////////////
        dateList = OrderActivity.getDateList();
        this.orders = new HashMap<Integer, Order>();
        OrderAdapter orderAdapter = new OrderAdapter(this, orders/*, dateList*/);

        int i = 0;
        for (Iterator<Order> orderList = ftms.getOrders().iterator();
             orderList.hasNext(); i++) {
            Order o = orderList.next();
            this.orders.put(i, o);
        }
        ListView list;

        list=(ListView)findViewById(R.id.orderhistorylist);
        list.setAdapter(orderAdapter);
///////////////////////////////////////////////////////////////////////////////////////

    }

    public void goToSupply (View view){
        Intent intent = new Intent(this, SupplyActivity.class);
        startActivity(intent);
    }

}
