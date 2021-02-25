package me.darklolly0312.map.BossMap.testing;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import net.minecraft.server.v1_16_R3.PlayerConnection;
import net.minecraft.server.v1_16_R3.Entity;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_16_R3.EntityHuman;
import net.minecraft.server.v1_16_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_16_R3.PacketPlayOutPlayerInfo;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import net.minecraft.server.v1_16_R3.WorldServer;
import net.minecraft.server.v1_16_R3.MinecraftServer;
import net.minecraft.server.v1_16_R3.PlayerInteractManager;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.Location;
import java.util.ArrayList;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import java.util.List;
import org.bukkit.event.Listener;

public class NPC implements Listener {
    private static List<EntityPlayer> npcs = new ArrayList<EntityPlayer>();
    
    public static void createNPC(final Location loc) {
        final MinecraftServer server = (MinecraftServer)((CraftServer)Bukkit.getServer()).getServer();
        final WorldServer world = ((CraftWorld)loc.getWorld()).getHandle();
        final GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "TestNPC");
        final EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
        npc.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        addNPCPacket(npc);
        NPC.npcs.add(npc);
    }
    
    public static void addNPCPacket(final EntityPlayer npc) {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            final PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[] { npc }));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn((EntityHuman)npc));
            connection.sendPacket(new PacketPlayOutEntityHeadRotation((Entity)npc, (byte)(npc.yaw * 256.0f / 360.0f)));
        }
    }
    
    @EventHandler
    public static void onPlayerJoinSendPacket(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (NPC.npcs != null && !NPC.npcs.isEmpty()) {
            for (final EntityPlayer npc : NPC.npcs) {
                final PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
                connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[] { npc }));
                connection.sendPacket(new PacketPlayOutNamedEntitySpawn((EntityHuman)npc));
                connection.sendPacket(new PacketPlayOutEntityHeadRotation((Entity)npc, (byte)(npc.yaw * 256.0f / 360.0f)));
            }
        }
    }
}
