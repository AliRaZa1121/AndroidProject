package com.farhan.sps.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.farhan.sps.R;
import com.farhan.sps.activities.CompanyActivity;
import com.farhan.sps.models.CompanyModel;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.companyViewHolder> {

    private final Context context;
    private final List<CompanyModel> itemList;

    public CompanyAdapter(List<CompanyModel> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public companyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new companyViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull companyViewHolder holder, int position) {

        CompanyModel item = itemList.get(position);
        if (item.getName() != null)
            holder.tv_name.setText(itemList.get(position).getName());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, CompanyActivity.class);
                intent.putExtra("title", "vacancies");
                intent.putExtra("companyId", item.getId());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class companyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public CardView cardView;


        public companyViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.comapny_list_item, parent, false));
            tv_name = itemView.findViewById(R.id.tv_company_name);
            cardView = itemView.findViewById(R.id.card_view);
        }


    }


}
