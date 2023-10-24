package com.example.examplemod.client.armor;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.item.ExtendedArmorItem;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class GenericArmorModel<T extends ExtendedArmorItem> extends DefaultedItemGeoModel<T> {

    public GenericArmorModel() {
        super(new ResourceLocation(IronsSpellbooks.MODID, ""));
    }

    @Override
    public ResourceLocation getModelResource(T object) {
        var identifier = object.getMaterial().getName();
        return new ResourceLocation(ExampleMod.MODID, "geo/" + identifier + "_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        var identifier = object.getMaterial().getName();
        return new ResourceLocation(ExampleMod.MODID, "textures/models/armor/" + identifier + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return new ResourceLocation(ExampleMod.MODID, "animations/default_armor.json");
    }
}