package coffeecatteam.playingcards.objects.items;

import java.util.List;
import java.util.Random;

import coffeecatteam.playingcards.objects.entities.cards.EntityCardThrowing;
import coffeecatteam.playingcards.util.CardProperties;
import coffeecatteam.playingcards.util.SoundHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemThrowingCard extends ItemBase {

	public ItemThrowingCard(String name) {
		super(name);
	}
	
	protected EntityThrowable getCard(World world, EntityPlayer entityPlayer) {
		return new EntityCardThrowing(world, entityPlayer).setCard(this);
	}
	
	public int getCardDamage() {
		int damage = 2;
		for (CardProperties.Number number : CardProperties.Number.values()) {
			String cardName = this.getUnlocalizedName();
			if (cardName.contains(number.getName())) {
				damage = number.getDamage();
			}
			if (damage >= 8) damage /= 2;
		}
		return damage;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entityPlayer, EnumHand hand) {
    	ItemStack parItemStack = entityPlayer.getHeldItem(hand);

        if (!entityPlayer.capabilities.isCreativeMode) {
            parItemStack.shrink(1);
        }

        if (!world.isRemote)  {
        	EntityThrowable entityCard = getCard(world, entityPlayer);
    		entityCard.shoot(entityPlayer, entityPlayer.rotationPitch, entityPlayer.rotationYaw, 0.0F, 1.5F, 1.0F);
    		world.spawnEntity(entityCard);
			
			EntityPlayerSP player = Minecraft.getMinecraft().player;
			player.playSound(SoundHandler.CARD_THROW, 0.1f, 0.9f);
        }

        return super.onItemRightClick(world, entityPlayer, hand);
    }
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Does " + getCardDamage() + " damage!");
		tooltip.add("Plus random 0-3");
	}
}
