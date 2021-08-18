package me.thecuddlybear.theyara.entity;

import me.thecuddlybear.theyara.TheYaraClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class ShroomieEntityRenderer extends MobEntityRenderer<ShroomieEntity, ShroomieEntityModel> {

    public ShroomieEntityRenderer(EntityRendererFactory.Context context){
        super(context, new ShroomieEntityModel(context.getPart(TheYaraClient.MODEL_CUBE_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(ShroomieEntity entity){
        return new Identifier("theyara", "textures/entity/shroomie/shroomie.png");
    }

}
