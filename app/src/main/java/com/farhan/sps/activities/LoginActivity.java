package com.farhan.sps.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.farhan.sps.R;
import com.farhan.sps.databinding.ActivityLoginBinding;
import com.farhan.sps.interfaces.DialogSingleButtonListener;
import com.farhan.sps.models.UserModel;
import com.farhan.sps.netwrok.NetworkHelper;
import com.farhan.sps.netwrok.VolleySingleton;
import com.farhan.sps.utils.AppUtils;
import com.farhan.sps.utils.BaseActivity;
import com.farhan.sps.utils.SharedPrefrencesManager;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding binding;
    public static final String VALID = "correct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);


        Animation emailAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_right_in);
        emailAnimation.reset();
        binding.emailWrapper.startAnimation(emailAnimation);

        Animation passwrodAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_left_in);
        passwrodAnimation.reset();
        binding.passwordWrapper.startAnimation(passwrodAnimation);

        Animation logoAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_top_in);
        logoAnimation.reset();
        binding.logo.startAnimation(logoAnimation);


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (new NetworkHelper(LoginActivity.this).isNetworkAvailable()) {
                    if (isValid()) {
                        String emailAddress = binding.etEmail.getText().toString().trim();
                        String password = binding.etPassword.getText().toString().trim();
                        loginUser(emailAddress, password);
                    }
                } else {

                    showSingleButtonDialog(LoginActivity.this, "Please connect to internet", new DialogSingleButtonListener() {
                        @Override
                        public void positiveButtonClicked(SweetAlertDialog dialog) {
                            dialog.dismissWithAnimation();
                        }
                    });
                }


            }
        });


    }

    private void loginUser(String emailAddress, String password) {


        showProgressDialog(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUtils.APIMethods.Login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "onResponse: " + response);
                        dismissProgressDialog();
                        try {
                            UserModel.User object = (UserModel.User) AppUtils.parseJSONObject(response, UserModel.User.class);
                            if (SharedPrefrencesManager.getInstance(LoginActivity.this).login(object, 1)) {
                                Intent mainIntent = new Intent(LoginActivity.this, DashboardActivity.class);
                                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mainIntent);
                                finish();

                            } else
                                Toast.makeText(LoginActivity.this, "Some error occurred", Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            showSingleButtonDialog(LoginActivity.this, response, SweetAlertDialog::dismissWithAnimation);

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dismissProgressDialog();

                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", emailAddress);
                params.put("password", password);
                return params;
            }

        };

        VolleySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);




    }


    private boolean isValid() {
        boolean isValid = true;

        String emailString = isValidEmail(binding.etEmail.getText().toString());
        String passwordString = binding.etPassword.getText().toString();

        if (!emailString.equals(VALID)) {
            isValid = false;
            binding.etEmail.setError("Enter valid email address");
        } else if (passwordString.length() == 0) {
            isValid = false;
            binding.etPassword.setError("Enter password");
        }

        return isValid;
    }


    public static String isValidEmail(String email) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return VALID;
        if (email.trim().length() == 0)
            return "Enter email address";
        else
            return "Enter valid email address";
    }


}