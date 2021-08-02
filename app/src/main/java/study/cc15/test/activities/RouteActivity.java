// TODO: Create this one similar to PlaceActivity.java
package study.cc15.test.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import study.cc15.test.R;
import study.cc15.test.datastructures.Place;
import study.cc15.test.datastructures.Route;
import study.cc15.test.datastructures.Saved;

public class RouteActivity extends AppCompatActivity {
    private TextView textName1, textMiscInfo1, textName2, textMiscInfo2, textDuration, textDeparture, textArrival;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        textName1 = findViewById(R.id.textName1);
        textMiscInfo1 = findViewById(R.id.textMiscInfo1);
        textName2 = findViewById(R.id.textName2);
        textMiscInfo2 = findViewById(R.id.textMiscInfo2);
        textDuration = findViewById(R.id.textDuration);
        textDeparture = findViewById(R.id.textDeparture);
        textArrival = findViewById(R.id.textArrival);
        buttonAdd = findViewById(R.id.buttonAdd);

        // TODO: Get Route information from recyclerview
        final Route route = getIntent().getExtras().getParcelable("selectedRoute");

        if (route != null)
            setData(route);
        else
            Toast.makeText(this, "The received selectedRoute is null", Toast.LENGTH_SHORT).show();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Saved saved = new Saved();
                saved.setIndex(MainActivity.saves.size());
                saved.setName(String.format("Saved %d", (MainActivity.saves.size() + 1)));
                saved.setDuration(route.getDuration());
                saved.setDepartureTime(route.getDepartureTime());
                saved.setArrivalTime(route.getArrivalTime());

                MainActivity.saves.add(saved);
                Toast.makeText(RouteActivity.this, "Route saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData(Route route) {
        textName1.setText(MainActivity.stops.getStartPoint().getName());
        textMiscInfo1.setText(String.format("%s in %s", MainActivity.stops.getStartPoint().getType(), MainActivity.stops.getStartPoint().getAddress()));
        textName2.setText(MainActivity.stops.getEndPoint().getName());
        textMiscInfo2.setText(String.format("%s in %s", MainActivity.stops.getEndPoint().getType(), MainActivity.stops.getEndPoint().getAddress()));
        textDuration.setText(route.getDuration());
        textDeparture.setText(route.getArrivalTime());
        textArrival.setText(route.getDepartureTime());
    }
}
