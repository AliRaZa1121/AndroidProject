package com.farhan.sps.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.farhan.sps.R;
import com.farhan.sps.models.UserModel;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private final List<UserModel.User> itemList;
    private final Context context;

    public StudentAdapter(List<UserModel.User> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {

        UserModel.User item = itemList.get(position);
        if (item.getName() != null)
            holder.tv_student_name.setText(itemList.get(position).getName());
        if (item.getBranch() != null)
            holder.tv_student_branch.setText(itemList.get(position).getBranch());
        if (item.getPercentage() != null)
            holder.tv_student_percentage.setText(itemList.get(position).getPercentage());
        if (item.getEmail() != null)
            holder.tv_student_email.setText(itemList.get(position).getEmail());


        if (item.getUserTypeId().equals("2")) {
            holder.percentageLinear.setVisibility(View.GONE);
            holder.branchLinear.setVisibility(View.GONE);
            Glide.with(context).load(R.drawable.view_tpo).into(holder.image);


        }

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SweetAlertDialog alertDialog;
                alertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
                alertDialog.setTitleText("Info");
                alertDialog.setCancelable(false);
                alertDialog.setContentText("Password: " + item.getPassword());
                alertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });
                alertDialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_student_name, tv_student_email, tv_student_branch, tv_student_percentage;
        public CardView cardView;
        public Button button;
        public LinearLayout percentageLinear, branchLinear;
        public ImageView image;


        public StudentViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_item, parent, false));

            tv_student_name = itemView.findViewById(R.id.tv_student_name);
            tv_student_email = itemView.findViewById(R.id.tv_student_email);
            tv_student_branch = itemView.findViewById(R.id.tv_student_branch);
            tv_student_percentage = itemView.findViewById(R.id.tv_student_percentage);
            cardView = itemView.findViewById(R.id.card_view);
            button = itemView.findViewById(R.id.btn_get_pass);
            image = itemView.findViewById(R.id.image);
            percentageLinear = itemView.findViewById(R.id.percent_linear);
            branchLinear = itemView.findViewById(R.id.branch_linear);
        }


    }


}
