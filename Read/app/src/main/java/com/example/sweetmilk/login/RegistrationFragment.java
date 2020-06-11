package com.example.sweetmilk.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationFragment extends Fragment {
private EditText Name,UserName,UserPassword;
private Button BnRegister;

    public RegistrationFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_registration, container, false);
        Name = view.findViewById(R.id.firstNumEditText3);
        UserName = view.findViewById(R.id.firstNumEditText4);
        UserPassword = view.findViewById(R.id.firstNumEditText5);
        BnRegister = view.findViewById(R.id.button2);

        BnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();
            }
        });
        return view;
    }

    public void performRegistration()
    {
        String name =Name.getText().toString();
        String username =UserName.getText().toString();
        String password =UserPassword.getText().toString();
        Call<User> call = MainActivity.apiInterface.performRegistration(name,username,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                  if(response.body().getResponse().equals("ok"))
                  {
                      MainActivity.prefConfig.displayToast("Registration success...");

                  }
                  else if(response.body().getResponse().equals("exist"))
                  {
                      MainActivity.prefConfig.displayToast("User already exist...");
                  }
                  else if(response.body().getResponse().equals("error"))
                  {
                      MainActivity.prefConfig.displayToast("Something is wrong...");
                  }


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                MainActivity.prefConfig.displayToast("Message was not send.");


            }
        });

        Name.setText("");
        UserName.setText("");
        UserPassword.setText("");






    }


}
