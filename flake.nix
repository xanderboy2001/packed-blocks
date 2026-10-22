{
  description = "Packed Blocks - Fabric Minecraft Mod Development Environment";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
  };

  outputs = { self, nixpkgs, ... }:
    let
      supportedSystems = [ "x86_64-linux" "aarch64-linux" "x86_64-darwin" "aarch64-darwin" ];
      forEachSystem = f: nixpkgs.lib.genAttrs supportedSystems (system: f {
        pkgs = import nixpkgs { inherit system; };
      });
    in {
      devShells = forEachSystem ({ pkgs }: {
        default = pkgs.mkShell {
          buildInputs = with pkgs; [
            jdk25         # Required for Minecraft 26.2 / Java 25 target
            git           # Version control
            pre-commit    # Hooks for formatting/spotless validation
            gh            # GitHub CLI for PR management and CI tools
            jq            # JSON processor for manifest inspection
          ];

          shellHook = ''
            export JAVA_HOME="${pkgs.jdk25}"
            
            echo "🧱 Packed Blocks Development Environment Loaded"
            echo "   • Java Home: $JAVA_HOME"
            echo "   • Java Version: $(java -version 2>&1 | head -n 1)"
            echo "   • Gradle Wrapper: ./gradlew"
          '';
        };
      });
    };
}
