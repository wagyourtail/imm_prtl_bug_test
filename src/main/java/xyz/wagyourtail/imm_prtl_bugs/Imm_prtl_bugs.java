package xyz.wagyourtail.imm_prtl_bugs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.level.Level;
import qouteall.imm_ptl.core.api.PortalAPI;
import qouteall.imm_ptl.core.chunk_loading.ChunkLoader;
import qouteall.imm_ptl.core.chunk_loading.DimensionalChunkPos;


public class Imm_prtl_bugs implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(s -> {

        });
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            PortalAPI.addChunkLoaderForPlayer(handler.player, new ChunkLoader(new DimensionalChunkPos(Level.OVERWORLD, 0, 0), 8));
        });
    }
}
