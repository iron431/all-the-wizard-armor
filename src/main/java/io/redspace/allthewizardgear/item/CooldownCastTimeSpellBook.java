package io.redspace.allthewizardgear.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.item.spell_books.SimpleAttributeSpellBook;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.UUID;

public class CooldownCastTimeSpellBook extends SimpleAttributeSpellBook {

    public CooldownCastTimeSpellBook(int spellSlots, double cooldown, double castTime) {
        super(spellSlots, SpellRarity.LEGENDARY,
                createMultimap(AttributeRegistry.COOLDOWN_REDUCTION.get(),new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", cooldown, AttributeModifier.Operation.MULTIPLY_BASE),
                        AttributeRegistry.CAST_TIME_REDUCTION.get(), new AttributeModifier(UUID.fromString("967ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", castTime, AttributeModifier.Operation.MULTIPLY_BASE))
        );
    }

    private static Multimap<Attribute, AttributeModifier> createMultimap(Attribute attribute, AttributeModifier modifier, Attribute attribute2, AttributeModifier modifier2) {
        Multimap<Attribute, AttributeModifier> map = HashMultimap.create();
        map.put(attribute, modifier);
        map.put(attribute2, modifier2);
        return map;
    }
}
