package me.darklolly0312.map.BossMap.classes;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ClassHandler {
    public static HashMap<Player, String> playerClasses = new HashMap<Player, String>();
    public static HashMap<Player, ItemStack[]> playerInventories = new HashMap<Player, ItemStack[]>();

	public static void setPlayerClass(Player player, String classname) {
		if (classname.equalsIgnoreCase("berserker")) {
            playerClasses.put(player, "Berserker");
        }
		if (classname.equalsIgnoreCase("beschützer")) {
            playerClasses.put(player, "Beschützer");
        }
		if (classname.equalsIgnoreCase("mönch")) {
            playerClasses.put(player, "Mönch");
        }
		if (classname.equalsIgnoreCase("schütze")) {
            playerClasses.put(player, "Schütze");
        }
		
		setClassInventory(player);
		
	}
	
	public static void setClassInventory(Player p) {
		if (playerClasses.containsKey(p)) {
			Inventory inv = p.getInventory();
			playerInventories.put(p, inv.getContents());
			//inv.clear();
			
			if (isBerserker(p)) {
				inv.setItem(0, ClassItems.berserkerItem(0));
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
		} else {
			p.sendMessage("§cnoClassActive");
		}
	}
	
	public static void loadDefaultInventory(Player p) {
		//p.getInventory().setContents(arg0);
	}
	
	
	public static String getClass(Player p) {
		if (playerClasses.containsKey(p)) {
			return playerClasses.get(p);
		}
		return null;
	}
	

	
	public static boolean isBerserker(Player p) {
		if (playerClasses.containsKey(p)) {
			if (playerClasses.get(p).equals("Berserker")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public static boolean isDefender(Player p) {
		if (playerClasses.containsKey(p)) {
			if (playerClasses.get(p).equals("Beschützer")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public static boolean isMonk(Player p) {
		if (playerClasses.containsKey(p)) {
			if (playerClasses.get(p).equals("Mönch")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public static boolean isArcher(Player p) {
		if (playerClasses.containsKey(p)) {
			if (playerClasses.get(p).equals("Schütze")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
    
    
    
    
    
    

}
