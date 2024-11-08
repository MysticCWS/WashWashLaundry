package com.example.washwashlaundry;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private List<Service> serviceList;

    // Constructor
    public ServiceAdapter(List<Service> serviceList) {
        this.serviceList = serviceList;
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
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    // ViewHolder class
    public static class ServiceViewHolder extends RecyclerView.ViewHolder {
        TextView serviceName, servicePrice;

        public ServiceViewHolder(View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.service_name);
            servicePrice = itemView.findViewById(R.id.service_price);
        }
    }
}