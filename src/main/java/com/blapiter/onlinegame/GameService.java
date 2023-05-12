package com.blapiter.onlinegame;

import java.util.LinkedList;
import java.util.List;

public interface GameService {
    public List<LinkedList<Clan>> getSortedClans(Players players);
}
