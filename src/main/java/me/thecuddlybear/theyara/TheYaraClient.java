package me.thecuddlybear.theyara;

import me.thecuddlybear.theyara.entity.ShroomieEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class TheYaraClient implements ClientModInitializer {

    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier("theyara", "shroomie"), "main");

    @Override
    public void onInitializeClient(){
        EntityRendererRegistry.INSTANCE.register(TheYara.SHROOMIE, (context) -> {
            return new ShroomieEntityRenderer(context);
        });
    }

}
