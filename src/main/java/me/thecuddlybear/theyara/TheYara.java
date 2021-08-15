package me.thecuddlybear.theyara;

import me.thecuddlybear.theyara.items.YaraAxe;
import me.thecuddlybear.theyara.items.YaraHoe;
import me.thecuddlybear.theyara.items.YaraIngotToolMaterial;
import me.thecuddlybear.theyara.items.YaraPickaxe;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
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

    // Yara Tools
    public static ToolItem YARA_SWORD = new SwordItem(YaraIngotToolMaterial.INSTANCE, 9, -2.4F, new Item.Settings().group(YARA_GROUP));
    public static ToolItem YARA_SHOVEL = new ShovelItem(YaraIngotToolMaterial.INSTANCE, 6, -3.0F, new Item.Settings().group(YARA_GROUP));
    public static ToolItem YARA_PICKAXE = new YaraPickaxe(YaraIngotToolMaterial.INSTANCE, 6, -2.8F, new Item.Settings().group(YARA_GROUP));
    public static ToolItem YARA_AXE = new YaraAxe(YaraIngotToolMaterial.INSTANCE, 10, -3.2F, new Item.Settings().group(YARA_GROUP));
    public static ToolItem YARA_HOE = new YaraHoe(YaraIngotToolMaterial.INSTANCE, 7, -3.2F, new Item.Settings().group(YARA_GROUP));

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        //TODO: Initializer
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_sword"), YARA_SWORD);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_shovel"), YARA_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_pickaxe"), YARA_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_axe"), YARA_AXE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_hoe"), YARA_HOE);
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}