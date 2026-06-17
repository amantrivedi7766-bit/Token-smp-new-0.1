package com.tokensmp.tokens;

public enum TokenRarity {
    COMMON("&7Common", 40),
    UNCOMMON("&aUncommon", 30),
    RARE("&9Rare", 20),
    EPIC("&5Epic", 8),
    LEGENDARY("&6Legendary", 2),
    ADMIN("&c&lAdmin", 0);

    private final String color;
    private final int weight;

    TokenRarity(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() { return color; }
    public int getWeight() { return weight; }
}
