package com.example.examplemod.item;

import com.thevortex.allthemodium.registry.ModRegistry;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public enum ExtendedArmorMaterials implements ArmorMaterial {

    ALLTHEMODIUM("allthemodium", 42, makeArmorMap(4, 9, 7, 4), 85, SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0F, 0.0F, () -> Ingredient.of(ModRegistry.ALLTHEMODIUM_INGOT.get()), Map.of(
            AttributeRegistry.MAX_MANA.get(), new AttributeModifier("Max Mana", 200, AttributeModifier.Operation.ADDITION),
            AttributeRegistry.SPELL_POWER.get(), new AttributeModifier("Additional Power", .15, AttributeModifier.Operation.MULTIPLY_TOTAL)
    )),
    VIBRANIUM("vibranium", 62, makeArmorMap(6, 11, 9, 6), 105, SoundEvents.ARMOR_EQUIP_NETHERITE, 6.0F, 0.0F, () -> Ingredient.of(ModRegistry.VIBRANIUM_INGOT.get()), Map.of(
            AttributeRegistry.MAX_MANA.get(), new AttributeModifier("Max Mana", 325, AttributeModifier.Operation.ADDITION),
            AttributeRegistry.SPELL_POWER.get(), new AttributeModifier("Additional Power", .25, AttributeModifier.Operation.MULTIPLY_TOTAL)
    )),
    UNOBTAINIUM("unobtainium", 82, makeArmorMap(8, 13, 11, 8), 125, SoundEvents.ARMOR_EQUIP_NETHERITE, 8.0F, 0.0F, () -> Ingredient.of(ModRegistry.UNOBTAINIUM_INGOT.get()), Map.of(
            AttributeRegistry.MAX_MANA.get(), new AttributeModifier("Max Mana", 450, AttributeModifier.Operation.ADDITION),
            AttributeRegistry.SPELL_POWER.get(), new AttributeModifier("Additional Power", .4, AttributeModifier.Operation.MULTIPLY_TOTAL)
    ));

//    ALLTHEMODIUM("allthemodium", 42, new int[]{4, 7, 9, 4}, 85, SoundEvents.ARMOR_EQUIP_NETHERITE, 5.0F, 0.5F, () -> {
//        return Ingredient.of(new ItemLike[]{(ItemLike) ModRegistry.ALLTHEMODIUM_INGOT.get()});
//    }),
//    VIBRANIUM("vibranium", 62, new int[]{6, 9, 11, 6}, 105, SoundEvents.ARMOR_EQUIP_NETHERITE, 9.0F, 0.8F, () -> {
//        return Ingredient.of(new ItemLike[]{(ItemLike)ModRegistry.VIBRANIUM_INGOT.get()});
//    }),
//    UNOBTAINIUM("unobtainium", 82, new int[]{8, 11, 13, 8}, 125, SoundEvents.ARMOR_EQUIP_NETHERITE, 15.0F, 1.0F, () -> {
//        return Ingredient.of(new ItemLike[]{(ItemLike)ModRegistry.UNOBTAINIUM_INGOT.get()});
//    });

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;
    private final Map<Attribute, AttributeModifier> additionalAttributes;

    private ExtendedArmorMaterials(String pName, int pDurabilityMultiplier, EnumMap<ArmorItem.Type, Integer> protectionMap, int pEnchantmentValue, SoundEvent pSound, float pToughness, float pKnockbackResistance, Supplier<Ingredient> pRepairIngredient, Map<Attribute, AttributeModifier> additionalAttributes) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.protectionFunctionForType = protectionMap;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.toughness = pToughness;
        this.knockbackResistance = pKnockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
        this.additionalAttributes = additionalAttributes;
    }

    static public EnumMap<ArmorItem.Type, Integer> makeArmorMap(int helmet, int chestplate, int leggings, int boots) {
        return Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266655_) -> {
            p_266655_.put(ArmorItem.Type.BOOTS, boots);
            p_266655_.put(ArmorItem.Type.LEGGINGS, leggings);
            p_266655_.put(ArmorItem.Type.CHESTPLATE, chestplate);
            p_266655_.put(ArmorItem.Type.HELMET, helmet);
        });
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

    public int getDefenseForType(ArmorItem.Type p_266752_) {
        return this.protectionFunctionForType.get(p_266752_);
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
        return this.toughness;
    }

    public Map<Attribute, AttributeModifier> getAdditionalAttributes() {
        return additionalAttributes;
    }

    /**
     * Gets the percentage of knockback resistance provided by armor of the material.
     */
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
