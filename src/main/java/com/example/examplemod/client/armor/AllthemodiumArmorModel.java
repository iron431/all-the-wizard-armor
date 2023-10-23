package com.example.examplemod.client.armor;

import com.example.examplemod.item.AllthemodiumArmorItem;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.item.armor.ElectromancerArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class AllthemodiumArmorModel extends DefaultedItemGeoModel<AllthemodiumArmorItem> {

    public AllthemodiumArmorModel() {
        super(new ResourceLocation(IronsSpellbooks.MODID, ""));
    }

    @Override
    public ResourceLocation getModelResource(AllthemodiumArmorItem object) {
        return new ResourceLocation(IronsSpellbooks.MODID, "geo/electromancer_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AllthemodiumArmorItem object) {
        return new ResourceLocation(IronsSpellbooks.MODID, "textures/models/armor/electromancer.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AllthemodiumArmorItem animatable) {
        return new ResourceLocation(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}