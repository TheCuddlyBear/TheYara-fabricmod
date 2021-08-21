package me.thecuddlybear.theyara;

import me.thecuddlybear.theyara.entity.ShroomieEntity;
import me.thecuddlybear.theyara.items.YaraAxe;
import me.thecuddlybear.theyara.items.YaraHoe;
import me.thecuddlybear.theyara.items.YaraIngotToolMaterial;
import me.thecuddlybear.theyara.items.YaraPickaxe;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TheYara implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "theyara";
    public static final String MOD_NAME = "The Yara";

    // Yara Creative Tab
    public static final ItemGroup YARA_GROUP = FabricItemGroupBuilder.create(
                    new Identifier(MOD_ID, "general"))
            .icon(() -> new ItemStack(Items.BOWL))
            .build();

    // Yara Tools & Materials
    public static ToolItem YARA_SWORD = new SwordItem(YaraIngotToolMaterial.INSTANCE, 9, -2.4F, new Item.Settings().group(YARA_GROUP));
    public static ToolItem YARA_SHOVEL = new ShovelItem(YaraIngotToolMaterial.INSTANCE, 6, -3.0F, new Item.Settings().group(YARA_GROUP));
    public static ToolItem YARA_PICKAXE = new YaraPickaxe(YaraIngotToolMaterial.INSTANCE, 6, -2.8F, new Item.Settings().group(YARA_GROUP));
    public static ToolItem YARA_AXE = new YaraAxe(YaraIngotToolMaterial.INSTANCE, 10, -3.2F, new Item.Settings().group(YARA_GROUP));
    public static ToolItem YARA_HOE = new YaraHoe(YaraIngotToolMaterial.INSTANCE, 7, -3.2F, new Item.Settings().group(YARA_GROUP));
    public static Item YARA_INGOT = new Item(new FabricItemSettings().group(YARA_GROUP));
    public static final Block YARA_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(3.5f));
    public static final Block YARA_INGOT_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(1.0f));

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
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_sword"), YARA_SWORD);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_shovel"), YARA_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_pickaxe"), YARA_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_axe"), YARA_AXE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_hoe"), YARA_HOE);
        //TODO: Make yara tool recipes, make raw yara ingot, add smelting recipe for yara ingot, add deepslate yara ore.
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_ingot"), YARA_INGOT);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "yara_ore"), YARA_ORE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_ore"), new BlockItem(YARA_ORE, new FabricItemSettings().group(YARA_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "yara_ingot_block"), YARA_INGOT_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_ingot_block"), new BlockItem(YARA_INGOT_BLOCK, new FabricItemSettings().group(YARA_GROUP)));
        CustomPortalApiRegistry.addPortal(Blocks.GOLD_BLOCK, new Identifier("yara_dimension", "yara_dimension"), 234, 183, 8);
        FabricDefaultAttributeRegistry.register(SHROOMIE, ShroomieEntity.createShroomieAttributes());
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}