package mochadick.common.block;

import java.util.Random;

import mochadick.common.MochaDick;
import mochadick.common.lib.RefStrings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneLight;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockWhaleOilLight extends Block {

	private final boolean isOn;
	
	public BlockWhaleOilLight(boolean signal) {
		super(Material.redstoneLight);
	 	this.isOn = signal;
		
		if (isOn) {
			setBlockName("BlockWhaleOilLightOn");
			this.setLightLevel(4.0F);
		} else {
			setBlockName("BlockWhaleOilLightOff");
			setCreativeTab(CreativeTabs.tabMisc);
		}
		
	}
	
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(RefStrings.MODID + ":" + (this.isOn ? "WhaleOilLightOn" : "WhaleOilLightOff"));
	}
	
	public void onBlockAdded(World world, int x, int y, int z) {
		if (!world.isRemote) {
			if (this.isOn) {
				world.scheduleBlockUpdate(x, y, z, this, 4);
			} else if (!this.isOn && world.isBlockIndirectlyGettingPowered(x, y, z)) {
				world.setBlock(x, y, z, MochaDick.whaleOilLightOn, 0, 2);
			} else if (!this.isOn && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
				world.setBlock(x, y, z, MochaDick.whaleOilLightOff, 0, 2);
			}
		}
	}
	
	
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (!world.isRemote) {
			if (this.isOn && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
				world.scheduleBlockUpdate(x, y, z, this, 4);
			} else if (!this.isOn && world.isBlockIndirectlyGettingPowered(x, y, z)) {
				world.setBlock(x, y, z, MochaDick.whaleOilLightOn, 0, 2);
			}
		}
	}
	
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (!world.isRemote && this.isOn && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
			world.setBlock(x, y, z, MochaDick.whaleOilLightOff, 0, 2);
		}
	}
	
	public Item getItemDropped(int i, Random random, int j) {
		return Item.getItemFromBlock(MochaDick.whaleOilLightOff);
	}
	
	protected ItemStack createStackedBlock(int i) {
		return new ItemStack(MochaDick.whaleOilLightOff);
	}
	
	
}
