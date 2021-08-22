package me.thecuddlybear.theyara;

import me.thecuddlybear.theyara.entity.ShroomieEntity;
import me.thecuddlybear.theyara.entity.ShroomieEntityRenderer;
import me.thecuddlybear.theyara.util.RegistryHandler;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import static me.thecuddlybear.theyara.util.RegistryHandler.YARA_INGOT;

public class TheYara implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "theyara";
    public static final String MOD_NAME = "The Yara";

    public static final ItemGroup YARA_GROUP = FabricItemGroupBuilder.create(
                    new Identifier(MOD_ID, "general"))
            .icon(() -> new ItemStack(YARA_INGOT))
            .build();

    //Shroomie
    public static final EntityType<ShroomieEntity> SHROOMIE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("theyara", "shroomie"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ShroomieEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        //TODO: Initializer
        GeckoLib.initialize();

        //TODO: Make yara tool recipes, make raw yara ingot, add smelting recipe for yara ingot, add deepslate yara ore.
        RegistryHandler.registerItem();
        RegistryHandler.registerBlocks();

        CustomPortalApiRegistry.addPortal(Blocks.GOLD_BLOCK, new Identifier("yara_dimension", "yara_dimension"), 234, 183, 8);
        FabricDefaultAttributeRegistry.register(SHROOMIE, ShroomieEntity.createShroomieAttributes());
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}