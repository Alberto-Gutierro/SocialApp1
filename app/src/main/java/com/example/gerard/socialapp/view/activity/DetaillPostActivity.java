package com.example.gerard.socialapp.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.VideoView;


import com.example.gerard.socialapp.GlideApp;
import com.example.gerard.socialapp.R;
import com.example.gerard.socialapp.model.Comment;
import com.example.gerard.socialapp.model.Post;
import com.example.gerard.socialapp.view.CommentViewHolder;
import com.example.gerard.socialapp.view.PostViewHolder;
import com.example.gerard.socialapp.view.fragment.PostsFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DetaillPostActivity extends AppCompatActivity {

    ImageView mImageView;
    VideoView mVideoView;
    ImageView auImageView;
    TextView auEditText;
    TextView postEditText;

    EditText coment;
    Button bt_send_coment;

    DatabaseReference dbr;

    String postKey;

    public FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaill_post);

        // Recibimos la imagen / video

        mImageView = findViewById(R.id.img_post);
        mVideoView = findViewById(R.id.vid_post);
        auImageView = findViewById(R.id.img_perfil);
        auEditText = findViewById(R.id.tv_author);
        postEditText = findViewById(R.id.tv_post);

        coment = findViewById(R.id.et_coment);
        bt_send_coment = findViewById(R.id.bt_enviar);

        postKey = getIntent().getStringExtra("postkey");

        dbr = FirebaseDatabase.getInstance().getReference();

        mUser = FirebaseAuth.getInstance().getCurrentUser();


        findViewById(R.id.bt_enviar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Comment comment = new Comment(mUser.getDisplayName(),coment.getText().toString());

                String commentkey = FirebaseDatabase.getInstance().getReference().push().getKey();

                dbr.child("/posts/comments/"+postKey+"/"+commentkey).setValue(comment);


            }
        });

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Comment>()
                .setQuery(dbr.child("posts/comments/"+postKey), Comment.class)
                .setLifecycleOwner(this)
                .build();


        RecyclerView recycler = findViewById(R.id.rvComments);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new FirebaseRecyclerAdapter<Comment, CommentViewHolder>(options) {
            @Override
            public CommentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                return new CommentViewHolder(inflater.inflate(R.layout.item_comment, viewGroup, false));
            }

            @Override
            protected void onBindViewHolder(final CommentViewHolder viewHolder, int position, final Comment comment) {
                final String postKey = getRef(position).getKey();
                Log.e("acb",postKey);

                viewHolder.et_author.setText(comment.getName());
                viewHolder.et_coment.setText(comment.getComent());
            }
        });

    }
}
