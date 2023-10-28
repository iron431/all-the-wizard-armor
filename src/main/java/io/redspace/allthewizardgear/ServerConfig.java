package io.redspace.allthewizardgear;

import io.redspace.allthewizardgear.item.ExtendedArmorMaterials;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = AllTheWizardGear.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ServerConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    static ForgeConfigSpec SPEC;

    public static ArmorSetConfig ALLTHEMODIUM_CONFIG;
    public static ArmorSetConfig VIBRANIUM_CONFIG;
    public static ArmorSetConfig UNOBTAINIUM_CONFIG;

    static {
        BUILDER.push("ArmorConfig");
        BUILDER.comment("Changing armor values requires world restart");

        ALLTHEMODIUM_CONFIG = defineConfig(BUILDER, "allthemodium",
                List.of(4, 7, 9, 4),
                4,
                0,
                200,
                .20,
                .05);
        VIBRANIUM_CONFIG = defineConfig(BUILDER, "vibranium",
                List.of(6, 9, 11, 6),
                5,
                0,
                325,
                .30,
                .10);
        UNOBTAINIUM_CONFIG = defineConfig(BUILDER, "unobtainium",
                List.of(8, 11, 13, 8),
                6,
                0,
                450,
                .40,
                .15);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    private static ArmorSetConfig defineConfig(ForgeConfigSpec.Builder builder, String name, List<Integer> defenseValues, int toughness, double knockbackResistance, int maxMana, double spellPower, double manaRegen) {
        builder.push(name);
        String localizedName = name.substring(0, 1).toUpperCase() + name.substring(1) + "'s ";
        var config = new ArmorSetConfig(
                builder.worldRestart().comment(localizedName + "Armor Values, in the form of [boots, leggings, chestplate, helmet]. Default: " + defenseValues).defineList("armorValues", () -> defenseValues, (x) -> true),
                builder.worldRestart().comment(localizedName + "Armor Toughness. Default: " + toughness).define("toughness", toughness),
                builder.worldRestart().comment(localizedName + "Knockback Resistance. Default: " + knockbackResistance).define("knockbackResistance", knockbackResistance),
                builder.worldRestart().comment(localizedName + "Max Mana. Default: " + maxMana).define("maxMana", maxMana),
                builder.worldRestart().comment(localizedName + String.format("Spell Power. Default: %s (+%s%%)", spellPower, (int) (spellPower * 100))).define("spellPower", spellPower),
                builder.worldRestart().comment(localizedName + String.format("Mana Regen. Default: %s (+%s%%)", manaRegen, (int) (manaRegen * 100))).define("manaRegen", manaRegen)
        );
        builder.pop();
        return config;
    }

    public static record ArmorSetConfig(
            ForgeConfigSpec.ConfigValue<List<? extends Integer>> defenseValues,
            ForgeConfigSpec.ConfigValue<? extends Integer> toughness,
            ForgeConfigSpec.ConfigValue<? extends Double> knockbackResistance,
            ForgeConfigSpec.ConfigValue<? extends Integer> maxMana,
            ForgeConfigSpec.ConfigValue<? extends Double> spellPower,
            ForgeConfigSpec.ConfigValue<? extends Double> manaRegen
    ) {
        public double getDefenseFor(EquipmentSlot slot) {
            if (defenseValues.get().size() != 4) {
                return defenseValues.getDefault().get(slot.getIndex());
            } else {
                return defenseValues.get().get(slot.getIndex());
            }
        }
    }

    @SubscribeEvent
    public static void onReload(ModConfigEvent.Reloading event) {
        for (ExtendedArmorMaterials value : ExtendedArmorMaterials.values()) {
            value.reload();
        }
    }
}
