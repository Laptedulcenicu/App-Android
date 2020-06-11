package com.example.sweetmilk.login;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

private TextView RegText;
private EditText UserName,UserPassword;
private Button LoginBtn;
OnLoginFormActivityListener loginFormActivityListener;
public interface  OnLoginFormActivityListener{
    public  void performRegister();
    public void  performLogin(String name);
}

    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_login, container, false);
       RegText= view.findViewById(R.id.textView2);
       UserName= view.findViewById(R.id.firstNumEditText);
       UserPassword= view.findViewById(R.id.firstNumEditText2);
        LoginBtn= view.findViewById(R.id.button);


        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });


       RegText.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
            loginFormActivityListener.performRegister();
           }
       });
        return view;
    }
    @Override
    public  void onAttach(Context context){
    super.onAttach(context);
        Activity activity = (Activity) context;
        loginFormActivityListener= (OnLoginFormActivityListener) activity;
    }

    private void performLogin(){
    String username = UserName.getText().toString();
    String password = UserPassword.getText().toString();

        Call<User> call= MainActivity.apiInterface.performUserLogin(username,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("Ok"))
                {
                    MainActivity.prefConfig.writeLoginStatus(true);
                    loginFormActivityListener.performLogin(response.body().getName());
                }
                else if(response.body().getResponse().equals("field"))
                {
                    MainActivity.prefConfig.displayToast("Logind Failed..Please try again..");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                MainActivity.prefConfig.displayToast("Message was not send.");


            }
        });

        UserName.setText("");
        UserPassword.setText("");
    }
}
