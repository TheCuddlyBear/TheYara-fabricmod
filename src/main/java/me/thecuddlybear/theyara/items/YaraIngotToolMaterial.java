package me.thecuddlybear.theyara.items;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class YaraIngotToolMaterial implements ToolMaterial {

    public static final YaraIngotToolMaterial INSTANCE = new YaraIngotToolMaterial();

    @Override
    public int getDurability() {
        return 2000;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 8.0F;
    }

    @Override
    public float getAttackDamage() {
        return 4.0F;
    }

    @Override
    public int getMiningLevel() {
        return 4;
    }

    @Override
    public int getEnchantability() {
        return 22;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}
