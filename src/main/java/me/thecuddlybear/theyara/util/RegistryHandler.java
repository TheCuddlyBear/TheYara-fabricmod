package me.thecuddlybear.theyara.util;

import me.thecuddlybear.theyara.TheYara;
import me.thecuddlybear.theyara.items.YaraAxe;
import me.thecuddlybear.theyara.items.YaraHoe;
import me.thecuddlybear.theyara.items.YaraIngotToolMaterial;
import me.thecuddlybear.theyara.items.YaraPickaxe;
import me.thecuddlybear.theyara.items.armor.YaraIngotArmorMaterial;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static me.thecuddlybear.theyara.TheYara.*;

public class RegistryHandler {

    //Yarathyst tools
    public static ToolItem YARA_SWORD = new SwordItem(YaraIngotToolMaterial.INSTANCE, 9, -2.4F, new Item.Settings().group(YARA_GROUP));
    public static ToolItem YARA_SHOVEL = new ShovelItem(YaraIngotToolMaterial.INSTANCE, 6, -3.0F, new Item.Settings().group(YARA_GROUP));
    public static ToolItem YARA_PICKAXE = new YaraPickaxe(YaraIngotToolMaterial.INSTANCE, 6, -2.8F, new Item.Settings().group(YARA_GROUP));
    public static ToolItem YARA_AXE = new YaraAxe(YaraIngotToolMaterial.INSTANCE, 10, -3.2F, new Item.Settings().group(YARA_GROUP));
    public static ToolItem YARA_HOE = new YaraHoe(YaraIngotToolMaterial.INSTANCE, 7, -3.2F, new Item.Settings().group(YARA_GROUP));

    //Yarathyst Armor
    public static final ArmorMaterial YARATHYST_ARMOR_MATERIAL = new YaraIngotArmorMaterial();
    public static final Item YARATHYST_HELMET = new ArmorItem(YARATHYST_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(YARA_GROUP));
    public static final Item YARATHYST_CHESTPLATE = new ArmorItem(YARATHYST_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(YARA_GROUP));
    public static final Item YARATHYST_LEGGINGS = new ArmorItem(YARATHYST_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(YARA_GROUP));
    public static final Item YARATHYST_BOOTS = new ArmorItem(YARATHYST_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(YARA_GROUP));

    //Yarathyst
    public static final Item YARA_INGOT = new Item(new FabricItemSettings().group(YARA_GROUP));
    public static final Block YARA_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(3.5f));
    public static final Block YARA_INGOT_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(1.0f));

    //Entity Spawn eggs
    public static final Item SHROOMIE_SPAWN_EGG = new SpawnEggItem(SHROOMIE, 16656938, 16777215, new Item.Settings().group(YARA_GROUP));

    public static void registerItem(){
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_sword"), YARA_SWORD);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_shovel"), YARA_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_pickaxe"), YARA_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_axe"), YARA_AXE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_hoe"), YARA_HOE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_ingot"), YARA_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "shroomie_spawn_egg"), SHROOMIE_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_ore"), new BlockItem(YARA_ORE, new FabricItemSettings().group(YARA_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_ingot_block"), new BlockItem(YARA_INGOT_BLOCK, new FabricItemSettings().group(YARA_GROUP)));
    }

    public static void registerArmor(){
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_helmet"), YARATHYST_HELMET);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_chestplate"), YARATHYST_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_leggings"), YARATHYST_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yara_boots"), YARATHYST_BOOTS);
    }

    public static void registerBlocks(){
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "yara_ore"), YARA_ORE);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "yara_ingot_block"), YARA_INGOT_BLOCK);
    }

    /*
    public static void registerSounds(){

    }*/

}
