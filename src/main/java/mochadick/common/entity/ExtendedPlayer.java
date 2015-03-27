package mochadick.common.entity;

import mochadick.common.MochaDick;
import mochadick.common.network.PacketDispatcher;
import mochadick.common.network.client.SyncPlayerPropsMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties {

	public final static String EXT_PROP_NAME = "ExtendedPlayer";
	
	private final EntityPlayer player;
	
	private int maxWhaleLight, whaleLightDegenTimer;
	
	private int WHALE_LIGHT_WATCHER = 30;
	
	public ExtendedPlayer(EntityPlayer player) {
		this.player = player;
		this.maxWhaleLight = 10;
		this.whaleLightDegenTimer = 0;
		
		// currentWhaleLight set to 0
		this.player.getDataWatcher().addObject(WHALE_LIGHT_WATCHER, 0);
	}

	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));	
	}
	
	public static final ExtendedPlayer get(EntityPlayer player) {
		return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	/**
	 * Copies additional player data from the given ExtendedPlayer instance
	 * Avoids NBT disk I/O overhead when cloning a player after respawn
	 */
	public void copy(ExtendedPlayer props) {
		player.getDataWatcher().updateObject(WHALE_LIGHT_WATCHER, props.getCurrentWhaleLight());
		maxWhaleLight = props.maxWhaleLight;
		whaleLightDegenTimer = props.whaleLightDegenTimer;
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		
		properties.setInteger("CurrentWhaleLight", player.getDataWatcher().getWatchableObjectInt(WHALE_LIGHT_WATCHER));
		properties.setInteger("MaxWhaleLight", maxWhaleLight);
		properties.setInteger("WhaleLightDegenTimer", whaleLightDegenTimer);
		
		compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		
		player.getDataWatcher().updateObject(WHALE_LIGHT_WATCHER, properties.getInteger("CurrentWhaleLight"));
		
		maxWhaleLight = properties.getInteger("MaxWhaleLight");
		whaleLightDegenTimer = properties.getInteger("WhaleLightDegenTimer");
		
		MochaDick.log.info("Conjuring Whale Oil Lighting Dynamics... Extended Player Properties.");
	}

	@Override
	public void init(Entity entity, World world) { }
	
	/**
	 * Updates anything that needs to be updated each tick
	 * NOT called automatically, so you must call it yourself from LivingUpdateEvent or a TickHandler
	 */
	public void onUpdate() {
		// only want to update the timer and degen whale light on the server:
		if (!player.worldObj.isRemote) {
			if (updateWhaleLightTimer()) {
				if (getCurrentWhaleLight() > 0) {
					degenWhaleLight(1);
				}
			}
		}
	}
	
	private boolean updateWhaleLightTimer() {
		if (whaleLightDegenTimer > 0) {
			--whaleLightDegenTimer;
		}
		if (whaleLightDegenTimer == 0) {
			whaleLightDegenTimer = getCurrentWhaleLight() < 0 ? 100 : 0;
			return true;
		}
		return false;
	}
	
	/**
	 * Returns max whale light variable
	 */
	public final int getMaxWhaleLight() {
		return maxWhaleLight;
	}
	
	/**
	 * Returns current whale light variable
	 */
	public final int getCurrentWhaleLight() {
		return player.getDataWatcher().getWatchableObjectInt(WHALE_LIGHT_WATCHER);
	}
	
	
	public final void degenWhaleLight(int amount) {
		//MochaDick.log.info("Reducing player whale light level " + (getCurrentWhaleLight() - amount) );
		setWhaleLight(getCurrentWhaleLight() - amount);
	}
	
	public void setWhaleLight(int level) {
		player.getDataWatcher().updateObject(WHALE_LIGHT_WATCHER, level);
	}

}
