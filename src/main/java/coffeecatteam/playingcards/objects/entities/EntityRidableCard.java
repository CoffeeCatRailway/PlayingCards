package coffeecatteam.playingcards.objects.entities;

import coffeecatteam.playingcards.Reference;
import coffeecatteam.playingcards.init.InitItem;
import com.mrcrayfish.vehicle.entity.EntityLandVehicle;
import com.mrcrayfish.vehicle.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.Random;

public class EntityRidableCard extends EntityLandVehicle {

    private static final DataParameter<ItemStack> CARD = EntityDataManager.createKey(EntityRidableCard.class, DataSerializers.ITEM_STACK);

    public float spinAngle;

    public EntityRidableCard(World world) {
        super(world);
        this.setSpeed(20.0F);
        this.setTurnSensitivity(15);
        this.setSize(2.0F, 1.0F);
        this.setFuelCapacity(20.0F);
        this.setFuelConsumption(0.01f);
    }

    @Override
    public void entityInit() {
        super.entityInit();
        this.dataManager.register(CARD, new ItemStack(InitItem.CARDS.get(new Random().nextInt(InitItem.CARDS.size() - 1))));

        this.spinAngle = this.turnAngle;
        if (world.isRemote) {
            body = this.getCard();
            wheel = new ItemStack(ModItems.WHEEL);
        }
    }

    @Override
    public SoundEvent getMovingSound() {
        return null;
    }

    @Override
    public SoundEvent getRidingSound() {
        return null;
    }

    @Override
    public boolean shouldRenderEngine() {
        return false;
    }

    @Override
    public double getMountedYOffset() {
        return 0.1D;
    }

    @Override
    public boolean canBeColored() {
        return false;
    }

    @Override
    public boolean canTowTrailer() {
        return false;
    }

    @Override
    public boolean shouldRenderFuelPort() {
        return false;
    }

    protected void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("card")) {
            this.setCard(new ItemStack(compound.getCompoundTag("card")));
        }
    }

    protected void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);

        NBTTagCompound cardCompund = new NBTTagCompound();
        this.getCard().writeToNBT(cardCompund);
        compound.setTag("card", cardCompund);
    }

    public void setCard(ItemStack card) {
        this.dataManager.set(CARD, card);
    }

    public ItemStack getCard() {
        return this.dataManager.get(CARD);
    }
}
