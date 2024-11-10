package com.example.washwashlaundry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private List<Service> serviceList;
    private DatabaseReference cartRef; // Reference to the "Cart" node in Firebase
    private Context context;

    // Constructor
    public ServiceAdapter(List<Service> serviceList, DatabaseReference servicesRef, ServicesActivity servicesActivity) {
        this.serviceList = serviceList;
        this.context = context;
        this.cartRef = FirebaseDatabase.getInstance().getReference("Cart").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item view (list item)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        // Get the current service item
        Service service = serviceList.get(position);

        // Set the data into the item view
        holder.serviceName.setText(service.getName());
        holder.servicePrice.setText(String.format("RM %.2f", service.getPrice()));
        holder.addToCartButton.setOnClickListener(v -> addToCart(service));

        holder.addToCartButton.setOnClickListener(V->{
            addToCart(service);
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    private void addToCart(Service service) {
        // Get the logged-in user's ID
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://washwashlaundry-ef8c4-default-rtdb.asia-southeast1.firebasedatabase.app/");
        cartRef = database.getReference("Cart").child(userId);

        // Generate a unique ID for the cart item
        String cartItemId = cartRef.child(userId).push().getKey();

        if (cartItemId != null) {
            // Save the service to the user's cart in Firebase
            cartRef.child(userId).child(cartItemId).setValue(service)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Failed to add to cart", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    // ViewHolder class
    public static class ServiceViewHolder extends RecyclerView.ViewHolder {
        TextView serviceName, servicePrice;
        Button addToCartButton;

        public ServiceViewHolder(View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.service_name);
            servicePrice = itemView.findViewById(R.id.service_price);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
        }
    }
}