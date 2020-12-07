package com.example.firebaseapp;

import androidx.annotation.NonNull;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.squareup.picasso.Picasso;


class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<User> users;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setData(users.get(position));
        if(holder.imageView != null){
            Picasso.get().load(users.get(position).getFoto())
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round)
                    .into(holder.imageView);
        }

    }
    public MyAdapter(ArrayList<User> users){
        this.users = users;
    }
    @Override
    public int getItemCount() {
        return users.size();
    }
    static public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tNombre, tEdad, tTel, tDirec;
        private ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tNombre = itemView.findViewById (R.id.textNombre);
            tEdad = itemView.findViewById (R.id.textEdad);
            tTel = itemView.findViewById (R.id.textTel);
            tDirec = itemView.findViewById (R.id.textDirec);
            imageView = itemView.findViewById(R.id.image);
        }

        public void setData(User u) {
            tNombre.setText(u.getNombre() + " " + u.getApellido());
            tEdad.setText(u.getEdad());
            tTel.setText(u.getTelefono());
            tDirec.setText(u.getDireccion());
        }
    }
}
