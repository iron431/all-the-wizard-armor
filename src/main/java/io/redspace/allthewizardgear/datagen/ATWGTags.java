package io.redspace.allthewizardgear.datagen;

import io.redspace.allthewizardgear.AllTheWizardGear;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ATWGTags {

    public static final TagKey<Item> BASE_WIZARD_HELMET = ItemTags.create(new ResourceLocation(AllTheWizardGear.MODID, "base_wizard_helmet"));
    public static final TagKey<Item> BASE_WIZARD_CHESTPLATE = ItemTags.create(new ResourceLocation(AllTheWizardGear.MODID, "base_wizard_chestplate"));
    public static final TagKey<Item> BASE_WIZARD_LEGGINGS = ItemTags.create(new ResourceLocation(AllTheWizardGear.MODID, "base_wizard_leggings"));
    public static final TagKey<Item> BASE_WIZARD_BOOTS = ItemTags.create(new ResourceLocation(AllTheWizardGear.MODID, "base_wizard_boots"));

    //These are just namespace references for using in code
    public static final TagKey<Item> ALLTHEMODIUM_INGOT = ItemTags.create(new ResourceLocation("forge:ingots/allthemodium"));
    public static final TagKey<Item> VIBRANIUM_INGOT = ItemTags.create(new ResourceLocation("forge:ingots/vibranium"));
    public static final TagKey<Item> UNOBTAINIUM_INGOT = ItemTags.create(new ResourceLocation("forge:ingots/unobtainium"));
    public static final TagKey<Item> ALLTHEMODIUM_SMITHING_TEMPLATE = ItemTags.create(new ResourceLocation(AllTheWizardGear.MODID,"allthemodium_smithing"));
    public static final TagKey<Item> VIBRANIUM_SMITHING_TEMPLATE = ItemTags.create(new ResourceLocation(AllTheWizardGear.MODID,"vibranium_smithing"));
    public static final TagKey<Item> UNOBTANIUM_SMITHING_TEMPLATE = ItemTags.create(new ResourceLocation(AllTheWizardGear.MODID,"unobtainium_smithing"));


}
