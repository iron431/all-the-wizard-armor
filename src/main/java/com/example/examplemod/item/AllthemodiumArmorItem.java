package com.example.examplemod.item;

import com.example.examplemod.client.armor.AllthemodiumArmorModel;
import io.redspace.ironsspellbooks.entity.armor.ElectromancerArmorModel;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class AllthemodiumArmorItem extends ExtendedArmorItem{
    public AllthemodiumArmorItem(Type type) {
        super(ExtendedArmorMaterials.ALLTHEMODIUM, type, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC));
    }

    @Override
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new AllthemodiumArmorModel() );
    }
}
