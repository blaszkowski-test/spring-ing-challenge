package com.blapiter.onlinegame;

import java.util.LinkedList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("GameServiceImpl")
public class GameServiceImpl implements GameService {
    @Override
    public List<LinkedList<Clan>> getPrioritizeClans(Players players) {
        Clan next = null;
        Iterator<Clan> it = null;
        Integer currentPlayers = 0;
        List<Clan> toRemove = new LinkedList<Clan>();
        List<LinkedList<Clan>> order = new LinkedList<LinkedList<Clan>>();
        while (!players.clans().isEmpty()) {
            Collections.sort(players.clans());
            LinkedList<Clan> clans = new LinkedList<Clan>();
            it = players.clans().iterator();
            while (it.hasNext() && currentPlayers < players.groupCount()) {
                next = it.next();
                if (next.numberOfPlayers + currentPlayers <= players.groupCount()) {
                    clans.add(next);
                    toRemove.add(next);
                    currentPlayers += next.numberOfPlayers;
                }
            }
            order.add(clans);
            players.clans().removeAll(toRemove);
            toRemove.clear();
            currentPlayers = 0;
        }
        return order;
    }

}
