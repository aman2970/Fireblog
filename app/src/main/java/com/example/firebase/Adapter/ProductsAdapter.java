package com.example.firebase.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firebase.DemoAreaActivity;
import com.example.firebase.DescriptionActivity;
import com.example.firebase.Model.Product;
import com.example.firebase.R;
import com.example.firebase.StorageDemoActivity;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {
    private Activity context;
    private List<Product> productList;

    public ProductsAdapter(Activity context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.MyViewHolder holder, int position) {
        Glide.with(context).load(productList.get(position).getImage()).into(holder.productIv);

        holder.itemCv.setOnClickListener(v -> {
            Intent intent = new Intent(context, DescriptionActivity.class);
            intent.putExtra("content", productList.get(position).getContent());
            intent.putExtra("url", productList.get(position).getArchitecture());
            intent.putExtra("codeone", productList.get(position).getCodeone());
            intent.putExtra("codetwo", productList.get(position).getCodetwo());
            intent.putExtra("codethree", productList.get(position).getCodethree());
            intent.putExtra("codefour", productList.get(position).getCodefour());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView productIv;
        public CardView itemCv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productIv = itemView.findViewById(R.id.productIv);
            itemCv = itemView.findViewById(R.id.itemCv);
        }
    }
}
