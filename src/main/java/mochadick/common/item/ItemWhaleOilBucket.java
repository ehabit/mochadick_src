package mochadick.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import mochadick.common.MochaDick;
import mochadick.common.block.BlockWhaleOil;
import mochadick.common.fluid.FluidWhaleOil;
import mochadick.common.lib.RefStrings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class ItemWhaleOilBucket extends ItemBucket {
	
	public ItemWhaleOilBucket(Block block) {
		super(block);
		setCreativeTab(CreativeTabs.tabMisc);
		setContainerItem(Items.bucket);
		setUnlocalizedName("WhaleOilBucket");
		setTextureName(RefStrings.MODID + ":WhaleOilBucket");
	}
  
  public void registerIcons(IIconRegister par1IconRegister)
  {
    this.itemIcon = par1IconRegister.registerIcon(RefStrings.MODID + ":ItemWhaleOilBucket");
  }
  
  public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
  {
    par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle, 3));
  }

}
