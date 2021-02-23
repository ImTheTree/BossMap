package me.darklolly0312.map.BossMap.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.MinecraftServer;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_16_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_16_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_16_R3.PlayerConnection;
import net.minecraft.server.v1_16_R3.PlayerInteractManager;
import net.minecraft.server.v1_16_R3.WorldServer;

public class NPC implements Listener {
	
	private static List<EntityPlayer> npcs = new ArrayList<EntityPlayer>();
	
	public static void createNPC(Location loc) {
		MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer world = ((CraftWorld) loc.getWorld()).getHandle();
		GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "TestNPC");
		EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
		npc.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		
		addNPCPacket(npc);
		npcs.add(npc);
	}
	
	public static void addNPCPacket(EntityPlayer npc) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
			connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
		}
	}
	
	@EventHandler
	public static void onPlayerJoinSendPacket(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if (npcs != null) {
			if (!npcs.isEmpty()) {
				for(EntityPlayer npc : npcs) {
					PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
					connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
					connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
					connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
				}
			}
		}
	}
	
	
	
	
}




























































































