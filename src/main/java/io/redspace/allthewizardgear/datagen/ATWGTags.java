package io.redspace.allthewizardgear.datagen;

import com.thevortex.allthemodium.reference.Reference;
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

    public static final TagKey<Item> CAN_BE_UPGRADED = ItemTags.create(new ResourceLocation("irons_spellbooks", "can_be_upgraded"));

    //These are just namespace references for using in code
    public static final TagKey<Item> ALLTHEMODIUM_INGOT = ItemTags.create(Reference.ingot("allthemodium"));
    public static final TagKey<Item> VIBRANIUM_INGOT = ItemTags.create(Reference.ingot("vibranium"));
    public static final TagKey<Item> UNOBTAINIUM_INGOT = ItemTags.create(Reference.ingot("unobtainium"));

}
