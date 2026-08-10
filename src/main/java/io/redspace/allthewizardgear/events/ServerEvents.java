package io.redspace.allthewizardgear.events;

import io.redspace.allthewizardgear.StartupConfig;
import io.redspace.allthewizardgear.item.WizardArmorItem;
import io.redspace.allthewizardgear.network.SyncArmorConfigPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.function.Predicate;

@EventBusSubscriber
public class ServerEvents {

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            PacketDistributor.sendToPlayer(player, SyncArmorConfigPacket.create());
        }
    }

    @SubscribeEvent
    public static void onPlayerFall(LivingFallEvent event) {
        if (event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem() instanceof WizardArmorItem armorItem && armorItem.getConfig().bootsPreventFallDamage()) {
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
            if (event.getSource() == damageSources.flyIntoWall() && checkItem(helmet, StartupConfig.ArmorSetData::helmetPreventsElytraDamage)) {
                event.setCanceled(true);
            } else if (event.getSource() == damageSources.drown() && checkItem(helmet, StartupConfig.ArmorSetData::helmetPreventsDrowning)) {
                event.getEntity().setAirSupply(event.getEntity().getMaxAirSupply());
                event.setCanceled(true);
            } else if (event.getSource().is(DamageTypeTags.IS_FIRE) && checkItem(chestplate, StartupConfig.ArmorSetData::chestplatePreventsFire)) {
                event.getEntity().clearFire();
                event.setCanceled(true);
            } else if (event.getSource() == damageSources.dragonBreath() && checkItem(chestplate, StartupConfig.ArmorSetData::chestplatePreventsDragonBreath)) {
                event.setCanceled(true);
            } else if (event.getSource() == damageSources.wither() && checkItem(leggings, StartupConfig.ArmorSetData::leggingsPreventWither)) {
                event.getEntity().removeEffect(MobEffects.WITHER);
                event.setCanceled(true);
            } else if (checkItem(leggings, StartupConfig.ArmorSetData::leggingsPreventLevitation)) {
                event.getEntity().removeEffect(MobEffects.LEVITATION);
            }

        }
    }

    private static boolean checkItem(ItemStack stack, Predicate<StartupConfig.ArmorSetData> run) {
        return stack.getItem() instanceof WizardArmorItem armorItem && run.test(armorItem.getConfig());
    }
}
