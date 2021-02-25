package me.darklolly0312.map.BossMap;

public class devMode {
    public static boolean enabled;
    
    static {
        devMode.enabled = false;
    }
    
    public static void enable() {
        devMode.enabled = true;
    }
    
    public static void disable() {
        devMode.enabled = false;
    }
}
