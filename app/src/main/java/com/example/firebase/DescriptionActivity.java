package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.firebase.databinding.ActivityDescriptionBinding;

public class DescriptionActivity extends AppCompatActivity {
    private ActivityDescriptionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String url = getIntent().getStringExtra("url");
        String codeone = getIntent().getStringExtra("codeone");
        String codetwo = getIntent().getStringExtra("codetwo");
        String codethree = getIntent().getStringExtra("codethree");
        String codefour = getIntent().getStringExtra("codefour");

        if(codethree != null){
            binding.codethreeIv.setVisibility(View.VISIBLE);
        }else{
            binding.codethreeIv.setVisibility(View.GONE);
        }

        if(codefour != null){
            binding.codefourIv.setVisibility(View.VISIBLE);
        }else{
            binding.codefourIv.setVisibility(View.GONE);
        }


        Glide.with(getApplicationContext()).load(url).into(binding.descriptionIv);
        Glide.with(getApplicationContext()).load(codeone).into(binding.codeoneIv);
        Glide.with(getApplicationContext()).load(codetwo).into(binding.codetwoIv);
        Glide.with(getApplicationContext()).load(codethree).into(binding.codethreeIv);
        Glide.with(getApplicationContext()).load(codefour).into(binding.codefourIv);

        String content = getIntent().getStringExtra("content");
        String new_line = content.replace("_b","\n");

        binding.contentTv.setText(new_line);







    }
}