package edu.unm.jeevan.xml_list;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import edu.unm.jeevan.xml_list.RoundImage;
import android.graphics.Bitmap;


import java.util.ArrayList;


public class MenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        try {


/*
            ImageView imgbtn = (ImageView) findViewById(R.id.imageButton);
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.deals_news);
            RoundImage roundedImage = new RoundImage(bm);
            imgbtn.setImageDrawable(roundedImage);
*/

            ImageView imgbtn = (ImageView) findViewById(R.id.imageButton);
            ImageView imgbtn2 = (ImageView) findViewById(R.id.imageButton2);
            ImageView imgbtn3 = (ImageView) findViewById(R.id.imageButton3);

            imgbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ebay_intent = new Intent(MenuActivity.this, MainActivity.class);
                    startActivity(ebay_intent);

                }
            });

            imgbtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent deals_intent = new Intent(MenuActivity.this, DealsActivity.class);
                    startActivity(deals_intent);

                }
            });


            /*final ArrayList<String> listdata = new ArrayList<String>();
            for (int i=1;i<=20;i++) {
                String item = "Item " + i;
                listdata.add(item);
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    getBaseContext(), android.R.layout.simple_expandable_list_item_1,listdata);
            lv.setAdapter(arrayAdapter);

        } catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }*/
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
