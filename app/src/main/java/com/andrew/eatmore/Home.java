package com.andrew.eatmore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    RecyclerView recyclerCategories, recyclerFoodItems;
    ImageView imgMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //NOT PART OF THE TUTORIAL
        imgMenu = findViewById(R.id.img_menu);

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, MainActivity.class));

            }
        });
        //NOT PART OF THE TUTORIAL
        recyclerCategories = findViewById(R.id.recycler_categories);
        recyclerFoodItems = findViewById(R.id.recycler_food);

        setCategories();
        setFoodItems(0);
    }

    private void setCategories(){
        List<FoodCategory> data = new ArrayList<>();

        FoodCategory foodCategory = new FoodCategory("Chicken", R.drawable.ic_chicken);
        FoodCategory foodCategory2 = new FoodCategory("Burger", R.drawable.ic_burger);
        FoodCategory foodCategory3 = new FoodCategory("Pizza", R.drawable.ic_pizza);
        FoodCategory foodCategory4 = new FoodCategory("Chicken", R.drawable.ic_chicken);
        FoodCategory foodCategory5 = new FoodCategory("Chicken", R.drawable.ic_chicken);

        data.add(foodCategory3);
        data.add(foodCategory);
        data.add(foodCategory2);
        data.add(foodCategory4);
        data.add(foodCategory5);

        FoodCategoryAdapter foodCategoryAdapter = new FoodCategoryAdapter(data, Home.this, new FoodCategoryAdapter.OnCategoryClickedListener() {
            @Override
            public void onCategoryClick(int position) {
                setFoodItems(position);
            }
        });

        recyclerCategories.setLayoutManager(new LinearLayoutManager(Home.this, RecyclerView.HORIZONTAL, false));
        recyclerCategories.setAdapter(foodCategoryAdapter);

        foodCategoryAdapter.notifyDataSetChanged();

    }

    private void setFoodItems(int categoryPos){
        List<FoodItem> data = new ArrayList<>();

        switch (categoryPos){
            case 0:
                FoodItem foodItem1 = new FoodItem("Pizza", 3.5f, 14, R.drawable.pizza_1);
                FoodItem foodItem2 = new FoodItem("Some Pizza", 3.5f, 14, R.drawable.pizza_2);
                FoodItem foodItem3 = new FoodItem("Crazy Pizza", 3.5f, 14, R.drawable.pizza_3);
                FoodItem foodItem4 = new FoodItem("Pizza 2", 3.5f, 14, R.drawable.pizza_4);

                data.add(foodItem1);
                data.add(foodItem2);
                data.add(foodItem3);
                data.add(foodItem4);
                break;

            case 1:
                FoodItem foodItem11 = new FoodItem("Chicken", 3.5f, 14, R.drawable.grill_chicken_1);
                FoodItem foodItem21 = new FoodItem("Chicken", 3.5f, 14, R.drawable.grill_chicken_2);
                FoodItem foodItem31 = new FoodItem("Grilled Chicken", 3.5f, 14, R.drawable.grill_chicken_3);
                FoodItem foodItem41 = new FoodItem("Chickeneee", 3.5f, 14, R.drawable.pizza_4);

                data.add(foodItem11);
                data.add(foodItem21);
                data.add(foodItem31);
                data.add(foodItem41);
                break;

            case 2:
                FoodItem foodItem111 = new FoodItem("Burger", 3.5f, 14, R.drawable.burger);
                FoodItem foodItem211 = new FoodItem("Burger2", 3.5f, 14, R.drawable.burger_two);

                data.add(foodItem111);
                data.add(foodItem211);
                break;
        }

        FoodItemAdapter foodItemAdapter = new FoodItemAdapter(data);
        recyclerFoodItems.setLayoutManager(new LinearLayoutManager(Home.this, RecyclerView.HORIZONTAL, false));
        recyclerFoodItems.setAdapter(foodItemAdapter);
        foodItemAdapter.notifyDataSetChanged();
    }
}