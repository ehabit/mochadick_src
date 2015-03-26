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



public class ItemRawWhaleMeat extends Item {

	public ItemRawWhaleMeat(String id)
	{
		maxStackSize = 64;
		GameRegistry.registerItem(this, id, RefStrings.MODID);
		setUnlocalizedName(id);
		setTextureName(RefStrings.MODID + ":RawWhaleMeat");
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public static void mainRegistry() {
		MochaDick.rawWhaleMeat = new ItemRawWhaleMeat("RawWhaleMeat");
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}
	
}
