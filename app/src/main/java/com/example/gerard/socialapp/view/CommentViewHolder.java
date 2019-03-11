package com.example.gerard.socialapp.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.TextView;

import com.example.gerard.socialapp.R;


public class CommentViewHolder extends RecyclerView.ViewHolder {

   public TextView et_coment;
   public TextView et_author;

    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);

        et_coment = itemView.findViewById(R.id.tv_comment);
        et_author = itemView.findViewById(R.id.author_coment);

    }


}
