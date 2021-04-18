package com.farhan.sps.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.farhan.sps.R;
import com.farhan.sps.adapters.StudentAdapter;
import com.farhan.sps.databinding.FragmentViewTPOBinding;
import com.farhan.sps.models.UserModel;
import com.farhan.sps.netwrok.VolleySingleton;
import com.farhan.sps.utils.AppUtils;
import com.farhan.sps.utils.BaseFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewTPOFragment extends BaseFragment {

    StudentAdapter adapter;
    FragmentViewTPOBinding binding;


    public ViewTPOFragment() {
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
                inflater, R.layout.fragment_view_t_p_o, container, false);

        getTPO();

        return binding.getRoot();

    }

    private void getTPO() {

        showProgressDialog(getContext());

        String url = AppUtils.APIMethods.tpo_fetch;
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                url, null,
                JsonResponse -> {
                    dismissProgressDialog();
                    String response = JsonResponse.toString();
                    Log.d("TAG", "onResponse: " + response);

                    Gson gson = new Gson();
                    String jsonOutput = response;
                    Type listType = new TypeToken<List<UserModel.User>>() {
                    }.getType();
                    List<UserModel.User> students = gson.fromJson(jsonOutput, listType);
                    adapter = new StudentAdapter(students, getContext());
                    binding.recyclerView.setHasFixedSize(true);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    binding.recyclerView.setLayoutManager(linearLayoutManager);
                    binding.recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    runLayoutAnimation(binding.recyclerView);

                    if (students.size() == 0)
                        binding.emptyView.setVisibility(View.VISIBLE);



                }, error -> {
            String body;
            dismissProgressDialog();

            try {

                if (error.networkResponse.data != null) {
                    try {
                        body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                        System.out.println(body);
                        JSONObject errorBody = new JSONObject(body);


                        String message = errorBody.getString("message");

                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }) {


            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }


        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(getContext()).addToRequestQueue(jsonObjReq);


    }


    private void runLayoutAnimation(RecyclerView recyclerView) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation);
        recyclerView.setLayoutAnimation(controller);
        if (recyclerView.getAdapter() != null)
            recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }


}