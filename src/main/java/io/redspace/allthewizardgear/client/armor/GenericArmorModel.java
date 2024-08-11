package io.redspace.allthewizardgear.client.armor;

import io.redspace.allthewizardgear.AllTheWizardGear;
import io.redspace.allthewizardgear.item.WizardArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class GenericArmorModel<T extends WizardArmorItem> extends DefaultedItemGeoModel<T> {

    final String name;
    public GenericArmorModel(String name) {
        super(ResourceLocation.fromNamespaceAndPath(AllTheWizardGear.MODID, ""));
        this.name = name;
    }

    @Override
    public ResourceLocation getModelResource(T object) {
        return ResourceLocation.fromNamespaceAndPath(AllTheWizardGear.MODID, "geo/" + name + "_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return ResourceLocation.fromNamespaceAndPath(AllTheWizardGear.MODID, "textures/models/armor/" + name + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return ResourceLocation.fromNamespaceAndPath(AllTheWizardGear.MODID, "animations/default_armor.json");
    }
}