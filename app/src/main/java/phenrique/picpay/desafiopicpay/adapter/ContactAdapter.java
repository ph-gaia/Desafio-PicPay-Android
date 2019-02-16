package phenrique.picpay.desafiopicpay.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import phenrique.picpay.desafiopicpay.R;
import phenrique.picpay.desafiopicpay.data.model.User;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private List<User> usersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username, name;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            username = view.findViewById(R.id.item_contact_username);
            name = view.findViewById(R.id.item_contact_name);
            image = view.findViewById(R.id.item_contact_image);
        }
    }

    public ContactAdapter(List<User> usersList) {
        this.usersList = usersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = usersList.get(position);
        holder.username.setText(user.getUsername());
        holder.name.setText(user.getName());

        Picasso.get()
                .load(user.getImg())
                .placeholder(R.drawable.ic_account_default)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
