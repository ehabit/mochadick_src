package mochadick.common;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import mochadick.common.block.BlockWhaleOil;
import mochadick.common.fluid.FluidWhaleOil;
import mochadick.common.handler.FogHandler;
import mochadick.common.handler.WhaleOilBucketHandler;
import mochadick.common.item.ItemRawWhaleMeat;
import mochadick.common.item.ItemWhaleBlubber;
import mochadick.common.item.ItemStoneHarpoon;
import mochadick.common.item.ItemWhaleOilBucket;
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
	public static ItemWhaleBlubber whaleBlubber;
	public static ItemRawWhaleMeat rawWhaleMeat;
	
	public static FluidWhaleOil whaleOil;
	public static BlockWhaleOil blockWhaleOil;
	public static ItemWhaleOilBucket whaleOilBucket;
	
    @EventHandler
    public void PreLoad(FMLPreInitializationEvent PreEvent) {
    	
    	// mainRegistry
    	log.info("Gathering the goods and supplies...");
    	ItemStoneHarpoon.mainRegistry();
    	ItemWhaleBlubber.mainRegistry();
    	ItemRawWhaleMeat.mainRegistry();
    	
    	whaleOil = new FluidWhaleOil("FluidWhaleOil");
    	FluidRegistry.registerFluid(whaleOil);
    	blockWhaleOil = (BlockWhaleOil) new BlockWhaleOil(whaleOil, Material.water).setBlockName("BlockWhaleOil");
    	GameRegistry.registerBlock(blockWhaleOil, blockWhaleOil.getUnlocalizedName());
    	whaleOil.setUnlocalizedName(blockWhaleOil.getUnlocalizedName());

    	whaleOilBucket = new ItemWhaleOilBucket(blockWhaleOil);
    	whaleOilBucket.setUnlocalizedName("WhaleOilBucket").setContainerItem(Items.bucket);
    	GameRegistry.registerItem(whaleOilBucket, "WhaleOilBucket");
    	FluidContainerRegistry.registerFluidContainer(whaleOil, new ItemStack(whaleOilBucket), new ItemStack(Items.bucket));
    	
    
    	// Event Buses
    	log.info("Manipulating the great beyond...");
    	FMLCommonHandler.instance().bus().register(instance);
    	
    	MochaDick.log.info("Blow the fog horns, she be rollin' in nice and thick tonight!");
    	fogHandler = new FogHandler();
    	MinecraftForge.EVENT_BUS.register(fogHandler);
        
    	WhaleOilBucketHandler.INSTANCE.buckets.put(blockWhaleOil, whaleOilBucket);
    	MinecraftForge.EVENT_BUS.register(WhaleOilBucketHandler.INSTANCE);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
       log.info("Raisin' the riggin'! Mocha Dick is initializing...");
    }
}
