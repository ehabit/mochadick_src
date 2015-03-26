package mochadick.common.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mochadick.common.MochaDick;
import mochadick.common.entity.ExtendedPlayer;
import mochadick.common.item.ItemWhaleOilLantern;
import mochadick.common.network.PacketDispatcher;
import mochadick.common.network.client.SyncPlayerPropsMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class WhaleLightHandler {
	
//	@SubscribeEvent
//	public void onLivingUpdateWhaleLight(LivingUpdateEvent event) {
//		
//		if (event.entity instanceof EntityPlayer) {
//			EntityPlayer player = (EntityPlayer) event.entity;
//			ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) event.entity);
//			MochaDick.log.info("Whale Light Level: " + props.getCurrentWhaleLight());
//		}
//	}
	
}
