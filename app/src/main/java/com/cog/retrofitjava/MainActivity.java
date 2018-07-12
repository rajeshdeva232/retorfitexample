package com.cog.retrofitjava;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.cog.api.Api;
import com.cog.model.SignUpResponse;

public class MainActivity extends AppCompatActivity {

    EditText etUserName, etEmail, etPassword;
    SignUpResponse signUpResponse;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUserName = findViewById(R.id.username);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDetails();
            }
        });


    }

    private void checkDetails() {
     if (validate(etUserName)&&validate(etEmail)&&validate(etPassword))
     {
         signUp();
     }


    }

    private void signUp() {
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        Api.getClient().registration(etUserName.getText().toString().trim(),
                                      etEmail.getText().toString().trim(),
                                      etPassword.getText().toString().trim(), new Callback<SignUpResponse>() {
                    @Override
                    public void success(SignUpResponse signUpResponse1, Response response) {

                        System.out.println("responce form signin : "+response.toString());
                        progressDialog.dismiss();
                        signUpResponse = signUpResponse1;
                        Toast.makeText(getApplicationContext(),"successfully sign in ",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        System.out.println("responce form signin : error "+error.toString());
                        Toast.makeText(getApplicationContext(),"Please Try again later",Toast.LENGTH_LONG).show();
                    }
                });
    }

    private Boolean validate(EditText editText)
    {
        if (editText.getText().toString().trim().length()>0)
        {
            return true;
        }
        editText.setError("Please Fill this");
        editText.requestFocus();
        return false;
    }
}
