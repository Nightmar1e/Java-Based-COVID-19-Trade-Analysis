package org.project;


import lombok.AllArgsConstructor;

@AllArgsConstructor
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

}
