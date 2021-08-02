// TODO: Deprecate later, this is useless
package study.cc15.test.datastructures;

import java.util.ArrayList;

public class Journey {
    private ArrayList<Route> routes;

    public Journey() {
        routes = new ArrayList<>();
    }

    public Journey(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }
}
