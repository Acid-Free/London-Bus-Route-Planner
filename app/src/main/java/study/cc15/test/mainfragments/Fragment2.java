/*
This is a fragment for the end point
TODO: Implement this functionality. This is like a mirror image of Fragment1
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

import study.cc15.test.adapters.PlacesRecViewAdapter2;
import study.cc15.test.R;
import study.cc15.test.apihandlers.DataService;
import study.cc15.test.datastructures.Place;

public class Fragment2 extends Fragment {

    private Context context;

    private EditText addressPrompt2;
    private RecyclerView contactsRecView2;

    public Fragment2(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_layout, container, false);

        addressPrompt2 = view.findViewById(R.id.addressPrompt2);
        contactsRecView2 = view.findViewById(R.id.contactsRecycleView2);

        final DataService dataService = new DataService(context);

        addressPrompt2.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    dataService.getPlaces(addressPrompt2.getText().toString(), new DataService.VolleyResponseListener() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(context, "Error: Failed to get places result", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(ArrayList<Place> places) {
                            PlacesRecViewAdapter2 adapter = new PlacesRecViewAdapter2(context);
                            adapter.setPlaces(places);
                            contactsRecView2.setAdapter(adapter);
                            contactsRecView2.setLayoutManager(new LinearLayoutManager(context));
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
