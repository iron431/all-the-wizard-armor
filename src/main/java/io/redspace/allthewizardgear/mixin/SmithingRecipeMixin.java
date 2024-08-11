package io.redspace.allthewizardgear.mixin;

import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.spells.SpellData;
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
    /**
     * Preventing smithing table from copying NBT that is not supposed to be copied:
     * - Spell Slot Count
     */
//    @Inject(method = "Lnet/minecraft/world/item/crafting/SmithingTransformRecipe;assemble(Lnet/minecraft/world/item/crafting/SmithingRecipeInput;Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/world/item/ItemStack;", at = @At(value = "RETURN"), cancellable = true)
//    public void fixSpellbookSlotCount(SmithingRecipeInput pInput, HolderLookup.Provider pRegistries, CallbackInfoReturnable<ItemStack> cir) {
//        ItemStack result = cir.getReturnValue();
//        if (result.getItem() instanceof IPresetSpellContainer iPresetSpellContainer) {
//            var resultContainer = ISpellContainer.get(result).mutableCopy();
//            ItemStack empty = ItemStack.EMPTY.copy();
//            iPresetSpellContainer.initializeSpellContainer(empty);
//            int count = ISpellContainer.get(empty).getMaxSpellCount();
//            resultContainer.setMaxSpellCount(count);
//            result.set(ComponentRegistry.SPELL_CONTAINER, resultContainer.toImmutable());
//        }
//    }
}
