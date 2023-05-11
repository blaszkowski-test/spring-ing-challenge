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
        Integer currentPlayers = 0;
        List<Clan> toRemove = new LinkedList<Clan>();
        List<ArrayList<Clan>> order = new ArrayList<ArrayList<Clan>>();
        Collections.sort(players.clans());
        while (!players.clans().isEmpty()) {

            ArrayList<Clan> clans = new ArrayList<Clan>();

            next = players.clans().get(0);
            players.clans().remove(0);

            currentPlayers = next.numberOfPlayers;
            clans.add(next);

            if (currentPlayers >= players.groupCount()) {
                order.add(clans);
                continue;
            }

            Iterator<Clan> it = players.clans().iterator();
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
            toRemove.clear();
        }
        return order;
    }

}
