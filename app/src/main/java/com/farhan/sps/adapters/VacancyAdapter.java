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
import com.farhan.sps.models.VacancyModel;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class VacancyAdapter extends RecyclerView.Adapter<VacancyAdapter.VacancyViewHolder> {

    private final List<VacancyModel> itemList;
    private final Context context;

    public VacancyAdapter(List<VacancyModel> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public VacancyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VacancyViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull VacancyViewHolder holder, int position) {

        VacancyModel item = itemList.get(position);
        if (item.getTitle() != null)
            holder.tv_name.setText(itemList.get(position).getTitle());
        if (item.getDescription() != null)
            holder.tv_desc.setText(itemList.get(position).getDescription());
        if (item.getVacancyCreated() != null)
            holder.tv_date.setText(itemList.get(position).getVacancyCreated());
        if (item.getNoOfVacancies() != null)
            holder.tv_no.setText(itemList.get(position).getNoOfVacancies());




        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SweetAlertDialog alertDialog;
                alertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
                alertDialog.setTitleText("Info");
                alertDialog.setCancelable(false);
                alertDialog.setContentText("Under Development");
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

    public static class VacancyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_desc, tv_date, tv_no;
        public CardView cardView;
        public Button button;


        public VacancyViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.vacancy_list_item, parent, false));

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_no = itemView.findViewById(R.id.tv_no);
            cardView = itemView.findViewById(R.id.card_view);
            button = itemView.findViewById(R.id.btn_get_pass);

        }


    }


}
