package com.example.esmidth.nasadailyimage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class NasaDailyImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasa_daily_image);
        Uri uri = Uri.parse("http://www.baidu.com");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
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

        TextView descriptionView = (TextView)findViewById(R.id.imageDescription);
        descriptionView.setText(description);

    }
}

