package io.redspace.allthewizardgear.network;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import io.redspace.allthewizardgear.AllTheWizardGear;

@EventBusSubscriber(modid = AllTheWizardGear.MODID, bus = EventBusSubscriber.Bus.MOD)
public class PayloadHandler {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar payloadRegistrar = event.registrar(AllTheWizardGear.MODID).versioned("1.0.0").optional();

        payloadRegistrar.playToClient(SyncArmorConfigPacket.TYPE, SyncArmorConfigPacket.STREAM_CODEC, SyncArmorConfigPacket::handle);
    }
}
