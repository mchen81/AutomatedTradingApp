package com.demo;

import com.demo.model.Price;
import com.demo.model.Trade;

public interface TradingAlgorithm {

    /**
     * Builds a trade to be executed based on the supplied prices.
     *
     * @param price data
     * @return trade to execute
     */
    Trade buildTrades(Price price);


}
