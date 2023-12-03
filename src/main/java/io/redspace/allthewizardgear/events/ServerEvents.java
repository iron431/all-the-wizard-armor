package io.redspace.allthewizardgear.events;

import io.redspace.allthewizardgear.item.ExtendedArmorMaterials;
import io.redspace.allthewizardgear.item.WizardArmorItem;
import io.redspace.allthewizardgear.registry.ItemRegistry;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Function;

@Mod.EventBusSubscriber
public class ServerEvents {

    @SubscribeEvent
    public static void onPlayerFall(LivingFallEvent event) {
        if (event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem() instanceof WizardArmorItem armorItem && armorItem.getMaterial().bootsPreventFallDamage()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onEntityHurt(LivingAttackEvent event) {
        if (!event.getEntity().getCommandSenderWorld().isClientSide) {
            var damageSources = event.getEntity().damageSources();
            ItemStack helmet = event.getEntity().getItemBySlot(EquipmentSlot.HEAD);
            ItemStack chestplate = event.getEntity().getItemBySlot(EquipmentSlot.CHEST);
            ItemStack leggings = event.getEntity().getItemBySlot(EquipmentSlot.LEGS);
            if (event.getSource() == damageSources.flyIntoWall() && checkItem(helmet, ExtendedArmorMaterials::helmetPreventsElytraDamage)) {
                event.setCanceled(true);
            } else if (event.getSource() == damageSources.drown() && checkItem(helmet, ExtendedArmorMaterials::helmetPreventsDrowning)) {
                event.getEntity().setAirSupply(event.getEntity().getMaxAirSupply());
                event.setCanceled(true);
            } else if (event.getSource().is(DamageTypeTags.IS_FIRE) && checkItem(chestplate, ExtendedArmorMaterials::chestplatePreventsFire)) {
                event.getEntity().clearFire();
                event.setCanceled(true);
            } else if (event.getSource() == damageSources.dragonBreath() && checkItem(chestplate, ExtendedArmorMaterials::chestplatePreventsDragonBreath)) {
                event.setCanceled(true);
            } else if (event.getSource() == damageSources.wither() && checkItem(leggings, ExtendedArmorMaterials::leggingsPreventWither)) {
                event.getEntity().removeEffect(MobEffects.WITHER);
                event.setCanceled(true);
            } else if (checkItem(leggings, ExtendedArmorMaterials::leggingsPreventLevitation)) {
                event.getEntity().removeEffect(MobEffects.LEVITATION);
            }

        }
    }

    private static boolean checkItem(ItemStack stack, Function<ExtendedArmorMaterials, Boolean> run) {
        return stack.getItem() instanceof WizardArmorItem armorItem ? run.apply(armorItem.getMaterial()) : false;
    }
}
