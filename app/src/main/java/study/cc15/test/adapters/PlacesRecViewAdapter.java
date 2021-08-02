package study.cc15.test.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import study.cc15.test.R;
import study.cc15.test.activities.PlaceActivity;
import study.cc15.test.datastructures.Place;

public class PlacesRecViewAdapter extends RecyclerView.Adapter<PlacesRecViewAdapter.ViewHolder>{

    private Context context;

    private ArrayList<Place> places = new ArrayList<>();

    public PlacesRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.places_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.textName.setText(places.get(position).getName());
        holder.textInfo.setText(String.format("%s in %s", places.get(position).getType(), places.get(position).getAddress()));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, contacts.get(position).getName() + " Selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, PlaceActivity.class);
                intent.putExtra("startPoint", places.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textName;
        private TextView textInfo;
        private RelativeLayout parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textInfo = itemView.findViewById(R.id.textInfo);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
