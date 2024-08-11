package io.redspace.allthewizardgear.registry;

import io.redspace.allthewizardgear.AllTheWizardGear;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CreativeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AllTheWizardGear.MODID);

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MOD_ARMOR_TAB = CREATIVE_MODE_TABS.register("allthewizardarmor_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("tab.allthewizardarmor.armor"))
            .icon(() -> ItemRegistry.UNOBTAINIUM_MAGE_CHESTPLATE.get().getDefaultInstance())
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS).build());

    public static void fillCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == MOD_ARMOR_TAB.getKey()) {
            ItemRegistry.items().stream().map(DeferredHolder::get).forEach(event::accept);
        }
    }
}
