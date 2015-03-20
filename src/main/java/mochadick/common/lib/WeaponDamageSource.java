package mochadick.common.lib;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import mochadick.common.entity.projectile.EntityProjectile;

public class WeaponDamageSource extends EntityDamageSourceIndirect {
	private EntityProjectile	projectileEntity;
	private Entity				shooterEntity;
	
	public WeaponDamageSource(String s, EntityProjectile projectile, Entity entity) {
		super(s, projectile, entity);
		projectileEntity = projectile;
		shooterEntity = entity;
	}
	
	public Entity getProjectile() {
		return projectileEntity;
	}
	
	@Override
	public Entity getEntity() {
		return shooterEntity;
	}
	
	public static DamageSource causeProjectileWeaponDamage(EntityProjectile projectile, Entity entity) {
		return (new WeaponDamageSource("weapon", projectile, entity)).setProjectile();
	}
}