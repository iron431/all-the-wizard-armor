package io.redspace.allthewizardgear.registry;

import io.redspace.allthewizardgear.AllTheWizardGear;
import io.redspace.allthewizardgear.item.ExtendedArmorMaterials;
import io.redspace.allthewizardgear.item.WizardArmorItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber
public class CreativeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AllTheWizardGear.MODID);

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static final RegistryObject<CreativeModeTab> MOD_ARMOR_TAB = CREATIVE_MODE_TABS.register("allthewizardarmor_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("tab.allthewizardarmor.armor"))
            .icon(() -> ItemRegistry.NETHERITE_MAGE_HELMET.get().getDefaultInstance())
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .displayItems((parameters, output) -> {
            }).build());

    @SubscribeEvent
    public static void fillCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == MOD_ARMOR_TAB.getKey()) {
            ItemRegistry.items().forEach(item -> {
                if (item.get() instanceof WizardArmorItem armorItem && armorItem.getMaterial() == ExtendedArmorMaterials.NETHERITE) {
                    event.accept(item);
                }
            });
        }
    }
}
