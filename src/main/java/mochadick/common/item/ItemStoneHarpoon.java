package mochadick.common.item;


import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import mochadick.common.MochaDick;
import mochadick.common.entity.projectile.EntityStoneHarpoon;
import mochadick.common.lib.RefStrings;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemStoneHarpoon extends Item
{
	public ItemStoneHarpoon(String id)
	{
		maxStackSize = 16;
		GameRegistry.registerItem(this, id, RefStrings.MODID);
		GameRegistry.addRecipe(new ItemStack(this, 1), new Object[] { "X  ", " X ", "  S", 'X', Blocks.stone, 'S', Items.stick });
		setUnlocalizedName(id);
		setTextureName("mochadick:StoneHarpoon");
		setCreativeTab(CreativeTabs.tabCombat);
	}
	
	public static void mainRegistry() {
		int randomid = EntityRegistry.findGlobalUniqueEntityId();
		ItemStoneHarpoon stoneHarpoon = new ItemStoneHarpoon("StoneHarpoon");
    	EntityRegistry.registerModEntity(EntityStoneHarpoon.class, "StoneHarpoon", randomid, MochaDick.instance, 120, 3, true );
	}
	
	@Override
	public int getItemEnchantability()
	{
		return 0;
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int i) {
		if (!entityplayer.inventory.hasItem(this)) return;
		
		int j = getMaxItemUseDuration(itemstack) - i;
		float f = j / 20F;
		f = (f * f + f * 2.0F) / 3F;
		if (f < 0.1F) return;
		if (f > 1.0F) {
			f = 1.0F;
		}
		
		boolean crit = false;
		if (!entityplayer.onGround && !entityplayer.isInWater()) {
			crit = true;
		}
		
		if (entityplayer.capabilities.isCreativeMode || entityplayer.inventory.consumeInventoryItem(this)) {
			world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote)
			{
				EntityStoneHarpoon entityStoneHarpoon = new EntityStoneHarpoon(world, entityplayer, f * (1.0F + (crit ? 0.5F : 0F)));
				entityStoneHarpoon.setIsCritical(crit);
				world.spawnEntityInWorld(entityStoneHarpoon);
			}
		}
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 0x11940;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.bow;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (entityplayer.inventory.hasItem(this))
		{
			entityplayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
		}
		return itemstack;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}
}