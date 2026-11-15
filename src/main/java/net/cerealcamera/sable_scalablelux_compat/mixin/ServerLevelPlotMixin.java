package net.cerealcamera.sable_scalablelux_compat.mixin;

import ca.spottedleaf.starlight.common.light.StarLightInterface;
import ca.spottedleaf.starlight.common.light.StarLightLightingProvider;
import dev.ryanhcode.sable.sublevel.plot.ServerLevelPlot;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LightChunkGetter;
import net.minecraft.world.level.lighting.LevelLightEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerLevelPlot.class)
public class ServerLevelPlotMixin {
    @Redirect(method = "<init>", at = @At(value = "NEW", target = "net/minecraft/world/level/lighting/LevelLightEngine"))
    private LevelLightEngine ssc$LevelLightEngine(LightChunkGetter lightChunkGetter, boolean hasBlockLight, boolean hasSkyLight) {
        if (lightChunkGetter.getLevel() instanceof Level level && level.getLightEngine() instanceof StarLightLightingProvider starLightLightingProvider) {
            StarLightInterface starLightInterface = starLightLightingProvider.scalablelux$getLightEngine();
            if (starLightInterface != null) {
                hasBlockLight = starLightInterface.hasBlockLight();
                hasSkyLight = starLightInterface.hasSkyLight();
            }
        }

        return new LevelLightEngine(lightChunkGetter, hasBlockLight, hasSkyLight);
    }
}
