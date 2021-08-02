/*
This is a fragment for the start point
*/
package study.cc15.test.mainfragments;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import study.cc15.test.adapters.PlacesRecViewAdapter;
import study.cc15.test.R;
import study.cc15.test.apihandlers.DataService;
import study.cc15.test.datastructures.Place;

public class Fragment1 extends Fragment {

    private Context context;

    private EditText addressPrompt1;
    private RecyclerView contactsRecView;

    public Fragment1(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);

        addressPrompt1 = view.findViewById(R.id.addressPrompt1);
        contactsRecView = view.findViewById(R.id.contactsRecycleView1);

        final DataService dataService = new DataService(context);

        addressPrompt1.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    dataService.getPlaces(addressPrompt1.getText().toString(), new DataService.VolleyResponseListener() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(context, "Error: Failed to get places result", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(ArrayList<Place> places) {
                            PlacesRecViewAdapter adapter = new PlacesRecViewAdapter(context);
                            adapter.setPlaces(places);
                            contactsRecView.setAdapter(adapter);
                            contactsRecView.setLayoutManager(new LinearLayoutManager(context));
                        }
                    });
                    return true;
                }
                return false;
            }
        });
        return view;
    }
}
