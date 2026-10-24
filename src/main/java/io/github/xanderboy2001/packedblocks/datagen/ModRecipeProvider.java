package io.github.xanderboy2001.packedblocks.datagen;

import java.util.concurrent.CompletableFuture;

import io.github.xanderboy2001.packedblocks.PackedBlocks;
import io.github.xanderboy2001.packedblocks.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.block.Block;

public class ModRecipeProvider extends FabricRecipeProvider {

  public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
    super(output, registriesFuture);
  }

  @Override
  protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
    return new RecipeProvider(registryLookup, exporter) {
      @Override
      public void buildRecipes() {
        for (ModBlocks.PackedBlockEntry entry : ModBlocks.BLOCKS_TO_PACK) {
          Block packed = ModBlocks.PACKED_BLOCK_MAP.get(entry.name());
          Block base = entry.baseBlock();
          if (packed == null) {
            continue;
          }

          shaped(RecipeCategory.BUILDING_BLOCKS, packed)
              .pattern("###")
              .pattern("###")
              .pattern("###")
              .define('#', base)
              .unlockedBy(getHasName(base), has(base))
              .save(output);

          shapeless(RecipeCategory.BUILDING_BLOCKS, base, 9)
              .requires(packed)
              .unlockedBy(getHasName(packed), has(packed))
              .save(output, PackedBlocks.id("unpack_" + entry.name()).toString());
        }
      }
    };
  }

  @Override
  public String getName() {
    return "PackedBlocksRecipeProvider";
  }
}
