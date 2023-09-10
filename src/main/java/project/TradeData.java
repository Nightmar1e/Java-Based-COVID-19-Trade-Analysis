package project;

public class TradeData {
    String direction;
    int year;
    String date;
    String weekday;
    String country;
    String commodity;
    String transportationMode;
    String measure;
    long value;
    long cumulative;

    public TradeData(String direction, int year, String date, String weekday, String country,
                     String commodity, String transportationMode, String measure, long value, long cumulative) {
        this.direction = direction;
        this.year = year;
        this.date = date;
        this.weekday = weekday;
        this.country = country;
        this.commodity = commodity;
        this.transportationMode = transportationMode;
        this.measure = measure;
        this.value = value;
        this.cumulative = cumulative;
    }
}
