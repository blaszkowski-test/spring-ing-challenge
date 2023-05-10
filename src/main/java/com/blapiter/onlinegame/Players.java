package com.blapiter.onlinegame;

import java.util.PriorityQueue;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record Players(
        @NotNull @Min(1) @Max(1000) Integer groupCount,
        @Size(min = 1, max = 20000) PriorityQueue<Clan> clans) {
}