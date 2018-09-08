package coffeecatteam.playingcards.init;

import coffeecatteam.playingcards.PlayingCards;
import coffeecatteam.playingcards.Reference;
import coffeecatteam.playingcards.objects.entities.EntityRidableCard;
import coffeecatteam.playingcards.objects.entities.cards.EntityCardMagic;
import coffeecatteam.playingcards.objects.entities.cards.EntityCardThrowing;
import com.mrcrayfish.vehicle.VehicleMod;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.ModListHelper;

public class InitEntity {

	public static void init() {
		registerEntity("throwing_card", EntityCardThrowing.class, 0, 80);
		registerEntity("magic_card", EntityCardMagic.class, 1, 80);

		if (Loader.isModLoaded("vehicle")) {
		    registerVehicle("ridable_card", 2, EntityRidableCard.class);
		}
	}

	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int trackingRange) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, PlayingCards.instance, trackingRange, 1, true);
	}

    private static void registerVehicle(String name, int  id, Class<? extends Entity> clazz) {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), clazz, name, id, PlayingCards.instance, 64, 1, true);
    }
}
