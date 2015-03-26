package mochadick.client;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import mochadick.common.CommonProxy;

public class ClientProxy extends CommonProxy {
	
	public static void registerRenderers() {
		
	}
	
	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		// Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
		// your packets will not work as expected because you will be getting a
		// client player even when you are on the server!
		// Sounds absurd, but it's true.

		// Solution is to double-check side before returning the player:
		return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
	}
}
