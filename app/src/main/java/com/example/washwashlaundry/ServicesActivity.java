package com.example.washwashlaundry;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import com.example.washwashlaundry.Service;

public class ServicesActivity extends AppCompatActivity {
    private RecyclerView recyclerViewServices;
    private DatabaseReference servicesRef;
    private List<Service> serviceList = new ArrayList<>();
    private ServiceAdapter serviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this); // Initialize Firebase
        setContentView(R.layout.activity_services);

        recyclerViewServices = findViewById(R.id.recyclerViewServices);
        recyclerViewServices.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://washwashlaundry-ef8c4-default-rtdb.asia-southeast1.firebasedatabase.app/");
        servicesRef = FirebaseDatabase.getInstance("https://washwashlaundry-ef8c4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Services");
        serviceAdapter = new ServiceAdapter(serviceList);
        recyclerViewServices.setAdapter(serviceAdapter);

        loadServices();
    }

    private void loadServices() {
        servicesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                serviceList.clear();
                for (DataSnapshot serviceSnapshot : snapshot.getChildren()) {
                    Service service = serviceSnapshot.getValue(Service.class);
                    if (service != null) {
                        serviceList.add(service);
                    }
                }
                serviceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ServicesActivity.this, "Failed to load services", Toast.LENGTH_SHORT).show();
            }
        });
    }
}