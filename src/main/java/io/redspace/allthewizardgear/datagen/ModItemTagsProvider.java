package io.redspace.allthewizardgear.datagen;

import com.thevortex.allthemodium.registry.ModRegistry;
import io.redspace.allthewizardgear.AllTheWizardGear;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends IntrinsicHolderTagsProvider<Item> {

    public ModItemTagsProvider(PackOutput p_256164_, CompletableFuture p_256488_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_256164_, ForgeRegistries.ITEMS.getRegistryKey(), p_256488_, (p_255790_) -> p_255790_.builtInRegistryHolder().key(), AllTheWizardGear.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.BASE_WIZARD_BOOTS)
                .add(ItemRegistry.ARCHEVOKER_BOOTS.get())
                .add(ItemRegistry.CRYOMANCER_BOOTS.get())
                .add(ItemRegistry.CULTIST_BOOTS.get())
                .add(ItemRegistry.PLAGUED_BOOTS.get())
                .add(ItemRegistry.PRIEST_BOOTS.get())
                .add(ItemRegistry.ELECTROMANCER_BOOTS.get())
                .add(ItemRegistry.PYROMANCER_BOOTS.get())
                .add(ItemRegistry.SHADOWWALKER_BOOTS.get());
        this.tag(ModTags.BASE_WIZARD_LEGGINGS)
                .add(ItemRegistry.ARCHEVOKER_LEGGINGS.get())
                .add(ItemRegistry.CRYOMANCER_LEGGINGS.get())
                .add(ItemRegistry.CULTIST_LEGGINGS.get())
                .add(ItemRegistry.PLAGUED_LEGGINGS.get())
                .add(ItemRegistry.PRIEST_LEGGINGS.get())
                .add(ItemRegistry.ELECTROMANCER_LEGGINGS.get())
                .add(ItemRegistry.PYROMANCER_LEGGINGS.get())
                .add(ItemRegistry.SHADOWWALKER_LEGGINGS.get());
        this.tag(ModTags.BASE_WIZARD_CHESTPLATE)
                .add(ItemRegistry.ARCHEVOKER_CHESTPLATE.get())
                .add(ItemRegistry.CRYOMANCER_CHESTPLATE.get())
                .add(ItemRegistry.CULTIST_CHESTPLATE.get())
                .add(ItemRegistry.PLAGUED_CHESTPLATE.get())
                .add(ItemRegistry.PRIEST_CHESTPLATE.get())
                .add(ItemRegistry.ELECTROMANCER_CHESTPLATE.get())
                .add(ItemRegistry.PYROMANCER_CHESTPLATE.get())
                .add(ItemRegistry.SHADOWWALKER_CHESTPLATE.get());
        this.tag(ModTags.BASE_WIZARD_HELMET)
                .add(ItemRegistry.ARCHEVOKER_HELMET.get())
                .add(ItemRegistry.CRYOMANCER_HELMET.get())
                .add(ItemRegistry.CULTIST_HELMET.get())
                .add(ItemRegistry.PLAGUED_HELMET.get())
                .add(ItemRegistry.PRIEST_HELMET.get())
                .add(ItemRegistry.ELECTROMANCER_HELMET.get())
                .add(ItemRegistry.PYROMANCER_HELMET.get())
                .add(ItemRegistry.SHADOWWALKER_HELMET.get());

        this.tag(ModTags.CAN_BE_UPGRADED).replace(false)
                .add(io.redspace.allthewizardgear.registry.ItemRegistry.ALLTHEMODIUM_MAGE_HELMET.get())
                .add(io.redspace.allthewizardgear.registry.ItemRegistry.ALLTHEMODIUM_MAGE_CHESTPLATE.get())
                .add(io.redspace.allthewizardgear.registry.ItemRegistry.ALLTHEMODIUM_MAGE_LEGGINGS.get())
                .add(io.redspace.allthewizardgear.registry.ItemRegistry.ALLTHEMODIUM_MAGE_BOOTS.get())
                .add(io.redspace.allthewizardgear.registry.ItemRegistry.VIBRANIUM_MAGE_HELMET.get())
                .add(io.redspace.allthewizardgear.registry.ItemRegistry.VIBRANIUM_MAGE_CHESTPLATE.get())
                .add(io.redspace.allthewizardgear.registry.ItemRegistry.VIBRANIUM_MAGE_LEGGINGS.get())
                .add(io.redspace.allthewizardgear.registry.ItemRegistry.VIBRANIUM_MAGE_BOOTS.get())
                .add(io.redspace.allthewizardgear.registry.ItemRegistry.UNOBTAINIUM_MAGE_HELMET.get())
                .add(io.redspace.allthewizardgear.registry.ItemRegistry.UNOBTAINIUM_MAGE_CHESTPLATE.get())
                .add(io.redspace.allthewizardgear.registry.ItemRegistry.UNOBTAINIUM_MAGE_LEGGINGS.get())
                .add(io.redspace.allthewizardgear.registry.ItemRegistry.UNOBTAINIUM_MAGE_BOOTS.get())
                .add(ModRegistry.ALLTHEMODIUM_HELMET.get())
                .add(ModRegistry.ALLTHEMODIUM_CHESTPLATE.get())
                .add(ModRegistry.ALLTHEMODIUM_LEGGINGS.get())
                .add(ModRegistry.ALLTHEMODIUM_BOOTS.get())
                .add(ModRegistry.VIBRANIUM_HELMET.get())
                .add(ModRegistry.VIBRANIUM_CHESTPLATE.get())
                .add(ModRegistry.VIBRANIUM_LEGGINGS.get())
                .add(ModRegistry.VIBRANIUM_BOOTS.get())
                .add(ModRegistry.UNOBTAINIUM_HELMET.get())
                .add(ModRegistry.UNOBTAINIUM_CHESTPLATE.get())
                .add(ModRegistry.UNOBTAINIUM_LEGGINGS.get())
                .add(ModRegistry.UNOBTAINIUM_BOOTS.get());

    }
}
