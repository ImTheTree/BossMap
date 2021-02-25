package me.darklolly0312.map.BossMap;

import org.bukkit.event.EventHandler;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerInteractEvent;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.Location;
import org.bukkit.Material;
import java.util.ArrayList;
import org.bukkit.event.Listener;

public class infoSigns implements Listener {
    public static ArrayList<Material> signTypes = new ArrayList<Material>();
    public static Location infoSign_berserker;
    public static Location infoSign_beschuetzer;
    public static Location infoSign_moench;
    public static Location infoSign_schuetze;
    public static Location spellsSign_berserker;
    public static Location spellsSign_beschuetzer;
    public static Location spellsSign_moench;
    public static Location spellsSign_schuetze;
    
    @SuppressWarnings("deprecation")
	@EventHandler
    public static void nSignClick(final PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK ) {
        	if (e.getClickedBlock().getWorld() == Bukkit.getWorld(init.worldName)) {
        		if (infoSigns.signTypes.contains(e.getClickedBlock().getType())) {
        			final Player p = e.getPlayer();
                    final Block b = e.getClickedBlock();
                    
                    
                    // classinfosigns
                    
                    if (b.getLocation().getX() == infoSigns.infoSign_berserker.getX() && b.getLocation().getY() == infoSigns.infoSign_berserker.getY() && b.getLocation().getZ() == infoSigns.infoSign_berserker.getZ()) {
                        p.sendMessage("");
                        p.sendMessage("");
                        p.sendMessage("");
                        p.sendMessage("§6[§eBerserker-Info§6]");
                        p.sendMessage("§7§l Nahkampf");
                        p.sendMessage("§6Lebenspunkte: §a45");
                        p.sendMessage("§6Waffenschaden: §c9");
                        p.sendMessage("");
                        p.sendMessage("§fNutze '§f§o/setclass Besch\u00fctzer§f' um diese Klasse zu w\u00e4hlen.");
                        p.sendMessage("");
                    }
                    if (b.getLocation().getX() == infoSigns.infoSign_beschuetzer.getX() && b.getLocation().getY() == infoSigns.infoSign_beschuetzer.getY() && b.getLocation().getZ() == infoSigns.infoSign_beschuetzer.getZ()) {
                        p.sendMessage("");
                        p.sendMessage("");
                        p.sendMessage("");
                        p.sendMessage("§6[§eBesch\u00fctzer-Info§6]");
                        p.sendMessage("§7§l Nahkampf");
                        p.sendMessage("§6Lebenspunkte: §a60");
                        p.sendMessage("§6Waffenschaden: §c7");
                        p.sendMessage("");
                        p.sendMessage("§fNutze '§f§o/setclass Besch\u00fctzer§f' um diese Klasse zu w\u00e4hlen.");
                        p.sendMessage("");
                        p.sendMessage("");
                    }
                    if (b.getLocation().getX() == infoSigns.infoSign_moench.getX() && b.getLocation().getY() == infoSigns.infoSign_moench.getY() && b.getLocation().getZ() == infoSigns.infoSign_moench.getZ()) {
                        p.sendMessage("");
                        p.sendMessage("");
                        p.sendMessage("");
                        p.sendMessage("§6[§eM\u00f6nch-Info§6]");
                        p.sendMessage("§7§l Nahkampf/Fernkampf/Heilung");
                        p.sendMessage("§6Lebenspunkte: §a30");
                        p.sendMessage("§6Waffenschaden: §c6 (Verschie\u00dft heilige Str\u00e4hle)");
                        p.sendMessage("");
                        p.sendMessage("§fNutze '§f§o/setclass Besch\u00fctzer§f' um diese Klasse zu w\u00e4hlen.");
                        p.sendMessage("");
                        p.sendMessage("");
                    }
                    if (b.getLocation().getX() == infoSigns.infoSign_schuetze.getX() && b.getLocation().getY() == infoSigns.infoSign_schuetze.getY() && b.getLocation().getZ() == infoSigns.infoSign_schuetze.getZ()) {
                        p.sendMessage("");
                        p.sendMessage("");
                        p.sendMessage("");
                        p.sendMessage("§6[§eSch\u00fctze-Info§6]");
                        p.sendMessage("§7§l Fernkampf");
                        p.sendMessage("§6Lebenspunkte: §a35");
                        p.sendMessage("§6Pfeilschaden: §c12 (4 Nahkampfschaden)");
                        p.sendMessage("");
                        p.sendMessage("§fNutze '§f§o/setclass Besch\u00fctzer§f' um diese Klasse zu w\u00e4hlen.");
                        p.sendMessage("");
                        p.sendMessage("");
                    }
                    
                    
                    
                    // Spellinfosigns
                    
                    if (b.getLocation().getX() == infoSigns.spellsSign_berserker.getX() && b.getLocation().getY() == infoSigns.spellsSign_berserker.getY() && b.getLocation().getZ() == infoSigns.spellsSign_berserker.getZ()) {
                        
                    	/* p.sendMessage("");
                        p.sendMessage("");
                        p.sendMessage("");
                        p.sendMessage("§6[§eBerserker-F\u00e4higkeiten§6]");
                        p.sendMessage(" §eBerserkerwut: §f§oDu wirst f\u00fcr 20 sekunden w\u00fctend und verursachst 40% mehr Schaden. Au\u00dferdem wirst du um 50% des verursachten Schadens geheilt. §6(§31 min Abklingzeit§6)");
                        p.sendMessage(" §eZerstampfen: §fDu springst in die Luft, schmetterst auf dem Boden und verursachst 16 Schaden im Umkreis von 3 Bl\u00f6cken. §6(§320 sek Abklingzeit§6)");
                        p.sendMessage(" §eT\u00f6dlicher Sto\u00df: §fDu f\u00fchrst einen m\u00e4chtigen Hieb aus welcher Gegnern vor 12 Schaden verursacht. §6(§34 sek Abklingzeit§6)");
                        p.sendMessage("");
                        p.sendMessage("§6[Waffenf\u00e4higkeit]");
                        p.sendMessage(" §eSchlachtruf: §f§oDein Kampfschrei motiviert dich und deine Freunde welches den verursachten Schaden und die Bewegungsgeschwindigkeit f\u00fcr 30 sekunden um 20% erh\u00f6ht. §6(§345 sek Abklingzeit§6)");
                        p.sendMessage("");
                        p.sendMessage("§fNutze '§f§o/setclass Berserker§f' um diese Klasse zu w\u00e4hlen.");
                        p.sendMessage("");
                        p.sendMessage("");
                        //-------------------------------------------------   */

                        TextComponent coreComp = new TextComponent(" ");
                        
                        TextComponent spaceComp = new TextComponent("\n");
                        TextComponent descComp1 = new TextComponent("§e§l[Berserker-Fähigkeiten] \n");
                        TextComponent descComp2 = new TextComponent("§e§l[Waffenfähigkeit] \n");
                        TextComponent descComp3 = new TextComponent("§7Nutze '§f§o/setclass Berserker§7' um diese Klasse zu wählen. \n");
                        
                        TextComponent comp1 = new TextComponent("§6Berserkerwut \n");
                        comp1.setHoverEvent(new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                        		"§6Abklingzeit: §31 min. \n" + 
                                "§7§oDu wirst für 20 sekunden wütend und verursachst 40% mehr Schaden. Außerdem wirst du um 50% des verursachten Schadens geheilt."
                        		).create()));
                        
                        TextComponent comp2 = new TextComponent("§6Zerstampfen \n");
                        comp2.setHoverEvent(new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                        		"§6Abklingzeit: §320 sec. \n" + 
                                "§7§oDu springst in die Luft, schmetterst auf dem Boden und verursachst 16 Schaden im Umkreis von 3 Blöcken."
                        		).create()));
                        
                        TextComponent comp3 = new TextComponent("§6Tödlicher Stoß \n");
                        comp3.setHoverEvent(new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                        		"§6Abklingzeit: §34 sec. \n" + 
                                "§7§oDu führst einen mächtigen Hieb aus der an Gegnern vor dir 12 Schaden verursacht."
                        		).create()));
                        
                        TextComponent comp4 = new TextComponent("§6Schlachtruf \n");
                        comp4.setHoverEvent(new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                        		"§6Abklingzeit: §345 sec. \n" + 
                                "§7§oDein Kampfschrei motiviert dich und deine Freunde welches den verursachten Schaden und die Bewegungsgeschwindigkeit für 30 sekunden um 20% erhöht."
                        		).create()));
                        
                        coreComp.addExtra(spaceComp);
                        coreComp.addExtra(descComp1);
                        coreComp.addExtra(comp1);
                        coreComp.addExtra(comp2);
                        coreComp.addExtra(comp3);
                        coreComp.addExtra(spaceComp);
                        coreComp.addExtra(descComp2);
                        coreComp.addExtra(comp4);
                        coreComp.addExtra(spaceComp);
                        coreComp.addExtra(descComp3);
                        
                        
                        p.spigot().sendMessage(coreComp);
                        
                        
                        
                        
                        
                    }
                    if (b.getLocation().getX() == infoSigns.spellsSign_beschuetzer.getX() && b.getLocation().getY() == infoSigns.spellsSign_beschuetzer.getY() && b.getLocation().getZ() == infoSigns.spellsSign_beschuetzer.getZ()) {
                        p.sendMessage("");
                        p.sendMessage("");
                        p.sendMessage("");
                        p.sendMessage("§6[§eBesch\u00fctzer-F\u00e4higkeiten§6]");
                        p.sendMessage(" §eSchildschlag: §f§oDu st\u00f6\u00dft mit dem Schild nach vorne was gegner zur\u00fcckst\u00f6\u00dft und 8 Schaden verursacht. §6(§36 sek Abklingzeit§6)");
                        p.sendMessage(" §eDornenpanzer: §f§oDeine R\u00fcstung wird f\u00fcr 10 Sekunden mit Stacheln \u00fcberzogen und reflektiert 100% des erlittenen Schaden. §6(§330 sek Abklingzeit§6)");
                        p.sendMessage(" §eLetztes Gefecht: §f§oDeine Maximalen Lebenspunkte erh\u00f6hen sich 15 sekunden lang um 40 und du wirst um 20 Lebenspunkte geheilt. §6(§31 min Abklingzeit§6)");
                        p.sendMessage(" §eDonnerknall: §f§oErzeugt eine Explosion die Gegnern innerhalb von 3 Bl\u00f6cken 10 Schaden verursacht und sie 6 Sekunden lang um 50% verlangsamt. §6(§315 sek Abklingzeit§6)");
                        p.sendMessage("");
                        p.sendMessage("§6[Waffenf\u00e4higkeit]");
                        p.sendMessage(" §eSchildblock: §f§oDu erh\u00e4lst f\u00fcr 8 Sekunden 30% weniger Schaden. §6(§310 sek Abklingzeit§6)");
                        p.sendMessage("");
                        p.sendMessage("§fNutze '§f§o/setclass Besch\u00fctzer§f' um diese Klasse zu w\u00e4hlen.");
                        p.sendMessage("");
                        p.sendMessage("");
                    }
                    if (b.getLocation().getX() == infoSigns.spellsSign_moench.getX() && b.getLocation().getY() == infoSigns.spellsSign_moench.getY() && b.getLocation().getZ() == infoSigns.spellsSign_moench.getZ()) {
                        p.sendMessage("");
                        p.sendMessage("");
                        p.sendMessage("");
                        p.sendMessage("§6[§eM\u00f6nch-F\u00e4higkeiten§6]");
                        p.sendMessage(" §eMachtword Barriere: §f§oDu erzeugst eine Sphere die f\u00fcr 10 sekunden jeden erlittenen Schaden innerhalb der Sphere um 75% reduziert. §6(§340 sek Abklingzeit§6)");
                        p.sendMessage(" §eHeiliger Griff: §f§oZieht den anvisierten Spieler zu dir und heilt ihn f\u00fcr 6 Lebenspunkte. §6(§330 sek Abklingzeit§6)");
                        p.sendMessage(" §eHeilige Explosion: §f§oDu erzeugst eine heilige Explosion die gegner im Umkreis von 3 bl\u00f6cken 8 Schaden zuf\u00fcgt und freunde um 8 Lebenspunkte heilt. §6(§310 sek Abklingzeit§6)");
                        p.sendMessage(" §eGebet der Heilung: §f§oAlle Spieler werden um 40 Lebenspunkte geheilt nachdem du 4 Sekunden lang nichts tust, jeder erlittene Schaden bricht den Vorgang jedoch ab. §6(§31 min Abklingzeit§6)");
                        p.sendMessage("");
                        p.sendMessage("§6[Waffenf\u00e4higkeit]");
                        p.sendMessage(" §eHeilung: §f§oHeilt Spieler im Zielgebiet um 12 Lebenspunkte. §6(§35 sek Abklingzeit§6)");
                        p.sendMessage("");
                        p.sendMessage("§fNutze '§f§o/setclass M\u00f6nch§f' um diese Klasse zu w\u00e4hlen.");
                        p.sendMessage("");
                        p.sendMessage("");
                    }
                    if (b.getLocation().getX() == infoSigns.spellsSign_schuetze.getX() && b.getLocation().getY() == infoSigns.spellsSign_schuetze.getY() && b.getLocation().getZ() == infoSigns.spellsSign_schuetze.getZ()) {
                        p.sendMessage("");
                        p.sendMessage("");
                        p.sendMessage("");
                        p.sendMessage("§6[§eSch\u00fctze-F\u00e4higkeiten§6]");
                        p.sendMessage(" §ePfeilsalve: §f§oL\u00e4sst 6 sekunden lang im Zielgebiet Pfeile vom Himmel regnen welche Gegnern bei Treffer 9 Schaden zuf\u00fcgt. §6(§318 sek Abklingzeit§6)");
                        p.sendMessage(" §eSchnellfeuer: §f§oDu kannst f\u00fcr 5 sekunden jede halbe sekunde einen Pfeil mit voller Geschwindigkeit verschie\u00dfen. §6(§312 sek Abklingzeit§6)");
                        p.sendMessage(" §eBello: §f§oRuft f\u00fcr 20 sekunden einen Wolf herbei der dir im Kampf zur Seite steht (40 Lebenspunkte, 10 Schaden). §6(§340 sek Abklingzeit§6)");
                        p.sendMessage(" §eTarnung: §f§oDu wirst f\u00fcr 6 sekunden unsichtbar. §6(§330 sek Abklingzeit§6)");
                        p.sendMessage("");
                        p.sendMessage("§6[Waffenf\u00e4higkeit]");
                        p.sendMessage(" §eGezielter Schuss: §f§oDein n\u00e4chste Schuss verursacht 24 Schaden wenn du den den Pfeil 3 Sekunden lang gespannt h\u00e4lst, jeder erlittene Schaden bricht den Vorgang jedoch ab. §6(§36 sek Abklingzeit§6)");
                        p.sendMessage("");
                        p.sendMessage("§fNutze '§f§o/setclass Sch\u00fctze§f' um diese Klasse zu w\u00e4hlen.");
                        p.sendMessage("");
                        p.sendMessage("");
                    }
        		}
        		
        	}
        	
        }
    }
}
