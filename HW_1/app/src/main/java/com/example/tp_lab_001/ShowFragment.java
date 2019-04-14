package com.example.tp_lab_001;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ShowFragment extends Fragment {

    TextView showTextView;
    View view;


    void dataSend(String getstring){

        int changeInt = Integer.parseInt(getstring);
        if ( changeInt % 2 == 0){
            showTextView.setTextColor(view.getContext().getResources().getColor(R.color.red));
        }else {
            showTextView.setTextColor(view.getContext().getResources().getColor(R.color.blue));
        }
        showTextView.setText(getstring);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.view =inflater.inflate(R.layout.fragment_show, container, false);
        showTextView = view.findViewById(R.id.showNumb);
        Log.d("numtest","sendednumbis"+Integer.toString(getArguments().getInt("getInt")));
        dataSend(Integer.toString(getArguments().getInt("getInt")));



        return view;
    }




}
