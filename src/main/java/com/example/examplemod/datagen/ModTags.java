package com.example.examplemod.datagen;

import com.example.examplemod.ExampleMod;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {

    public static final TagKey<Item> BASE_WIZARD_HELMET = ItemTags.create(new ResourceLocation(ExampleMod.MODID, "base_wizard_helmet"));
    public static final TagKey<Item> BASE_WIZARD_CHESTPLATE = ItemTags.create(new ResourceLocation(ExampleMod.MODID, "base_wizard_chestplate"));
    public static final TagKey<Item> BASE_WIZARD_LEGGINGS = ItemTags.create(new ResourceLocation(ExampleMod.MODID, "base_wizard_leggings"));
    public static final TagKey<Item> BASE_WIZARD_BOOTS = ItemTags.create(new ResourceLocation(ExampleMod.MODID, "base_wizard_boots"));
}
