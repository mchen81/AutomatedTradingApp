package com.demo;


import com.demo.model.Price;
import com.demo.model.Trade;

import java.util.Scanner;

public class Application {

    /**
     * A console application to execute TradingAlgorithm
     * <p>
     * Example of product names input: productA,productB
     * Example of price input: productA,20.05
     *
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        TradingAlgorithm tradingAlgorithm = null;
        boolean isFirstLine = true;
        System.out.println("Please register your products separated by comma(no space)");
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (isFirstLine) {
                String[] productNames = line.split(",");
                tradingAlgorithm = new TradeWhenAvgHigherAlgorithm(productNames);
                System.out.println("Please enter product_name,price(no space)");
                isFirstLine = false;
                continue;
            }

            String[] splits = line.split(",");
            if (splits.length != 2) {
                System.out.println("[ERROR]Please input correct value.");
            } else {
                if (!isDouble(splits[1])) {
                    System.out.println("[ERROR]The price is not a value.");
                    continue;
                }
                Double price = Double.valueOf(splits[1]);
                Trade tradeResult = tradingAlgorithm.buildTrades(new Price(splits[0], price));
                if (tradeResult != null) {
                    System.out.println("A trade is made: " + tradeResult.toString());
                }
            }
        }


    }

    public static boolean isDouble(String s) {
        try {
            Double.valueOf(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
