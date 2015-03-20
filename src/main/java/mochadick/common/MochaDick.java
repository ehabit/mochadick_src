package mochadick.common;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mochadick.common.handler.FogHandler;
import mochadick.common.item.ItemBabyWhaleBlubber;
import mochadick.common.item.ItemStoneHarpoon;
import mochadick.common.lib.RefStrings;

@Mod(modid = RefStrings.MODID, name = RefStrings.NAME, version = RefStrings.VERSION)
public class MochaDick {
	public static final Logger log = LogManager.getLogger("MOCHADICK");
	
	@SidedProxy(clientSide=RefStrings.CLIENTSIDE, serverSide=RefStrings.SERVERSIDE)
	public static CommonProxy proxy;
	
	@Instance(RefStrings.MODID)
	public static MochaDick instance;

	public FogHandler fogHandler; 
	
	public static Minecraft mc = Minecraft.getMinecraft();
	
	
	// ItemStack
	public static ItemStoneHarpoon stoneHarpoon;
	public static ItemBabyWhaleBlubber babyWhaleBlubber;
	
    @EventHandler
    public void PreLoad(FMLPreInitializationEvent PreEvent) {
    	log.info("Gathering the goods and supplies...");
    	ItemStoneHarpoon.mainRegistry();
    	ItemBabyWhaleBlubber.mainRegistry();
    	
    	MochaDick.log.info("Blow the fog horns, she be rollin' in nice and thick tonight!");
    	fogHandler = new FogHandler();
    	MinecraftForge.EVENT_BUS.register(fogHandler);
		FMLCommonHandler.instance().bus().register(instance);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
       log.info("Raisin' the riggin'! Mocha Dick is initializing...");
    }
}
