package io.github.xanderboy2001.packedblocks.item;

import io.github.xanderboy2001.packedblocks.PackedBlocks;
import io.github.xanderboy2001.packedblocks.block.ModBlocks;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class ModCreativeModeTabs {
  public static final CreativeModeTab PACKED_BLOCKS_CREATIVE_TAB =
      Registry.register(
          BuiltInRegistries.CREATIVE_MODE_TAB,
          Identifier.fromNamespaceAndPath(PackedBlocks.MOD_ID, "packed_blocks_tab"),
          FabricCreativeModeTab.builder()
              .icon(
                  () ->
                      new ItemStack(ModBlocks.PACKED_BLOCK_MAP.get("packed_cobblestone").asItem()))
              .title(Component.translatable("creativeTab." + PackedBlocks.MOD_ID))
              .displayItems(
                  (params, output) -> {
                    for (Block block : ModBlocks.PACKED_BLOCK_MAP.values()) {
                      output.accept(block.asItem());
                    }
                  })
              .build());

  public static void registerCreativeModeTabs() {
    PackedBlocks.LOGGER.info("Registering Creative Mode Tabs for " + PackedBlocks.MOD_ID);
  }
}
