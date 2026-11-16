package net.cerealcamera.sable_scalablelux_compat.mixin;

import ca.spottedleaf.starlight.common.light.StarLightInterface;
import ca.spottedleaf.starlight.common.light.StarLightLightingProvider;
import dev.ryanhcode.sable.sublevel.plot.ServerLevelPlot;
import net.cerealcamera.sable_scalablelux_compat.SableScalableLuxCompat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LightChunkGetter;
import net.minecraft.world.level.lighting.LevelLightEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerLevelPlot.class)
public class ServerLevelPlotMixin {
    @Redirect(method = "<init>", at = @At(value = "NEW", target = "net/minecraft/world/level/lighting/LevelLightEngine"))
    private LevelLightEngine ssc$LevelLightEngine(LightChunkGetter lightChunkGetter, boolean blockLight, boolean skyLight) {
        if (lightChunkGetter.getLevel() instanceof Level level && level.getLightEngine() instanceof StarLightLightingProvider starLightLightingProvider) {
            StarLightInterface starLightInterface;

            // This mixin is only run when ScalableLux is installed, so scalableLuxVersion cannot be null
            if (SableScalableLuxCompat.scalableLuxVersion.startsWith("0.1"))
                // Not deprecated on 0.1.0
                starLightInterface = starLightLightingProvider.getLightEngine();
            else starLightInterface = starLightLightingProvider.scalablelux$getLightEngine();

            if (starLightInterface != null) {
                blockLight = starLightInterface.hasBlockLight();
                skyLight = starLightInterface.hasSkyLight();
            }
        }

        return new LevelLightEngine(lightChunkGetter, blockLight, skyLight);
    }
}
