package me.darklolly0312.map.BossMap.CustomEntity;

import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.EntityMonster;
import net.minecraft.server.v1_16_R3.AttributeProvider;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.World;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.Location;
import net.minecraft.server.v1_16_R3.EntityPigZombie;

public class Boss extends EntityPigZombie {
    private boolean b;
    
    @SuppressWarnings("deprecation")
	public Boss(final Location loc) {
        super(EntityTypes.ZOMBIFIED_PIGLIN, (World)((CraftWorld)loc.getWorld()).getHandle());
        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        this.setAggressive(true);
        this.setCustomNameVisible(true);
        this.setCustomName((IChatBaseComponent)new ChatComponentText("§4[§c§lBOSS§4]"));
        final LivingEntity test = (LivingEntity)this.getBukkitEntity();
        test.getEquipment().setHelmet(new ItemStack(Material.GOLDEN_HELMET));
        test.getEquipment().setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
        test.getEquipment().setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS));
        test.getEquipment().setBoots(new ItemStack(Material.GOLDEN_BOOTS));
        test.setMaxHealth(1000.0);
        this.setHealth(1000.0f);
    }
    
    public static AttributeProvider.Builder eS() {
        return EntityMonster.eR().a(GenericAttributes.FOLLOW_RANGE, 200.0).a(GenericAttributes.ATTACK_DAMAGE, 14.0).a(GenericAttributes.ARMOR, 0.0);
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
