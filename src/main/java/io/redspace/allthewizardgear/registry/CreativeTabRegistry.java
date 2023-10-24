package io.redspace.allthewizardgear.registry;

import io.redspace.allthewizardgear.AllTheWizardGear;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AllTheWizardGear.MODID);

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static final RegistryObject<CreativeModeTab> MOD_ARMOR_TAB = CREATIVE_MODE_TABS.register("allthewizardarmor_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.translatable("tab.allthewizardarmor.armor"))
            .icon(() -> ItemRegistry.UNOBTAINIUM_MAGE_CHESTPLATE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ItemRegistry.ALLTHEMODIUM_MAGE_HELMET.get());
                output.accept(ItemRegistry.ALLTHEMODIUM_MAGE_CHESTPLATE.get());
                output.accept(ItemRegistry.ALLTHEMODIUM_MAGE_LEGGINGS.get());
                output.accept(ItemRegistry.ALLTHEMODIUM_MAGE_BOOTS.get());

                output.accept(ItemRegistry.VIBRANIUM_MAGE_HELMET.get());
                output.accept(ItemRegistry.VIBRANIUM_MAGE_CHESTPLATE.get());
                output.accept(ItemRegistry.VIBRANIUM_MAGE_LEGGINGS.get());
                output.accept(ItemRegistry.VIBRANIUM_MAGE_BOOTS.get());

                output.accept(ItemRegistry.UNOBTAINIUM_MAGE_HELMET.get());
                output.accept(ItemRegistry.UNOBTAINIUM_MAGE_CHESTPLATE.get());
                output.accept(ItemRegistry.UNOBTAINIUM_MAGE_LEGGINGS.get());
                output.accept(ItemRegistry.UNOBTAINIUM_MAGE_BOOTS.get());
            }).build());

}
