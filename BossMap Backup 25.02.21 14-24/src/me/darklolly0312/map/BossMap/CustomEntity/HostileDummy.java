package me.darklolly0312.map.BossMap.CustomEntity;

import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.EntityMonster;
import net.minecraft.server.v1_16_R3.AttributeProvider;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import org.bukkit.entity.LivingEntity;
import net.minecraft.server.v1_16_R3.World;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.Location;
import net.minecraft.server.v1_16_R3.EntityZombie;

public class HostileDummy extends EntityZombie {
    private boolean b;
    
    @SuppressWarnings("deprecation")
	public HostileDummy(final Location loc) {
        super(EntityTypes.ZOMBIE, (World)((CraftWorld)loc.getWorld()).getHandle());
        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        final LivingEntity test = (LivingEntity)this.getBukkitEntity();
        test.setMaxHealth(100.0f);
        test.setHealth(100.0f);
        this.setNoAI(true);
        this.setSilent(true);
        this.setCustomNameVisible(true);
        this.setCustomName((IChatBaseComponent)new ChatComponentText("§cFeindliche Kampfattrappe [§2§lHP: §2" + this.getHealth() + "/100§a]"));
    }
    
    public static AttributeProvider.Builder eS() {
        return EntityMonster.eR().a(GenericAttributes.ARMOR, 0.0);
    }
    
    public boolean isSpecial() {
        return this.b;
    }
    
    public void setSpecial(final boolean b) {
        if (b) {
            this.b = b;
        }
    }
}
