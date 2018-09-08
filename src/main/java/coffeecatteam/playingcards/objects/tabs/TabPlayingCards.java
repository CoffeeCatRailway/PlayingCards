package coffeecatteam.playingcards.objects.tabs;

import coffeecatteam.playingcards.init.InitItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabPlayingCards extends CreativeTabs {

    public TabPlayingCards(String label) {
        super(label);
    }

    public ItemStack getTabIconItem() {
        return new ItemStack(InitItem.PLAYING_CARD);
    }
}
