package io.redspace.allthewizardgear;

import io.redspace.allthewizardgear.item.ExtendedArmorMaterials;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = AllTheWizardGear.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    static ForgeConfigSpec SPEC;

    public static ArmorSetConfig ALLTHEMODIUM_CONFIG;

    static {
        ALLTHEMODIUM_CONFIG = defineConfig(BUILDER, "allthemodium", List.of(1d, 2d, 4d, 2d), 4, 0, 400, .4);
        SPEC = BUILDER.build();
    }

    private static ArmorSetConfig defineConfig(ForgeConfigSpec.Builder builder, String name, List<Double> defenseValues, double toughness, double knockbackResistance, double maxMana, double spellPower) {
        return new ArmorSetConfig(
                builder.push(name).worldRestart().defineList("armor values [boots, leggings, chestplate, helmet]", () -> defenseValues, (x) -> true),
                builder.worldRestart().define("toughness", toughness), builder.define("knockbackResistance", knockbackResistance),
                builder.worldRestart().define("maxMana", maxMana),
                builder.worldRestart().define("spellPower", spellPower)
        );
    }

    public static record ArmorSetConfig(
            ForgeConfigSpec.ConfigValue<List<? extends Double>> defenseValues,
            ForgeConfigSpec.ConfigValue<? extends Double> toughness,
            ForgeConfigSpec.ConfigValue<? extends Double> knockbackResistance,
            ForgeConfigSpec.ConfigValue<? extends Double> maxMana,
            ForgeConfigSpec.ConfigValue<? extends Double> spellPower
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
