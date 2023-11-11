package io.redspace.allthewizardgear.mixin;

import io.redspace.ironsspellbooks.capabilities.spell.SpellData;
import io.redspace.ironsspellbooks.capabilities.spellbook.SpellBookData;
import io.redspace.ironsspellbooks.item.SpellBook;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(SmithingTransformRecipe.class)
public class SmithingRecipeMixin {

    @Inject(method = "assemble", at = @At(value = "RETURN"), cancellable = true)
    public void fixSpellbookSlotCount(Container p_267036_, RegistryAccess p_266699_, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack result = cir.getReturnValue();
        if (result.getItem() instanceof SpellBook spellbookitem) {
            SpellBookData spellBookData = new SpellBookData(spellbookitem.getSpellSlots());
            SpellData[] spells = SpellBookData.getSpellBookData(result).getInscribedSpells();
            for (int i = 0; i < spells.length; i++) {
                if (spells[i] != null) {
                    spellBookData.addSpell(spells[i].getSpell(), spells[i].getLevel(), i, null);
                }
            }
            SpellBookData.setSpellBookData(result, spellBookData);
            cir.setReturnValue(result);
        }
    }
}
