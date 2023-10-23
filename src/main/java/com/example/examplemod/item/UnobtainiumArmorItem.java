package com.example.examplemod.item;

import com.example.examplemod.client.armor.AllthemodiumArmorModel;
import com.example.examplemod.client.armor.GenericArmorModel;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import net.minecraft.world.item.Rarity;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class UnobtainiumArmorItem extends ExtendedArmorItem{
    public UnobtainiumArmorItem(Type type) {
        super(ExtendedArmorMaterials.UNOBTAINIUM, type, new Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC));
    }

    @Override
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new GenericArmorModel<UnobtainiumArmorItem>());
    }
}
