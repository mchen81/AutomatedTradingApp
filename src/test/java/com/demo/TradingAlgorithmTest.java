package com.demo;

import com.demo.model.Price;
import com.demo.model.Trade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

public class TradingAlgorithmTest {

    @Test
    public void testExample1() throws IOException {
        List<Price> testCases = getTestCase("example1.csv");
        String[] productNames = getProductsName(testCases);
        TradingAlgorithm tradingAlgorithm = new TradeWhenAvgHigherAlgorithm(productNames);
        List<Trade> tradeResult = new ArrayList<>();
        for (Price price : testCases) {
            Trade trade = tradingAlgorithm.buildTrades(price);
            if (trade != null) {
                tradeResult.add(trade);
            }
        }
        Assertions.assertEquals(1, tradeResult.size());
        Assertions.assertEquals("AAL UW,BUY,7.67,1000", tradeResult.get(0).toString());

    }

    @Test
    public void testExample2() throws IOException {
        List<Price> testCases = getTestCase("example2.csv");
        String[] productNames = getProductsName(testCases);
        TradingAlgorithm tradingAlgorithm = new TradeWhenAvgHigherAlgorithm(productNames);
        List<Trade> tradeResult = new ArrayList<>();
        for (Price price : testCases) {
            Trade trade = tradingAlgorithm.buildTrades(price);
            if (trade != null) {
                tradeResult.add(trade);
            }
        }
        Assertions.assertEquals(3, tradeResult.size());
        Assertions.assertEquals("A,BUY,10.00,1000", tradeResult.get(0).toString());
        Assertions.assertEquals("A,BUY,11.00,1000", tradeResult.get(1).toString());
        Assertions.assertEquals("A,BUY,1.00,1000", tradeResult.get(2).toString());
    }


    private static List<Price> getTestCase(String fileName) throws IOException {
        InputStream stream = TradingAlgorithmTest.class.getClassLoader().
                getResourceAsStream(fileName);
        assert stream != null;
        List<Price> testcase = new ArrayList<>();
        try {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] splits = line.split(",");
                    if (splits.length != 2) {
                        throw new IllegalArgumentException(line + " is not a valid line.");
                    }
                    try {
                        Double price = Double.valueOf(splits[1]);
                        testcase.add(new Price(splits[0], price));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(splits[1] + " is not a number.");
                    }
                }
            }
        } finally {
            stream.close();
        }
        return testcase;
    }

    private static String[] getProductsName(List<Price> testCase) {
        Set<String> productNames = new HashSet<>();
        testCase.forEach(p -> productNames.add(p.getProductName()));
        return productNames.toArray(new String[productNames.size()]);

    }

}
