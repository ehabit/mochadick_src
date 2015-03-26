package mochadick.common.item;

import mochadick.common.MochaDick;
import mochadick.common.entity.ExtendedPlayer;
import mochadick.common.lib.RefStrings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWhaleOilLantern extends Item {
	
	private boolean isOn;
	private float lightLevel;
	
	public ItemWhaleOilLantern(String id) {
		maxStackSize = 1;
		GameRegistry.registerItem(this, id, RefStrings.MODID);
		setUnlocalizedName(id);
		setTextureName(RefStrings.MODID + ":WhaleOilLantern");
		setCreativeTab(CreativeTabs.tabMisc);
		this.isOn = false;
		this.lightLevel = 0.5F;
	}
	
	public static void mainRegistry() {
		MochaDick.whaleOilLantern = new ItemWhaleOilLantern("WhaleOilLantern");
    }
	
	public float getLightLevel() {
		return lightLevel;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			if (isOn) {
				isOn = true;
			} else {
				isOn = false;
			}
		}
		
		return itemstack;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isHeld) {
		if (isHeld) {
			if (entity instanceof EntityPlayer) {
				ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) entity);
				props.setWhaleLight(10);
				//MochaDick.log.info("Set whale light level to " + 8 + "\nCurrent whale light level " + props.getCurrentWhaleLight());
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}
}
