package app.sunrin.myapplication.ui.dashboard;

import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import app.sunrin.myapplication.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;


    public String[][] getData(String word) {
        return getData(word, 5);
    }

    public String[][] getData(String word, int max) {
        try {
            JSONObject json = new JSONObject();
            json.put("word", word);
            json.put("max", max);
            String url = "http://sunrin.soohy.uk:3000/content/정보보안";
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


    public app.sunrin.myapplication.ui.dashboard.RecyclerAdapter adapter;
    RecyclerView recyclerView;
    Button buttonSearch;
    EditText textSearch;
    Switch switch5;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        buttonSearch = root.findViewById(R.id.buttonSearch);

        adapter = new app.sunrin.myapplication.ui.dashboard.RecyclerAdapter();
        recyclerView.setAdapter(adapter);


        //buttonSearch = root.findViewById(R.id.buttonSearch);
        textSearch = root.findViewById(R.id.textSearch);

        System.out.println("아아 됐으요");
        buttonSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                adapter = new app.sunrin.myapplication.ui.dashboard.RecyclerAdapter();
                recyclerView.setAdapter(adapter);

                String[][] str = getData(textSearch.getText().toString(), 10);
                System.out.println(str[0][0]);

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







/*
        for (int i = 0; i < ChangeArr.length; i++) {

            tiatt.jw.ui.home.Data data = new tiatt.jw.ui.home.Data();
            data.setTextBeforeChange(ChangeArr[i][0]);
            data.setTextMeaning(ChangeArr[i][3]);
            data.setTextAfterChange(ChangeArr[i][1]);
            data.setTextEnglish(ChangeArr[i][2]);
            // data.setTextOtherWord(ChangeArr[i][4]);

            adapter.addItem(data);
        }
*/

        //     adapter.notifyDataSetChanged();


        //   switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        //      @Override
        //       public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        //           search();
//
        //       }
        //    });


        textSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int a, int a1, int a2) {
                //  search();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });









        return root;
    }


}