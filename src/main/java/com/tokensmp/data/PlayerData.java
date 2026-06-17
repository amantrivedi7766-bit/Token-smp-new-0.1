package com.tokensmp.data;

import com.tokensmp.tokens.Token;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerData {
    private final UUID uuid;
    private Map<Token, Integer> tokens = new HashMap<>();
    private Map<Token, Integer> tokenLevels = new HashMap<>();
    private long lastSpinTime;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
    }

    public void addToken(Token token, int amount) {
        tokens.put(token, tokens.getOrDefault(token, 0) + amount);
    }

    public void removeToken(Token token, int amount) {
        tokens.put(token, Math.max(0, tokens.getOrDefault(token, 0) - amount));
    }

    public int getTokenLevel(Token token) {
        return tokenLevels.getOrDefault(token, 1);
    }

    public void setTokenLevel(Token token, int level) {
        tokenLevels.put(token, level);
    }

    // getters and setters
    public Map<Token, Integer> getTokens() { return tokens; }
    public Map<Token, Integer> getTokenLevels() { return tokenLevels; }
    public long getLastSpinTime() { return lastSpinTime; }
    public void setLastSpinTime(long time) { this.lastSpinTime = time; }
}
