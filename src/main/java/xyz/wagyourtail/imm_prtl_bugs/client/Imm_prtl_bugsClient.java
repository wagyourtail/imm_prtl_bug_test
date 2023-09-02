package xyz.wagyourtail.imm_prtl_bugs.client;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import qouteall.imm_ptl.core.ClientWorldLoader;
import qouteall.imm_ptl.core.render.GuiPortalRendering;
import qouteall.imm_ptl.core.render.MyRenderHelper;
import qouteall.imm_ptl.core.render.context_management.WorldRenderInfo;

import java.util.UUID;

public class Imm_prtl_bugsClient implements ClientModInitializer {

    /**
     * Runs the mod initializer on the client environment.
     */

    RenderTarget frameBuffer = null;

    @Override
    public void onInitializeClient() {
        UUID id = UUID.randomUUID();
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            frameBuffer = new TextureTarget(100, 100, true, true);
        });

        HudRenderCallback.EVENT.register((stack, tickDelta) -> {
            Vec3 pos = new Vec3(0, 100, 0);

            // camera view
            Matrix4f cameraTransform = new Matrix4f()
                    .identity()
                    .rotate(new Quaternionf()
                            .rotateLocalY((float) Math.toRadians(0))
                            .rotateLocalX((float) Math.toRadians(90))
                    );


            WorldRenderInfo renderInfo = new WorldRenderInfo(
                    ClientWorldLoader.getWorld(Level.OVERWORLD),
                    pos,
                    cameraTransform,
                    true,
                    id,
                    8,
                    false,
                    false
            );

            // render
            GuiPortalRendering.submitNextFrameRendering(renderInfo, frameBuffer);

            MyRenderHelper.drawFramebuffer(
                    frameBuffer,
                    false, false,
                    0, 200, 0, 200
            );
        });
    }
}
