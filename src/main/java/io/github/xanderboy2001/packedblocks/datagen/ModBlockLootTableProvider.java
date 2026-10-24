package io.github.xanderboy2001.packedblocks.datagen;

import java.util.concurrent.CompletableFuture;

import io.github.xanderboy2001.packedblocks.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {

  public ModBlockLootTableProvider(
      FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
    super(output, registryLookup);
  }

  @Override
  public void generate() {
    for (Block block : ModBlocks.PACKED_BLOCK_MAP.values()) {
      dropSelf(block);
    }
  }
}
