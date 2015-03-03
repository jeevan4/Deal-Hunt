package edu.unm.jeevan.xml_list;

import java.io.FileNotFoundException;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private SitesAdapter mAdapter;
    private ListView sitesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("EbayList", "OnCreate()");
        setContentView(R.layout.activity_main);
        Intent ebay_intent = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get reference to our ListView
        sitesList = (ListView)findViewById(R.id.lview);

        //Set the click listener to launch the browser when a row is clicked.
        sitesList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos,long id) {
                String url = mAdapter.getItem(pos).getDealurl();
                String title = mAdapter.getItem(pos).getTitle();
                String price = mAdapter.getItem(pos).getPricenow();
                String saving = mAdapter.getItem(pos).getSavings();
                String imageurl = mAdapter.getItem(pos).getImgUrl();

                Intent i = new Intent(MainActivity.this,DealShow.class);
                i.putExtra("url",url);
                i.putExtra("title",title);
                i.putExtra("price",price);
                i.putExtra("saving",saving);
                i.putExtra("imageurl",imageurl);

                //i.setData(Uri.parse(url));
                startActivity(i);

            }

        });
	/*
		 * If network is available download the xml from the Internet.
		 * If not then try to use the local file from last time.
		 */
        if(isNetworkAvailable()){
            Log.i("EbayList", "starting download Task");
            SitesDownloadTask download = new SitesDownloadTask();
            download.execute();
        }else{
            Log.i("EbayList", "In Else");
            Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();
            mAdapter = new SitesAdapter(getApplicationContext(), -1, SitesXmlPullParser.getEbayDataFromFile(MainActivity.this));
            sitesList.setAdapter(mAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Helper method to determine if Internet connection is available.
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /*
     * AsyncTask that will download the xml file for us and store it locally.
     * After the download is done we'll parse the local file.
     */
    private class SitesDownloadTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... arg0) {
            //Download the file
            try {
                Downloader.DownloadFromUrl("http://deals.ebay.com/feeds/xml", openFileOutput("EbayData.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            //setup our Adapter and set it to the ListView.
            mAdapter = new SitesAdapter(MainActivity.this, -1, SitesXmlPullParser.getEbayDataFromFile(MainActivity.this));
            sitesList.setAdapter(mAdapter);
            Log.i("EbayList", "adapter size = "+ mAdapter.getCount());
        }
    }

}