package io.redspace.allthewizardgear.registry;

import io.redspace.allthewizardgear.AllTheWizardGear;
import io.redspace.allthewizardgear.StartupConfig;
import io.redspace.allthewizardgear.datagen.ItemModelDataGenerator;
import io.redspace.allthewizardgear.item.CooldownCastTimeSpellBook;
import io.redspace.allthewizardgear.item.WizardArmorItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;
import java.util.function.Supplier;

public class ItemRegistry {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, AllTheWizardGear.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static final DeferredHolder<Item, Item> ALLTHEMODIUM_MAGE_HELMET = generateItem("allthemodium_mage_helmet", () -> new WizardArmorItem(StartupConfig::allthemodium, ArmorItem.Type.HELMET));
    public static final DeferredHolder<Item, Item> ALLTHEMODIUM_MAGE_CHESTPLATE = generateItem("allthemodium_mage_chestplate", () -> new WizardArmorItem(StartupConfig::allthemodium, ArmorItem.Type.CHESTPLATE));
    public static final DeferredHolder<Item, Item> ALLTHEMODIUM_MAGE_LEGGINGS = generateItem("allthemodium_mage_leggings", () -> new WizardArmorItem(StartupConfig::allthemodium, ArmorItem.Type.LEGGINGS));
    public static final DeferredHolder<Item, Item> ALLTHEMODIUM_MAGE_BOOTS = generateItem("allthemodium_mage_boots", () -> new WizardArmorItem(StartupConfig::allthemodium, ArmorItem.Type.BOOTS));

    public static final DeferredHolder<Item, Item> VIBRANIUM_MAGE_HELMET = generateItem("vibranium_mage_helmet", () -> new WizardArmorItem(StartupConfig::vibranium, ArmorItem.Type.HELMET));
    public static final DeferredHolder<Item, Item> VIBRANIUM_MAGE_CHESTPLATE = generateItem("vibranium_mage_chestplate", () -> new WizardArmorItem(StartupConfig::vibranium, ArmorItem.Type.CHESTPLATE));
    public static final DeferredHolder<Item, Item> VIBRANIUM_MAGE_LEGGINGS = generateItem("vibranium_mage_leggings", () -> new WizardArmorItem(StartupConfig::vibranium, ArmorItem.Type.LEGGINGS));
    public static final DeferredHolder<Item, Item> VIBRANIUM_MAGE_BOOTS = generateItem("vibranium_mage_boots", () -> new WizardArmorItem(StartupConfig::vibranium, ArmorItem.Type.BOOTS));

    public static final DeferredHolder<Item, Item> UNOBTAINIUM_MAGE_HELMET = generateItem("unobtainium_mage_helmet", () -> new WizardArmorItem(StartupConfig::unobtainium, ArmorItem.Type.HELMET));
    public static final DeferredHolder<Item, Item> UNOBTAINIUM_MAGE_CHESTPLATE = generateItem("unobtainium_mage_chestplate", () -> new WizardArmorItem(StartupConfig::unobtainium, ArmorItem.Type.CHESTPLATE));
    public static final DeferredHolder<Item, Item> UNOBTAINIUM_MAGE_LEGGINGS = generateItem("unobtainium_mage_leggings", () -> new WizardArmorItem(StartupConfig::unobtainium, ArmorItem.Type.LEGGINGS));
    public static final DeferredHolder<Item, Item> UNOBTAINIUM_MAGE_BOOTS = generateItem("unobtainium_mage_boots", () -> new WizardArmorItem(StartupConfig::unobtainium, ArmorItem.Type.BOOTS));

    public static final DeferredHolder<Item, Item> ALLTHEMODIUM_SPELLBOOK = generateSpellBook("allthemodium_spell_book", () -> new CooldownCastTimeSpellBook(13, .3, .15));
    public static final DeferredHolder<Item, Item> VIBRANIUM_SPELLBOOK = generateSpellBook("vibranium_spell_book", () -> new CooldownCastTimeSpellBook(14, .4, .25));
    public static final DeferredHolder<Item, Item> UNOBTAINIUM_SPELLBOOK = generateSpellBook("unobtainium_spell_book", () -> new CooldownCastTimeSpellBook(15, .5, .35));

    private static DeferredHolder<Item, Item> generateItem(String name, Supplier<Item> supplier) {
        var s = ITEMS.register(name, supplier);
        ItemModelDataGenerator.toRegister.add(generator -> generator.simpleItem(s));
        return s;
    }

    private static DeferredHolder<Item, Item> generateSpellBook(String name, Supplier<Item> supplier) {
        var s = ITEMS.register(name, supplier);
        //ItemModelDataGenerator.toRegister.add(generator -> generator.spellBookItem(s));
        return s;
    }

    public static Collection<DeferredHolder<Item, ? extends Item>> items() {
        return ITEMS.getEntries();
    }
}
