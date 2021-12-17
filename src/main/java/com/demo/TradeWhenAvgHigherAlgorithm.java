package com.demo;

import com.demo.model.Direction;
import com.demo.model.Price;
import com.demo.model.Trade;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Buying the product when the average of window is higher then the first element in the window
 */
public class TradeWhenAvgHigherAlgorithm implements TradingAlgorithm {

    private final Map<String, Queue<Double>> priceHistoryMap;

    private final int windowSize;

    /**
     * Default window size is 4
     *
     * @param productNames
     */
    public TradeWhenAvgHigherAlgorithm(String[] productNames) {
        this(4, productNames);
    }

    /**
     * @param windowSize   decides how many elements kept in the queue
     * @param productNames products name
     */
    public TradeWhenAvgHigherAlgorithm(int windowSize, String[] productNames) {
        this.windowSize = windowSize;
        priceHistoryMap = new HashMap<>();
        for (String product : productNames) {
            priceHistoryMap.put(product, new LinkedList<>());
        }
    }


    /**
     * @param price data
     * @return null is returned if there is not any trade made
     */
    @Override
    public Trade buildTrades(Price price) {
        if (!priceHistoryMap.containsKey(price.getProductName())) {
            return null;
        }
        synchronized (priceHistoryMap.get(price.getProductName())) {
            Queue<Double> priceHistory = priceHistoryMap.get(price.getProductName());
            priceHistory.add(price.getPrice());
            if (priceHistory.size() > windowSize) {
                priceHistory.poll();
            } else if (priceHistory.size() < windowSize) {
                return null;
            }
            Double average = calculateAverage(priceHistory);
            if (average > priceHistory.peek()) {
                Trade trade = new Trade();
                trade.setDirection(Direction.BUY);
                trade.setPrice(price.getPrice());
                trade.setQuantity(1000L);
                trade.setProductName(price.getProductName());
                return trade;
            }

        }

        return null;
    }


    private static Double calculateAverage(Queue<Double> queue) {
        return queue
                .stream()
                .mapToDouble((x) -> x)
                .average()
                .orElse(0);
    }

}






















