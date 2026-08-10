package io.redspace.allthewizardgear;

import io.redspace.allthewizardgear.item.WizardArmorItem;
import io.redspace.allthewizardgear.registry.ItemRegistry;
import net.minecraft.core.Holder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = AllTheWizardGear.MODID, bus = EventBusSubscriber.Bus.MOD)
public class StartupConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static ModConfigSpec SPEC;

    public static ArmorSetConfig ALLTHEMODIUM_CONFIG;
    public static ArmorSetConfig VIBRANIUM_CONFIG;
    public static ArmorSetConfig UNOBTAINIUM_CONFIG;

    @Nullable
    private static ArmorSetData allthemodiumSync;
    @Nullable
    private static ArmorSetData vibraniumSync;
    @Nullable
    private static ArmorSetData unobtainiumSync;

    static {
        BUILDER.push("ArmorConfig");
        BUILDER.comment("Changing armor config requires world restart");

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
                true,
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
                true,
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
                true,
                true);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    private static ArmorSetConfig defineConfig(ModConfigSpec.Builder builder, String name, List<Integer> defenseValues, int toughness, double knockbackResistance, int maxMana, double spellPower, double manaRegen,
                                               boolean helmetPreventsDrowning,
                                               boolean helmetPreventsElytraDamage,
                                               boolean chestplatePreventsFire,
                                               boolean chestplatePreventsDragonBreath,
                                               boolean leggingsPreventWither,
                                               boolean leggingsPreventLevitation,
                                               boolean bootsPreventFallDamage,
                                               boolean makesPiglinsNeutral) {
        builder.push(name);
        String localizedName = name.substring(0, 1).toUpperCase() + name.substring(1) + "'s ";
        var config = new ArmorSetConfig(
                name,
                builder.comment(localizedName + "Armor Values, in the form of [boots, leggings, chestplate, helmet]. Default: " + defenseValues).defineList("armorValues", () -> defenseValues, (x) -> true),
                builder.comment(localizedName + "Armor Toughness. Default: " + toughness).define("toughness", toughness),
                builder.comment(localizedName + "Knockback Resistance. Default: " + knockbackResistance).define("knockbackResistance", knockbackResistance),
                builder.comment(localizedName + "Max Mana. Default: " + maxMana).define("maxMana", maxMana),
                builder.comment(localizedName + String.format("Spell Power. Default: %s (+%s%%)", spellPower, (int) (spellPower * 100))).define("spellPower", spellPower),
                builder.comment(localizedName + String.format("Mana Regen. Default: %s", manaRegen)).define("manaRegen", manaRegen),
                builder.comment(localizedName + String.format("Helmet Prevents Drowning. Default: %s", helmetPreventsDrowning)).define("helmetPreventsDrowning", helmetPreventsDrowning),
                builder.comment(localizedName + String.format("Helmet Prevents Elytra Damage. Default: %s", helmetPreventsElytraDamage)).define("helmetPreventsElytraDamage", helmetPreventsElytraDamage),
                builder.comment(localizedName + String.format("Chestplate Prevents Fire Damage. Default: %s", chestplatePreventsFire)).define("chestplatePreventsFire", chestplatePreventsFire),
                builder.comment(localizedName + String.format("Chestplate Prevents Dragon Breath. Default: %s", chestplatePreventsDragonBreath)).define("chestplatePreventsDragonBreath", chestplatePreventsDragonBreath),
                builder.comment(localizedName + String.format("Leggings Prevent Wither. Default: %s", leggingsPreventWither)).define("leggingsPreventWither", leggingsPreventWither),
                builder.comment(localizedName + String.format("Leggings Prevent Levitation. Default: %s", leggingsPreventLevitation)).define("leggingsPreventLevitation", leggingsPreventLevitation),
                builder.comment(localizedName + String.format("Boots Prevent Fall Damage. Default: %s", bootsPreventFallDamage)).define("bootsPreventFallDamage", bootsPreventFallDamage),
                builder.comment(localizedName + String.format("Armor makes Piglins Neutral (like gold armor). Default: %s", makesPiglinsNeutral)).define("makesPiglinsNeutral", makesPiglinsNeutral)
        );
        builder.pop();
        return config;
    }

    public static ArmorSetData allthemodium() {
        return allthemodiumSync != null ? allthemodiumSync : ALLTHEMODIUM_CONFIG.toData();
    }

    public static ArmorSetData vibranium() {
        return vibraniumSync != null ? vibraniumSync : VIBRANIUM_CONFIG.toData();
    }

    public static ArmorSetData unobtainium() {
        return unobtainiumSync != null ? unobtainiumSync : UNOBTAINIUM_CONFIG.toData();
    }

    public static void reloadArmorItems() {
        for (Holder<Item> item : ItemRegistry.items()) {
            if (item.value() instanceof WizardArmorItem armorItem) {
                armorItem.reload();
            }
        }
    }

    public static void applyClientSync(ArmorSetData allthemodium, ArmorSetData vibranium, ArmorSetData unobtainium) {
        allthemodiumSync = allthemodium;
        vibraniumSync = vibranium;
        unobtainiumSync = unobtainium;
        reloadArmorItems();
    }

    public static void clearClientSync() {
        allthemodiumSync = null;
        vibraniumSync = null;
        unobtainiumSync = null;
        reloadArmorItems();
    }

    @SubscribeEvent
    public static void onReload(ModConfigEvent.Reloading event) {
        if (event.getConfig().getSpec() != SPEC) {
            return;
        }
        reloadArmorItems();
        var server = ServerLifecycleHooks.getCurrentServer();
        if (server != null) {
            PacketDistributor.sendToAllPlayers(io.redspace.allthewizardgear.network.SyncArmorConfigPacket.create());
        }
    }

    public record ArmorSetData(
            String name,
            List<Integer> defenseValues,
            int toughness,
            double knockbackResistance,
            int maxMana,
            double spellPower,
            double manaRegen,
            boolean helmetPreventsDrowning,
            boolean helmetPreventsElytraDamage,
            boolean chestplatePreventsFire,
            boolean chestplatePreventsDragonBreath,
            boolean leggingsPreventWither,
            boolean leggingsPreventLevitation,
            boolean bootsPreventFallDamage,
            boolean makesPiglinsNeutral
    ) {
        public static final StreamCodec<FriendlyByteBuf, ArmorSetData> STREAM_CODEC = StreamCodec.of(ArmorSetData::write, ArmorSetData::read);

        public double getDefenseFor(EquipmentSlot slot) {
            return defenseValues.get(slot.getIndex());
        }

        private static void write(FriendlyByteBuf buf, ArmorSetData data) {
            buf.writeUtf(data.name);
            buf.writeVarInt(data.defenseValues.size());
            for (int value : data.defenseValues) {
                buf.writeVarInt(value);
            }
            buf.writeVarInt(data.toughness);
            buf.writeDouble(data.knockbackResistance);
            buf.writeVarInt(data.maxMana);
            buf.writeDouble(data.spellPower);
            buf.writeDouble(data.manaRegen);
            buf.writeBoolean(data.helmetPreventsDrowning);
            buf.writeBoolean(data.helmetPreventsElytraDamage);
            buf.writeBoolean(data.chestplatePreventsFire);
            buf.writeBoolean(data.chestplatePreventsDragonBreath);
            buf.writeBoolean(data.leggingsPreventWither);
            buf.writeBoolean(data.leggingsPreventLevitation);
            buf.writeBoolean(data.bootsPreventFallDamage);
            buf.writeBoolean(data.makesPiglinsNeutral);
        }

        private static ArmorSetData read(FriendlyByteBuf buf) {
            String name = buf.readUtf();
            int size = buf.readVarInt();
            List<Integer> defenseValues = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                defenseValues.add(buf.readVarInt());
            }
            return new ArmorSetData(
                    name,
                    defenseValues,
                    buf.readVarInt(),
                    buf.readDouble(),
                    buf.readVarInt(),
                    buf.readDouble(),
                    buf.readDouble(),
                    buf.readBoolean(),
                    buf.readBoolean(),
                    buf.readBoolean(),
                    buf.readBoolean(),
                    buf.readBoolean(),
                    buf.readBoolean(),
                    buf.readBoolean(),
                    buf.readBoolean()
            );
        }
    }

    public record ArmorSetConfig(
            String name,
            ModConfigSpec.ConfigValue<List<? extends Integer>> defenseValues,
            ModConfigSpec.ConfigValue<? extends Integer> toughness,
            ModConfigSpec.ConfigValue<? extends Double> knockbackResistance,
            ModConfigSpec.ConfigValue<? extends Integer> maxMana,
            ModConfigSpec.ConfigValue<? extends Double> spellPower,
            ModConfigSpec.ConfigValue<? extends Double> manaRegen,
            ModConfigSpec.ConfigValue<? extends Boolean> helmetPreventsDrowning,
            ModConfigSpec.ConfigValue<? extends Boolean> helmetPreventsElytraDamage,
            ModConfigSpec.ConfigValue<? extends Boolean> chestplatePreventsFire,
            ModConfigSpec.ConfigValue<? extends Boolean> chestplatePreventsDragonBreath,
            ModConfigSpec.ConfigValue<? extends Boolean> leggingsPreventWither,
            ModConfigSpec.ConfigValue<? extends Boolean> leggingsPreventLevitation,
            ModConfigSpec.ConfigValue<? extends Boolean> bootsPreventFallDamage,
            ModConfigSpec.ConfigValue<? extends Boolean> makesPiglinsNeutral
    ) {
        public ArmorSetData toData() {
            List<? extends Integer> values = defenseValues.get();
            if (values.size() != 4) {
                values = defenseValues.getDefault();
            }
            return new ArmorSetData(
                    name,
                    List.copyOf(values),
                    toughness.get(),
                    knockbackResistance.get(),
                    maxMana.get(),
                    spellPower.get(),
                    manaRegen.get(),
                    helmetPreventsDrowning.get(),
                    helmetPreventsElytraDamage.get(),
                    chestplatePreventsFire.get(),
                    chestplatePreventsDragonBreath.get(),
                    leggingsPreventWither.get(),
                    leggingsPreventLevitation.get(),
                    bootsPreventFallDamage.get(),
                    makesPiglinsNeutral.get()
            );
        }
    }
}
