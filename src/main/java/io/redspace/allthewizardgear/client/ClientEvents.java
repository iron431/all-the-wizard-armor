package io.redspace.allthewizardgear.client;

import io.redspace.allthewizardgear.AllTheWizardGear;
import io.redspace.allthewizardgear.StartupConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;

@EventBusSubscriber(modid = AllTheWizardGear.MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        StartupConfig.clearClientSync();
    }
}
