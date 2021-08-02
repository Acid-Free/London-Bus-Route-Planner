/*
This is a fragment for the routes
*/
package study.cc15.test.mainfragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import study.cc15.test.activities.MainActivity;
import study.cc15.test.R;
import study.cc15.test.adapters.RoutesRecViewAdapter;
import study.cc15.test.apihandlers.DataService;
import study.cc15.test.datastructures.Route;

public class Fragment3 extends Fragment {

    private Context context;

    private TextView textStartName, textEndName, textJourneys, textEmpty;
    private Button buttonConfirm;
    private RecyclerView routesRecView;

    private View view;

    public Fragment3(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment3_layout, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        textStartName = view.findViewById(R.id.textStartName);
        textEndName = view.findViewById(R.id.textEndName);
        textJourneys = view.findViewById(R.id.textJourneys);
        textEmpty = view.findViewById(R.id.textEmpty);
        buttonConfirm = view.findViewById(R.id.buttonConfirm);
        routesRecView = view.findViewById(R.id.contactsRecycleView3);

        try {
            textStartName.setText(MainActivity.stops.getStartPoint().getName());
        } catch (Exception e) {
//            Toast.makeText(context, "Start Point is not set yet", Toast.LENGTH_SHORT).show();
        }

        try {
            textEndName.setText(MainActivity.stops.getEndPoint().getName());
        } catch (Exception e) {
//            Toast.makeText(context, "End Point is not set yet", Toast.LENGTH_SHORT).show();
        }

        try {
            final String startCoordinates = String.format("%f,%f", MainActivity.stops.getStartPoint().getLongitude(), MainActivity.stops.getStartPoint().getLatitude());
            final String endCoordinates = String.format("%f,%f", MainActivity.stops.getEndPoint().getLongitude(), MainActivity.stops.getEndPoint().getLatitude());

            buttonConfirm.setOnClickListener(new View.OnClickListener() {
                final DataService dataService = new DataService(context);

                @Override
                public void onClick(View view) {
                    textJourneys.setVisibility(View.VISIBLE);
                    routesRecView.setVisibility(View.VISIBLE);

                    dataService.getRoutes(startCoordinates, endCoordinates, new DataService.RouteResponseListener() {
                        @Override
                        public void onError(String message) {
//                            Toast.makeText(context, "Error: Failed to get routes result", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(ArrayList<Route> routes) {
                            RoutesRecViewAdapter adapter = new RoutesRecViewAdapter(context);
                            adapter.setRoutes(routes);
                            routesRecView.setAdapter(adapter);
                            routesRecView.setLayoutManager(new LinearLayoutManager(context));

                            if (adapter.getItemCount() == 0)
                                textEmpty.setVisibility(View.VISIBLE);
                            else
                                textEmpty.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            });
        } catch (NullPointerException e) {
//            Toast.makeText(context, "Error: At least one point is not set", Toast.LENGTH_SHORT).show();
        }
    }
}
