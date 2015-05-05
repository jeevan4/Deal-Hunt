package edu.unm.jeevan.xml_list;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
<<<<<<< HEAD
import android.os.SystemClock;
import android.content.Context;
import android.widget.Toast;
=======
>>>>>>> origin/master

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;


public class DealsActivity extends ActionBarActivity {

    private SitesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_layout);
        Intent deals_intent = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ListView lv = (ListView)findViewById(R.id.listView);
        db_handler db = new db_handler(getApplicationContext());

        mAdapter = new SitesAdapter(this, -1, db.getAllDeals());
        lv.setAdapter(mAdapter);

//        final List<EbayData> ebaylist = db.getAllDeals();
//
//        ArrayAdapter<EbayData> adap = new ArrayAdapter<EbayData>(getApplicationContext(),android.R.layout.simple_list_item_1,ebaylist);
//        lv.setAdapter(adap);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos,long id) {
                String url = mAdapter.getItem(pos).getDealurl();
                String title = mAdapter.getItem(pos).getTitle();
                String price = mAdapter.getItem(pos).getPricenow();
                String saving = mAdapter.getItem(pos).getSavings();
                String imageurl = mAdapter.getItem(pos).getImgUrl();
                String deal_id = mAdapter.getItem(pos).getId();

                Intent i = new Intent(DealsActivity.this,saved_deal.class);
                i.putExtra("url",url);
                i.putExtra("title",title);
                i.putExtra("price",price);
                i.putExtra("saving",saving);
                i.putExtra("imageurl",imageurl);
                i.putExtra("deal_id",deal_id);
                //i.setData(Uri.parse(url));
                startActivity(i);

            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.notification_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            finish();
        }

        //noinspection SimplifiableIfStatement
<<<<<<< HEAD
        if (id == R.id.notification_menu) {
            scheduleNotification(getNotification("Tap Here To View"), 10000);
            Toast.makeText(getApplicationContext(), "Notification enabled", Toast.LENGTH_SHORT).show();
=======
        if (id == R.id.action_settings) {
            Intent deals_intent = new Intent(this, DealsActivity.class);
            startActivity(deals_intent);
>>>>>>> origin/master
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Deals About to Expire!!");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_launcher);
        return builder.build();
    }

}
