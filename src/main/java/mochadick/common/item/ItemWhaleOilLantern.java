package mochadick.common.item;

import mochadick.common.MochaDick;
import mochadick.common.entity.ExtendedPlayer;
import mochadick.common.lib.RefStrings;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWhaleOilLantern extends Item {
	
//	private boolean isOn;
	private int lightLevel;
	
	public ItemWhaleOilLantern(String id) {
		maxStackSize = 1;
		GameRegistry.registerItem(this, id, RefStrings.MODID);
		setUnlocalizedName(id);
		setTextureName(RefStrings.MODID + ":WhaleOilLantern");
		setCreativeTab(CreativeTabs.tabMisc);
		GameRegistry.addRecipe(new ItemStack(this, 1), new Object[] { "XXX", "XOX", "XXX", 'X', Blocks.glass, 'O', MochaDick.whaleOilBucket });
		
//		this.isOn = false;
		this.lightLevel = 8;
	}
	
	public static void mainRegistry() {
		MochaDick.whaleOilLantern = new ItemWhaleOilLantern("WhaleOilLantern");
    }
	
	public float getLightLevel() {
		return lightLevel;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
//		if (!world.isRemote) {
//			if (isOn) {
//				isOn = true;
//			} else {
//				isOn = false;
//			}
//		}
		return itemstack;
	}
	
	public Block findBlockUnderEntity(Entity parEntity) {
	    int blockX = MathHelper.floor_double(parEntity.posX);
	    int blockY = MathHelper.floor_double(parEntity.boundingBox.minY)-1;
	    int blockZ = MathHelper.floor_double(parEntity.posZ);
	    return parEntity.worldObj.getBlock(blockX, blockY, blockZ);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isHeld) {
		if (isHeld) {
			if (entity instanceof EntityPlayer) {
				ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) entity);
				props.setWhaleLight(lightLevel);
				//MochaDick.log.info("Set whale light level to " + 8 + "\nCurrent whale light level " + props.getCurrentWhaleLight());
			}
			// set light level of block below entity holding lantern
			findBlockUnderEntity(entity).setLightLevel((float) lightLevel / 10);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}
}
