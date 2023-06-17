package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.firebase.databinding.ActivityStorageDemoBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class StorageDemoActivity extends AppCompatActivity {
    private ActivityStorageDemoBinding binding;
    private Uri resultUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStorageDemoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // UPLOAD TO FIREBASE CLOUD STORAGE

/*        binding.testImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 1);
        });

        binding.uploadButton.setOnClickListener(v -> {
            if(resultUri != null){
                final StorageReference filePath = FirebaseStorage.getInstance().getReference().child("demo_area_image");

                UploadTask uploadTask = filePath.putFile(resultUri);
                uploadTask.addOnSuccessListener(taskSnapshot -> {
                    Toast.makeText(this, "IMAGE UPLOADED", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(e -> {
                    Toast.makeText(this, "IMAGE FAILED", Toast.LENGTH_SHORT).show();
                });

            }

        });*/


        //DOWNLOAD A FILE FROM FIREBASE CLOUD STORAGE

     //   binding.downloadButton.setOnClickListener(v -> {
            //GET THE URL AND LOAD IN YOUR CONTAINER
       /*     StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("demo_area.jpg");
            storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                String downloadurl = uri.toString();
                Log.d("Firebase>>>>",downloadurl);
                Glide.with(getApplicationContext()).load(downloadurl).into(binding.testImage);
            });*/

            //DOWNLOAD THE FILE FROM FIREBASE CLOUD STORAGE
            /*StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("demo_area.jpg");
            File file = new File("/storage/emulated/0/Download/firebase.jpg");
            storageReference.getFile(file).addOnSuccessListener(taskSnapshot -> {
                Toast.makeText(this, "FILE DOWNLOADED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(e -> {
                Toast.makeText(this, "FILE NOT DOWNLOADED BRO", Toast.LENGTH_SHORT).show();
            });*/

  //      });

        //DELETING A FILE FROM FIREBASE CLOUD STORAGE
      /*  binding.deleteButton.setOnClickListener(v -> {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("firebase2.png");
            storageReference.delete().addOnSuccessListener(unused -> {
                Toast.makeText(this, "FILE HAS BEEN DELETED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(e -> {
                Toast.makeText(this, "FILED DELETION FAILED", Toast.LENGTH_SHORT).show();
            });
        });
*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            resultUri = data.getData();
            binding.testImage.setImageURI(resultUri);
        }
    }
}