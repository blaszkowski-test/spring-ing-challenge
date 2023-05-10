package com.blapiter.onlinegame;

import java.util.List;
import java.util.PriorityQueue;

public interface GameService {
    public List<PriorityQueue<Clan>> getPrioritizeClans(Players players);
}
