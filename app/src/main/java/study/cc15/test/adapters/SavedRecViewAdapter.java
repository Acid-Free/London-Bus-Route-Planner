package study.cc15.test.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import study.cc15.test.R;
import study.cc15.test.activities.MainActivity;
import study.cc15.test.activities.PlaceActivity;
import study.cc15.test.apihandlers.DataService;
import study.cc15.test.datastructures.Place;
import study.cc15.test.datastructures.Saved;

public class SavedRecViewAdapter extends RecyclerView.Adapter<SavedRecViewAdapter.ViewHolder>{

    private Context context;

    private ArrayList<Saved> saves = new ArrayList<>();

    public SavedRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saves_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.textName.setText(saves.get(position).getName());
        holder.textStartName.setText(MainActivity.stops.getStartPoint().getName());
        holder.textEndName.setText(MainActivity.stops.getEndPoint().getName());
        holder.textDuration.setText(saves.get(position).getDuration());
        holder.textDeparture.setText(saves.get(position).getDepartureTime());
        holder.textArrival.setText(saves.get(position).getArrivalTime());
//        holder.parent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(context, contacts.get(position).getName() + " Selected", Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(context, PlaceActivity.class);
////                intent.putExtra("startPoint", saves.get(position));
////                context.startActivity(intent);
//            }
//        });

        holder.textName.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    saves.get(position).setName(holder.textName.getText().toString());
                    return true;
                }
                return false;
            }
        });

        holder.iconDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.saves.remove(MainActivity.saves.get(position));
                setSaved(MainActivity.saves);
                // Iterate over the MainActivity.saves to reestablish index
                // Not necessary as position does this implicitly
//                for (int i = 0; i < MainActivity.saves.size(); ++i) {
//                    MainActivity.saves.get(i).setIndex(i);
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return saves.size();
    }

    public void setSaved(ArrayList<Saved> saves) {
        this.saves = saves;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private EditText textName;
        private TextView textStartName, textEndName, textDuration, textDeparture, textArrival;
        private ImageView iconDelete;
        private RelativeLayout parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textStartName = itemView.findViewById(R.id.textStartName);
            textEndName = itemView.findViewById(R.id.textEndName);
            textDuration = itemView.findViewById(R.id.textDuration);
            textDeparture = itemView.findViewById(R.id.textDeparture);
            textArrival = itemView.findViewById(R.id.textArrival);
            iconDelete = itemView.findViewById(R.id.iconDelete);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
