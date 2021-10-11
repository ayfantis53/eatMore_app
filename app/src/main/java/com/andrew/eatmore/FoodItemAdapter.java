package com.andrew.eatmore;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodHolder> {

    List<FoodItem> data;

    int selectedFoodItemPos = 0;
    public FoodItemAdapter(List<FoodItem> data){
        this.data = data;
    }

    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.food_view_holder,parent,false);
        return new FoodHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemAdapter.FoodHolder holder, int position) {
        holder.txtPrice.setText(String.format("$%d", data.get(position).getPrice()));
        holder.txtName.setText(data.get(position).getName());
        holder.imgFood.setImageResource(data.get(position).getImage());
        holder.ratingBar.setRating(data.get(position).getRating());

        if(selectedFoodItemPos == position){
            holder.txtName.setTextColor(Color.WHITE);
            holder.txtPrice.setTextColor(Color.WHITE);
            holder.cardView.animate().scaleX(1.1f);
            holder.cardView.animate().scaleY(1.1f);
            holder.ll.setBackgroundResource(R.drawable.launcher_image);
        }else{
            holder.txtName.setTextColor(Color.BLACK);
            holder.txtPrice.setTextColor(Color.BLACK);
            holder.cardView.animate().scaleX(1f);
            holder.cardView.animate().scaleY(1f);
            holder.ll.setBackgroundResource(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class FoodHolder extends RecyclerView.ViewHolder{

        ImageView imgFood;
        TextView txtPrice, txtName;
        RatingBar ratingBar;
        CardView cardView;
        LinearLayout ll;

        public FoodHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.food_title);
            txtPrice = itemView.findViewById(R.id.txt_price);
            ratingBar = itemView.findViewById(R.id.rating);
            imgFood = itemView.findViewById(R.id.food_img);
            ll = itemView.findViewById(R.id.ll_background);
            cardView = itemView.findViewById(R.id.food_card);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedFoodItemPos = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }

}
