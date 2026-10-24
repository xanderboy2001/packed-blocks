package io.github.xanderboy2001.packedblocks.datagen;

import java.util.concurrent.CompletableFuture;

import io.github.xanderboy2001.packedblocks.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;

public class ModLanguageProvider extends FabricLanguageProvider {

  public ModLanguageProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
    super(packOutput, "en_us", registryLookup);
  }

  @Override
  public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
    translationBuilder.add("creativeTab.packedblocks", "Packed Blocks");

    for (ModBlocks.PackedBlockEntry entry : ModBlocks.BLOCKS_TO_PACK) {
      Block packedBlock = ModBlocks.PACKED_BLOCK_MAP.get(entry.name());
      if (packedBlock != null) {
        translationBuilder.add(packedBlock, formatName(entry.name()));
      }
    }
  }

  private String formatName(String internalName) {
    String[] parts = internalName.split("_");
    StringBuilder sb = new StringBuilder();
    for (String part : parts) {
      if (!part.isEmpty()) {
        sb.append(Character.toUpperCase(part.charAt(0)))
            .append(part.substring(1))
            .append(" ");
      }
    }
    return sb.toString().trim();
  }
}
