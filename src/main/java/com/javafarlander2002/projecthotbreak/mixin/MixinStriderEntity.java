package com.javafarlander2002.projecthotbreak.mixin;

import net.minecraft.entity.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StriderEntity.class)
public class MixinStriderEntity extends AnimalEntity {

    MixinStriderEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }
    @Shadow private EntityData method_30336(ServerWorldAccess serverWorldAccess, LocalDifficulty localDifficulty, MobEntity mobEntity, @Nullable EntityData entityData) {
        return new PassiveData(0.0F);
    }
    @Shadow public void saddle(@Nullable SoundCategory sound) {}

    @Shadow
    public StriderEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return (StriderEntity)EntityType.STRIDER.create(serverWorld);
    }

    /**@author Mojang (Edited by FarlanderCraft, but he just changed like three letters so it doesn't count.)*/
    @Overwrite
    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityDataMain, @Nullable CompoundTag entityTag) {
        if (false) {
            return super.initialize(world, difficulty, spawnReason, entityDataMain, entityTag);
        } else {
            Object entityData;
            if (this.random.nextInt(30) == 0) {
                MobEntity mobEntity = (MobEntity)EntityType.ZOMBIFIED_PIGLIN.create(world.toServerWorld());
                entityData = this.method_30336(world, difficulty, mobEntity, new ZombieEntity.ZombieData(ZombieEntity.method_29936(this.random), false));
                mobEntity.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.WARPED_FUNGUS_ON_A_STICK));
                this.saddle((SoundCategory)null);
            } else if (this.random.nextInt(10) == 0) {
                PassiveEntity passiveEntity = (PassiveEntity)EntityType.STRIDER.create(world.toServerWorld());
                passiveEntity.setBreedingAge(-24000);
                entityData = this.method_30336(world, difficulty, passiveEntity, (EntityData)null);
            } else {
                entityData = new PassiveData(0.5F);
            }

            return super.initialize(world, difficulty, spawnReason, (EntityData)entityData, entityTag);
        }
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
