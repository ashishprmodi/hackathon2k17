package app.siemens.com.sos;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import app.siemens.com.sos.model.TaskModel;

public class MainActivity extends AppCompatActivity {

    Button button;

    List<TaskModel> list = new ArrayList<>();

    String[] tasks = new String[]{
    };

    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] alertIcons = new int[]{
            R.drawable.alert_icon,
            R.drawable.checkmark
    };

    // Array of strings to store currencies


    String[] status = new String[]{
            "www.google.com"

    };


    String[] latLong = new String[]{
            "https://www.google.com/maps/@18.5460323,73.7727039,13z"
    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();
        String json = loadJSONFromAsset();

        // Each row in the list stores country name, currency and flag
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        list = gson.fromJson(json, new TypeToken<List<TaskModel>>() {
        }.getType());
        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("task", list.get(i).getUsername());

            if(list.get(i).getAcknowledged().equalsIgnoreCase("true")){
                hm.put("alertIcon", Integer.toString(alertIcons[1]));
            }
            else {
                hm.put("alertIcon", Integer.toString(alertIcons[0]));
            }
            hm.put("location", "Location : " + list.get(i).getGeoLocation());
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = {"alertIcon", "task", "location"};

        // Ids of views in listview_layout
        int[] to = {R.id.alertIcon, R.id.task, R.id.location};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = (ListView) findViewById(R.id.listview);

        // Setting the adapter to the listView
        listView.setAdapter(adapter);

        // Item Click Listener for the listview
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
                // Getting the Container Layout of the ListView
                LinearLayout linearLayoutParent = (LinearLayout) container;

                // Getting the inner Linear Layout
                LinearLayout linearLayoutChild = (LinearLayout) linearLayoutParent.getChildAt(1);

                // Getting the Country TextView for status
                TextView status = (TextView) linearLayoutChild.getChildAt(2);

                Toast.makeText(getBaseContext(), "Acknowledged", Toast.LENGTH_SHORT).show();

                //The one withthe image on it
                ImageView linearLayoutOuterChild = (ImageView) linearLayoutParent.getChildAt(0);
                //ImageView alert = (ImageView) linearLayoutChild.getChildAt(0);
                linearLayoutOuterChild.setImageResource(R.drawable.checkmark);

                new SendGetRequest().execute();

            }
        };

        // Setting the item click listener for the listview
        listView.setOnItemClickListener(itemClickListener);
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("tasks.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public class SendGetRequest extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL("http://52.178.41.80:8090/alarm/ackAll");



                KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                trustStore.load(null, null);
                TrustManagerFactory tmf =
                        TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                tmf.init(trustStore);
                SSLContext ctx = SSLContext.getInstance("TLS");
                ctx.init(null, tmf.getTrustManagers(), null);
                SSLSocketFactory sslFactory = ctx.getSocketFactory();

                urlConnection = (HttpURLConnection) url
                        .openConnection();
                //urlConnection.setSSLSocketFactory(new MySSlFactory(trustStore));
//                urlConnection.setReadTimeout( 10000 /*milliseconds*/ );
//                urlConnection.setConnectTimeout( 15000 /* milliseconds */ );
                //urlConnection.setRequestMethod("GET");
//                urlConnection.setDoInput(true);
//                urlConnection.setDoOutput(true);


                //make some HTTP header nicety
//                urlConnection.setRequestProperty("Content-Type", "application/json");

                int responseCode=urlConnection.getResponseCode();
                StringBuffer sb = new StringBuffer("");
                if (responseCode == HttpsURLConnection.HTTP_OK || responseCode == HttpsURLConnection.HTTP_CREATED) {

                    BufferedReader br=new BufferedReader(
                            new InputStreamReader(
                                    urlConnection.getInputStream()));

                    String line="";

                    while((line = br.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    br.close();
                    return sb.toString();
                }
                else {
                    return new String("false : "+responseCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();

                }
            }
            return new String("false ");
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + result);
           /* Toast.makeText(getApplicationContext(), result,
                    Toast.LENGTH_LONG).show();*/
        }

        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {

        }
    }



}

