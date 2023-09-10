package project;

import java.util.*;

public class TradeAnalyzerCalculator {
    public static void calculateMonthlyTotal(List<TradeData> tradeDataList, int year, String month) {
        long totalExports = 0;
        long totalImports = 0;

        for (TradeData tradeData : tradeDataList) {
            if (tradeData.year == year && tradeData.date.startsWith(month)) {
                if (tradeData.direction.equals("Exports")) {
                    totalExports += tradeData.value;
                } else if (tradeData.direction.equals("Imports")) {
                    totalImports += tradeData.value;
                }
            }
        }

        System.out.println("Monthly Total Exports: " + totalExports);
        System.out.println("Monthly Total Imports: " + totalImports);
    }

    public static void calculateMonthlyAverage(List<TradeData> tradeDataList, int year, String month) {
        long totalExports = 0;
        long totalImports = 0;
        int exportCount = 0;
        int importCount = 0;

        for (TradeData tradeData : tradeDataList) {
            if (tradeData.year == year && tradeData.date.startsWith(month)) {
                if (tradeData.direction.equals("Exports")) {
                    totalExports += tradeData.value;
                    exportCount++;
                } else if (tradeData.direction.equals("Imports")) {
                    totalImports += tradeData.value;
                    importCount++;
                }
            }
        }

        if (exportCount > 0) {
            double averageExports = (double) totalExports / exportCount;
            System.out.println("Monthly Average Exports: " + averageExports);
        }

        if (importCount > 0) {
            double averageImports = (double) totalImports / importCount;
            System.out.println("Monthly Average Imports: " + averageImports);
        }
    }

    public static void calculateYearlyTotal(List<TradeData> tradeDataList, int year) {
        Map<String, Long> monthlyTotalsExports = new HashMap<>();
        Map<String, Long> monthlyTotalsImports = new HashMap<>();

        for (TradeData tradeData : tradeDataList) {
            if (tradeData.year == year) {
                String month = tradeData.date.substring(3, 5);
                long value = tradeData.value;

                if (tradeData.direction.equals("Exports")) {
                    monthlyTotalsExports.put(month, monthlyTotalsExports.getOrDefault(month, 0L) + value);
                } else if (tradeData.direction.equals("Imports")) {
                    monthlyTotalsImports.put(month, monthlyTotalsImports.getOrDefault(month, 0L) + value);
                }
            }
        }

        System.out.println("Yearly Total Exports: " + sumMapValues(monthlyTotalsExports));
        System.out.println("Yearly Total Imports: " + sumMapValues(monthlyTotalsImports));
    }

    public static void calculateYearlyAverage(List<TradeData> tradeDataList, int year) {
        Map<String, Long> monthlyTotalsExports = new HashMap<>();
        Map<String, Long> monthlyTotalsImports = new HashMap<>();
        Map<String, Integer> monthlyCountsExports = new HashMap<>();
        Map<String, Integer> monthlyCountsImports = new HashMap<>();

        for (TradeData tradeData : tradeDataList) {
            if (tradeData.year == year) {
                String month = tradeData.date.substring(3, 5);
                long value = tradeData.value;

                if (tradeData.direction.equals("Exports")) {
                    monthlyTotalsExports.put(month, monthlyTotalsExports.getOrDefault(month, 0L) + value);
                    monthlyCountsExports.put(month, monthlyCountsExports.getOrDefault(month, 0) + 1);
                } else if (tradeData.direction.equals("Imports")) {
                    monthlyTotalsImports.put(month, monthlyTotalsImports.getOrDefault(month, 0L) + value);
                    monthlyCountsImports.put(month, monthlyCountsImports.getOrDefault(month, 0) + 1);
                }
            }
        }

        double yearlyAverageExports = calculateMapAverages(monthlyTotalsExports, monthlyCountsExports);
        double yearlyAverageImports = calculateMapAverages(monthlyTotalsImports, monthlyCountsImports);

        System.out.println("Yearly Average Exports: " + yearlyAverageExports);
        System.out.println("Yearly Average Imports: " + yearlyAverageImports);
    }

    public static long sumMapValues(Map<String, Long> map) {
        long sum = 0;
        for (Long value : map.values()) {
            sum += value;
        }
        return sum;
    }

    public static double calculateMapAverages(Map<String, Long> totals, Map<String, Integer> counts) {
        double sum = 0;
        int totalCount = 0;

        for (Map.Entry<String, Long> entry : totals.entrySet()) {
            String month = entry.getKey();
            Long totalValue = entry.getValue();
            Integer count = counts.getOrDefault(month, 0);

            sum += totalValue;
            totalCount += count;
        }

        if (totalCount > 0) {
            return sum / totalCount;
        } else {
            return 0.0;
        }
    }
}
