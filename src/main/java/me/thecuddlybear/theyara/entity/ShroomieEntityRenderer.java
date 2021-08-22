package me.thecuddlybear.theyara.entity;

import me.thecuddlybear.theyara.TheYaraClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ShroomieEntityRenderer extends GeoEntityRenderer<ShroomieEntity> {

    public ShroomieEntityRenderer(EntityRendererFactory.Context context){
        super(context, new ShroomieEntityModel());
    }


}
