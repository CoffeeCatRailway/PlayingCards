package coffeecatteam.playingcards;

import org.apache.logging.log4j.Logger;

import coffeecatteam.playingcards.objects.tabs.TabPlayingCards;
import coffeecatteam.playingcards.proxy.ProxyCommon;
import coffeecatteam.playingcards.util.Utils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, dependencies = Reference.DEPENDCIES)
public class PlayingCards {
	
	public static final CreativeTabs PLAYINGCARDSTAB = new TabPlayingCards("playingcardstab");

    public static Logger logger = Utils.getLogger();

	@Mod.Instance
	public static PlayingCards instance;
	
	@SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.COMMONPROXY)
	private static ProxyCommon proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	proxy.preInit(event);
		logger.info("Pre Initialize");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
		proxy.init(event);
		logger.info("Initialize");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
		logger.info("Post Initialize");
    }
}
