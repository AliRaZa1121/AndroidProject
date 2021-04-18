package com.farhan.sps.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.farhan.sps.R;
import com.farhan.sps.models.MainItemModel;

import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainListItemViewHolder> {

    private final List<MainItemModel> itemList;
    private final Context context;
    private final OnItemClickListener listener;

    public MainListAdapter(List<MainItemModel> itemList, Context context, OnItemClickListener listener) {
        this.itemList = itemList;
        this.context = context;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(MainItemModel item);
    }


    @Override
    public MainListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.main_list_item, null);


        MainListItemViewHolder viewHolder = new MainListItemViewHolder(layoutView);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainListItemViewHolder holder, int position) {

        MainItemModel item = itemList.get(position);

        holder.titleTv.setText(itemList.get(position).getTitle());
        Glide.with(context).load(itemList.get(position).getDrawable()).into(holder.imageView);
        holder.mainLinear.setBackgroundColor(Color.parseColor(itemList.get(position).getBackgroundColor()));


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item);

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }


    public class MainListItemViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTv;
        public ImageView imageView;
        public LinearLayout mainLinear;
        public CardView cardView;

        public MainListItemViewHolder(View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.title_tv);
            cardView = itemView.findViewById(R.id.card_view);
            imageView = itemView.findViewById(R.id.imageView);
            mainLinear = itemView.findViewById(R.id.mainLinear);
        }


    }
}
