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

public class ItemBabyWhaleBlubber extends Item {
	
	public ItemBabyWhaleBlubber(String id) {
		maxStackSize = 16;
		GameRegistry.registerItem(this, id, RefStrings.MODID);
		setUnlocalizedName(id);
		setTextureName("mochadick:BabyWhaleBlubber");
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public static void mainRegistry() {
		int randomid = EntityRegistry.findGlobalUniqueEntityId();
		ItemBabyWhaleBlubber babyWhaleBlubber = new ItemBabyWhaleBlubber("BabyWhaleBlubber");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}
}
