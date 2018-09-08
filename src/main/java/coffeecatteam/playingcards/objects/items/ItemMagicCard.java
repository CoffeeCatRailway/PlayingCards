package coffeecatteam.playingcards.objects.items;

import coffeecatteam.playingcards.objects.entities.cards.EntityCardMagic;
import coffeecatteam.playingcards.util.CardProperties;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemMagicCard extends ItemThrowingCard {

    public ItemMagicCard(String name) {
        super(name);
    }

    @Override
    protected EntityThrowable getCard(World world, EntityPlayer entityPlayer) {
        return new EntityCardMagic(world, entityPlayer).setCard(this);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        String effectMsg = "Has the power of ";
        for (CardProperties.Magic magic : CardProperties.Magic.values()) {
            String cardName = this.getUnlocalizedName();
            if (cardName.contains(magic.getName())) {
                String effectName = I18n.format(magic.getEffect().getEffects().get(0).getEffectName());
                effectMsg += effectName + "!";
            }
        }
        tooltip.add(effectMsg);
    }
}
