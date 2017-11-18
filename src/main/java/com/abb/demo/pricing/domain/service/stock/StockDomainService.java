package com.abb.demo.pricing.domain.service.stock;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.time.Duration;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abb.demo.pricing.common.util.log.LogName;
import com.abb.demo.pricing.domain.common.Stock;
import com.abb.demo.pricing.domain.common.StockPriceUpdate;
import com.abb.demo.pricing.repository.stock.StockRepository;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

@Component
public class StockDomainService {

    private static int refreshPriceInterval = 50;

    @Autowired
    private StockRepository stockRepository;

    @LogName
    public Stock changePriceRandomly() {
        Random random = new Random();
        Optional<Stock> changePrice = stockRepository.changePrice(StockPriceUpdate.of(random.nextInt(Stock.STOCK_SIZE) + 1, random.nextInt(1_000)));
        return changePrice.get();
    }

    @LogName
    public Collection<Stock> getStocks() {
        return stockRepository.getStocks()
                .stream()
                .sorted(comparing(Stock::getCode))
                .unordered()
                .collect(toList());
    }

    @LogName
    public Flux<Stock> getStockUpdateEvents() {
        // TODO trigger change price by an event
        // TODO manage unsubscribe
        Flux<Long> interval = Flux.interval(Duration.ofMillis(refreshPriceInterval));
        interval.subscribe();

        Flux<Stock> stocks = Flux.fromStream(Stream.generate(() -> changePriceRandomly()));

        return Flux.zip(interval, stocks).map(Tuple2::getT2);
    }
}
