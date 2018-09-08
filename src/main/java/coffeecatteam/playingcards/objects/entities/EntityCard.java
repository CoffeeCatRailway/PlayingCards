package coffeecatteam.playingcards.objects.entities;

import coffeecatteam.playingcards.objects.items.ItemBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityCard extends EntityThrowable {

	protected Item card;

	public EntityCard(World world) {
		super(world);
	}

	public EntityCard(World world, EntityPlayer playerEntity) {
		super(world, playerEntity);
	}

	public EntityCard(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
	
	public EntityCard setCard(Item card) {
		this.card = card;
		return this;
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		hitBlock(result);
	}
	
	protected void dealDamage(RayTraceResult result, int damage) {
		if (!(result.entityHit instanceof EntityItemFrame))
			result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) damage);
	}
	
	protected void hitBlock(RayTraceResult result) {
		if (!this.world.isRemote) {
			this.world.setEntityState(this, (byte) 3);
			this.setDead();
			
			if (result.entityHit == null || result.entityHit instanceof EntityItemFrame) {
				EntityItem entityItem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(card));
				this.world.spawnEntity(entityItem);
			}
		}
	}
}
