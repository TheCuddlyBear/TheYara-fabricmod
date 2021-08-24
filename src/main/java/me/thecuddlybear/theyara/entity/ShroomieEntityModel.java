// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.17
// Paste this class into your mod and generate all required imports

package me.thecuddlybear.theyara.entity;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShroomieEntityModel extends AnimatedGeoModel {

    @Override
    public Identifier getModelLocation(Object object) {
        return new Identifier("theyara", "geo/shroomie.geo.json");
    }

    @Override
    public Identifier getTextureLocation(Object object) {
        return new Identifier("theyara", "textures/entity/shroomie/shroomie.png");
    }

    @Override
    public Identifier getAnimationFileLocation(Object animatable) {
        return new Identifier("theyara", "animations/shroomie.animation.json");
    }
}