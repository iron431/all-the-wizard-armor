package io.redspace.allthewizardgear.registry;

import io.redspace.allthewizardgear.AllTheWizardGear;
import io.redspace.allthewizardgear.datagen.ItemModelDataGenerator;
import io.redspace.allthewizardgear.item.CooldownCastTimeSpellBook;
import io.redspace.allthewizardgear.item.WizardArmorItem;
import io.redspace.allthewizardgear.item.ExtendedArmorMaterials;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.item.spell_books.SimpleAttributeSpellBook;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
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

    public static final RegistryObject<Item> ALLTHEMODIUM_MAGE_HELMET = generateItem("allthemodium_mage_helmet", () -> new WizardArmorItem(ExtendedArmorMaterials.ALLTHEMODIUM, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> ALLTHEMODIUM_MAGE_CHESTPLATE = generateItem("allthemodium_mage_chestplate", () -> new WizardArmorItem(ExtendedArmorMaterials.ALLTHEMODIUM, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> ALLTHEMODIUM_MAGE_LEGGINGS = generateItem("allthemodium_mage_leggings", () -> new WizardArmorItem(ExtendedArmorMaterials.ALLTHEMODIUM, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> ALLTHEMODIUM_MAGE_BOOTS = generateItem("allthemodium_mage_boots", () -> new WizardArmorItem(ExtendedArmorMaterials.ALLTHEMODIUM, ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> VIBRANIUM_MAGE_HELMET = generateItem("vibranium_mage_helmet", () -> new WizardArmorItem(ExtendedArmorMaterials.VIBRANIUM, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> VIBRANIUM_MAGE_CHESTPLATE = generateItem("vibranium_mage_chestplate", () -> new WizardArmorItem(ExtendedArmorMaterials.VIBRANIUM, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> VIBRANIUM_MAGE_LEGGINGS = generateItem("vibranium_mage_leggings", () -> new WizardArmorItem(ExtendedArmorMaterials.VIBRANIUM, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> VIBRANIUM_MAGE_BOOTS = generateItem("vibranium_mage_boots", () -> new WizardArmorItem(ExtendedArmorMaterials.VIBRANIUM, ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> UNOBTAINIUM_MAGE_HELMET = generateItem("unobtainium_mage_helmet", () -> new WizardArmorItem(ExtendedArmorMaterials.UNOBTAINIUM, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> UNOBTAINIUM_MAGE_CHESTPLATE = generateItem("unobtainium_mage_chestplate", () -> new WizardArmorItem(ExtendedArmorMaterials.UNOBTAINIUM, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> UNOBTAINIUM_MAGE_LEGGINGS = generateItem("unobtainium_mage_leggings", () -> new WizardArmorItem(ExtendedArmorMaterials.UNOBTAINIUM, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> UNOBTAINIUM_MAGE_BOOTS = generateItem("unobtainium_mage_boots", () -> new WizardArmorItem(ExtendedArmorMaterials.UNOBTAINIUM, ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> ALLTHEMODIUM_SPELLBOOK = generateSpellBook("allthemodium_spell_book", () -> new CooldownCastTimeSpellBook(13, .3, .15));
    public static final RegistryObject<Item> VIBRANIUM_SPELLBOOK = generateSpellBook("vibranium_spell_book", () -> new CooldownCastTimeSpellBook(14, .4, .25));
    public static final RegistryObject<Item> UNOBTAINIUM_SPELLBOOK = generateSpellBook("unobtainium_spell_book", () -> new CooldownCastTimeSpellBook(15, .5, .35));

    private static RegistryObject<Item> generateItem(String name, Supplier<Item> supplier) {
        var s = ITEMS.register(name, supplier);
        ItemModelDataGenerator.toRegister.add(generator -> generator.simpleItem(s));
        return s;
    }

    private static RegistryObject<Item> generateSpellBook(String name, Supplier<Item> supplier) {
        var s = ITEMS.register(name, supplier);
        //ItemModelDataGenerator.toRegister.add(generator -> generator.spellBookItem(s));
        return s;
    }

    public static Collection<RegistryObject<Item>> items() {
        return ITEMS.getEntries();
    }
}
