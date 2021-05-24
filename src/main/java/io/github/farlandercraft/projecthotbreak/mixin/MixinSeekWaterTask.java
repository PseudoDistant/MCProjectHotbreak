package io.github.farlandercraft.projecthotbreak.mixin;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.LookTargetUtil;
import net.minecraft.entity.ai.brain.task.SeekWaterTask;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;
import java.util.Map;

@Mixin(SeekWaterTask.class)
public class MixinSeekWaterTask extends Task<PathAwareEntity> {

    @Shadow
    private final int range;
    @Shadow
    private final float speed;
    public MixinSeekWaterTask(int range, float speed) {
        super(ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryModuleState.VALUE_ABSENT, MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT, MemoryModuleType.LOOK_TARGET, MemoryModuleState.REGISTERED));
        this.range = range;
        this.speed = speed;
    }
    /**
     * @author Mojang
     */
    @Overwrite
    public void run(ServerWorld serverWorld, PathAwareEntity pathAwareEntity, long l) {
        BlockPos blockPos = null;
        Iterable<BlockPos> iterable = BlockPos.iterateOutwards(pathAwareEntity.getBlockPos(), this.range, this.range, this.range);
        Iterator var7 = iterable.iterator();
        while(var7.hasNext()) {
            BlockPos blockPos2 = (BlockPos) var7.next();
            if (pathAwareEntity.world.getFluidState(blockPos2).isIn(FluidTags.WATER)) {
                blockPos = blockPos2.toImmutable();
                break;
            }
        }
        if (blockPos != null) {
            LookTargetUtil.walkTowards(pathAwareEntity, (BlockPos)blockPos, this.speed, 0);
        }
    }
}
