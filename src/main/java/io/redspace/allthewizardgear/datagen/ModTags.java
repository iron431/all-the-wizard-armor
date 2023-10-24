package io.redspace.allthewizardgear.datagen;

import io.redspace.allthewizardgear.AllTheWizardGear;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {

    public static final TagKey<Item> BASE_WIZARD_HELMET = ItemTags.create(new ResourceLocation(AllTheWizardGear.MODID, "base_wizard_helmet"));
    public static final TagKey<Item> BASE_WIZARD_CHESTPLATE = ItemTags.create(new ResourceLocation(AllTheWizardGear.MODID, "base_wizard_chestplate"));
    public static final TagKey<Item> BASE_WIZARD_LEGGINGS = ItemTags.create(new ResourceLocation(AllTheWizardGear.MODID, "base_wizard_leggings"));
    public static final TagKey<Item> BASE_WIZARD_BOOTS = ItemTags.create(new ResourceLocation(AllTheWizardGear.MODID, "base_wizard_boots"));
}
