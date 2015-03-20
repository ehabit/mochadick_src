package mochadick.common.entity.projectile;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import mochadick.common.lib.WeaponDamageSource;
import mochadick.common.MochaDick;


public class EntityStoneHarpoon extends EntityProjectile
{
	public EntityStoneHarpoon(World world) {
		super(world);
	}
	
	
	public EntityStoneHarpoon(World world, double x, double y, double z) {
		this(world);
		setPickupMode(PICKUP_ALL);
		setPosition(x, y, z);
	}
	
	public EntityStoneHarpoon(World world, EntityLivingBase entityliving, float f) {
		this(world);
		shootingEntity = entityliving;
		setPickupModeFromEntity(entityliving);
		setLocationAndAngles(entityliving.posX, entityliving.posY + entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
		posX -= MathHelper.cos((rotationYaw / 180F) * 3.141593F) * 0.16F;
		posY -= 0.1D;
		posZ -= MathHelper.sin((rotationYaw / 180F) * 3.141593F) * 0.16F;
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		motionX = -MathHelper.sin((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F);
		motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F);
		motionZ = MathHelper.cos((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F);
		setThrowableHeading(motionX, motionY, motionZ, f * 1.1F, 3.0F);
	}
	
	@Override
	public void onEntityHit(Entity entity) {
		
		if (entity instanceof EntityBoat) {
			// if the entity is a boat just pass through it to prevent
			// players breaking their boats with their harpoons
		} else {
			double vel = getTotalVelocity();
			int damage = MathHelper.ceiling_double_int(vel * (4D + extraDamage));
			if (getIsCritical()) {
				damage += rand.nextInt(damage / 2 + 2);
			}
			DamageSource damagesource = null;
			if (shootingEntity == null) {
				damagesource = WeaponDamageSource.causeProjectileWeaponDamage(this, this);
			} else {
				damagesource = WeaponDamageSource.causeProjectileWeaponDamage(this, shootingEntity);
			}
			if (entity.attackEntityFrom(damagesource, damage)) {
				applyEntityHitEffects(entity);
				playHitSound();
				setDead();
			} else {
				bounceBack();
			} 
		}
	}
	
	@Override
	public void playHitSound() {
		worldObj.playSoundAtEntity(this, "random.bowhit", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.9F));
	}
	
	@Override
	public boolean canBeCritical() {
		return true;
	}
	
	@Override
	public int getMaxArrowShake() {
		return 10;
	}
	
	@Override
	public float getGravity() {
		return 0.03F;
	}
	
	@Override
	public ItemStack getPickupItem() {
		return new ItemStack(MochaDick.stoneHarpoon, 1);
	}
}