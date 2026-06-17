package com.tokensmp.tokens;

import com.tokensmp.abilities.Ability;
import com.tokensmp.abilities.impl.*;
import java.util.Arrays;
import java.util.List;

public enum Token {

    // ---- Normal Tokens (7) ----
    LIGHTNING("lightning", "Lightning", TokenRarity.EPIC, 1001,
            Arrays.asList(new LightningStrikeAbility(), new ChainReactionAbility(), new StormSummonAbility())),
    TIME_WARP("timewarp", "Time Warp", TokenRarity.RARE, 1002,
            Arrays.asList(new TimeWarpAbility(), new TemporalSlowAbility(), new RewindAbility())),
    GRAVITY("gravity", "Gravity", TokenRarity.UNCOMMON, 1003,
            Arrays.asList(new GravityPulseAbility(), new BlackHoleAbility(), new FloatAbility())),
    INVISIBILITY("invisibility", "Invisibility", TokenRarity.RARE, 1004,
            Arrays.asList(new InvisibilityFieldAbility(), new ShadowStepAbility(), new CloakAbility())),
    LIFE_DRAIN("lifedrain", "Life Drain", TokenRarity.UNCOMMON, 1005,
            Arrays.asList(new LifeDrainAbility(), new SoulStealAbility(), new VampiricTouchAbility())),
    FIRE_STORM("firestorm", "Fire Storm", TokenRarity.EPIC, 1006,
            Arrays.asList(new FireStormAbility(), new FlameAuraAbility(), new MeteorStrikeAbility())),
    ICE_SHARD("iceshard", "Ice Shard", TokenRarity.COMMON, 1007,
            Arrays.asList(new IceShardAbility(), new FreezeAbility(), new SnowStormAbility())),

    // ---- Admin Tokens (3) ----
    ADMIN_OMNI("admin_omni", "Omni", TokenRarity.ADMIN, 2001,
            Arrays.asList(new AdminWipeAbility(), new GlobalBuffAbility(), new InstantKillAbility()), true),
    ADMIN_GOD("admin_god", "God", TokenRarity.ADMIN, 2002,
            Arrays.asList(new GodModeAbility(), new SmiteAbility(), new HealAllAbility()), true),
    ADMIN_VOID("admin_void", "Void", TokenRarity.ADMIN, 2003,
            Arrays.asList(new VoidWalkAbility(), new VoidRiftAbility(), new VoidAnnihilationAbility()), true);

    private final String id;
    private final String displayName;
    private final TokenRarity rarity;
    private final int modelData;
    private final List<Ability> abilities;
    private final boolean adminOnly;

    Token(String id, String displayName, TokenRarity rarity, int modelData, List<Ability> abilities) {
        this(id, displayName, rarity, modelData, abilities, false);
    }

    Token(String id, String displayName, TokenRarity rarity, int modelData, List<Ability> abilities, boolean adminOnly) {
        this.id = id;
        this.displayName = displayName;
        this.rarity = rarity;
        this.modelData = modelData;
        this.abilities = abilities;
        this.adminOnly = adminOnly;
    }

    public String getId() { return id; }
    public String getDisplayName() { return displayName; }
    public TokenRarity getRarity() { return rarity; }
    public int getModelData() { return modelData; }
    public List<Ability> getAbilities() { return abilities; }
    public boolean isAdminOnly() { return adminOnly; }

    public static Token fromId(String id) {
        for (Token t : values()) {
            if (t.id.equalsIgnoreCase(id)) return t;
        }
        return null;
    }
}
