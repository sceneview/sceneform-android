#!/usr/bin/env bash
# Recompiles every Sceneform material (.mat) into its .filamat blob.
#
# A .filamat must be compiled by the matc of the exact Filament version the
# library depends on (filament_version in gradle/libs.versions.toml), or the
# material fails to load at runtime.
#
# Usage: MATC=/path/to/filament-<version>/bin/matc tools/generate_filamat.sh
#
# Sceneform creates its Filament Engine on a shared EGL context, so OpenGL is
# the only backend that needs shaders.
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
MATC="${MATC:-matc}"
FLAGS=(--optimize-size --platform=mobile --api=opengl)

expected="$(sed -n 's/^filament_version *= *"\(.*\)"/\1/p' "$ROOT/gradle/libs.versions.toml")"
actual="$("$MATC" --version 2>&1 | head -1 | tr -d '[:space:]')"
echo "matc: $MATC (reports '$actual'), filament_version: $expected"
# matc --version prints only the minor version (e.g. "72" for 1.72.x).
if [ "$actual" != "$(echo "$expected" | cut -d. -f2)" ]; then
  echo "matc minor version '$actual' does not match Filament $expected" >&2
  exit 1
fi

compile_dir() {
  local src="$1" out="$2"
  for mat in "$src"/*.mat; do
    local name
    name="$(basename "$mat" .mat)"
    "$MATC" "${FLAGS[@]}" -o "$out/$name.filamat" "$mat"
    echo "  $out/$name.filamat"
  done
}

compile_dir "$ROOT/core/assets-sources/materials" "$ROOT/core/src/main/res/raw"
compile_dir "$ROOT/ux/assets-sources/materials" "$ROOT/ux/src/main/res/raw"
