package me.darklolly0312.map.BossMap;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

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
	
	@EventHandler
	public static void nSignClick(PlayerInteractEvent e) {
		
		if (e.getClickedBlock().getWorld() == Bukkit.getWorld(init.worldName)) {
			if (signTypes.contains(e.getClickedBlock().getType())) {
				Player p = e.getPlayer();
				Block b = e.getClickedBlock();
				
				
				//
				//
				// Infosigns
				if (b.getLocation().getX() == infoSign_berserker.getX() && b.getLocation().getY() == infoSign_berserker.getY() && b.getLocation().getZ() == infoSign_berserker.getZ()) { // Berserker info
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("�6[�eBerserker-Info�6]");
					p.sendMessage("�7�l Nahkampf");
					p.sendMessage("�6Lebenspunkte: �a45");
					p.sendMessage("�6Waffenschaden: �c9");
					p.sendMessage("");
					p.sendMessage("�fNutze '�f�o/setclass Besch�tzer�f' um diese Klasse zu w�hlen.");
					p.sendMessage("");
					p.sendMessage("");
				}
				
				if (b.getLocation().getX() == infoSign_beschuetzer.getX() && b.getLocation().getY() == infoSign_beschuetzer.getY() && b.getLocation().getZ() == infoSign_beschuetzer.getZ()) { // Besch�tzer info
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("�6[�eBesch�tzer-Info�6]");
					p.sendMessage("�7�l Nahkampf");
					p.sendMessage("�6Lebenspunkte: �a60");
					p.sendMessage("�6Waffenschaden: �c7");
					p.sendMessage("");
					p.sendMessage("�fNutze '�f�o/setclass Besch�tzer�f' um diese Klasse zu w�hlen.");
					p.sendMessage("");
					p.sendMessage("");
					
				}
				
				if (b.getLocation().getX() == infoSign_moench.getX() && b.getLocation().getY() == infoSign_moench.getY() && b.getLocation().getZ() == infoSign_moench.getZ()) { // M�nch info
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("�6[�eM�nch-Info�6]");
					p.sendMessage("�7�l Nahkampf/Fernkampf/Heilung");
					p.sendMessage("�6Lebenspunkte: �a30");
					p.sendMessage("�6Waffenschaden: �c6 (Verschie�t heilige Str�hle)");
					p.sendMessage("");
					p.sendMessage("�fNutze '�f�o/setclass Besch�tzer�f' um diese Klasse zu w�hlen.");
					p.sendMessage("");
					p.sendMessage("");
					
				}
				
				if (b.getLocation().getX() == infoSign_schuetze.getX() && b.getLocation().getY() == infoSign_schuetze.getY() && b.getLocation().getZ() == infoSign_schuetze.getZ()) { // Sch�tze info
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("�6[�eSch�tze-Info�6]");
					p.sendMessage("�7�l Fernkampf");
					p.sendMessage("�6Lebenspunkte: �a35");
					p.sendMessage("�6Pfeilschaden: �c12 (4 Nahkampfschaden)");
					p.sendMessage("");
					p.sendMessage("�fNutze '�f�o/setclass Besch�tzer�f' um diese Klasse zu w�hlen.");
					p.sendMessage("");
					p.sendMessage("");
					
				}
				
				
				
				//
				//
				// Spellsigns
				if (b.getLocation().getX() == spellsSign_berserker.getX() && b.getLocation().getY() == spellsSign_berserker.getY() && b.getLocation().getZ() == spellsSign_berserker.getZ()) { // Berserker info
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("�6[�eBerserker-F�higkeiten�6]");
					p.sendMessage(" �eBerserkerwut: �f�oDu wirst f�r 20 sekunden w�tend und verursachst 40% mehr Schaden. Au�erdem wirst du um 50% des verursachten Schadens geheilt. �6(�31 min Abklingzeit�6)");
					p.sendMessage(" �eZerstampfen: �fDu springst in die Luft, schmetterst auf dem Boden und verursachst 16 Schaden im Umkreis von 3 Bl�cken. �6(�320 sek Abklingzeit�6)");
					p.sendMessage(" �eT�dlicher Sto�: �fDu f�hrst einen m�chtigen Hieb aus welcher Gegnern vor 12 Schaden verursacht. �6(�34 sek Abklingzeit�6)");
					p.sendMessage("");
					p.sendMessage("�6[Waffenf�higkeit]");
					p.sendMessage(" �eSchlachtruf: �f�oDein Kampfschrei motiviert dich und deine Freunde welches den verursachten Schaden und die Bewegungsgeschwindigkeit f�r 30 sekunden um 20% erh�ht. �6(�345 sek Abklingzeit�6)");
					p.sendMessage("");
					p.sendMessage("�fNutze '�f�o/setclass Berserker�f' um diese Klasse zu w�hlen.");
					p.sendMessage("");
					p.sendMessage("");
				}
				
				if (b.getLocation().getX() == spellsSign_beschuetzer.getX() && b.getLocation().getY() == spellsSign_beschuetzer.getY() && b.getLocation().getZ() == spellsSign_beschuetzer.getZ()) { // Besch�tzer info
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("�6[�eBesch�tzer-F�higkeiten�6]");
					p.sendMessage(" �eSchildschlag: �f�oDu st��t mit dem Schild nach vorne was gegner zur�ckst��t und 8 Schaden verursacht. �6(�36 sek Abklingzeit�6)");
					p.sendMessage(" �eDornenpanzer: �f�oDeine R�stung wird f�r 10 Sekunden mit Stacheln �berzogen und reflektiert 100% des erlittenen Schaden. �6(�330 sek Abklingzeit�6)");
					p.sendMessage(" �eLetztes Gefecht: �f�oDeine Maximalen Lebenspunkte erh�hen sich 15 sekunden lang um 40 und du wirst um 20 Lebenspunkte geheilt. �6(�31 min Abklingzeit�6)");
					p.sendMessage(" �eDonnerknall: �f�oErzeugt eine Explosion die Gegnern innerhalb von 3 Bl�cken 10 Schaden verursacht und sie 6 Sekunden lang um 50% verlangsamt. �6(�315 sek Abklingzeit�6)");
					p.sendMessage("");
					p.sendMessage("�6[Waffenf�higkeit]");
					p.sendMessage(" �eSchildblock: �f�oDu erh�lst f�r 8 Sekunden 30% weniger Schaden. �6(�310 sek Abklingzeit�6)");
					p.sendMessage("");
					p.sendMessage("�fNutze '�f�o/setclass Besch�tzer�f' um diese Klasse zu w�hlen.");
					p.sendMessage("");
					p.sendMessage("");
					
				}
				
				if (b.getLocation().getX() == spellsSign_moench.getX() && b.getLocation().getY() == spellsSign_moench.getY() && b.getLocation().getZ() == spellsSign_moench.getZ()) { // M�nch info
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("�6[�eM�nch-F�higkeiten�6]");
					p.sendMessage(" �eMachtword Barriere: �f�oDu erzeugst eine Sphere die f�r 10 sekunden jeden erlittenen Schaden innerhalb der Sphere um 75% reduziert. �6(�340 sek Abklingzeit�6)");
					p.sendMessage(" �eHeiliger Griff: �f�oZieht den anvisierten Spieler zu dir und heilt ihn f�r 6 Lebenspunkte. �6(�330 sek Abklingzeit�6)");
					p.sendMessage(" �eHeilige Explosion: �f�oDu erzeugst eine heilige Explosion die gegner im Umkreis von 3 bl�cken 8 Schaden zuf�gt und freunde um 8 Lebenspunkte heilt. �6(�310 sek Abklingzeit�6)");
					p.sendMessage(" �eGebet der Heilung: �f�oAlle Spieler werden um 40 Lebenspunkte geheilt nachdem du 4 Sekunden lang nichts tust, jeder erlittene Schaden bricht den Vorgang jedoch ab. �6(�31 min Abklingzeit�6)");
					p.sendMessage("");
					p.sendMessage("�6[Waffenf�higkeit]");
					p.sendMessage(" �eHeilung: �f�oHeilt Spieler im Zielgebiet um 12 Lebenspunkte. �6(�35 sek Abklingzeit�6)");
					p.sendMessage("");
					p.sendMessage("�fNutze '�f�o/setclass M�nch�f' um diese Klasse zu w�hlen.");
					p.sendMessage("");
					p.sendMessage("");
					
				}
				
				if (b.getLocation().getX() == spellsSign_schuetze.getX() && b.getLocation().getY() == spellsSign_schuetze.getY() && b.getLocation().getZ() == spellsSign_schuetze.getZ()) { // Sch�tze info
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("�6[�eSch�tze-F�higkeiten�6]");
					p.sendMessage(" �ePfeilsalve: �f�oL�sst 6 sekunden lang im Zielgebiet Pfeile vom Himmel regnen welche Gegnern bei Treffer 9 Schaden zuf�gt. �6(�318 sek Abklingzeit�6)");
					p.sendMessage(" �eSchnellfeuer: �f�oDu kannst f�r 5 sekunden jede halbe sekunde einen Pfeil mit voller Geschwindigkeit verschie�en. �6(�312 sek Abklingzeit�6)");
					p.sendMessage(" �eBello: �f�oRuft f�r 20 sekunden einen Wolf herbei der dir im Kampf zur Seite steht (40 Lebenspunkte, 10 Schaden). �6(�340 sek Abklingzeit�6)");
					p.sendMessage(" �eTarnung: �f�oDu wirst f�r 6 sekunden unsichtbar. �6(�330 sek Abklingzeit�6)");
					p.sendMessage("");
					p.sendMessage("�6[Waffenf�higkeit]");
					p.sendMessage(" �eGezielter Schuss: �f�oDein n�chste Schuss verursacht 24 Schaden wenn du den den Pfeil 3 Sekunden lang gespannt h�lst, jeder erlittene Schaden bricht den Vorgang jedoch ab. �6(�36 sek Abklingzeit�6)");
					p.sendMessage("");
					p.sendMessage("�fNutze '�f�o/setclass Sch�tze�f' um diese Klasse zu w�hlen.");
					p.sendMessage("");
					p.sendMessage("");
					
				}
				
				
			}
		}
		
	}
	
}
