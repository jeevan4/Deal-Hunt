package edu.unm.jeevan.xml_list;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.net.MalformedURLException;
import java.net.URL;


public class saved_deal extends ActionBarActivity {

    ImageLoader imageLoader;
//    public String deal_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_deal_show);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView dealdesc = (TextView) findViewById(R.id.dealdesc);
        Intent deal_show = getIntent();
        final String url = deal_show.getStringExtra("url");
        final String title = deal_show.getStringExtra("title");
        final String price = deal_show.getStringExtra("price");
        final String saving = deal_show.getStringExtra("saving");
        final String imageurl = deal_show.getStringExtra("imageurl");
        String deal_id = getResources().getString(R.string.deal_id);
        deal_id = deal_show.getStringExtra("deal_id");

        ImageView iconImg = (ImageView) findViewById(R.id.imgdisp);
        ImageView dealbtn = (ImageView) findViewById(R.id.dealbtn);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
        imageLoader.displayImage(imageurl,iconImg);

/*
        try {
            URL lod = new URL(deal_show.getStringExtra("url"));
            Bitmap bmp = BitmapFactory.decodeStream(lod.openConnection().getInputStream());
            iconImg.setImageBitmap(bmp);

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
*/
        dealdesc.setText(Html.fromHtml("<B>"+title + "</b> is available for <b>$" + price + "</b> Your saving on this deal is <b>" + saving+"</b>"));

        dealbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        /*
        Ddisp.setText(Html.fromHtml("<img src='"+imageurl+"' style='float: left;vertical-align: top;margin: 0 50px 50px 0'>"+title+" " +
                        "is available for <strong>$"+price+"</strong>. Your saving on this deal is <strong style='color:#ffff541f'>"+saving+"</strong>"
        ));
*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_deal_show, menu);
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
        if (id == R.id.delete_deal) {

            db_handler db = new db_handler(getApplicationContext());
            Intent deal_show = getIntent();
            String deal_id = deal_show.getStringExtra("deal_id");
//
            db.deleteDeal(getApplicationContext(),deal_id);
            Intent deals_intent = new Intent(this, MainActivity.class);
            startActivity(deals_intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
