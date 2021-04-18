package com.farhan.sps.fragments;

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
import com.farhan.sps.databinding.FragmentAddStudentBinding;
import com.farhan.sps.interfaces.DialogSingleButtonListener;
import com.farhan.sps.netwrok.VolleySingleton;
import com.farhan.sps.utils.AppUtils;
import com.farhan.sps.utils.BaseFragment;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class AddStudentFragment extends BaseFragment {

    FragmentAddStudentBinding binding;
    private View mrootView;
    public static final String VALID = "correct";


    public AddStudentFragment() {
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
                inflater, R.layout.fragment_add_student, container, false);

        mrootView = binding.getRoot();


        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stuName = binding.etStuName.getText().toString();
                String emailString = isValidEmail(binding.etStuId.getText().toString());
                String stuPassword = binding.etStuPassword.getText().toString();
                String stuBrach = binding.etStuBranchId.getText().toString();
                String stuPercentage = binding.etPercentage.getText().toString();
                if (stuName.length() == 0) {
                    binding.etStuName.setError("Please enter name");
                }
                if (!emailString.equals(VALID)) {
                    binding.etStuId.setError("Enter valid email address");
                } else if (stuPassword.length() == 0) {
                    binding.etStuPassword.setError("Please enter password");
                } else if (stuBrach.length() == 0) {
                    binding.etStuBranchId.setError("Please enter branch");
                } else if (stuPercentage.length() == 0) {
                    binding.etPercentage.setError("Please enter percentage");
                } else {
                    insertStudent(stuName, binding.etStuId.getText().toString(), stuPassword, stuBrach, stuPercentage);
                }


            }
        });


        return mrootView;
    }

    private void insertStudent(String stuName, String emailString, String stuPassword, String stuBrach, String stuPercentage) {

        showProgressDialog(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUtils.APIMethods.student_insert,
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
                params.put("name", stuName);
                params.put("email", emailString);
                params.put("password", stuPassword);
                params.put("branch", stuBrach);
                params.put("percentage", stuPercentage);
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