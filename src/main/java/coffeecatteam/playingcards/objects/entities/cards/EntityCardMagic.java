package coffeecatteam.playingcards.objects.entities.cards;

import java.util.Random;

import coffeecatteam.playingcards.objects.entities.EntityCard;
import coffeecatteam.playingcards.objects.items.ItemMagicCard;
import coffeecatteam.playingcards.util.CardProperties;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityCardMagic extends EntityCard {
	
	public EntityCardMagic(World world) {
		super(world);
	}

	public EntityCardMagic(World world, EntityPlayer playerEntity) {
		super(world, playerEntity);
	}

	public EntityCardMagic(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (card instanceof ItemMagicCard) {
			if (result.entityHit != null) {
				
				int damage = 1;
				dealDamage(result, damage + (new Random().nextInt(2)));
				
				PotionEffect cardEffect = null;
				for (CardProperties.Magic magic : CardProperties.Magic.values()) {
					String cardName = card.getUnlocalizedName();
					if (cardName.contains(magic.getName())) {
						cardEffect = magic.getEffect().getEffects().get(0);
					}
				}
				
				if (cardEffect != null) {
					int amplifier = new Random().nextInt(4)+1;
					if (result.entityHit instanceof EntityLivingBase) {
						PotionEffect effect = new PotionEffect(cardEffect.getPotion(), cardEffect.getDuration(), amplifier);
						boolean applyEffect = !((EntityLivingBase) result.entityHit).getActivePotionEffects().contains(effect);
						if (applyEffect)
							((EntityLivingBase) result.entityHit).addPotionEffect(effect);
					}
				}
			}
		}
		super.onImpact(result);
	}
}
