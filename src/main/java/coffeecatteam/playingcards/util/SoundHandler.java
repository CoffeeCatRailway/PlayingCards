package coffeecatteam.playingcards.util;

import coffeecatteam.playingcards.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SoundHandler {

	public static final SoundEvent CARD_THROW = registerSound("card_throw");

	private static SoundEvent registerSound(String soundNameIn) {
		ResourceLocation resource = new ResourceLocation(Reference.MODID, soundNameIn);
		SoundEvent sound = new SoundEvent(resource).setRegistryName(soundNameIn);
		return sound;
	}

	@EventBusSubscriber(modid = Reference.MODID)
	public static class SoundRegistration {

		private static SoundEvent[] sounds = { CARD_THROW };

		@SubscribeEvent
		public static void registerSounds(final RegistryEvent.Register<SoundEvent> event) {
			event.getRegistry().registerAll(sounds);
		}
	}
}
