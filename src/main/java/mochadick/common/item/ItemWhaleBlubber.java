package mochadick.common.item;

import mochadick.common.MochaDick;
import mochadick.common.entity.projectile.EntityStoneHarpoon;
import mochadick.common.lib.RefStrings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWhaleBlubber extends Item {
	
	public ItemWhaleBlubber(String id) {
		maxStackSize = 64;
		GameRegistry.registerItem(this, id, RefStrings.MODID);
		setUnlocalizedName(id);
		setTextureName(RefStrings.MODID + ":WhaleBlubber");
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public static void mainRegistry() {
		ItemWhaleBlubber babyWhaleBlubber = new ItemWhaleBlubber("WhaleBlubber");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}
}
