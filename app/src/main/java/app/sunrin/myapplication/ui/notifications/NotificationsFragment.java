package app.sunrin.myapplication.ui.notifications;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.sunrin.myapplication.R;
import app.sunrin.myapplication.ui.SearchActivity;
import app.sunrin.myapplication.ui.dashboard.DashboardFragment;
import app.sunrin.myapplication.ui.home.HomeFragment;

public class NotificationsFragment extends Fragment {


    private NotificationsViewModel notificationsViewModel;

    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;



    private void setStringArrayPref(Context context, String key, String values) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();


            a.put(values);


        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }

        editor.apply();
    }





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        button2 = root.findViewById(R.id.button2);
        button3 = root.findViewById(R.id.button3);
        button4 = root.findViewById(R.id.button4);
        button5 = root.findViewById(R.id.button5);
        button6 = root.findViewById(R.id.button6);
        button7 = root.findViewById(R.id.button7);
        button8 = root.findViewById(R.id.button8);
        button9 = root.findViewById(R.id.button9);

        final Button buttons[] = {
                button2, button3, button4, button5, button6, button7, button8, button9
        };

        //  final BottomNavigationView navView = getView().findViewById(R.id.nav_view);

        for(int i = 0; i<8; i++)
        {
            System.out.println("시작해");
            final int finalI = i;
            System.out.println("퇴보해" + i);
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setStringArrayPref(getActivity().getApplicationContext(), "subject", buttons[finalI].getText().toString());
                    System.out.println(buttons[finalI].getText().toString());

                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    startActivity(intent);




                }
            }
            );


        }


        return root;
    }






}