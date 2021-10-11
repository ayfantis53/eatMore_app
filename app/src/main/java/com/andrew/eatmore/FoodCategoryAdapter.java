package com.andrew.eatmore;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class FoodCategoryAdapter extends RecyclerView.Adapter<FoodCategoryAdapter.CategoryHolder> {

    List<FoodCategory> data;
    Context context;
    int selectedItemPos = 0;
    OnCategoryClickedListener listener;

    public FoodCategoryAdapter(List<FoodCategory> data, Context context, OnCategoryClickedListener listener){
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.category_view_holder,parent, false);

        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.img.setImageResource(data.get(position).getImage());
        holder.txtName.setText(data.get(position).getName());

        if (position == selectedItemPos){
            holder.txtName.setTextColor(context.getColor(R.color.red));
            holder.img.setColorFilter(ContextCompat.getColor(context,R.color.red), PorterDuff.Mode.SRC_IN);
            holder.cardCategory.setOutlineAmbientShadowColor(context.getColor(R.color.red));
            holder.cardCategory.setOutlineSpotShadowColor(context.getColor(R.color.red));
            holder.cardCategory.setStrokeWidth(2);
        }else{
            holder.cardCategory.setOutlineAmbientShadowColor(context.getColor(R.color.gray_one));
            holder.cardCategory.setOutlineSpotShadowColor(context.getColor(R.color.gray_one));
            holder.cardCategory.setStrokeWidth(0);
            holder.img.setColorFilter(ContextCompat.getColor(context,R.color.gray_one), PorterDuff.Mode.SRC_IN);
            holder.txtName.setTextColor(context.getColor(R.color.gray_one));
        }
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        ImageView img;
        MaterialCardView cardCategory;
        
        public CategoryHolder(@NonNull View itemView){
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_title);
            img = itemView.findViewById(R.id.img_category);
            cardCategory = itemView.findViewById(R.id.card_category);
            
            cardCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedItemPos = getAdapterPosition();
                    if(listener != null){
                        listener.onCategoryClick(getAdapterPosition());
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }
    public interface OnCategoryClickedListener{
        void onCategoryClick(int position);
    }
}
