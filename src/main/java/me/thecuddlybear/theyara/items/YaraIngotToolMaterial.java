package me.thecuddlybear.theyara.items;

import me.thecuddlybear.theyara.TheYara;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import me.thecuddlybear.theyara.util.RegistryHandler;

import static me.thecuddlybear.theyara.util.RegistryHandler.YARA_INGOT;

public class YaraIngotToolMaterial implements ToolMaterial {

    public static final YaraIngotToolMaterial INSTANCE = new YaraIngotToolMaterial();

    @Override
    public int getDurability() {
        return 2000;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 10.0F;
    }

    @Override
    public float getAttackDamage() {
        return 4.0F;
    }

    @Override
    public int getMiningLevel() {
        return 6;
    }

    @Override
    public int getEnchantability() {
        return 22;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(YARA_INGOT);
    }
}
