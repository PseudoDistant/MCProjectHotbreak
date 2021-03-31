package com.javafarlander2002.projecthotbreak.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StriderEntity.class)
public class MixinStriderEntity extends AnimalEntity {

    protected MixinStriderEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public StriderEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return (StriderEntity)EntityType.STRIDER.create(serverWorld);
    }

    public boolean hasNoGravity() {
        return this.isInLava() || super.hasNoGravity();
    }
}
