package io.github.xanderboy2001.packedblocks.block;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import io.github.xanderboy2001.packedblocks.PackedBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {

  public record PackedBlockEntry(String name, Block baseBlock) {
  }

  public static final List<PackedBlockEntry> BLOCKS_TO_PACK = List.of(
      new PackedBlockEntry("packed_cobblestone", Blocks.COBBLESTONE),
      new PackedBlockEntry("packed_dirt", Blocks.DIRT),
      new PackedBlockEntry("packed_netherrack", Blocks.NETHERRACK));

  public static final Map<String, Block> PACKED_BLOCK_MAP = new HashMap<>();

  private static Block register(ResourceKey<Block> id, Function<BlockBehaviour.Properties, Block> blockFactory,
      BlockBehaviour.Properties properties) {
    Block block = blockFactory.apply(properties.setId(id));

    return Registry.register(BuiltInRegistries.BLOCK, id, block);
  }

  private static Block register(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFactory,
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

      BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(entry.baseBlock())
          .strength(3.0F, 8.0F);

      Block block = register(blockItemId, Block::new, properties);
      PACKED_BLOCK_MAP.put(entry.name(), block);
    }
  }

  public static void initialize() {
    // Triggers static initialization
  }
}
