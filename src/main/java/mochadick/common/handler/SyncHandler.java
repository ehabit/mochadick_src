package mochadick.common.handler;

import mochadick.common.MochaDick;
import mochadick.common.entity.ExtendedPlayer;
import mochadick.common.network.PacketDispatcher;
import mochadick.common.network.client.SyncPlayerPropsMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;


public class SyncHandler {
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null) {
			ExtendedPlayer.register((EntityPlayer) event.entity);
		}
		
		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(ExtendedPlayer.EXT_PROP_NAME) == null) {
			event.entity.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer((EntityPlayer) event.entity));
		}
	}
	
	@SubscribeEvent
	public void onJoinWorld(EntityJoinWorldEvent event) {
		// If you have any non-DataWatcher fields in your extended properties that
		// need to be synced to the client, you must send a packet each time the
		// player joins the world; this takes care of dying, changing dimensions, etc.
		if (event.entity instanceof EntityPlayerMP) {
			MochaDick.log.info("Player joined world, sending extended properties to client");
			PacketDispatcher.sendTo(new SyncPlayerPropsMessage((EntityPlayer) event.entity), (EntityPlayerMP) event.entity);
		}
	}

	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone event) {
		MochaDick.log.info("Cloning player extended properties");
		ExtendedPlayer.get(event.entityPlayer).copy(ExtendedPlayer.get(event.original));
	}

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			ExtendedPlayer.get(player).onUpdate();
		}
	}
}
