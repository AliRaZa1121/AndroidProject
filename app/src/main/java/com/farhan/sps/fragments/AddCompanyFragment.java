package com.farhan.sps.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.farhan.sps.R;
import com.farhan.sps.databinding.FragmentAddCompanyBinding;
import com.farhan.sps.interfaces.DialogSingleButtonListener;
import com.farhan.sps.netwrok.VolleySingleton;
import com.farhan.sps.utils.AppUtils;
import com.farhan.sps.utils.BaseFragment;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class AddCompanyFragment extends BaseFragment {


    FragmentAddCompanyBinding binding;
    private View mrootView;

    public AddCompanyFragment() {
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
                inflater, R.layout.fragment_add_company, container, false);

        mrootView = binding.getRoot();

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String companyName = binding.etCompanyName.getText().toString();

                if (companyName.length() == 0) {
                    binding.etCompanyName.setError("Please enter name");
                }
                 else {
                    insertCompany(companyName);
                }


            }
        });




        return mrootView;

    }

    private void insertCompany(String companyName) {
        showProgressDialog(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUtils.APIMethods.company_insert,
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
                params.put("name", companyName);
                return params;
            }

        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }
}