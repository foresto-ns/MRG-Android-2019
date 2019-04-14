package com.example.tp_lab_001;

import android.app.Activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class GeneralFragment extends Fragment {

    RecyclerView myRecycler;
    TextView numbSet;
    Button addNumb;

    private Integer columns;

    private String LIST_SAVED = "ListArray";
    private ArrayList<String> strings;

    public interface onTouchEventListener{
        void touchEvent(int i);
    }

    onTouchEventListener touchEventListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            touchEventListener = (onTouchEventListener) activity;

        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+ "must implements onTouuchEventListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_general, container, false);


        addNumb = view.findViewById(R.id.addNumb);
        myRecycler = view.findViewById(R.id.myRecycler);
        numbSet = view.findViewById(R.id.numbSet);

        strings = new ArrayList<>();

        if(getActivity().getResources().getConfiguration().orientation ==Configuration.ORIENTATION_LANDSCAPE){
            columns = 4;
        }else {
            columns =3 ;
        }
        myRecycler.setLayoutManager(new GridLayoutManager(view.getContext(), columns));


        if(savedInstanceState == null) {
            fillList(strings);
            Log.d("test", "null"+ strings.size());
            Toast.makeText(getContext(),"null"+strings.size(),Toast.LENGTH_SHORT).show();

        }else{
            strings = savedInstanceState.getStringArrayList(LIST_SAVED);
            Log.d("test", "create"+ strings.size());
            Toast.makeText(getContext(),"create"+strings.size(),Toast.LENGTH_SHORT).show();
        }


        final DataAdater mAdapter = new DataAdater(view.getContext(), strings, new DataAdater.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {

                touchEventListener.touchEvent(i);

            }
        });

        myRecycler.setAdapter(mAdapter);

        addNumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newNubm = strings.size()+1;
                strings.add(Integer.toString(newNubm));
                mAdapter.notifyItemChanged(strings.size());
            }
        });

        return view;
    }

    private void fillList(List<String> string) {

        for (int i = 1; i < 1001; i++) {
            string.add(i + "");
        }

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList( LIST_SAVED ,strings);
        Log.d("test", "SaVED"+ strings.size());
        Toast.makeText(getContext(),"saved"+strings.size(),Toast.LENGTH_SHORT).show();
    }


    

}
