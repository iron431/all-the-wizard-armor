package io.redspace.allthewizardgear.events;

import io.redspace.allthewizardgear.ServerConfig;
import io.redspace.allthewizardgear.item.WizardArmorItem;
import io.redspace.allthewizardgear.registry.ItemRegistry;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.function.Function;
import java.util.function.Supplier;

@EventBusSubscriber
public class ServerEvents {

    @SubscribeEvent
    public static void onPlayerFall(LivingFallEvent event) {
        if (event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem() instanceof WizardArmorItem armorItem && armorItem.getConfig().bootsPreventFallDamage().get()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onEntityHurt(LivingIncomingDamageEvent event) {
        if (!event.getEntity().getCommandSenderWorld().isClientSide) {
            var damageSources = event.getEntity().damageSources();
            ItemStack helmet = event.getEntity().getItemBySlot(EquipmentSlot.HEAD);
            ItemStack chestplate = event.getEntity().getItemBySlot(EquipmentSlot.CHEST);
            ItemStack leggings = event.getEntity().getItemBySlot(EquipmentSlot.LEGS);
            if (event.getSource() == damageSources.flyIntoWall() && checkItem(helmet, ServerConfig.ArmorSetConfig::helmetPreventsElytraDamage)) {
                event.setCanceled(true);
            } else if (event.getSource() == damageSources.drown() && checkItem(helmet, ServerConfig.ArmorSetConfig::helmetPreventsDrowning)) {
                event.getEntity().setAirSupply(event.getEntity().getMaxAirSupply());
                event.setCanceled(true);
            } else if (event.getSource().is(DamageTypeTags.IS_FIRE) && checkItem(chestplate, ServerConfig.ArmorSetConfig::chestplatePreventsFire)) {
                event.getEntity().clearFire();
                event.setCanceled(true);
            } else if (event.getSource() == damageSources.dragonBreath() && checkItem(chestplate, ServerConfig.ArmorSetConfig::chestplatePreventsDragonBreath)) {
                event.setCanceled(true);
            } else if (event.getSource() == damageSources.wither() && checkItem(leggings, ServerConfig.ArmorSetConfig::leggingsPreventWither)) {
                event.getEntity().removeEffect(MobEffects.WITHER);
                event.setCanceled(true);
            } else if (checkItem(leggings, ServerConfig.ArmorSetConfig::leggingsPreventLevitation)) {
                event.getEntity().removeEffect(MobEffects.LEVITATION);
            }

        }
    }

    private static boolean checkItem(ItemStack stack, Function<ServerConfig.ArmorSetConfig, Supplier<? extends Boolean>> run) {
        return stack.getItem() instanceof WizardArmorItem armorItem && run.apply(armorItem.getConfig()).get();
    }
}
