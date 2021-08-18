package me.thecuddlybear.theyara.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class ShroomieEntity extends PathAwareEntity {

    public ShroomieEntity(EntityType<? extends PathAwareEntity> entityType, World world){
        super(entityType, world);
    }

}
