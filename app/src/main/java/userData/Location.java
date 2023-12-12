package userData;
import java.lang.Math;

public class Location {

    private String address;
    private Coordinates coordinates;

    public Location(String address, double x, double y){
            this.address = address;
            this.coordinates = new Coordinates(x, y);
    }

    public String getAddress() {
        return address;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public static double calcDistance(Location l1, Location l2){
            return Math.sqrt(Math.pow((l1.coordinates.x - l2.coordinates.x), 2) + Math.pow((l1.coordinates.y - l2.coordinates.y), 2));
        }

    private final class Coordinates{
        private double x;
        private double y;

        public Coordinates(double x, double y){
            this.x = x;
            this.y = y;
        }
    }
}
