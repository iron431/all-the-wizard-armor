package io.redspace.allthewizardgear.datagen;

import io.redspace.allthewizardgear.AllTheWizardGear;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AllTheWizardGear.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(true, new ItemModelDataGenerator(packOutput, existingFileHelper));
        generator.addProvider(true, new ItemTagsProvider(packOutput, event.getLookupProvider(), existingFileHelper));
        generator.addProvider(true, new RecipeDataProvider(packOutput));
    }
}
