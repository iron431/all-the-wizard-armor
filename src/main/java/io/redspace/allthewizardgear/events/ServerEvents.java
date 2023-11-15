package io.redspace.allthewizardgear.events;

import io.redspace.allthewizardgear.registry.ItemRegistry;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ServerEvents {

    @SubscribeEvent
    public static void onPlayerFall(LivingFallEvent event) {
        for (ItemStack armor : event.getEntity().getArmorSlots()) {
            if (armor.is(ItemRegistry.ALLTHEMODIUM_MAGE_BOOTS.get())
                    || armor.is(ItemRegistry.VIBRANIUM_MAGE_BOOTS.get())
                    || armor.is(ItemRegistry.UNOBTAINIUM_MAGE_BOOTS.get())) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityHurt(LivingAttackEvent event) {
        if (!event.getEntity().getCommandSenderWorld().isClientSide) {
            var damageSources = event.getEntity().damageSources();
            for (ItemStack armor : event.getEntity().getArmorSlots()) {
                if (armor.is(ItemRegistry.ALLTHEMODIUM_MAGE_CHESTPLATE.get())
                        || armor.is(ItemRegistry.VIBRANIUM_MAGE_CHESTPLATE.get())
                        || armor.is(ItemRegistry.UNOBTAINIUM_MAGE_CHESTPLATE.get())) {
                    if (event.getSource() == damageSources.dragonBreath() && armor.is(ItemRegistry.UNOBTAINIUM_MAGE_CHESTPLATE.get())) {
                        event.setCanceled(true);
                    } else if (event.getSource().is(DamageTypeTags.IS_FIRE)) {
                        event.getEntity().clearFire();
                        event.setCanceled(true);
                    }
                } else if (armor.is(ItemRegistry.VIBRANIUM_MAGE_LEGGINGS.get()) || armor.is(ItemRegistry.UNOBTAINIUM_MAGE_LEGGINGS.get())) {
                    if (event.getSource() == damageSources.wither()) {
                        event.getEntity().removeEffect(MobEffects.WITHER);
                        event.setCanceled(true);
                    }
                    if (!event.isCanceled() && armor.is(ItemRegistry.UNOBTAINIUM_MAGE_LEGGINGS.get())) {
                        event.getEntity().removeEffect(MobEffects.LEVITATION);
                    }
                }

                if (armor.is(ItemRegistry.ALLTHEMODIUM_MAGE_HELMET.get())
                        || armor.is(ItemRegistry.VIBRANIUM_MAGE_HELMET.get())
                        || armor.is(ItemRegistry.UNOBTAINIUM_MAGE_HELMET.get())) {
                    if (event.getSource() == damageSources.flyIntoWall()) {
                        event.setCanceled(true);
                    }
                    if (event.getSource() == damageSources.drown()) {
                        event.getEntity().setAirSupply(event.getEntity().getMaxAirSupply());
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}
