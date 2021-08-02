/*
This is a fragment for saved
*/
package study.cc15.test.mainfragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import study.cc15.test.R;
import study.cc15.test.activities.MainActivity;
import study.cc15.test.adapters.SavedRecViewAdapter;

public class Fragment4 extends Fragment {

    private Context context;
    private RecyclerView savedRecView;

    public Fragment4(Context context) {
        this.context = context;
    }

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment4_layout, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        
        savedRecView = view.findViewById(R.id.savedRecView);

        SavedRecViewAdapter adapter = new SavedRecViewAdapter(context);
        adapter.setSaved(MainActivity.saves);
        savedRecView.setAdapter(adapter);
        savedRecView.setLayoutManager(new LinearLayoutManager(context));
    }
}
