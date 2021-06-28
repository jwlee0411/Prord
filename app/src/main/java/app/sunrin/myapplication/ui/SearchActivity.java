package app.sunrin.myapplication.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import app.sunrin.myapplication.R;
import app.sunrin.myapplication.ui.dashboard.DashboardViewModel;
import app.sunrin.myapplication.ui.dashboard.Data;

public class SearchActivity extends AppCompatActivity {

    public String[][] getData(String word) {
        return getData(word, 5);
    }

    public String[][] getData(String word, int max) {
        try {
            JSONObject json = new JSONObject();
            json.put("word", word);
            json.put("max", max);
            String url = "http://sunrin.soohy.uk:3000/content/" + getStringArrayPref(this);
            //String url = "http://discord.com";
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.addRequestProperty("User-Agent", "Mozilla/4.76");
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Accept-Charset", "UTF-8");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            OutputStream os = connection.getOutputStream();
            os.write(json.toString().getBytes("utf8"));
            os.flush();
            os.close();

            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            connection.disconnect();

            //JSONObject jsonObj = new JSONObject(sb.toString());
            JSONArray arr = new JSONArray(sb.toString());
            String[][] result = new String[arr.length()][2];
            for (int i = 0; i < arr.length(); i++) {
                result[i][0] = arr.getJSONObject(i).getString("word");
                result[i][1] = arr.getJSONObject(i).getString("detail");
            }

            return result;
        } catch (Exception e) {
            System.out.println("ㅎㅎ 안되네");
            return new String[0][0];
        }
    }

    private ArrayList getStringArrayPref(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString("subject", null);
        ArrayList urls = new ArrayList();

        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);

                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }



    public app.sunrin.myapplication.ui.RecyclerAdapter adapter;
    RecyclerView recyclerView;
    Button buttonSearch;
    EditText textSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        recyclerView = findViewById(R.id.recyclerView2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        buttonSearch = findViewById(R.id.buttonSearch4);

        adapter = new app.sunrin.myapplication.ui.RecyclerAdapter();
        recyclerView.setAdapter(adapter);


        //buttonSearch = root.findViewById(R.id.buttonSearch);
        textSearch = findViewById(R.id.textSearch2);

        System.out.println("아아 됐으요");


        buttonSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                adapter = new app.sunrin.myapplication.ui.RecyclerAdapter();
                recyclerView.setAdapter(adapter);

                String[][] str = getData(textSearch.getText().toString(), 10);
                //System.out.println(str[0][0]);

                for(int i = 0; i < str.length;i++)
                {
                    // Log.d("F", str[i][0] + " " + str[i][1]);
                    // textSearch.setText(textSearch.getText().toString()+str[i][0] + "\n\n" + str[i][1] + "\n\n\n");
                    Data data = new Data();
                    data.setTextBeforeChange(str[i][0]);
                    data.setTextMeaning(str[i][1]);
                    data.setTextAfterChange(str[i][0]);
                    data.setTextEnglish(str[i][0]);

                    adapter.addItem(data);

                }


                adapter.notifyDataSetChanged();
            }
        });










    }
}
