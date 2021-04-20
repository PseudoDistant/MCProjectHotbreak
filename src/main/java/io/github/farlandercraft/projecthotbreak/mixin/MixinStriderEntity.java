package io.github.farlandercraft.projecthotbreak.mixin;

import net.minecraft.entity.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StriderEntity.class)
public class MixinStriderEntity extends AnimalEntity {

    MixinStriderEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }
    @Shadow
    public StriderEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return (StriderEntity)EntityType.STRIDER.create(serverWorld);
    }

    /** Redirect mixin suggested by LlamaLad7 (https://github.com/LlamaLad7) */
    @Redirect(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/StriderEntity;isBaby()Z"))
    private boolean replaceBabyCheck(StriderEntity striderEntity) {
        return false;
    }

    @Inject(method = "canAddPassenger", at = @At(value = "RETURN"), cancellable = true)
    protected void canAddPassengerInject(CallbackInfoReturnable<Boolean> ci) {
        if (!ci.getReturnValue()) {
            ci.setReturnValue(true);
        }
    }

    public boolean hasNoGravity() {
        return this.isInLava() || super.hasNoGravity();
    }
}
