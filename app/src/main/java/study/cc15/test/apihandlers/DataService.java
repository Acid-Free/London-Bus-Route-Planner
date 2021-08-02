package study.cc15.test.apihandlers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import study.cc15.test.datastructures.Place;
import study.cc15.test.datastructures.Route;

public class DataService {
    Context context;

    final String API_ID = "e2075363";
    final String API_KEY = "30b15a6d3cd805dae01eaee6f2871976";

    public DataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onResponse(ArrayList<Place> places);
        void onError(String error);
    }

    public void getPlaces(String query, final VolleyResponseListener volleyResponseListener) {
        final ArrayList<Place> places = new ArrayList<>();

        // TODO: If no results, try removing the whole ?query part
        String placesUrl = String.format("%s%s%s", "https://transportapi.com/v3/uk/places.json?app_id=e2075363&app_key=30b15a6d3cd805dae01eaee6f2871976&lat=51.55597&lon=-0.2797&max_lat=51.7287&max_lon=0.3049&min_lat=51.2482&min_lon=-0.5823&query=", query, " station&type=bus_stop");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, placesUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray apiPlacesResult = response.getJSONArray("member");

                    for(int i = 0; i < apiPlacesResult.length(); ++i) {
                        Place place = new Place();
                        JSONObject apiMember = (JSONObject) apiPlacesResult.get(i);

                        place.setName(apiMember.getString("name"));
                        place.setAddress(apiMember.getString("description"));
                        place.setType(apiMember.getString("type"));
                        place.setLatitude(apiMember.getDouble("latitude"));
                        place.setLongitude(apiMember.getDouble("longitude"));
                        place.setAtcocode(apiMember.getString("atcocode"));
                        places.add(place);
                    }
                    volleyResponseListener.onResponse(places);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("Error: Places not returned");
            }
        });

        // Add the request to the RequestQueue.
        RequestHandler.getInstance(context).addToRequestQueue(request);
    }

    public interface RouteResponseListener {
        void onResponse(ArrayList<Route> routes);
        void onError(String error);
    }

    public void getRoutes(String startCoordinates, String endCoordinates, final RouteResponseListener routeResponseListener) {
        final ArrayList<Route> routes = new ArrayList<>();

        String url = String.format("https://transportapi.com/v3/uk/public/journey/from/%s/to/%s.json?app_id=e2075363&app_key=30b15a6d3cd805dae01eaee6f2871976&modes=bus&not_modes=foot&service=tfl", startCoordinates, endCoordinates);
//        Toast.makeText(context, MainActivity.accessToken, Toast.LENGTH_SHORT).show();

        // get the json object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray apiRoutesResult = response.getJSONArray("routes");

                    for(int i = 0; i < apiRoutesResult.length(); ++i) {
                        Route route = new Route();
                        JSONObject apiMember = (JSONObject) apiRoutesResult.get(i);

                        route.setDuration(apiMember.getString("duration"));
                        route.setDepartureTime(apiMember.getString("departure_datetime"));
                        route.setArrivalTime(apiMember.getString("arrival_datetime"));
                        routes.add(route);
                    }
                    routeResponseListener.onResponse(routes);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                routeResponseListener.onError("Error: Routes not returned");
            }
        });

        RequestHandler.getInstance(context).addToRequestQueue(request);
    }

}
