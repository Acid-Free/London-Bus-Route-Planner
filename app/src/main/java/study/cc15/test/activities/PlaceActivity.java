package study.cc15.test.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import study.cc15.test.R;
import study.cc15.test.datastructures.Place;

public class PlaceActivity extends AppCompatActivity {

    private TextView textName, textAddress, textType, textLatitude, textLongitude, textAtcoCode;
    private Button buttonSetStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        textName = findViewById(R.id.textName);
        textAddress = findViewById(R.id.textAddress);
        textType = findViewById(R.id.textType);
        textLatitude = findViewById(R.id.textLatitude);
        textLongitude = findViewById(R.id.textLongitude);
        textAtcoCode = findViewById(R.id.textAtcoCode);
        buttonSetStart = findViewById(R.id.buttonSetStart);

        // TODO: Get Place information from recyclerview
        final Place place = getIntent().getExtras().getParcelable("startPoint");

        if (place != null)
            setData(place);
        else
            Toast.makeText(this, "The received startPoint is null", Toast.LENGTH_SHORT).show();

        buttonSetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.stops.setStartPoint(place);
                Toast.makeText(PlaceActivity.this, "Start Point Added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData(Place place) {
        textName.setText(place.getName());
        textAddress.setText(place.getAddress());
        textType.setText(place.getType());
        textLatitude.setText(String.format("Latitude: %f", place.getLatitude()));
        textLongitude.setText(String.format("Latitude: %f", place.getLongitude()));
        textAtcoCode.setText(place.getAtcocode());
    }
}