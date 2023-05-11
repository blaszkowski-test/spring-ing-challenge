package com.blapiter.onlinegame;

import java.util.ArrayList;
import java.util.List;

public interface GameService {
    public List<ArrayList<Clan>> getPrioritizeClans(Players players);
}
