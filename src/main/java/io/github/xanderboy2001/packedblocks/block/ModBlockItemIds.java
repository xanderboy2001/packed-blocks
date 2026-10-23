package io.github.xanderboy2001.packedblocks.block;

import io.github.xanderboy2001.packedblocks.PackedBlocks;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;

public class ModBlockItemIds {
  public static final BlockItemId PACKED_COBBLESTONE = create("packed_cobblestone");

  private static BlockItemId create(String name) {
    Identifier id = PackedBlocks.id(name);
    return BlockItemId.create(id, id);
  }

  public static void initialize() {

  }
}
