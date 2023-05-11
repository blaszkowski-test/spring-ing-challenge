package com.blapiter.onlinegame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("GameServiceImpl")
public class GameServiceImpl implements GameService {
    @Override
    public List<ArrayList<Clan>> getPrioritizeClans(Players players) {
        Clan next = null;
        Iterator<Clan> it = null;
        Integer currentPlayers = 0;
        List<Clan> toRemove = new LinkedList<Clan>();
        List<ArrayList<Clan>> order = new ArrayList<ArrayList<Clan>>();
        Collections.sort(players.clans());
        while (!players.clans().isEmpty()) {
            ArrayList<Clan> clans = new ArrayList<Clan>();
            it = players.clans().iterator();
            while (it.hasNext()) {
                next = it.next();
                if (next.numberOfPlayers + currentPlayers <= players.groupCount()) {
                    clans.add(next);
                    toRemove.add(next);
                    currentPlayers += next.numberOfPlayers;
                }
                if (currentPlayers >= players.groupCount()) {
                    break;
                }
            }
            order.add(clans);
            players.clans().removeAll(toRemove);
            Collections.sort(players.clans());
            currentPlayers = 0;
            toRemove.clear();
        }
        return order;
    }

}
