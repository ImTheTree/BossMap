package me.darklolly0312.map.BossMap.CustomEntity;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import net.minecraft.server.v1_16_R3.AttributeProvider;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EntityMonster;
import net.minecraft.server.v1_16_R3.EntityPigZombie;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.GenericAttributes;

public class Boss extends EntityPigZombie {
	private boolean b;
	
	@SuppressWarnings("deprecation")
	public Boss(Location loc) {
		super(EntityTypes.ZOMBIFIED_PIGLIN, ((CraftWorld) loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		this.setAggressive(true);
		this.setCustomNameVisible(true);
		this.setCustomName(new ChatComponentText("§4[§c§lBOSS§4]"));
		
		//this.goalSelector.a(0, new PathfinderGoalMeleeAttack(this, 64, true));
		//this.goalSelector.a(1, new PathfinderGoalLeapAtTarget(this, 4));
		LivingEntity test = (LivingEntity) this.getBukkitEntity();
		test.getEquipment().setHelmet(new ItemStack(Material.GOLDEN_HELMET));
		test.getEquipment().setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
		test.getEquipment().setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS));
		test.getEquipment().setBoots(new ItemStack(Material.GOLDEN_BOOTS));
		test.setMaxHealth(1000.0f);
		this.setHealth(1000.0f);
	}
	
	public static AttributeProvider.Builder eS() {
        return EntityMonster.eR()	.a(GenericAttributes.FOLLOW_RANGE, 200D)
        							//.a(GenericAttributes.MOVEMENT_SPEED, 0.001D)
        							.a(GenericAttributes.ATTACK_DAMAGE, 14.0D)
        							.a(GenericAttributes.ARMOR, 0.0D);
    }
	
	
	public boolean isSpecial() {
		return b;
	}
	
	public void setSpecial(boolean b) {
		if (b) {this.b = b;}
	}
	

}






























































