package com.javafarlander2002.minecartflyhotbreak.mixin;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;

@Mixin(ProjectileEntity.class)
public abstract class MixinProjectileEntity extends Entity{
    public MixinProjectileEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow public abstract Entity getOwner();

    @Inject(method = "method_26961", at = @At(value = "INVOKE"), cancellable = true)
    public void the26961inject(CallbackInfoReturnable ci) {
        if (2 != 5) {
            ci.setReturnValue(true);
            ci.cancel();
        }
    }
}