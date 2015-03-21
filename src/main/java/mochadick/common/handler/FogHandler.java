package mochadick.common.handler;
/*
 * Mocha Dick Adaptive FogHandler by Glen Baker <glen@ehab.it>
 * 
 * Fog Handler creates fog around players.  The fog occurs
 * if you are in or near an ocean or beach.  The density
 * increases upon oceans and decreases the further away
 * the player is from deep waters.
 * 
 */

import java.util.Arrays;
import java.util.List;

import org.lwjgl.opengl.GL11;

import mochadick.common.MochaDick;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class FogHandler {
	
	private float daylightFactor = 0.10F;
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void fog(EntityViewRenderEvent.FogDensity event) {
		 Boolean isDaytime = MinecraftServer.getServer().worldServers[0].isDaytime();
		
		if (event.entity instanceof EntityPlayer) {
			BiomeGenBase biome = event.entity.worldObj.getBiomeGenForCoords((int)event.entity.posX, (int)event.entity.posZ);
			// MochaDick.log.info(biome);
			if (isOcean(biome)) {
				// Dense fog over oceans
				if (isDaytime) {
					event.density = 0.40F - daylightFactor;
				} else {
					event.density = 0.40F;
				}
				GL11.glFogi(2917, 2048);
				event.setCanceled(true);
				
			} else if (isBeach(biome)) {
				// Mild fog along beaches
				// Also fog in swamps because they should be spooky
				if (isDaytime) {
					event.density = 0.14F - daylightFactor;
				} else {
					event.density = 0.14F;
				}
				GL11.glFogi(2917, 2048);
				event.setCanceled(true);
				
			} else if (beachOrOceanInArea(event.entity.worldObj, (int)event.entity.posX, (int)event.entity.posZ, 5)) {
				// Mild fog if a beach of ocean is within 5 blocks
				if (isDaytime) {
					event.density = 0.14F - daylightFactor;
				} else {
					event.density = 0.14F;
				}
				GL11.glFogi(2917, 2048);
				event.setCanceled(true);
				
			} else if (beachOrOceanInArea(event.entity.worldObj, (int)event.entity.posX, (int)event.entity.posZ, 30)) {
				// Slight fog if a beach of ocean is within 30 blocks
				if (isDaytime) {
					event.density = 0.04F - daylightFactor;
				} else {
					event.density = 0.04F;
				}
				GL11.glFogi(2917, 2048);
				event.setCanceled(true);
			}
		}
	}
	
	public Boolean isOcean(BiomeGenBase biome) {
		if (biome == BiomeGenBase.deepOcean || 
			biome == BiomeGenBase.frozenOcean || 
			biome == BiomeGenBase.ocean) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean isBeach(BiomeGenBase biome) {
		if (biome == BiomeGenBase.beach || 
			biome == BiomeGenBase.stoneBeach ||
			biome == BiomeGenBase.mushroomIslandShore ||
			biome == BiomeGenBase.swampland ||
			biome == BiomeGenBase.coldBeach || 
			biome == BiomeGenBase.river) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean beachOrOceanInArea(World world, int posX, int posZ, int radius) {
		BiomeGenBase biome = world.getBiomeGenForCoords(posX + radius, posZ + radius);
		if (isOcean(biome) || isBeach(biome)) {
			return true;
		}
		biome = world.getBiomeGenForCoords(posX + radius, posZ - radius);
		if (isOcean(biome) || isBeach(biome)) {
			return true;
		}
		biome = world.getBiomeGenForCoords(posX - radius, posZ + radius);
		if (isOcean(biome) || isBeach(biome)) {
			return true;
		}
		biome = world.getBiomeGenForCoords(posX - radius, posZ - radius);
		if (isOcean(biome) || isBeach(biome)) {
			return true;
		}
		return false;
	}
		
}
