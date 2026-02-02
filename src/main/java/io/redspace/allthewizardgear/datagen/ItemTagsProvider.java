package io.redspace.allthewizardgear.datagen;

import io.redspace.allthewizardgear.AllTheWizardGear;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import io.redspace.ironsspellbooks.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagsProvider extends IntrinsicHolderTagsProvider<Item> {

    public ItemTagsProvider(PackOutput p_256164_, CompletableFuture p_256488_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_256164_, Registries.ITEM, p_256488_, (p_255790_) -> p_255790_.builtInRegistryHolder().key(), AllTheWizardGear.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        var builder = this.tag(ATWGTags.REMOVE_TRIM_ARMOR);
        for (var item : io.redspace.allthewizardgear.registry.ItemRegistry.items()) {
            if (item.get() instanceof ArmorItem) {
                builder.remove(item.get());
            }
        }

        this.tag(ATWGTags.BASE_WIZARD_BOOTS)
                .add(ItemRegistry.NETHERITE_MAGE_BOOTS.get())
                .add(ItemRegistry.ARCHEVOKER_BOOTS.get())
                .add(ItemRegistry.CRYOMANCER_BOOTS.get())
                .add(ItemRegistry.CULTIST_BOOTS.get())
                .add(ItemRegistry.PLAGUED_BOOTS.get())
                .add(ItemRegistry.PRIEST_BOOTS.get())
                .add(ItemRegistry.ELECTROMANCER_BOOTS.get())
                .add(ItemRegistry.PYROMANCER_BOOTS.get())
                .add(ItemRegistry.SHADOWWALKER_BOOTS.get());
        this.tag(ATWGTags.BASE_WIZARD_LEGGINGS)
                .add(ItemRegistry.NETHERITE_MAGE_LEGGINGS.get())
                .add(ItemRegistry.ARCHEVOKER_LEGGINGS.get())
                .add(ItemRegistry.CRYOMANCER_LEGGINGS.get())
                .add(ItemRegistry.CULTIST_LEGGINGS.get())
                .add(ItemRegistry.PLAGUED_LEGGINGS.get())
                .add(ItemRegistry.PRIEST_LEGGINGS.get())
                .add(ItemRegistry.ELECTROMANCER_LEGGINGS.get())
                .add(ItemRegistry.PYROMANCER_LEGGINGS.get())
                .add(ItemRegistry.SHADOWWALKER_LEGGINGS.get());
        this.tag(ATWGTags.BASE_WIZARD_CHESTPLATE)
                .add(ItemRegistry.NETHERITE_MAGE_CHESTPLATE.get())
                .add(ItemRegistry.ARCHEVOKER_CHESTPLATE.get())
                .add(ItemRegistry.CRYOMANCER_CHESTPLATE.get())
                .add(ItemRegistry.CULTIST_CHESTPLATE.get())
                .add(ItemRegistry.PLAGUED_CHESTPLATE.get())
                .add(ItemRegistry.PRIEST_CHESTPLATE.get())
                .add(ItemRegistry.ELECTROMANCER_CHESTPLATE.get())
                .add(ItemRegistry.PYROMANCER_CHESTPLATE.get())
                .add(ItemRegistry.SHADOWWALKER_CHESTPLATE.get());
        this.tag(ATWGTags.BASE_WIZARD_HELMET)
                .add(ItemRegistry.NETHERITE_MAGE_HELMET.get())
                .add(ItemRegistry.ARCHEVOKER_HELMET.get())
                .add(ItemRegistry.CRYOMANCER_HELMET.get())
                .add(ItemRegistry.CULTIST_HELMET.get())
                .add(ItemRegistry.PLAGUED_HELMET.get())
                .add(ItemRegistry.PRIEST_HELMET.get())
                .add(ItemRegistry.ELECTROMANCER_HELMET.get())
                .add(ItemRegistry.PYROMANCER_HELMET.get())
                .add(ItemRegistry.SHADOWWALKER_HELMET.get());

    }
}
