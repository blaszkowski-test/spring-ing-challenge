package com.blapiter.onlinegame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("GameServiceImpl")
public class GameServiceImpl implements GameService {
    @Override
    public List<PriorityQueue<Clan>> getPrioritizeClans(Players players) {
        Clan next = null;
        Integer currentPlayers = 0;
        List<Clan> toRemove = new LinkedList<Clan>();
        List<PriorityQueue<Clan>> order = new ArrayList<PriorityQueue<Clan>>();
        while (!players.clans().isEmpty()) {

            PriorityQueue<Clan> clans = new PriorityQueue<Clan>();

            next = players.clans().poll();

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
