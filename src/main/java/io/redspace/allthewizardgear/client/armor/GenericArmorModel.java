package io.redspace.allthewizardgear.client.armor;

import io.redspace.allthewizardgear.AllTheWizardGear;
import io.redspace.allthewizardgear.item.WizardArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class GenericArmorModel<T extends WizardArmorItem> extends DefaultedItemGeoModel<T> {

    public GenericArmorModel() {
        super(new ResourceLocation(AllTheWizardGear.MODID, ""));
    }

    @Override
    public ResourceLocation getModelResource(T object) {
        var identifier = object.getMaterial().getName();
        return new ResourceLocation(AllTheWizardGear.MODID, "geo/" + identifier + "_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        var identifier = object.getMaterial().getName();
        return new ResourceLocation(AllTheWizardGear.MODID, "textures/models/armor/" + identifier + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return new ResourceLocation(AllTheWizardGear.MODID, "animations/default_armor.json");
    }
}