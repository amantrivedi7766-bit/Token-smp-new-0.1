package com.tokensmp.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class TaskUtil {
    public static void runAsync(Plugin plugin, Runnable task) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, task);
    }
}
