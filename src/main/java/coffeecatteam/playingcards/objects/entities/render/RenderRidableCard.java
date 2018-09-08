package coffeecatteam.playingcards.objects.entities.render;

import coffeecatteam.playingcards.objects.entities.EntityRidableCard;
import com.mrcrayfish.vehicle.client.render.RenderLandVehicle;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderRidableCard extends RenderLandVehicle<EntityRidableCard> {

    public RenderRidableCard(RenderManager renderManager) {
        super(renderManager);

//        float xOffset = 9.5F;
//        float zOffsetFront = 9.5F;
//        float zOffsetRear = -9.5F;
//
//        wheels.add(new Wheel(Wheel.Side.LEFT, Wheel.Position.FRONT, xOffset, zOffsetFront, 1.4F));
//        wheels.add(new Wheel(Wheel.Side.RIGHT, Wheel.Position.FRONT, xOffset, zOffsetFront, 1.4F));
//        wheels.add(new Wheel(Wheel.Side.LEFT, Wheel.Position.REAR, xOffset, zOffsetRear, 1.4F));
//        wheels.add(new Wheel(Wheel.Side.RIGHT, Wheel.Position.REAR, xOffset, zOffsetRear, 1.4F));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityRidableCard entity) {
        return null;
    }

    @Override
    public void doRender(EntityRidableCard vehicle, double x, double y, double z, float currentYaw, float partialTicks) {
        RenderHelper.enableStandardItemLighting();

        float additionalYaw = vehicle.prevAdditionalYaw + (vehicle.additionalYaw - vehicle.prevAdditionalYaw) * partialTicks;

        EntityLivingBase entityLivingBase = (EntityLivingBase) vehicle.getControllingPassenger();
        if (entityLivingBase != null) {
            entityLivingBase.renderYawOffset = currentYaw - additionalYaw;
            entityLivingBase.prevRenderYawOffset = currentYaw - additionalYaw;
        }

        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(x, y, z);
            GlStateManager.rotate(-currentYaw, 0, 1, 0);
            GlStateManager.rotate(additionalYaw, 0, 1, 0);
            GlStateManager.scale(1, 1, 1);

            // Extra detail
            float currentSpeedNormal = (vehicle.prevCurrentSpeed + (vehicle.currentSpeed - vehicle.prevCurrentSpeed) * partialTicks) / vehicle.getMaxSpeed();
            float turnAngleNormal = ((float) vehicle.prevTurnAngle + (float) (vehicle.turnAngle - vehicle.prevTurnAngle) * partialTicks) / 45.0F;
            GlStateManager.rotate(turnAngleNormal * currentSpeedNormal * -15.0F, 0.0F, 0.0F, 1.0F);

//            // Spin spin
//            if (vehicle.isMoving()) {
//                if ((vehicle.spinAngle += 10.0F) >= 360.0F) {
//                    vehicle.spinAngle = 0.0F;
//                }
//            }
//            GlStateManager.rotate(vehicle.spinAngle, 0, 1, 0);

            this.setupBreakAnimation(vehicle, partialTicks);

            double bodyOffset = 0.5625;

            // Render the body
            GlStateManager.pushMatrix();
            {
                GlStateManager.translate(0, bodyOffset + -0.35F, 0);
                GlStateManager.rotate(90.0F, 1, 0, 0);

                float scale = 2.0F;
                GlStateManager.scale(scale, scale, scale / 2);
                ItemStack stack = vehicle.getCard();
                if (stack.getItem() instanceof ItemBlock) {
                    GlStateManager.rotate(-90.0F, 1, 0, 0);
                    GlStateManager.rotate(90.0F, 0, 1, 0);
                }
                Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.NONE);
            }
            GlStateManager.popMatrix();

            GlStateManager.translate(0, 3.5F * 0.0625F, 0);
            super.doRender(vehicle, x, y, z, currentYaw, partialTicks);
        }
        GlStateManager.popMatrix();
    }
}
