# Packed Blocks

A lightweight, vanilla-friendly Fabric mod that adds compressed storage blocks for common resources like Cobblestone, Dirt, and Netherrack. Save inventory and storage space with ease!

## Features

- **Compressed Blocks**: Adds 3x3 compressed variants for:
  - Cobblestone (`Packed Cobblestone`)
  - Dirt (`Packed Dirt`)
  - Netherrack (`Packed Netherrack`)
- **Crafting & Uncrafting**:
  - Craft **9x** of the base block in a 3x3 grid to pack it.
  - Shape-less crafting allows you to unpack 1 packed block back into **9x** of the original material instantly.
- **Data-Driven**: Fully built using Fabric Data Generation for seamless compatibility and automatic recipe, loot table, and mining tool tag generation.

## Requirements

- **Minecraft**: 26.2
- **Fabric Loader**: >= 0.20.0
- **Fabric API**: >= 0.161.0
- **Java**: >= 25

## Building from Source

```bash
./gradlew build
./gradlew runDatagen
```

## License

This project is open-source and available under the CC0-1.0 License.
