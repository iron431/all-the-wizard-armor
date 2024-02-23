package io.redspace.allthewizardgear.mixin;

import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.spells.SpellData;
import io.redspace.ironsspellbooks.item.SpellBook;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
    @Inject(method = "assemble", at = @At(value = "RETURN"), cancellable = true)
    public void fixSpellbookSlotCount(Container p_267036_, RegistryAccess p_266699_, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack result = cir.getReturnValue();
        if (result.getItem() instanceof IPresetSpellContainer iPresetSpellContainer) {
            ISpellContainer resultContainer = ISpellContainer.get(result);
            ItemStack empty = ItemStack.EMPTY.copy();
            iPresetSpellContainer.initializeSpellContainer(empty);
            int count = ISpellContainer.get(empty).getMaxSpellCount();
            ISpellContainer correctedContainer = ISpellContainer.create(count, resultContainer.spellWheel(), resultContainer.mustEquip());
            SpellData[] spells = resultContainer.getAllSpells();
            for (int i = 0; i < spells.length; i++) {
                if (spells[i] != null) {
                    correctedContainer.addSpellAtIndex(spells[i].getSpell(), spells[i].getLevel(), i, spells[i].isLocked(), empty);
                }
            }
            correctedContainer.save(result);
            cir.setReturnValue(result);
        }
    }
}
