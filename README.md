# Sable ScalableLux Compat

A mod which fixes compatibility issues between [Sable][1] and [ScalableLux][2].

# Installation

Unfortunately, Sable declares ScalableLux as incompatible which prevents the game from launching with both. To get around this, Sable ScalableLux Compat has to be first launched once without ScalableLux. On the first launch without ScalableLux it adds a dependency override in `config/fml.toml` which bypasses said incompatibility declaration.

Steps:

1. Add the mod to your mods folder (assuming you already have Sable)
2. Launch the game once without ScalableLux
3. Install ScalableLux (just add it to your mods folder) and launch the game

### Acknowledgements

This mod is a standalone fork of [SableCat's][3] ScalableLux compat with some improvements to the code.

[1]: https://github.com/ryanhcode/sable
[2]: https://github.com/RelativityMC/ScalableLux
[3]: https://github.com/eviesaurr/SableCat
