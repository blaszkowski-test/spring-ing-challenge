package com.blapiter.onlinegame;

import java.util.List;
import java.util.TreeSet;

public interface GameService {
    public List<TreeSet<Clan>> getPrioritizeClans(Players players);
}
