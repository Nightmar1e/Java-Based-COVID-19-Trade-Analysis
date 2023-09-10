package project;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TradeDataOverview {
    public static void displayOverview(List<TradeData> tradeDataList) {
        Set<Integer> years = new HashSet<>();
        Set<String> countries = new HashSet<>();
        Set<String> commodities = new HashSet<>();
        Set<String> transportationModes = new HashSet<>();
        Set<String> measures = new HashSet<>();

        for (TradeData tradeData : tradeDataList) {
            years.add(tradeData.year);
            countries.add(tradeData.country);
            commodities.add(tradeData.commodity);
            transportationModes.add(tradeData.transportationMode);
            measures.add(tradeData.measure);
        }

        System.out.println("Unique Years: " + years);
        System.out.println("Unique Countries: " + countries);
        System.out.println("Unique Commodities: " + commodities);
        System.out.println("Unique Transportation Modes: " + transportationModes);
        System.out.println("Unique Measures: " + measures);
    }
}
