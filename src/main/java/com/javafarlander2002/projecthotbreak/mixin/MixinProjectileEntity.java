package com.javafarlander2002.projecthotbreak.mixin;


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
import java.util.Objects;

@Mixin(ProjectileEntity.class)
public abstract class MixinProjectileEntity extends Entity{
    public MixinProjectileEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "method_26961", at = @At(value = "RETURN"), cancellable = true)
    public void the26961inject(CallbackInfoReturnable<Boolean> ci) {
        if (!ci.getReturnValue()) {
            ci.setReturnValue(true);
        }
    }
}