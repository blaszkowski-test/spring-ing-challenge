package com.blapiter.onlinegame;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class Clan implements Comparable<Clan> {

    @NotNull
    @Min(1)
    @Max(1000)
    protected Integer numberOfPlayers;

    @NotNull
    @Min(1)
    @Max(1000000)
    protected Integer points;

    Clan(Integer numberOfPlayers, Integer points) {
        this.numberOfPlayers = numberOfPlayers;
        this.points = points;
    }

    public Integer getNumberOfPlayers() {
        return this.numberOfPlayers;
    }

    public Integer getPoints() {
        return this.points;
    }

    @Override
    public int compareTo(Clan other) {
        if (!this.points.equals(other.points)) {
            return -this.points.compareTo(other.points);
        }
        return this.numberOfPlayers.compareTo(other.numberOfPlayers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.numberOfPlayers, this.points);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (this.getClass() != object.getClass()) {
            return false;
        }

        final Clan other = (Clan) object;

        return this.points.equals(other.points) && this.numberOfPlayers.equals(other.numberOfPlayers);
    }

    @Override
    public String toString() {
        return "Clan [numberOfPlayers=" + numberOfPlayers + ", points=" + points + "]";
    }
}
