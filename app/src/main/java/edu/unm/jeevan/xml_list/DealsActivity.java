package edu.unm.jeevan.xml_list;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        getMenuInflater().inflate(R.menu.menu_deals, menu);
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
        if (id == R.id.action_settings) {
            Intent deals_intent = new Intent(this, DealsActivity.class);
            startActivity(deals_intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
