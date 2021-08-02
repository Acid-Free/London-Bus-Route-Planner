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
import study.cc15.test.activities.RouteActivity;
import study.cc15.test.datastructures.Route;

public class RoutesRecViewAdapter extends RecyclerView.Adapter<RoutesRecViewAdapter.ViewHolder> {

    private Context context;

    private ArrayList<Route> routes = new ArrayList<>();

    public RoutesRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.routes_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        // this isn't even necessary
//        try {
//            holder.textStartPointName.setText(MainActivity.stops.getStartPoint().getName());
//        } catch (NullPointerException e) {
//            Toast.makeText(context, "Start Point doesn't exist yet", Toast.LENGTH_SHORT).show();
//        }
//        try {
//            holder.textEndPointName.setText(MainActivity.stops.getEndPoint().getName());
//        } catch (Exception e) {
//            Toast.makeText(context, "End Point doesn't exist yet", Toast.LENGTH_SHORT).show();
//        }

        holder.textRouteNumber.setText(String.format("Route %d", (position + 1)));
        holder.textInfo.setText(routes.get(position).getDuration());

        holder.routeParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, contacts.get(position).getName() + " Selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, RouteActivity.class);
                intent.putExtra("selectedRoute", routes.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textRouteNumber;
        private TextView textInfo;
        private RelativeLayout routeParent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textRouteNumber = itemView.findViewById(R.id.textDesc);
            textInfo = itemView.findViewById(R.id.textInfo);
            routeParent = itemView.findViewById(R.id.routeParent);
        }
    }
}
