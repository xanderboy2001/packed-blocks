package io.github.xanderboy2001.packedblocks.datagen;

import java.util.Optional;

import io.github.xanderboy2001.packedblocks.PackedBlocks;
import io.github.xanderboy2001.packedblocks.block.ModBlocks;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;

public class ModModelProvider extends FabricModelProvider {

  private static final TextureSlot BASE = TextureSlot.create("base");

  private static final ModelTemplate PACKED_CUBE = new ModelTemplate(Optional.of(PackedBlocks.id("block/packed_cube")),
      Optional.empty(), BASE);

  public ModModelProvider(FabricPackOutput output) {
    super(output);
  }

  @Override
  public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
    for (ModBlocks.PackedBlockEntry entry : ModBlocks.BLOCKS_TO_PACK) {
      Block block = ModBlocks.PACKED_BLOCK_MAP.get(entry.name());
      if (block != null) {
        TexturedModel.Provider provider = TexturedModel.createDefault(
            b -> new TextureMapping().put(BASE, TextureMapping.getBlockTexture(entry.baseBlock())),
            PACKED_CUBE);
        blockModelGenerators.createTrivialBlock(block, provider);
      }
    }
  }

  @Override
  public void generateItemModels(ItemModelGenerators itemModelGenerators) {

  }
}
