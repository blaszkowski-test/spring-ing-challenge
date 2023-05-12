package com.blapiter.atmservice;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("AtmServiceImpl")
public class AtmServiceImpl implements AtmService {
    @Override
    public Order getSortedOrders(ServiceTasks collection) {
        return collection
                .stream()
                .sorted()
                .distinct()
                .map(item -> new ATM(item.getRegion(), item.getAtmId()))
                .collect(Collectors.toCollection(Order::new));
    }
}
