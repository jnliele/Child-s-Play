package com.example.teachingtoddlers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder>{
    private ArrayList<String> topicsList;
    private ArrayList<Long> accuracyList;
    private ArrayList<Long> playCountList;

    public recyclerAdapter(ArrayList<String> topicsList, ArrayList<Long> accuracyList, ArrayList<Long> playCountList){
        // template to create an instance of recycler adapter
        this.topicsList = topicsList;
        this.accuracyList = accuracyList;
        this.playCountList = playCountList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView topicText;
        private TextView accuracyText;
        private TextView playCountText;

        // find the textview id from xml
        public MyViewHolder(final View view){
            super(view);
            topicText = view.findViewById(R.id.topic);
            accuracyText = view.findViewById(R.id.accuracy);
            playCountText = view.findViewById(R.id.play_count);
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        // extract the values from array list
        String topicName = topicsList.get(position);
        long accuracy = accuracyList.get(position);
        long playCount = playCountList.get(position);

        // set the extracted value into xml
        if (topicName.equals("None"))
        {
            holder.topicText.setText("          None");
        }
        else if (accuracy == -2) // showing favorites list
        {
            holder.topicText.setText("      "+topicName);
        }
        else if (topicName.equals("-")) // for when there is no valid data to display
        {
            holder.topicText.setText("  --");
            holder.accuracyText.setText("--   ");
            holder.playCountText.setText("--");
        }
        else
        {
            holder.topicText.setText(topicName);
            holder.accuracyText.setText(String.valueOf(accuracy)+"%");
            holder.playCountText.setText(String.valueOf(playCount));
        }
    }

    @Override
    public int getItemCount() {
        return topicsList.size();
    }
}
