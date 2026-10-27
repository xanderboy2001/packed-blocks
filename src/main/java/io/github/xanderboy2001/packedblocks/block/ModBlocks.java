package io.github.xanderboy2001.packedblocks.block;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import io.github.xanderboy2001.packedblocks.PackedBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {

  /**
   * A packed block definition.
   *
   * @param name      registry path of the packed block
   * @param baseBlock the vanilla block that gets packed
   * @param tags      block tags the packed block should be in (mining tool and
   *                  tier tags)
   */
  public record PackedBlockEntry(String name, Block baseBlock, List<TagKey<Block>> tags) {
    @SafeVarargs
    public static PackedBlockEntry of(String name, Block baseBlock, TagKey<Block>... tags) {
      return new PackedBlockEntry(name, baseBlock, List.of(tags));
    }
  }

  public static final List<PackedBlockEntry> BLOCKS_TO_PACK = List.of(
      PackedBlockEntry.of("packed_cobblestone", Blocks.COBBLESTONE, BlockTags.MINEABLE_WITH_PICKAXE),
      PackedBlockEntry.of("packed_dirt", Blocks.DIRT, BlockTags.MINEABLE_WITH_SHOVEL),
      PackedBlockEntry.of("packed_netherrack", Blocks.NETHERRACK, BlockTags.MINEABLE_WITH_PICKAXE));

  public static final Map<String, Block> PACKED_BLOCK_MAP = new LinkedHashMap<>();

  private static Block register(
      ResourceKey<Block> id,
      Function<BlockBehaviour.Properties, Block> blockFactory,
      BlockBehaviour.Properties properties) {
    Block block = blockFactory.apply(properties.setId(id));

    return Registry.register(BuiltInRegistries.BLOCK, id, block);
  }

  private static Block register(
      BlockItemId id,
      Function<BlockBehaviour.Properties, Block> blockFactory,
      BlockBehaviour.Properties properties) {
    Block block = register(id.block(), blockFactory, properties);
    BlockItem blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(id.item()));
    Registry.register(BuiltInRegistries.ITEM, id.item(), blockItem);

    return block;
  }

  static {
    for (PackedBlockEntry entry : BLOCKS_TO_PACK) {
      Identifier id = Identifier.fromNamespaceAndPath(PackedBlocks.MOD_ID, entry.name());
      BlockItemId blockItemId = BlockItemId.create(id, id);

      BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(entry.baseBlock()).strength(3.0F,
          8.0F);

      Block block = register(blockItemId, Block::new, properties);
      PACKED_BLOCK_MAP.put(entry.name(), block);
    }
  }

  public static void initialize() {
    // Triggers static initialization
  }
}
