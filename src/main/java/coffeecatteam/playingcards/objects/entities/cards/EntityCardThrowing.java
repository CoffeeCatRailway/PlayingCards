package coffeecatteam.playingcards.objects.entities.cards;

import java.util.Random;

import coffeecatteam.playingcards.objects.entities.EntityCard;
import coffeecatteam.playingcards.objects.items.ItemThrowingCard;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityCardThrowing extends EntityCard {
	
	public EntityCardThrowing(World world) {
		super(world);
	}

	public EntityCardThrowing(World world, EntityPlayer playerEntity) {
		super(world, playerEntity);
	}

	public EntityCardThrowing(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (card instanceof ItemThrowingCard) {
			if (result.entityHit != null) {
				
				int damage = ((ItemThrowingCard) card).getCardDamage() + (new Random().nextInt(3));
				dealDamage(result, damage);
			}
		}
		super.onImpact(result);
	}
}
