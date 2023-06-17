package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.firebase.databinding.ActivityDemoAreaBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DemoAreaActivity extends AppCompatActivity {
    private ActivityDemoAreaBinding binding;
    private DatabaseReference databaseReference;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDemoAreaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /***************************************************************************************************************/

                                     /** CRUD OPERATION ON REALTIME DATABASE**/

        /***************************************************************************************************************/

//        databaseReference = FirebaseDatabase.getInstance().getReference().child("test");
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("test");


        // READ FROM REALTIME DATABASE

       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    binding.testTv.append(dataSnapshot.getKey() + ":" + dataSnapshot.getValue() + "\n");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


        // CREATE IN REALTIME DATABASE WITH EVERYTIME A NEW CHILDREN KEY

       /* binding.addButton.setOnClickListener(v -> {
            String text = Objects.requireNonNull(binding.testEt.getText()).toString().trim();
            Map<String,Object> data = new HashMap<>();
            data.put("testvalue",text);

            databaseReference.push().setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(DemoAreaActivity.this, "Data set successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(DemoAreaActivity.this, "Data setting failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        });*/

        //UPDATE IN REALTIME DATABASE WITH MULTIPLE VALUES OR SINGLE VALUES

     /*   binding.updateButton.setOnClickListener(v -> {

            String updatedText = Objects.requireNonNull(binding.testEt.getText()).toString().trim();

            Map<String, Object> data = new HashMap<>();
            data.put("hello", updatedText);
            data.put("one", "updatedText");

            databaseReference.updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(DemoAreaActivity.this, "Data is updated", Toast.LENGTH_SHORT).show();

                }
            });

        });*/


        //DELETE IN REALTIME DATABASE WITH MULTIPLE VALUES AND SINGLE VALUES

       /* binding.deleteButton.setOnClickListener(v -> {

            databaseReference.removeValue();

           *//* String[] keys = {"one","two","three"};

            for(String key : keys){
                 DatabaseReference reference = databaseReference.child(key);
                reference.removeValue();
            }*//*

        });*/

        /**********************************************************************************************************************/

                                        /** CRUD OPERATIONS ON FIRESTORE DATABASE**/

        /*********************************************************************************************************************/

        //CREATE IN FIREBASE FIRESTORE DATABASE
      /*  binding.addButton.setOnClickListener(v -> {
            String name = Objects.requireNonNull(binding.testEt.getText()).toString().trim();
            firebaseFirestore = FirebaseFirestore.getInstance();
            Map<String,Object> data = new HashMap<>();
            data.put("name",name);
            data.put("number","1");

          //  DocumentReference document = FirebaseFirestore.getInstance().collection("users").document();

            firebaseFirestore.collection("users").add(data).addOnSuccessListener(documentReference -> {
                Toast.makeText(this, "Data added in firestore successfully", Toast.LENGTH_SHORT).show();

            }).addOnFailureListener(e -> {
                Toast.makeText(this, "Data failed to  add in firestore", Toast.LENGTH_SHORT).show();
            });
        });*/


        //READ IN FIREBASE FIRESTORE DATABASE

        firebaseFirestore = FirebaseFirestore.getInstance();
//        DocumentReference documentReference = firebaseFirestore.collection("users").document("documentid");

    /*    documentReference.get().addOnSuccessListener(documentSnapshot -> {

            binding.testTv.append(documentSnapshot.getData().get("name").toString());

        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Data fetching failed", Toast.LENGTH_SHORT).show();

        });*/

        //READ DATA FROM FIREBASE FIRESTORE DATABASE WITH REALTIME USING SNAPSHOT LISTENER

       /* firebaseFirestore.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for(DocumentChange change : value.getDocumentChanges()){
                    DocumentSnapshot snapshot =  change.getDocument();
                    String name = snapshot.getString("name");
                    String number = snapshot.getString("number");
                    binding.testTv.append(name+":"+ number + "\n");
                }
            }
        });*/

        //READ DATA FROM FIRESTORE DATABASE ONLY ONCE WITH MANUALLY REFRESH

      /*  firebaseFirestore.collection("users").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                for(QueryDocumentSnapshot snapshot : task.getResult())  {
                    String name = snapshot.getString("name");
                    String number = snapshot.getString("number");
                    binding.testTv.append(name+":"+ number + "\n");

                }
            }
        });*/


        //UPDATE IN FIREBASE FIRESTORE DATABASE
   //     firebaseFirestore = FirebaseFirestore.getInstance();

       /* binding.updateButton.setOnClickListener(v -> {
            DocumentReference documentReference = firebaseFirestore.collection("users").document("pTBOAC8Rxv7PoiZjvwRK");

            documentReference.update("name", Objects.requireNonNull(binding.testEt.getText()).toString().trim(),"number","unknown").addOnSuccessListener(unused -> {
                Toast.makeText(this, "Successfully updated", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(e -> {

            });

        });*/

       /* binding.updateButton.setOnClickListener(v -> {
            Map<String,Object> updated = new HashMap<>();
            updated.put("name", Objects.requireNonNull(binding.testEt.getText()).toString().trim());

            DocumentReference documentReference = firebaseFirestore.collection("users").document("yXVuzZFWM37q92vDkOKz");
            documentReference.update(updated).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(DemoAreaActivity.this, "UPDATED", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(DemoAreaActivity.this, "NOT UDPATED", Toast.LENGTH_SHORT).show();
                }
            });

        });*/


        //DELETE FROM FIREBASE FIRESTORE DATABASE

       /* binding.deleteButton.setOnClickListener(v -> {
            firebaseFirestore = FirebaseFirestore.getInstance();
            DocumentReference documentReference = firebaseFirestore.collection("users").document("yXVuzZFWM37q92vDkOKz");

            documentReference.delete().addOnCompleteListener(task -> {
                Toast.makeText(this, "SUCCESSFULLY DELETED", Toast.LENGTH_SHORT).show();

            });

        });*/

        //For deleting whole collection

        /*binding.deleteButton.setOnClickListener(v -> {
            firebaseFirestore = FirebaseFirestore.getInstance();

            firebaseFirestore.collection("users").get().addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        document.getReference().delete();
                    }
                }
                Toast.makeText(this, "COLLECTION SUCCESSFULLY DELETED", Toast.LENGTH_SHORT).show();
            });
        });*/

    }

}