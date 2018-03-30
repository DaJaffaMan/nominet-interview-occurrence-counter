package com.nominet.test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LongCounter implements Counter {

    @Override
    public String getTop(int limit, Producer... producers) {
        List<Producer> producerList = Arrays.asList(producers);

        Map<Long, Integer> mappedOccurences = new ConcurrentHashMap<>();

        producerList.parallelStream().forEach(producer -> {
            HashMap<Long, Integer> maps = new HashMap<>();
            while (producer.hasNext()) {
                final long longValue = producer.next();
                if (maps.containsKey(longValue)) {
                    maps.put(longValue, maps.get(longValue) + 1);
                } else {
                    maps.put(longValue, 1);
                }
            }
            mappedOccurences.putAll(maps);
        });

        List<Map.Entry<Long, Integer>> list = new ArrayList<>(mappedOccurences.entrySet());
        list.sort(Comparator.comparing(Map.Entry::getValue));
        Collections.reverse(list);

        list = list.subList(0, limit < list.size() ? limit : list.size());

        return list.toString();
    }
}
