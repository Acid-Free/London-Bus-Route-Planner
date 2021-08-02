package study.cc15.test.activities;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import study.cc15.test.R;
import study.cc15.test.apihandlers.DataService;
import study.cc15.test.datastructures.Place;
import study.cc15.test.datastructures.Saved;
import study.cc15.test.datastructures.Stops;
import study.cc15.test.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    public ViewPager viewPager;

    public static Stops stops;
    public static ArrayList<Saved> saves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        stops = new Stops();
        saves = new ArrayList<>();
    }
}