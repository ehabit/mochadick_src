package mochadick.common.item;

import mochadick.common.lib.RefStrings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWhaleOilLantern extends Item {
	public ItemWhaleOilLantern(String id)
	{
		maxStackSize = 16;
		GameRegistry.registerItem(this, id, RefStrings.MODID);
		setUnlocalizedName(id);
		setTextureName(RefStrings.MODID + ":RedstoneLampActive");
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public int lightLevel() {
		return 5;
	}
	
	public static void mainRegistry() {
		int randomid = EntityRegistry.findGlobalUniqueEntityId();
		ItemWhaleOilLantern whaleOilLight = new ItemWhaleOilLantern("WhaleOilLantern");
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}
}
