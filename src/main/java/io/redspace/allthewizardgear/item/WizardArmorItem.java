package io.redspace.allthewizardgear.item;

import io.redspace.allthewizardgear.AllTheWizardGear;
import io.redspace.allthewizardgear.ServerConfig;
import io.redspace.allthewizardgear.client.armor.GenericArmorModel;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.item.armor.ExtendedArmorItem;
import io.redspace.ironsspellbooks.registries.ArmorMaterialRegistry;
import io.redspace.ironsspellbooks.registries.ComponentRegistry;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class WizardArmorItem extends ArmorItem implements GeoItem, IPresetSpellContainer {
    class Cache<T> {
        Cache(Supplier<T> supplier) {
            this.supplier = supplier;
        }

        final Supplier<T> supplier;
        T value;

        T get() {
            if (value == null) {
                value = supplier.get();
            }
            return value;
        }

        void reload() {
            this.value = null;
        }
    }

    public void reload() {
        this.defaultModifiers.reload();
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    //Shadowing
    private final Supplier<ServerConfig.ArmorSetConfig> config;
    private Cache<ItemAttributeModifiers> defaultModifiers;

    public WizardArmorItem(Supplier<ServerConfig.ArmorSetConfig> config, Type type) {
        this(config, type, Rarity.EPIC);
    }

    public WizardArmorItem(Supplier<ServerConfig.ArmorSetConfig> config, Type type, Rarity rarity) {
        super(ArmorMaterialRegistry.SCHOOL, type, new Properties().stacksTo(1).fireResistant().rarity(rarity));
        this.config = config;
        this.defaultModifiers = new Cache<>(() -> makeAttributeMap(config.get(), type.getSlot()));
    }

    private ItemAttributeModifiers makeAttributeMap(ServerConfig.ArmorSetConfig config, EquipmentSlot slot) {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        double defense = config.getDefenseFor(slot);
        double toughness = config.toughness().get();
        double knockbackResistance = config.knockbackResistance().get();
        double maxMana = config.maxMana().get();
        double power = config.spellPower().get();
        double manaRegen = config.manaRegen().get();
        ResourceLocation resourcelocation = ResourceLocation.fromNamespaceAndPath(AllTheWizardGear.MODID, "armor." + slot.getName());
        EquipmentSlotGroup equipmentslotgroup = EquipmentSlotGroup.bySlot(slot);

        if (defense != 0) {
            builder.add(Attributes.ARMOR, new AttributeModifier(resourcelocation, defense, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
        }
        if (toughness != 0) {
            builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(resourcelocation, toughness, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
        }
        if (knockbackResistance != 0) {
            builder.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(resourcelocation, knockbackResistance, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
        }
        if (maxMana != 0) {
            builder.add(AttributeRegistry.MAX_MANA, new AttributeModifier(resourcelocation, maxMana, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
        }
        if (power != 0) {
            builder.add(AttributeRegistry.SPELL_POWER, new AttributeModifier(resourcelocation, power, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), equipmentslotgroup);
        }
        if (manaRegen != 0) {
            builder.add(AttributeRegistry.MANA_REGEN, new AttributeModifier(resourcelocation, manaRegen, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), equipmentslotgroup);
        }

        return builder.build();
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return this.config.get().makesPiglinsNeutral().get();
    }

    public ServerConfig.ArmorSetConfig getConfig() {
        return this.config.get();
    }

    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        return (ItemAttributeModifiers) this.defaultModifiers.get();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 20, this::predicate));
    }

    private PlayState predicate(AnimationState<WizardArmorItem> extendedArmorItemAnimationState) {
        extendedArmorItemAnimationState.getController().setAnimation(RawAnimation.begin().thenLoop("idle"));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public <T extends LivingEntity> HumanoidModel<?> getGeoArmorRenderer(@Nullable T livingEntity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, @Nullable HumanoidModel<T> original) {
                if (this.renderer == null) {
                    this.renderer = new GeoArmorRenderer<>(new GenericArmorModel<>(config.get().name()));
                }
                return this.renderer;
            }
        });
    }

    @Override
    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack != null) {
            Item var3 = itemStack.getItem();
            if (var3 instanceof ArmorItem armorItem) {
                if (armorItem.getType() == Type.CHESTPLATE && !ISpellContainer.isSpellContainer(itemStack)) {
                    ISpellContainer spellContainer = ISpellContainer.create(1, true, true);
                    itemStack.set(ComponentRegistry.SPELL_CONTAINER, spellContainer);
                }
            }
        }
    }
}