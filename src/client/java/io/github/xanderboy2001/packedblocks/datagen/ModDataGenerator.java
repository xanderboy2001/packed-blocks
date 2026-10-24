package io.github.xanderboy2001.packedblocks.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ModDataGenerator implements DataGeneratorEntrypoint {
  @Override
  public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
    FabricDataGenerator.Pack pack = dataGenerator.createPack();

    pack.addProvider(ModLanguageProvider::new);
    pack.addProvider(ModModelProvider::new);
    pack.addProvider(ModBlockLootTableProvider::new);
    pack.addProvider(ModRecipeProvider::new);
  }
}
