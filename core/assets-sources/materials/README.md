The `.filamat` files in `src/main/res/raw` are compiled from the `.mat` sources in this folder.
They must be compiled with the `matc` of the exact Filament version set as `filament_version` in
`gradle/libs.versions.toml`, or the materials fail to load at runtime.

Download that version's binaries from **https://github.com/google/filament/releases**, then run
from the repository root:

`MATC=/path/to/filament/bin/matc tools/generate_filamat.sh`

The script checks the `matc` version and compiles every material of `core` and `ux` with
`--optimize-size --platform=mobile --api=opengl` (Sceneform's Engine runs on a shared EGL context,
so OpenGL is the only backend it needs).

More information on **matc**: **https://google.github.io/filament/Materials.html**, section 5
**Compiling materials**.
