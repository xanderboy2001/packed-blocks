package io.github.xanderboy2001.packedblocks;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import io.github.xanderboy2001.packedblocks.block.ModBlocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PackedBlocks implements ModInitializer {
  public static final String MOD_ID = "packedblocks";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  @Override
  public void onInitialize() {
    LOGGER.info("Initializing Packed Blocks!");
    ModBlocks.initialize();
  }

  public static Identifier id(String path) {
    return Identifier.fromNamespaceAndPath(MOD_ID, path);
  }
}
