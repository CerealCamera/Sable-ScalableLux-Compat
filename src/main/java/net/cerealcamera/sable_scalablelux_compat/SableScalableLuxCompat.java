package net.cerealcamera.sable_scalablelux_compat;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Mod(SableScalableLuxCompat.MOD_ID)
public class SableScalableLuxCompat {
    public static final String MOD_ID = "sable_scalablelux_compat";
    public static final String MOD_NAME = "Sable ScalableLux Compat";
    public static Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public SableScalableLuxCompat() {
        try {
            Path fmlCfg = FMLPaths.CONFIGDIR.get().resolve("fml.toml");

            List<String> lines;
            lines = new ArrayList<>(Files.readAllLines(fmlCfg, StandardCharsets.UTF_8));

            if (lines.stream().anyMatch(line -> line.contains("sable = [\"-scalablelux\"]") && line.contains("sable_scalablelux_compat = [\"-scalablelux\"]"))) return;
            lines.removeIf(line -> line.trim().equals("dependencyOverrides = {}"));

            if (lines.stream().noneMatch(line -> line.contains("[dependencyOverrides]")))
                lines.add("[dependencyOverrides]");
            if (lines.stream().noneMatch(line -> line.contains("sable = [\"-scalablelux\"]")))
                lines.add("sable = [\"-scalablelux\"]");
            if (lines.stream().noneMatch(line -> line.contains("sable_scalablelux_compat = [\"-scalablelux\"]")))
                lines.add("sable_scalablelux_compat = [\"-scalablelux\"]");

            Files.write(fmlCfg, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("Failed to add Sable dependency override automatically, please add it manually by following the guide on our mod page!", e);
        }
    }
}