package com.blapiter.onlinegame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("GameServiceImpl")
public class GameServiceImpl implements GameService {
    @Override
    public List<TreeSet<Clan>> getPrioritizeClans(Players players) {
        Clan next = null;
        Integer currentPlayers = 0;
        List<Clan> toRemove = new LinkedList<Clan>();
        List<TreeSet<Clan>> order = new ArrayList<TreeSet<Clan>>();
        while (!players.clans().isEmpty()) {

            TreeSet<Clan> clans = new TreeSet<Clan>();

            next = players.clans().pollFirst();

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
            toRemove.clear();
        }
        return order;
    }

}
