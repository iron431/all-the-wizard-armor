package io.redspace.allthewizardgear.mixin;

import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.spells.SpellData;
import io.redspace.ironsspellbooks.api.spells.SpellSlot;
import io.redspace.ironsspellbooks.capabilities.magic.SpellContainer;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.registries.ComponentRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(SmithingTransformRecipe.class)
public class SmithingRecipeMixin {
    @Inject(method = "Lnet/minecraft/world/item/crafting/SmithingTransformRecipe;assemble(Lnet/minecraft/world/item/crafting/SmithingRecipeInput;Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/world/item/ItemStack;", at = @At(value = "RETURN"), cancellable = true)
    public void fixSpellbookSlotCount(SmithingRecipeInput pInput, HolderLookup.Provider pRegistries, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack result = cir.getReturnValue();
        if (result.getItem() instanceof SpellBook spellBook) {
            ItemStack input = pInput.base();
            var baseSpellContainer = ISpellContainer.getOrCreate(input);
            var resultSpellContainer = ISpellContainer.getOrCreate(result).mutableCopy();
            //copy previous spells
            for (SpellSlot slot : baseSpellContainer.getActiveSpells()) {
                resultSpellContainer.addSpellAtIndex(slot.getSpell(), slot.getLevel(), slot.index(), slot.isLocked());
            }
            result.set(ComponentRegistry.SPELL_CONTAINER, resultSpellContainer.toImmutable());
            cir.setReturnValue(result);
        }
    }
}
