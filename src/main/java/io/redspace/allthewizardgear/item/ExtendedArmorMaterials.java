package io.redspace.allthewizardgear.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.thevortex.allthemodium.registry.ModRegistry;
import com.thevortex.allthemodium.registry.TagRegistry;
import io.redspace.allthewizardgear.ServerConfig;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.UUID;
import java.util.function.Supplier;

public enum ExtendedArmorMaterials implements ArmorMaterial {
    //TODO: come up find finalized set of attributes, and hardcode those
    ALLTHEMODIUM("allthemodium", ServerConfig.ALLTHEMODIUM_CONFIG, 42, 85, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(TagRegistry.ALLTHEMODIUM_INGOT)),
    VIBRANIUM("vibranium", ServerConfig.VIBRANIUM_CONFIG, 62, 105, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(TagRegistry.VIBRANIUM_INGOT)),
    UNOBTAINIUM("unobtainium", ServerConfig.UNOBTAINIUM_CONFIG, 82, 125, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(TagRegistry.UNOBTAINIUM_INGOT));

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final LazyLoadedValue<Ingredient> repairIngredient;
    private final ServerConfig.ArmorSetConfig config;

    private ExtendedArmorMaterials(String pName, ServerConfig.ArmorSetConfig config, int pDurabilityMultiplier, int pEnchantmentValue, SoundEvent pSound, Supplier<Ingredient> pRepairIngredient) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
        this.config = config;
        slotToAttributeMap = null;
    }

    private EnumMap<EquipmentSlot, Multimap<Attribute, AttributeModifier>> slotToAttributeMap;

    public EnumMap<EquipmentSlot, Multimap<Attribute, AttributeModifier>> getSlotToAttributeMap() {
        if (slotToAttributeMap == null) {
            slotToAttributeMap = makeSlotToAttributeMap();
        }
        return slotToAttributeMap;
    }

    private EnumMap<EquipmentSlot, Multimap<Attribute, AttributeModifier>> makeSlotToAttributeMap() {
        return Util.make(new EnumMap<>(EquipmentSlot.class), (map) -> {
            map.put(EquipmentSlot.FEET, makeAttributeMap(EquipmentSlot.FEET));
            map.put(EquipmentSlot.LEGS, makeAttributeMap(EquipmentSlot.LEGS));
            map.put(EquipmentSlot.CHEST, makeAttributeMap(EquipmentSlot.CHEST));
            map.put(EquipmentSlot.HEAD, makeAttributeMap(EquipmentSlot.HEAD));
        });
    }

    public void reload() {
        this.slotToAttributeMap = null;
    }

    private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};

    private Multimap<Attribute, AttributeModifier> makeAttributeMap(EquipmentSlot slot) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[slot.getIndex()];
        double defense = this.config.getDefenseFor(slot);
        double toughness = this.config.toughness().get();
        double knockbackResistance = this.config.knockbackResistance().get();
        double maxMana = this.config.maxMana().get();
        double power = this.config.spellPower().get();
        double manaRegen = this.config.manaRegen().get();
        if (defense != 0) {
            builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", defense, AttributeModifier.Operation.ADDITION));
        }
        if (toughness != 0) {
            builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", toughness, AttributeModifier.Operation.ADDITION));
        }
        if (knockbackResistance != 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor  knockback resistance", knockbackResistance, AttributeModifier.Operation.ADDITION));
        }
        if (maxMana != 0) {
            builder.put(AttributeRegistry.MAX_MANA.get(), new AttributeModifier(uuid, "Armor maxMana", maxMana, AttributeModifier.Operation.ADDITION));
        }
        if (power != 0) {
            builder.put(AttributeRegistry.SPELL_POWER.get(), new AttributeModifier(uuid, "Armor spell power", power, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        if (manaRegen != 0) {
            builder.put(AttributeRegistry.MANA_REGEN.get(), new AttributeModifier(uuid, "Armor mana regen", manaRegen, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }

        return builder.build();
    }

    static public EnumMap<ArmorItem.Type, Integer> makeArmorMap(int helmet, int chestplate, int leggings, int boots) {
        return Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266655_) -> {
            p_266655_.put(ArmorItem.Type.BOOTS, boots);
            p_266655_.put(ArmorItem.Type.LEGGINGS, leggings);
            p_266655_.put(ArmorItem.Type.CHESTPLATE, chestplate);
            p_266655_.put(ArmorItem.Type.HELMET, helmet);
        });
    }

    public void getAttributesFor(EquipmentSlot pSlot) {

    }

    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return HEALTH_PER_SLOT[pSlot.getIndex()] * this.durabilityMultiplier;
    }

    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);
    });

    public int getDurabilityForType(ArmorItem.Type p_266745_) {
        return HEALTH_FUNCTION_FOR_TYPE.get(p_266745_) * this.durabilityMultiplier;
    }

    public int getDefenseForType(ArmorItem.Type type) {
        return -1;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return -1;
    }

    /**
     * Gets the percentage of knockback resistance provided by armor of the material.
     */
    public float getKnockbackResistance() {
        return -1;
    }
}
