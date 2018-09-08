package coffeecatteam.playingcards.objects.entities;

import coffeecatteam.playingcards.Reference;
import coffeecatteam.playingcards.init.InitItem;
import coffeecatteam.playingcards.util.CardProperties;
import com.mrcrayfish.vehicle.entity.EngineType;
import com.mrcrayfish.vehicle.entity.EntityLandVehicle;
import com.mrcrayfish.vehicle.entity.EntityVehicle;
import com.mrcrayfish.vehicle.init.ModItems;
import com.mrcrayfish.vehicle.init.ModSounds;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

public class EntityRidableCard extends EntityLandVehicle {

    private static final DataParameter<String> CARD;

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
        this.dataManager.register(CARD, Reference.MODID + ":" + InitItem.CARDS.get(new Random().nextInt(InitItem.CARDS.size()-1)).getUnlocalizedName().substring(5));

        this.spinAngle = this.turnAngle;

        if (world.isRemote) {
            body = new ItemStack(InitItem.PLAYING_CARD);
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
        if (compound.hasKey("card")) {
            this.setCard(compound.getString("card"));
        }
    }

    protected void writeEntityToNBT(NBTTagCompound compound) {
        if (this.getCard() != null)
            compound.setString("card", this.getCard());
        else
            compound.setString("card", Reference.MODID + ":" + InitItem.PLAYING_CARD.getUnlocalizedName());
    }

    public void setCard(String card) {
        this.dataManager.set(CARD, card);
    }

    public String getCard() {
        String domain = this.dataManager.get(CARD).split(":")[0];
        if (domain.equals(Reference.MODID))
            return this.dataManager.get(CARD);
        return Reference.MODID + ":" + InitItem.PLAYING_CARD.getUnlocalizedName();
    }

    static {
        CARD = EntityDataManager.createKey(EntityRidableCard.class, DataSerializers.STRING);
    }
}
