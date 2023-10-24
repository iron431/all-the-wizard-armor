package io.redspace.allthewizardgear.registry;

import io.redspace.allthewizardgear.AllTheWizardGear;
import io.redspace.allthewizardgear.datagen.ModItemModelDataGenerator;
import io.redspace.allthewizardgear.item.ExtendedArmorItem;
import io.redspace.allthewizardgear.item.ExtendedArmorMaterials;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.function.Supplier;

public class ItemRegistry {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AllTheWizardGear.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


    public static final RegistryObject<Item> ALLTHEMODIUM_MAGE_HELMET = registerArmorPiece("allthemodium_mage_helmet", () -> new ExtendedArmorItem(ExtendedArmorMaterials.ALLTHEMODIUM, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> ALLTHEMODIUM_MAGE_CHESTPLATE = registerArmorPiece("allthemodium_mage_chestplate", () -> new ExtendedArmorItem(ExtendedArmorMaterials.ALLTHEMODIUM, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> ALLTHEMODIUM_MAGE_LEGGINGS = registerArmorPiece("allthemodium_mage_leggings", () -> new ExtendedArmorItem(ExtendedArmorMaterials.ALLTHEMODIUM, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> ALLTHEMODIUM_MAGE_BOOTS = registerArmorPiece("allthemodium_mage_boots", () -> new ExtendedArmorItem(ExtendedArmorMaterials.ALLTHEMODIUM, ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> VIBRANIUM_MAGE_HELMET = registerArmorPiece("vibranium_mage_helmet", () -> new ExtendedArmorItem(ExtendedArmorMaterials.VIBRANIUM, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> VIBRANIUM_MAGE_CHESTPLATE = registerArmorPiece("vibranium_mage_chestplate", () -> new ExtendedArmorItem(ExtendedArmorMaterials.VIBRANIUM, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> VIBRANIUM_MAGE_LEGGINGS = registerArmorPiece("vibranium_mage_leggings", () -> new ExtendedArmorItem(ExtendedArmorMaterials.VIBRANIUM, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> VIBRANIUM_MAGE_BOOTS = registerArmorPiece("vibranium_mage_boots", () -> new ExtendedArmorItem(ExtendedArmorMaterials.VIBRANIUM, ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> UNOBTAINIUM_MAGE_HELMET = registerArmorPiece("unobtainium_mage_helmet", () -> new ExtendedArmorItem(ExtendedArmorMaterials.UNOBTAINIUM, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> UNOBTAINIUM_MAGE_CHESTPLATE = registerArmorPiece("unobtainium_mage_chestplate", () -> new ExtendedArmorItem(ExtendedArmorMaterials.UNOBTAINIUM, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> UNOBTAINIUM_MAGE_LEGGINGS = registerArmorPiece("unobtainium_mage_leggings", () -> new ExtendedArmorItem(ExtendedArmorMaterials.UNOBTAINIUM, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> UNOBTAINIUM_MAGE_BOOTS = registerArmorPiece("unobtainium_mage_boots", () -> new ExtendedArmorItem(ExtendedArmorMaterials.UNOBTAINIUM, ArmorItem.Type.BOOTS));

    private static RegistryObject<Item> registerArmorPiece(String name, Supplier<Item> supplier) {
        var s = ITEMS.register(name, supplier);
        ModItemModelDataGenerator.toRegister.add(generator -> generator.simpleItem(s));
        return s;
    }

    public static Collection<RegistryObject<Item>> items(){
        return ITEMS.getEntries();
    }
}
