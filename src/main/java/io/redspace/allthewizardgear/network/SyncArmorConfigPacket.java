package io.redspace.allthewizardgear.network;

import io.redspace.allthewizardgear.AllTheWizardGear;
import io.redspace.allthewizardgear.StartupConfig;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SyncArmorConfigPacket(
        StartupConfig.ArmorSetData allthemodium,
        StartupConfig.ArmorSetData vibranium,
        StartupConfig.ArmorSetData unobtainium
) implements CustomPacketPayload {

    public static final Type<SyncArmorConfigPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(AllTheWizardGear.MODID, "sync_armor_config"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SyncArmorConfigPacket> STREAM_CODEC =
            CustomPacketPayload.codec(SyncArmorConfigPacket::write, SyncArmorConfigPacket::new);

    public SyncArmorConfigPacket(FriendlyByteBuf buf) {
        this(
                StartupConfig.ArmorSetData.STREAM_CODEC.decode(buf),
                StartupConfig.ArmorSetData.STREAM_CODEC.decode(buf),
                StartupConfig.ArmorSetData.STREAM_CODEC.decode(buf)
        );
    }

    public void write(FriendlyByteBuf buf) {
        StartupConfig.ArmorSetData.STREAM_CODEC.encode(buf, allthemodium);
        StartupConfig.ArmorSetData.STREAM_CODEC.encode(buf, vibranium);
        StartupConfig.ArmorSetData.STREAM_CODEC.encode(buf, unobtainium);
    }

    public static SyncArmorConfigPacket create() {
        return new SyncArmorConfigPacket(
                StartupConfig.ALLTHEMODIUM_CONFIG.toData(),
                StartupConfig.VIBRANIUM_CONFIG.toData(),
                StartupConfig.UNOBTAINIUM_CONFIG.toData()
        );
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(SyncArmorConfigPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> StartupConfig.applyClientSync(
                packet.allthemodium(),
                packet.vibranium(),
                packet.unobtainium()
        ));
    }
}
