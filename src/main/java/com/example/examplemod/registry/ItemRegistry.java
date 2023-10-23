package com.example.examplemod.registry;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.datagen.ItemModelDataGenerator;
import com.example.examplemod.item.AllthemodiumArmorItem;
import com.example.examplemod.item.UnobtainiumArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ItemRegistry {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


    public static final RegistryObject<Item> ALLTHEMODIUM_MAGE_HELMET = registerArmorPiece("allthemodium_mage_helmet", () -> new AllthemodiumArmorItem(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> ALLTHEMODIUM_MAGE_CHESTPLATE = registerArmorPiece("allthemodium_mage_chestplate", () -> new AllthemodiumArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> ALLTHEMODIUM_MAGE_LEGGINGS = registerArmorPiece("allthemodium_mage_leggings", () -> new AllthemodiumArmorItem(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> ALLTHEMODIUM_MAGE_BOOTS = registerArmorPiece("allthemodium_mage_boots", () -> new AllthemodiumArmorItem(ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> UNOBTAINIUM_MAGE_HELMET = registerArmorPiece("unobtainium_mage_helmet", () -> new UnobtainiumArmorItem(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> UNOBTAINIUM_MAGE_CHESTPLATE = registerArmorPiece("unobtainium_mage_chestplate", () -> new UnobtainiumArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> UNOBTAINIUM_MAGE_LEGGINGS = registerArmorPiece("unobtainium_mage_leggings", () -> new UnobtainiumArmorItem(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> UNOBTAINIUM_MAGE_BOOTS = registerArmorPiece("unobtainium_mage_boots", () -> new UnobtainiumArmorItem(ArmorItem.Type.BOOTS));

    private static RegistryObject<Item> registerArmorPiece(String name, Supplier<Item> supplier) {
        var s = ITEMS.register(name, supplier);
        ItemModelDataGenerator.toRegister.add(generator -> generator.simpleItem(s));
        return s;
    }
}
