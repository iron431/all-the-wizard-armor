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
                .05,
                true,
                true,
                true,
                false,
                false,
                false,
                true);
        VIBRANIUM_CONFIG = defineConfig(BUILDER, "vibranium",
                List.of(6, 9, 11, 6),
                5,
                0,
                325,
                .30,
                .10,
                true,
                true,
                true,
                false,
                true,
                false,
                true);
        UNOBTAINIUM_CONFIG = defineConfig(BUILDER, "unobtainium",
                List.of(8, 11, 13, 8),
                6,
                0,
                450,
                .40,
                .15,
                true,
                true,
                true,
                true,
                true,
                true,
                true);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    private static ArmorSetConfig defineConfig(ForgeConfigSpec.Builder builder, String name, List<Integer> defenseValues, int toughness, double knockbackResistance, int maxMana, double spellPower, double manaRegen,
                                               boolean helmetPreventsDrowning,
                                               boolean helmetPreventsElytraDamage,
                                               boolean chestplatePreventsFire,
                                               boolean chestplatePreventsDragonBreath,
                                               boolean leggingsPreventWither,
                                               boolean leggingsPreventLevitation,
                                               boolean bootsPreventFallDamage) {
        builder.push(name);
        String localizedName = name.substring(0, 1).toUpperCase() + name.substring(1) + "'s ";
        var config = new ArmorSetConfig(
                builder.worldRestart().comment(localizedName + "Armor Values, in the form of [boots, leggings, chestplate, helmet]. Default: " + defenseValues).defineList("armorValues", () -> defenseValues, (x) -> true),
                builder.worldRestart().comment(localizedName + "Armor Toughness. Default: " + toughness).define("toughness", toughness),
                builder.worldRestart().comment(localizedName + "Knockback Resistance. Default: " + knockbackResistance).define("knockbackResistance", knockbackResistance),
                builder.worldRestart().comment(localizedName + "Max Mana. Default: " + maxMana).define("maxMana", maxMana),
                builder.worldRestart().comment(localizedName + String.format("Spell Power. Default: %s (+%s%%)", spellPower, (int) (spellPower * 100))).define("spellPower", spellPower),
                builder.worldRestart().comment(localizedName + String.format("Mana Regen. Default: %s", manaRegen)).define("manaRegen", manaRegen),
                builder.worldRestart().comment(localizedName + String.format("Helmet Prevents Drowning. Default: %s", helmetPreventsDrowning)).define("helmetPreventsDrowning", helmetPreventsDrowning),
                builder.worldRestart().comment(localizedName + String.format("Helmet Prevents Elytra Damage. Default: %s", helmetPreventsElytraDamage)).define("helmetPreventsElytraDamage", helmetPreventsElytraDamage),
                builder.worldRestart().comment(localizedName + String.format("Chestplate Prevents Fire Damage. Default: %s", chestplatePreventsFire)).define("chestplatePreventsFire", chestplatePreventsFire),
                builder.worldRestart().comment(localizedName + String.format("Chestplate Prevents Dragon Breath. Default: %s", chestplatePreventsDragonBreath)).define("chestplatePreventsDragonBreath", chestplatePreventsDragonBreath),
                builder.worldRestart().comment(localizedName + String.format("Leggings Prevent Wither. Default: %s", leggingsPreventWither)).define("leggingsPreventWither", leggingsPreventWither),
                builder.worldRestart().comment(localizedName + String.format("Leggings Prevent Levitation. Default: %s", leggingsPreventLevitation)).define("leggingsPreventLevitation", leggingsPreventLevitation),
                builder.worldRestart().comment(localizedName + String.format("Boots Prevent Fall Damage. Default: %s", bootsPreventFallDamage)).define("bootsPreventFallDamage", bootsPreventFallDamage)
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
            ForgeConfigSpec.ConfigValue<? extends Double> manaRegen,
            ForgeConfigSpec.ConfigValue<? extends Boolean> helmetPreventsDrowning,
            ForgeConfigSpec.ConfigValue<? extends Boolean> helmetPreventsElytraDamage,
            ForgeConfigSpec.ConfigValue<? extends Boolean> chestplatePreventsFire,
            ForgeConfigSpec.ConfigValue<? extends Boolean> chestplatePreventsDragonBreath,
            ForgeConfigSpec.ConfigValue<? extends Boolean> leggingsPreventWither,
            ForgeConfigSpec.ConfigValue<? extends Boolean> leggingsPreventLevitation,
            ForgeConfigSpec.ConfigValue<? extends Boolean> bootsPreventFallDamage
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
