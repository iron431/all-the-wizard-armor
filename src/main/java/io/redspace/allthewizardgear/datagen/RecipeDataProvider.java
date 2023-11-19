package io.redspace.allthewizardgear.datagen;

import io.redspace.allthewizardgear.AllTheWizardGear;
import io.redspace.allthewizardgear.registry.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class RecipeDataProvider extends RecipeProvider {
    public RecipeDataProvider(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        smithing(consumer, ATWGTags.ALLTHEMODIUM_SMITHING_TEMPLATE, ATWGTags.BASE_WIZARD_HELMET, ATWGTags.ALLTHEMODIUM_INGOT, ItemRegistry.ALLTHEMODIUM_MAGE_HELMET.get());
        smithing(consumer, ATWGTags.ALLTHEMODIUM_SMITHING_TEMPLATE, ATWGTags.BASE_WIZARD_CHESTPLATE, ATWGTags.ALLTHEMODIUM_INGOT, ItemRegistry.ALLTHEMODIUM_MAGE_CHESTPLATE.get());
        smithing(consumer, ATWGTags.ALLTHEMODIUM_SMITHING_TEMPLATE, ATWGTags.BASE_WIZARD_LEGGINGS, ATWGTags.ALLTHEMODIUM_INGOT, ItemRegistry.ALLTHEMODIUM_MAGE_LEGGINGS.get());
        smithing(consumer, ATWGTags.ALLTHEMODIUM_SMITHING_TEMPLATE, ATWGTags.BASE_WIZARD_BOOTS, ATWGTags.ALLTHEMODIUM_INGOT, ItemRegistry.ALLTHEMODIUM_MAGE_BOOTS.get());
        smithing(consumer, ATWGTags.ALLTHEMODIUM_SMITHING_TEMPLATE, io.redspace.ironsspellbooks.registries.ItemRegistry.NETHERITE_SPELL_BOOK, ATWGTags.ALLTHEMODIUM_INGOT, ItemRegistry.ALLTHEMODIUM_SPELLBOOK.get());

        smithing(consumer, ATWGTags.VIBRANIUM_SMITHING_TEMPLATE, ItemRegistry.ALLTHEMODIUM_MAGE_HELMET, ATWGTags.VIBRANIUM_INGOT, ItemRegistry.VIBRANIUM_MAGE_HELMET.get());
        smithing(consumer, ATWGTags.VIBRANIUM_SMITHING_TEMPLATE, ItemRegistry.ALLTHEMODIUM_MAGE_CHESTPLATE, ATWGTags.VIBRANIUM_INGOT, ItemRegistry.VIBRANIUM_MAGE_CHESTPLATE.get());
        smithing(consumer, ATWGTags.VIBRANIUM_SMITHING_TEMPLATE, ItemRegistry.ALLTHEMODIUM_MAGE_LEGGINGS, ATWGTags.VIBRANIUM_INGOT, ItemRegistry.VIBRANIUM_MAGE_LEGGINGS.get());
        smithing(consumer, ATWGTags.VIBRANIUM_SMITHING_TEMPLATE, ItemRegistry.ALLTHEMODIUM_MAGE_BOOTS, ATWGTags.VIBRANIUM_INGOT, ItemRegistry.VIBRANIUM_MAGE_BOOTS.get());
        smithing(consumer, ATWGTags.VIBRANIUM_SMITHING_TEMPLATE, ItemRegistry.ALLTHEMODIUM_SPELLBOOK, ATWGTags.VIBRANIUM_INGOT, ItemRegistry.VIBRANIUM_SPELLBOOK.get());

        smithing(consumer, ATWGTags.UNOBTANIUM_SMITHING_TEMPLATE, ItemRegistry.VIBRANIUM_MAGE_HELMET, ATWGTags.UNOBTAINIUM_INGOT, ItemRegistry.UNOBTAINIUM_MAGE_HELMET.get());
        smithing(consumer, ATWGTags.UNOBTANIUM_SMITHING_TEMPLATE, ItemRegistry.VIBRANIUM_MAGE_CHESTPLATE, ATWGTags.UNOBTAINIUM_INGOT, ItemRegistry.UNOBTAINIUM_MAGE_CHESTPLATE.get());
        smithing(consumer, ATWGTags.UNOBTANIUM_SMITHING_TEMPLATE, ItemRegistry.VIBRANIUM_MAGE_LEGGINGS, ATWGTags.UNOBTAINIUM_INGOT, ItemRegistry.UNOBTAINIUM_MAGE_LEGGINGS.get());
        smithing(consumer, ATWGTags.UNOBTANIUM_SMITHING_TEMPLATE, ItemRegistry.VIBRANIUM_MAGE_BOOTS, ATWGTags.UNOBTAINIUM_INGOT, ItemRegistry.UNOBTAINIUM_MAGE_BOOTS.get());
        smithing(consumer, ATWGTags.UNOBTANIUM_SMITHING_TEMPLATE, ItemRegistry.VIBRANIUM_SPELLBOOK, ATWGTags.UNOBTAINIUM_INGOT, ItemRegistry.UNOBTAINIUM_SPELLBOOK.get());
    }

    protected static void smithing(Consumer<FinishedRecipe> consumer, TagKey<Item> smithingTemplate, TagKey<Item> baseItem, TagKey<Item> modifierItem, Item result) {
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(smithingTemplate),
                Ingredient.of(baseItem),
                Ingredient.of(modifierItem),
                RecipeCategory.COMBAT,
                result).unlocks(AllTheWizardGear.MODID + ":has_" + modifierItem.location().getPath() + "_ingot", has(modifierItem)).save(consumer, getItemName(result) + "_smithing");
    }

    protected static void smithing(Consumer<FinishedRecipe> consumer, TagKey<Item> smithingTemplate, RegistryObject<Item> baseItem, TagKey<Item> modifierItem, Item result) {
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(smithingTemplate),
                Ingredient.of(baseItem.get()),
                Ingredient.of(modifierItem),
                RecipeCategory.COMBAT,
                result).unlocks(AllTheWizardGear.MODID + "has_" + modifierItem.location().getPath() + "_ingot", has(modifierItem)).save(consumer, getItemName(result) + "_smithing");
    }
}
