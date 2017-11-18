package com.abb.demo.pricing.web.resource;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abb.demo.pricing.common.util.log.LogName;
import com.abb.demo.pricing.domain.common.Stock;
import com.abb.demo.pricing.domain.service.stock.StockDomainService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api")
public class StockWebResource {

    @Autowired
    private StockDomainService stockDomainService;

    @LogName
    @GetMapping("ping")
    public String ping() {
        return "OK";
    }

    @LogName
    @GetMapping("stocks")
    public Collection<Stock> stocks() {
        return stockDomainService.getStocks();
    }

    @GetMapping(path = "stocks/stream", produces = { MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE })
    public Flux<Stock> stockChangeEvents() {
        // TODO manage multiple price changes
        return stockDomainService.getStockUpdateEvents();
    }

}
