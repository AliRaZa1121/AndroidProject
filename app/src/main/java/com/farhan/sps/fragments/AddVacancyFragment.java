package com.farhan.sps.fragments;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.farhan.sps.R;
import com.farhan.sps.databinding.FragmentAddVacancyBinding;
import com.farhan.sps.interfaces.DialogSingleButtonListener;
import com.farhan.sps.netwrok.VolleySingleton;
import com.farhan.sps.utils.AppUtils;
import com.farhan.sps.utils.BaseFragment;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AddVacancyFragment extends BaseFragment {

    FragmentAddVacancyBinding binding;
    private View mrootView;


    public AddVacancyFragment() {
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
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_add_vacancy, container, false);

        Resources res = getResources();
        String[] companiesArray = res.getStringArray(R.array.companies);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item_selected, companiesArray);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        binding.companySpinner.setAdapter(arrayAdapter);


        mrootView = binding.getRoot();

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jobTitle = binding.etJobTitle.getText().toString();
                String jobDesc = binding.etJobDesc.getText().toString();
                String skillsRequired = binding.etPrefferedSkills.getText().toString();
                String noOfVacancies = binding.etNoOfVancies.getText().toString();

                if (jobTitle.length() == 0) {
                    binding.etJobTitle.setError("Please enter title");
                } else if (jobDesc.length() == 0) {
                    binding.etJobDesc.setError("Please enter description");
                } else if (skillsRequired.length() == 0) {
                    binding.etPrefferedSkills.setError("Please enter skills required");
                } else if (noOfVacancies.length() == 0) {
                    binding.etNoOfVancies.setError("Please enter description");
                } else {
                    insertVacancies(jobTitle, jobDesc, skillsRequired, noOfVacancies);
                }


            }
        });


        return mrootView;
    }

    private void insertVacancies(String jobTitle, String jobDesc, String skillsRequired, String noOfVacancies) {
        showProgressDialog(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUtils.APIMethods.vacancy_insert,
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
                params.put("title", jobTitle);
                params.put("description", jobDesc);
                params.put("noofvacancies", noOfVacancies);
                params.put("ownedby", skillsRequired);
                params.put("companyid", "10");
                return params;
            }

        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }
}