package mochadick.common.item;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mochadick.common.MochaDick;
import mochadick.common.entity.projectile.EntityStoneHarpoon;
import mochadick.common.lib.RefStrings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;



public class ItemWhaleBone extends Item {

	public ItemWhaleBone(String id)
	{
		maxStackSize = 64;
		GameRegistry.registerItem(this, id, RefStrings.MODID);
		setUnlocalizedName(id);
		setTextureName(RefStrings.MODID + ":WhaleBone");
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public static void mainRegistry() {
		MochaDick.whaleBone = new ItemWhaleBone("WhaleBone");
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}
	
}
