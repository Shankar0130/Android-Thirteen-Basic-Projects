package com.shankaryadav.www.whatsappchatbox;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatViewHolder> {

    Context context;
    List<Chat> list;

    public ChatListAdapter(Context context, List<Chat> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from (viewGroup.getContext ());

        View view = inflater.inflate (R.layout.item,viewGroup,false);

        return new ChatViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder chatViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    public class ChatViewHolder extends  RecyclerView.ViewHolder{

        TextView name,lastmsg;
        CircleImageView circleImageView;

        public ChatViewHolder(@NonNull View itemView) {
            super (itemView);

            name = itemView.findViewById (R.id.name);
            lastmsg = itemView.findViewById (R.id.lasttext);
            circleImageView = itemView.findViewById (R.id.profile_image);

            itemView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent (context.getApplicationContext (),AddChat.class);

                    Chat chat = new Chat ();

                    intent.putExtra ("detail",chat);
                    context.startActivity (intent);

                }
            });
        }
    }
}
