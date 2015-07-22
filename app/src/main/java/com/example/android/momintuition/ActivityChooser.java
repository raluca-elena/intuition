package com.example.android.momintuition;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


public class ActivityChooser extends Activity {
    static boolean canGetLocation = false;
    Location location;


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;//1 min/2
    double latitude;
    double longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_chooser);


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);





        // specify an adapter (see also next example)
        String[] myDataset = new String[4];
        myDataset[0] = "play";
        myDataset[1] = "eat";
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);





//TO INVESTIGATE
        FetchCordinates fetchCordinates = new FetchCordinates();
        fetchCordinates.execute();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_chooser, menu);
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

/////
public class FetchCordinates extends AsyncTask<String, Void, String> {

    public double lati = 0.0;
    public double longi = 0.0;

    public LocationManager mLocationManager;
    public mLocationListener ll;

    @Override
    protected void onPreExecute() {
        ll= new mLocationListener();
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        mLocationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 0, 0,
                ll);


    }

    @Override
    protected void onCancelled(){
        mLocationManager.removeUpdates(ll);
    }

    @Override
    protected void onPostExecute(String result) {
        Log.i("LATITUDE  LONGITUDE : ", lati + " " + longi);
        String[] params = new String[3];
        params[0] = "52.11.50.74:9000/";
        //params[1] = user;
        //params[2] = password;
        AsyncTask<String, Void, String> fm = new RequestTask().execute(params);


        RecyclerView rv = new RecyclerView(getApplicationContext());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);

    }

    @Override
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub

    //    while (this.lati == 0.0) {

    //      }
        return null;
    }

    public class mLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            int lat = (int) location.getLatitude(); // * 1E6);
            int log = (int) location.getLongitude(); // * 1E6);
            int acc = (int) (location.getAccuracy());

            String info = location.getProvider();
            try {

                //LocatorService.myLatitude=location.getLatitude();

                //LocatorService.myLongitude=location.getLongitude();

                lati = location.getLatitude();
                longi = location.getLongitude();

            } catch (Exception e) {
                // progDailog.dismiss();
                // Toast.makeText(getApplicationContext(),"Unable to get Location"
                // , Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.i("onStatusChanged", "onStatusChanged");

        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.i("onProviderEnabled", "onProviderEnabled");

        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.i("OnProviderDisabled", "OnProviderDisabled");

        }


    }

}

}


/////





