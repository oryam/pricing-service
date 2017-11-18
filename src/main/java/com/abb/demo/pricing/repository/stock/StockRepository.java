package com.abb.demo.pricing.repository.stock;

import static java.lang.String.format;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.StringUtils.leftPad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.abb.demo.pricing.common.util.log.LogName;
import com.abb.demo.pricing.domain.common.Stock;
import com.abb.demo.pricing.domain.common.StockPriceUpdate;

@Component
public class StockRepository {

    private Map<Long, Stock> stocksData;

    @LogName
    public int createStocks() {
        List<Long> ids = new ArrayList<>();
        for (long i = 1; i <= Stock.STOCK_SIZE; i++) {
            ids.add(i);
        }

        stocksData = ids
                .stream()
                .map(id -> Stock.of(id, format("ID#%s", leftPad(id + "", 3, '0')), 0., new Date()))
                .collect(toMap(Stock::getId, identity()));

        return stocksData.size();
    }

    @LogName
    public Collection<Stock> getStocks() {
        return stocksData.values();
    }

    @LogName
    public Optional<Stock> changePrice(StockPriceUpdate priceUpdate) {
        if (stocksData.containsKey(priceUpdate.getId())) {
            Stock stock = stocksData.get(priceUpdate.getId());
            stock.updatePrice(priceUpdate.getPrice());
            return Optional.of(stock);
        }
        return Optional.empty();
    }

}
