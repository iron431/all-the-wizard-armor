package io.redspace.allthewizardgear.registry;

import io.redspace.allthewizardgear.AllTheWizardGear;
import io.redspace.allthewizardgear.datagen.ItemModelDataGenerator;
import io.redspace.allthewizardgear.item.CooldownCastTimeSpellBook;
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


    public static final RegistryObject<Item> ALLTHEMODIUM_MAGE_HELMET = generateItem("allthemodium_mage_helmet", () -> new ExtendedArmorItem(ExtendedArmorMaterials.ALLTHEMODIUM, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> ALLTHEMODIUM_MAGE_CHESTPLATE = generateItem("allthemodium_mage_chestplate", () -> new ExtendedArmorItem(ExtendedArmorMaterials.ALLTHEMODIUM, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> ALLTHEMODIUM_MAGE_LEGGINGS = generateItem("allthemodium_mage_leggings", () -> new ExtendedArmorItem(ExtendedArmorMaterials.ALLTHEMODIUM, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> ALLTHEMODIUM_MAGE_BOOTS = generateItem("allthemodium_mage_boots", () -> new ExtendedArmorItem(ExtendedArmorMaterials.ALLTHEMODIUM, ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> VIBRANIUM_MAGE_HELMET = generateItem("vibranium_mage_helmet", () -> new ExtendedArmorItem(ExtendedArmorMaterials.VIBRANIUM, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> VIBRANIUM_MAGE_CHESTPLATE = generateItem("vibranium_mage_chestplate", () -> new ExtendedArmorItem(ExtendedArmorMaterials.VIBRANIUM, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> VIBRANIUM_MAGE_LEGGINGS = generateItem("vibranium_mage_leggings", () -> new ExtendedArmorItem(ExtendedArmorMaterials.VIBRANIUM, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> VIBRANIUM_MAGE_BOOTS = generateItem("vibranium_mage_boots", () -> new ExtendedArmorItem(ExtendedArmorMaterials.VIBRANIUM, ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> UNOBTAINIUM_MAGE_HELMET = generateItem("unobtainium_mage_helmet", () -> new ExtendedArmorItem(ExtendedArmorMaterials.UNOBTAINIUM, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> UNOBTAINIUM_MAGE_CHESTPLATE = generateItem("unobtainium_mage_chestplate", () -> new ExtendedArmorItem(ExtendedArmorMaterials.UNOBTAINIUM, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> UNOBTAINIUM_MAGE_LEGGINGS = generateItem("unobtainium_mage_leggings", () -> new ExtendedArmorItem(ExtendedArmorMaterials.UNOBTAINIUM, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> UNOBTAINIUM_MAGE_BOOTS = generateItem("unobtainium_mage_boots", () -> new ExtendedArmorItem(ExtendedArmorMaterials.UNOBTAINIUM, ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> ALLTHEMODIUM_SPELLBOOK = generateItem("allthemodium_spell_book", () -> new CooldownCastTimeSpellBook(11, .3, .15));
    public static final RegistryObject<Item> VIBRANIUM_SPELLBOOK = generateItem("vibranium_spell_book", () -> new CooldownCastTimeSpellBook(12, .4, .25));
    public static final RegistryObject<Item> UNOBTAINIUM_SPELLBOOK = generateItem("unobtainium_spell_book", () -> new CooldownCastTimeSpellBook(13, .5, .35));

    private static RegistryObject<Item> generateItem(String name, Supplier<Item> supplier) {
        var s = ITEMS.register(name, supplier);
        ItemModelDataGenerator.toRegister.add(generator -> generator.simpleItem(s));
        return s;
    }

    public static Collection<RegistryObject<Item>> items() {
        return ITEMS.getEntries();
    }
}
