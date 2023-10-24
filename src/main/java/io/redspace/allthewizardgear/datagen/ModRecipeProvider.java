package io.redspace.allthewizardgear.datagen;

import io.redspace.allthewizardgear.AllTheWizardGear;
import io.redspace.allthewizardgear.registry.ItemRegistry;
import com.thevortex.allthemodium.registry.ModRegistry;
import com.thevortex.allthemodium.registry.TagRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        smithing(consumer, ModRegistry.ATM_SMITHING.get(), ModTags.BASE_WIZARD_HELMET, TagRegistry.ALLTHEMODIUM_INGOT, ItemRegistry.ALLTHEMODIUM_MAGE_HELMET.get());
        smithing(consumer, ModRegistry.ATM_SMITHING.get(), ModTags.BASE_WIZARD_CHESTPLATE, TagRegistry.ALLTHEMODIUM_INGOT, ItemRegistry.ALLTHEMODIUM_MAGE_CHESTPLATE.get());
        smithing(consumer, ModRegistry.ATM_SMITHING.get(), ModTags.BASE_WIZARD_LEGGINGS, TagRegistry.ALLTHEMODIUM_INGOT, ItemRegistry.ALLTHEMODIUM_MAGE_LEGGINGS.get());
        smithing(consumer, ModRegistry.ATM_SMITHING.get(), ModTags.BASE_WIZARD_BOOTS, TagRegistry.ALLTHEMODIUM_INGOT, ItemRegistry.ALLTHEMODIUM_MAGE_BOOTS.get());
        smithing(consumer, ModRegistry.ATM_SMITHING.get(), io.redspace.ironsspellbooks.registries.ItemRegistry.NETHERITE_SPELL_BOOK, TagRegistry.ALLTHEMODIUM_INGOT, ItemRegistry.ALLTHEMODIUM_SPELLBOOK.get());

        smithing(consumer, ModRegistry.VIB_SMITHING.get(), ItemRegistry.ALLTHEMODIUM_MAGE_HELMET, TagRegistry.VIBRANIUM_INGOT, ItemRegistry.VIBRANIUM_MAGE_HELMET.get());
        smithing(consumer, ModRegistry.VIB_SMITHING.get(), ItemRegistry.ALLTHEMODIUM_MAGE_CHESTPLATE, TagRegistry.VIBRANIUM_INGOT, ItemRegistry.VIBRANIUM_MAGE_CHESTPLATE.get());
        smithing(consumer, ModRegistry.VIB_SMITHING.get(), ItemRegistry.ALLTHEMODIUM_MAGE_LEGGINGS, TagRegistry.VIBRANIUM_INGOT, ItemRegistry.VIBRANIUM_MAGE_LEGGINGS.get());
        smithing(consumer, ModRegistry.VIB_SMITHING.get(), ItemRegistry.ALLTHEMODIUM_MAGE_BOOTS, TagRegistry.VIBRANIUM_INGOT, ItemRegistry.VIBRANIUM_MAGE_BOOTS.get());
        smithing(consumer, ModRegistry.VIB_SMITHING.get(), ItemRegistry.ALLTHEMODIUM_SPELLBOOK, TagRegistry.VIBRANIUM_INGOT, ItemRegistry.VIBRANIUM_SPELLBOOK.get());

        smithing(consumer, ModRegistry.UNO_SMITHING.get(), ItemRegistry.VIBRANIUM_MAGE_HELMET, TagRegistry.UNOBTAINIUM_INGOT, ItemRegistry.UNOBTAINIUM_MAGE_HELMET.get());
        smithing(consumer, ModRegistry.UNO_SMITHING.get(), ItemRegistry.VIBRANIUM_MAGE_CHESTPLATE, TagRegistry.UNOBTAINIUM_INGOT, ItemRegistry.UNOBTAINIUM_MAGE_CHESTPLATE.get());
        smithing(consumer, ModRegistry.UNO_SMITHING.get(), ItemRegistry.VIBRANIUM_MAGE_LEGGINGS, TagRegistry.UNOBTAINIUM_INGOT, ItemRegistry.UNOBTAINIUM_MAGE_LEGGINGS.get());
        smithing(consumer, ModRegistry.UNO_SMITHING.get(), ItemRegistry.VIBRANIUM_MAGE_BOOTS, TagRegistry.UNOBTAINIUM_INGOT, ItemRegistry.UNOBTAINIUM_MAGE_BOOTS.get());
        smithing(consumer, ModRegistry.UNO_SMITHING.get(), ItemRegistry.VIBRANIUM_SPELLBOOK, TagRegistry.UNOBTAINIUM_INGOT, ItemRegistry.UNOBTAINIUM_SPELLBOOK.get());
    }

    protected static void smithing(Consumer<FinishedRecipe> consumer, Item smithingTemplate, TagKey<Item> baseItem, TagKey<Item> modifierItem, Item result) {
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(smithingTemplate),
                Ingredient.of(baseItem),
                Ingredient.of(modifierItem),
                RecipeCategory.COMBAT,
                result).unlocks(AllTheWizardGear.MODID + ":has_" + modifierItem.location().getPath() + "_ingot", has(modifierItem)).save(consumer, getItemName(result) + "_smithing");
    }

    protected static void smithing(Consumer<FinishedRecipe> consumer, Item smithingTemplate, RegistryObject<Item> baseItem, TagKey<Item> modifierItem, Item result) {
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(smithingTemplate),
                Ingredient.of(baseItem.get()),
                Ingredient.of(modifierItem),
                RecipeCategory.COMBAT,
                result).unlocks(AllTheWizardGear.MODID + "has_" + modifierItem.location().getPath() + "_ingot", has(modifierItem)).save(consumer, getItemName(result) + "_smithing");
    }
}
