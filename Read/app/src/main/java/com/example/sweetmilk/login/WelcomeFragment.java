package com.example.sweetmilk.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Scanner;


public class WelcomeFragment extends Fragment {
    private TextView textView;
    private Button BnLogOut;
    OnLogoutListener logoutListener;

    public interface  OnLogoutListener
    {
        public void logoutPerformed();
    }

    private OnFragmentInteractionListener mListener;

    public WelcomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_welcome, container, false);
        textView=view.findViewById(R.id.textView4);
        BnLogOut=view.findViewById(R.id.button3);
        textView.setText("Welcome "+MainActivity.prefConfig.readName());
        Button goToScanBtn = (Button) view.findViewById(R.id.goToScanTest);
        goToScanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(view.getContext(), Scanner_Activity.class);
                startActivity(startIntent);
            }
        });

        BnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            logoutListener.logoutPerformed();
            }
        });
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity= (Activity) context;
        logoutListener= (OnLogoutListener) activity;

    }




    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
