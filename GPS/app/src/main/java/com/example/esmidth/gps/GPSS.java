package com.example.esmidth.gps;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class GPSS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpss);
        openGPSSettings();
        getLocation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gps, menu);
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

    private void openGPSSettings() {
        LocationManager alm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (alm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPS works well", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(this, "Please turn on GPS!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
            startActivityForResult(intent, 0);
        }
    }

    //@TargetApi(Build.VERSION_CODES.MARSHMALLOW)
    private void getLocation() {
         LocationListener locationListener = new LocationListener(){
            //位置发生改变时调用
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Location", "onLocationChanged");
            }

            //provider失效时调用
            @Override
            public void onProviderDisabled(String provider) {
                Log.d("Location", "onProviderDisabled");
            }

            //provider启用时调用
            @Override
            public void onProviderEnabled(String provider) {
                Log.d("Location", "onProviderEnabled");
            }

            //状态改变时调用
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d("Location", "onStatusChanged");
            }
        };
        LocationManager locationManger;
        String serviceName = Context.LOCATION_SERVICE;
        locationManger = (LocationManager) this.getSystemService(serviceName);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        /*
        String provider = locationManger.getBestProvider(criteria, true); // 获取GPS信息
        Location location = locationManger.getLastKnownLocation(provider);// 通过GPS获取位置
        updateToNewLocation(location);
        locationManger.requestLocationUpdates(provider,100*1000,500,locationListener);
        */
        refresh(locationManger,criteria,locationListener);
    }
    private void updateToNewLocation(Location location) {
        TextView tv1;
        tv1 = (TextView) this.findViewById(R.id.gps);
        if(location != null)
        {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            tv1.setText("维度：" +  latitude+ "\n经度：" + longitude);
            //tv1.setText("Hello FUCK");
        }
        else
        {
            tv1.setText("无法获取地理信息");
        }
    }
    private void refresh(LocationManager locationManger,Criteria criteria,LocationListener locationListener)
    {
        String provider = locationManger.getBestProvider(criteria, true); // 获取GPS信息
        Location location = locationManger.getLastKnownLocation(provider);// 通过GPS获取位置
        updateToNewLocation(location);
        locationManger.requestLocationUpdates(provider, 100 * 1000, 500, locationListener);
    }

    public void refresh(View view) {
        getLocation();
    }

    /*
    private LocationListener locationListener = new LocationListener(){
        //位置发生改变时调用
        @Override
        public void onLocationChanged(Location location) {
            Log.d("Location", "onLocationChanged");
        }

        //provider失效时调用
        @Override
        public void onProviderDisabled(String provider) {
            Log.d("Location", "onProviderDisabled");
        }

        //provider启用时调用
        @Override
        public void onProviderEnabled(String provider) {
            Log.d("Location", "onProviderEnabled");
        }

        //状态改变时调用
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("Location", "onStatusChanged");
        }
    };
    */

}
