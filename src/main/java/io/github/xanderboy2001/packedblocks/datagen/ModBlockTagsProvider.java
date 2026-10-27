package io.github.xanderboy2001.packedblocks.datagen;

import java.util.concurrent.CompletableFuture;

import io.github.xanderboy2001.packedblocks.PackedBlocks;
import io.github.xanderboy2001.packedblocks.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {

  public ModBlockTagsProvider(
      FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
    super(output, registriesFuture);
  }

  @Override
  protected void addTags(HolderLookup.Provider registries) {
    for (ModBlocks.PackedBlockEntry entry : ModBlocks.BLOCKS_TO_PACK) {
      ResourceKey<Block> packedKey = ResourceKey.create(Registries.BLOCK, PackedBlocks.id(entry.name()));

      for (TagKey<Block> tag : entry.tags()) {
        builder(tag).add(packedKey);
      }
    }
  }
}
