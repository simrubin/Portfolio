package com.example.lab02;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab02.provider.Book;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    List<Book> data;
    public void setData(List<Book> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerAdapter.ViewHolder holder, int position) {
        holder.bookIdTv.setText("ID: "+ data.get(position).getId());
        holder.titleTv.setText("Title: "+ data.get(position).getTitle());
        holder.isbnTv.setText("ISBN: "+ data.get(position).getIsbn() + "");
        holder.authorTv.setText("Author: "+ data.get(position).getAuthor());
        holder.descTv.setText("Desc: "+ data.get(position).getDescription());
        holder.priceTv.setText("Price: "+data.get(position).getPrice() + "");


    }

    @Override
    public int getItemCount() {

        if (data == null) {
            return 0;
        }
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookIdTv;
        TextView titleTv;
        TextView isbnTv;
        TextView authorTv;
        TextView descTv;
        TextView priceTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookIdTv = itemView.findViewById(R.id.card_book_id);
            titleTv = itemView.findViewById(R.id.card_title_id);
            isbnTv = itemView.findViewById(R.id.card_isbn_id);
            authorTv = itemView.findViewById(R.id.card_author_id);
            descTv = itemView.findViewById(R.id.card_desc_id);
            priceTv = itemView.findViewById(R.id.card_price_id );
        }
    }
}
