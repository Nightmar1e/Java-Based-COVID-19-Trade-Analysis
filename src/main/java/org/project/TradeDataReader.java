package org.project;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TradeDataReader {
    public static List<TradeData> readTradeDataFromCSV(String filePath) {
        List<TradeData> tradeDataList = new ArrayList<>();

        try (CSVParser csvParser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord record : csvParser) {
                String direction = record.get("Direction");
                int year = Integer.parseInt(record.get("Year"));
                String date = record.get("Date");
                String weekday = record.get("Weekday");
                String country = record.get("Country");
                String commodity = record.get("Commodity");
                String transportationMode = record.get("Transport_Mode");
                String measure = record.get("Measure");
                long value = Long.parseLong(record.get("Value"));
                long cumulative = Long.parseLong(record.get("Cumulative"));

                tradeDataList.add(new TradeData(
                        direction, year, date, weekday, country, commodity,
                        transportationMode, measure, value, cumulative
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tradeDataList;
    }
}
