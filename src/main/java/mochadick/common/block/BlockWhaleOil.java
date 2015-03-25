package mochadick.common.block;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mochadick.common.MochaDick;
import mochadick.common.lib.RefStrings;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraft.block.material.Material;
import mochadick.common.fluid.FluidWhaleOil;

public class BlockWhaleOil extends BlockFluidClassic {
  @SideOnly(Side.CLIENT)
  protected IIcon stillIcon;
  @SideOnly(Side.CLIENT)
  protected IIcon flowingIcon;
  
  public BlockWhaleOil(Fluid fluid, Material material) {
    super(fluid, material);
    setBlockName("BlockWhaleOil");
    setCreativeTab(CreativeTabs.tabMisc);
  }
  
  public static void mainRegistry() {
	  FluidWhaleOil whaleOil = new FluidWhaleOil("FluidWhaleOil");
	  BlockWhaleOil blockWhaleOil = new BlockWhaleOil(whaleOil, Material.water);
	  GameRegistry.registerBlock(blockWhaleOil, "BlockWhaleOil").getUnlocalizedName();
  }
  
  @Override
  public IIcon getIcon(int side, int meta) {
    return (side == 0) || (side == 1) ? this.stillIcon : this.flowingIcon;
  }
  
  @SideOnly(Side.CLIENT)
  @Override
  public void registerBlockIcons(IIconRegister iconregister)
  {
    this.stillIcon = iconregister.registerIcon(RefStrings.MODID +":BlockWhaleOilStill");
    this.flowingIcon = iconregister.registerIcon(RefStrings.MODID +":BlockWhaleOilFlowing");
  }
  
  @Override
  public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
          if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
          return super.canDisplace(world, x, y, z);
  }
  
  @Override
  public boolean displaceIfPossible(World world, int x, int y, int z) {
          if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
          return super.displaceIfPossible(world, x, y, z);
  }
  
}
