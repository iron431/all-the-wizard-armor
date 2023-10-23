package com.example.examplemod.client.armor;

import com.example.examplemod.item.AllthemodiumArmorItem;
import com.example.examplemod.item.ExtendedArmorItem;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class GenericArmorModel<T extends ExtendedArmorItem> extends DefaultedItemGeoModel<T> {

    //TODO: use proper namespace and use the identifier of the armor item's material
    public GenericArmorModel() {
        super(new ResourceLocation(IronsSpellbooks.MODID, ""));
    }

    @Override
    public ResourceLocation getModelResource(T object) {
        var identifier = "electromancer";//object.getMaterial().getName();
        return new ResourceLocation(IronsSpellbooks.MODID, "geo/" + identifier + "_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        var identifier = "electromancer";//object.getMaterial().getName();
        return new ResourceLocation(IronsSpellbooks.MODID, "textures/models/armor/" + identifier + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return new ResourceLocation(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}