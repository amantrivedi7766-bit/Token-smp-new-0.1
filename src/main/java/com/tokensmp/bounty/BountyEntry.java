package com.tokensmp.bounty;

import java.util.UUID;

public class BountyEntry {
    private final UUID target;
    private final UUID setter;
    private final double amount;

    public BountyEntry(UUID target, UUID setter, double amount) {
        this.target = target; this.setter = setter; this.amount = amount;
    }
    // getters...
}
