package io.redspace.allthewizardgear.datagen;

import io.redspace.allthewizardgear.AllTheWizardGear;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.SeparateTransformsModel;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ItemModelDataGenerator extends ItemModelProvider {

    public static List<Consumer<ItemModelDataGenerator>> toRegister = new ArrayList<>();

    public ItemModelDataGenerator(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AllTheWizardGear.MODID, exFileHelper);
    }

    @Override
    protected void registerModels() {
        toRegister.forEach(c -> c.accept(this));
    }

    public ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AllTheWizardGear.MODID, "block/" + item.getId().getPath()));
    }

    public ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AllTheWizardGear.MODID, "item/" + item.getId().getPath()));
    }

    public ItemModelBuilder spellBookItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation(AllTheWizardGear.MODID, "item/template_spell_book")).texture("layer0",
                new ResourceLocation(AllTheWizardGear.MODID, "item/" + item.getId().getPath())).texture("book",
                new ResourceLocation(AllTheWizardGear.MODID, "item/model/" + item.getId().getPath()));
    }

    public ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(AllTheWizardGear.MODID, "item/" + item.getId().getPath()));
    }
}
