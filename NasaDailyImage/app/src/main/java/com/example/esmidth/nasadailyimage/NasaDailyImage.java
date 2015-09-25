package com.example.esmidth.nasadailyimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xml.sax.helpers.DefaultHandler;

public class NasaDailyImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasa_daily_image);
        //IotHandler handler = new IotHandler();
        //handler.processFeed();
        //resetDisplay(handler.getTitle(),handler.getDate(),handler.getImage(),handler.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nasa_daily_image, menu);
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

    private void resetDisplay(String title,String date,String imageUrl,String description)
    {
        TextView titleView = (TextView)findViewById(R.id.imageTitle);
        titleView.setText(title);

        TextView dateView = (TextView)findViewById(R.id.imageData);
        dateView.setText(date);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageBitmap(image);

        TextView descriptionView = (TextView)findViewById(R.id.imageDescription);
        descriptionView.setText(description);

    }
}

