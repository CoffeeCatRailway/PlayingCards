package coffeecatteam.playingcards.init;

import coffeecatteam.playingcards.PlayingCards;
import coffeecatteam.playingcards.Reference;
import coffeecatteam.playingcards.objects.items.ItemBase;
import coffeecatteam.playingcards.objects.items.ItemMagicCard;
import coffeecatteam.playingcards.objects.items.ItemThrowingCard;
import coffeecatteam.playingcards.util.CardProperties;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InitItem {

    public static final Item PLAYING_CARD = new ItemBase("playing_card");
    public static final Item JOCKER_BLACK = new ItemThrowingCard("jocker_black");
    public static final Item JOCKER_RED = new ItemThrowingCard("jocker_red");

    public static final List<Item> CARDS = new ArrayList<Item>();

    public static void init() {
        for (CardProperties.Suit suit : CardProperties.Suit.values()) {
            for (CardProperties.Number number : CardProperties.Number.values()) {
                Item card = new ItemThrowingCard("card_" + suit.getName() + "_" + number.getName());
                CARDS.add(card);
            }
        }

        for (CardProperties.Magic magic : CardProperties.Magic.values()) {
            Item card = new ItemMagicCard("card_magic_" + magic.getName());
            CARDS.add(card);
        }
    }

    @EventBusSubscriber(modid = Reference.MODID)
    public static class ItemRegistrationHandler {
        public static final Set<Item> ITEM_LIST = new HashSet<Item>();

        private static final Set<Item> registeredItemList = new HashSet<>();
        private static final Item[] items = {
                PLAYING_CARD,
                JOCKER_BLACK,
                JOCKER_RED
        };

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            IForgeRegistry<Item> reg = event.getRegistry();

            for (Item item : items) {
                reg.register(item);
                ITEM_LIST.add(item);
            }

            for (Item item : CARDS) {
                reg.register(item);
                ITEM_LIST.add(item);
                PlayingCards.logger.info("Registered Playing Card [" + item.getUnlocalizedName() + "]!");
            }
        }

        @SubscribeEvent
        public static void registerModels(final ModelRegistryEvent event) {
            for (Item item : ITEM_LIST)
                if (!item.getHasSubtypes() || item instanceof ItemSlab)
                    if (!registeredItemList.contains(item))
                        registerItemModel(item);
        }

        private static void registerItemModel(final Item item) {
            final String registryName = item.getRegistryName().toString();
            final ModelResourceLocation location = new ModelResourceLocation(registryName, "inventory");
            registerItemModel(item, location);
        }

        private static void registerItemModel(final Item item, final ModelResourceLocation modelResourceLocation) {
            ModelLoader.setCustomModelResourceLocation(item, 0, modelResourceLocation);
            registeredItemList.add(item);
        }
    }
}
