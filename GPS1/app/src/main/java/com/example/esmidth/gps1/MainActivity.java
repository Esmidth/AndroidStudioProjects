package com.example.esmidth.gps1;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;

public class MainActivity extends Activity {
    private LocationManager locationManager;
    private GpsStatus gpsstatus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取到LocationManager对象
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //根据设置的Criteria对象，获取最符合此标准的provider对象
        String currentProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER).getName();

        //根据当前provider对象获取最后一次位置信息
        Location currentLocation = locationManager.getLastKnownLocation(currentProvider);
        //如果位置信息为null，则请求更新位置信息
        if (currentLocation == null) {
            locationManager.requestLocationUpdates(currentProvider, 0, 0, locationListener);
        }
        //增加GPS状态监听器
        locationManager.addGpsStatusListener(gpsListener);

        //直到获得最后一次位置信息为止，如果未获得最后一次位置信息，则显示默认经纬度
        //每隔10秒获取一次位置信息
        while (true) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
            currentLocation = locationManager.getLastKnownLocation(currentProvider);
            if(currentLocation != null){
                //Log.d("Location", "Latitude: " + currentLocation.getLatitude());
                //Log.d("Location", "location: " + currentLocation.getLongitude());
                updateToNewLocation(currentLocation);
                break;
            }else{
                Log.d("Location", "Latitude: " + 0);
                Log.d("Location", "location: " + 0);
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Log.e("Location", e.getMessage());
            }
        }
    }
    private void updateToNewLocation(Location location) {
        TextView tv1;
        tv1 = (TextView) this.findViewById(R.id.gps);
        if(location != null)
        {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            //tv1.setText("维度：" +  latitude+ "\n经度" + longitude);
            tv1.setText("Hello FUCK");
        }
        else
        {
            tv1.setText("无法获取地理信息");
        }
    }
    private GpsStatus.Listener gpsListener = new GpsStatus.Listener(){
        //GPS状态发生变化时触发
        @Override
        public void onGpsStatusChanged(int event) {
            //获取当前状态
            gpsstatus=locationManager.getGpsStatus(null);
            switch(event){
                //第一次定位时的事件
                case GpsStatus.GPS_EVENT_FIRST_FIX:
                    break;
                //开始定位的事件
                case GpsStatus.GPS_EVENT_STARTED:
                    break;
                //发送GPS卫星状态事件
                case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
                    Toast.makeText(MainActivity.this, "GPS_EVENT_SATELLITE_STATUS", Toast.LENGTH_SHORT).show();
                    Iterable<GpsSatellite> allSatellites = gpsstatus.getSatellites();
                    Iterator<GpsSatellite> it=allSatellites.iterator();
                    int count = 0;
                    while(it.hasNext())
                    {
                        count++;
                    }
                    Toast.makeText(MainActivity.this, "Satellite Count:" + count, Toast.LENGTH_SHORT).show();
                    break;
                //停止定位事件
                case GpsStatus.GPS_EVENT_STOPPED:
                    Log.d("Location", "GPS_EVENT_STOPPED");
                    break;
            }
        }
    };


    //创建位置监听器
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
}