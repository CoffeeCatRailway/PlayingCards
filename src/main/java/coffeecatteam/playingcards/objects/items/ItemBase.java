package coffeecatteam.playingcards.objects.items;

import coffeecatteam.playingcards.PlayingCards;
import net.minecraft.item.Item;

public class ItemBase extends Item {

    public ItemBase(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(PlayingCards.PLAYINGCARDSTAB);
        setMaxStackSize(54);
    }
}
