package com.farhan.sps.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.farhan.sps.R;
import com.farhan.sps.activities.DashboardActivity;
import com.farhan.sps.activities.LoginActivity;
import com.farhan.sps.databinding.FragmentAddTPOBinding;
import com.farhan.sps.interfaces.DialogSingleButtonListener;
import com.farhan.sps.models.UserModel;
import com.farhan.sps.netwrok.VolleySingleton;
import com.farhan.sps.utils.AppUtils;
import com.farhan.sps.utils.BaseFragment;
import com.farhan.sps.utils.SharedPrefrencesManager;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AddTPOFragment extends BaseFragment {


    FragmentAddTPOBinding binding;
    private View mrootView;
    public static final String VALID = "correct";

    public AddTPOFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_add_t_p_o, container, false);

        mrootView = binding.getRoot();


        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tpoName = binding.etTpoName.getText().toString();
                String emailString = isValidEmail(binding.etTpoId.getText().toString());
                String tpoPassword = binding.etPassword.getText().toString();
                if (tpoName.length() == 0) {
                    binding.etTpoName.setError("Please enter name");
                }
                if (!emailString.equals(VALID)) {
                    binding.etTpoId.setError("Enter valid email address");
                } else if (tpoPassword.length() == 0) {
                    binding.etTpoName.setError("Please enter password");
                } else {
                    insertTPO(tpoName, binding.etTpoId.getText().toString(), tpoPassword);
                }


            }
        });


        return mrootView;
    }

    private void insertTPO(String tpoName, String emailString, String tpoPassword) {


        showProgressDialog(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUtils.APIMethods.tpo_insert,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "onResponse: " + response);
                        dismissProgressDialog();
                        showSingleButtonDialog(getContext(), response, new DialogSingleButtonListener() {
                            @Override
                            public void positiveButtonClicked(SweetAlertDialog dialog) {
                                dialog.dismissWithAnimation();
                                getActivity().onBackPressed();
                            }
                        });
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dismissProgressDialog();
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", tpoName);
                params.put("email", emailString);
                params.put("password", tpoPassword);
                return params;
            }

        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);


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