package com.tokensmp.bounty;

import com.tokensmp.TokenSMP;
import java.util.*;

public class BountyManager {
    private final TokenSMP plugin;
    private final Map<UUID, BountyEntry> bounties = new HashMap<>();

    public BountyManager(TokenSMP plugin) { this.plugin = plugin; }

    public void setBounty(UUID target, UUID setter, double amount) {
        bounties.put(target, new BountyEntry(target, setter, amount));
    }

    public void removeBounty(UUID target) { bounties.remove(target); }

    public BountyEntry getBounty(UUID target) { return bounties.get(target); }
}
