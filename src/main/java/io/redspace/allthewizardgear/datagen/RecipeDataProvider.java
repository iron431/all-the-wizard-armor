package io.redspace.allthewizardgear.datagen;

import io.redspace.allthewizardgear.AllTheWizardGear;
import io.redspace.allthewizardgear.registry.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class RecipeDataProvider extends RecipeProvider {

    public RecipeDataProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        smithing(recipeOutput, ATWGTags.ALLTHEMODIUM_SMITHING_TEMPLATE, ATWGTags.BASE_WIZARD_HELMET, ATWGTags.ALLTHEMODIUM_INGOT, ItemRegistry.ALLTHEMODIUM_MAGE_HELMET.get());
        smithing(recipeOutput, ATWGTags.ALLTHEMODIUM_SMITHING_TEMPLATE, ATWGTags.BASE_WIZARD_CHESTPLATE, ATWGTags.ALLTHEMODIUM_INGOT, ItemRegistry.ALLTHEMODIUM_MAGE_CHESTPLATE.get());
        smithing(recipeOutput, ATWGTags.ALLTHEMODIUM_SMITHING_TEMPLATE, ATWGTags.BASE_WIZARD_LEGGINGS, ATWGTags.ALLTHEMODIUM_INGOT, ItemRegistry.ALLTHEMODIUM_MAGE_LEGGINGS.get());
        smithing(recipeOutput, ATWGTags.ALLTHEMODIUM_SMITHING_TEMPLATE, ATWGTags.BASE_WIZARD_BOOTS, ATWGTags.ALLTHEMODIUM_INGOT, ItemRegistry.ALLTHEMODIUM_MAGE_BOOTS.get());
        smithing(recipeOutput, ATWGTags.ALLTHEMODIUM_SMITHING_TEMPLATE, io.redspace.ironsspellbooks.registries.ItemRegistry.NETHERITE_SPELL_BOOK, ATWGTags.ALLTHEMODIUM_INGOT, ItemRegistry.ALLTHEMODIUM_SPELLBOOK.get());

        smithing(recipeOutput, ATWGTags.VIBRANIUM_SMITHING_TEMPLATE, ItemRegistry.ALLTHEMODIUM_MAGE_HELMET, ATWGTags.VIBRANIUM_INGOT, ItemRegistry.VIBRANIUM_MAGE_HELMET.get());
        smithing(recipeOutput, ATWGTags.VIBRANIUM_SMITHING_TEMPLATE, ItemRegistry.ALLTHEMODIUM_MAGE_CHESTPLATE, ATWGTags.VIBRANIUM_INGOT, ItemRegistry.VIBRANIUM_MAGE_CHESTPLATE.get());
        smithing(recipeOutput, ATWGTags.VIBRANIUM_SMITHING_TEMPLATE, ItemRegistry.ALLTHEMODIUM_MAGE_LEGGINGS, ATWGTags.VIBRANIUM_INGOT, ItemRegistry.VIBRANIUM_MAGE_LEGGINGS.get());
        smithing(recipeOutput, ATWGTags.VIBRANIUM_SMITHING_TEMPLATE, ItemRegistry.ALLTHEMODIUM_MAGE_BOOTS, ATWGTags.VIBRANIUM_INGOT, ItemRegistry.VIBRANIUM_MAGE_BOOTS.get());
        smithing(recipeOutput, ATWGTags.VIBRANIUM_SMITHING_TEMPLATE, ItemRegistry.ALLTHEMODIUM_SPELLBOOK, ATWGTags.VIBRANIUM_INGOT, ItemRegistry.VIBRANIUM_SPELLBOOK.get());

        smithing(recipeOutput, ATWGTags.UNOBTANIUM_SMITHING_TEMPLATE, ItemRegistry.VIBRANIUM_MAGE_HELMET, ATWGTags.UNOBTAINIUM_INGOT, ItemRegistry.UNOBTAINIUM_MAGE_HELMET.get());
        smithing(recipeOutput, ATWGTags.UNOBTANIUM_SMITHING_TEMPLATE, ItemRegistry.VIBRANIUM_MAGE_CHESTPLATE, ATWGTags.UNOBTAINIUM_INGOT, ItemRegistry.UNOBTAINIUM_MAGE_CHESTPLATE.get());
        smithing(recipeOutput, ATWGTags.UNOBTANIUM_SMITHING_TEMPLATE, ItemRegistry.VIBRANIUM_MAGE_LEGGINGS, ATWGTags.UNOBTAINIUM_INGOT, ItemRegistry.UNOBTAINIUM_MAGE_LEGGINGS.get());
        smithing(recipeOutput, ATWGTags.UNOBTANIUM_SMITHING_TEMPLATE, ItemRegistry.VIBRANIUM_MAGE_BOOTS, ATWGTags.UNOBTAINIUM_INGOT, ItemRegistry.UNOBTAINIUM_MAGE_BOOTS.get());
        smithing(recipeOutput, ATWGTags.UNOBTANIUM_SMITHING_TEMPLATE, ItemRegistry.VIBRANIUM_SPELLBOOK, ATWGTags.UNOBTAINIUM_INGOT, ItemRegistry.UNOBTAINIUM_SPELLBOOK.get());
    }

    protected static void smithing(RecipeOutput output, TagKey<Item> smithingTemplate, TagKey<Item> baseItem, TagKey<Item> modifierItem, Item result) {
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(smithingTemplate),
                Ingredient.of(baseItem),
                Ingredient.of(modifierItem),
                RecipeCategory.COMBAT,
                result).unlocks(AllTheWizardGear.MODID + ":has_" + modifierItem.location().getPath() + "_ingot", has(modifierItem)).save(output, getItemName(result) + "_smithing");
    }

    protected static void smithing(RecipeOutput output, TagKey<Item> smithingTemplate, Supplier<Item> baseItem, TagKey<Item> modifierItem, Item result) {
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(smithingTemplate),
                Ingredient.of(baseItem.get()),
                Ingredient.of(modifierItem),
                RecipeCategory.COMBAT,
                result).unlocks(AllTheWizardGear.MODID + "has_" + modifierItem.location().getPath() + "_ingot", has(modifierItem)).save(output, getItemName(result) + "_smithing");
    }
}
