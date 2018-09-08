package coffeecatteam.playingcards.proxy;

import coffeecatteam.playingcards.objects.entities.EntityRidableCard;
import coffeecatteam.playingcards.objects.entities.cards.EntityCardMagic;
import coffeecatteam.playingcards.objects.entities.cards.EntityCardThrowing;
import coffeecatteam.playingcards.objects.entities.render.RenderCard;
import coffeecatteam.playingcards.objects.entities.render.RenderRidableCard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ProxyClient extends ProxyCommon {

	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
        RenderingRegistry.registerEntityRenderingHandler(EntityRidableCard.class, RenderRidableCard::new);
	}
	
	public void init(FMLInitializationEvent event) {
		super.init(event);
        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        RenderingRegistry.registerEntityRenderingHandler(EntityCardThrowing.class, new RenderCard(renderManager, Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(EntityCardMagic.class, new RenderCard(renderManager, Minecraft.getMinecraft().getRenderItem()));
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}
