# Changelog
## v0.0.2 - 2025-12-12
### Added
- **Object Manipulation Utilities** - New functions for working with maps and objects:
  - `pick` - Create a new map containing only specified keys from the source map
  - `pickBy` - Create a new map with entries that pass a predicate function
  - `omit` - Create a new map excluding specified keys
  - `omitBy` - Create a new map excluding entries that pass a predicate function
  - `keys` - Extract all keys from a map as a list of strings
  - `values` - Extract all values from a map as a list
  - `entries` - Convert a map to a list of key-value pairs
  - `mapValues` - Transform all values in a map using a transform function
  - `mapKeys` - Transform all keys in a map using a transform function
  - `has` - Check if a path exists in an object/map structure
  - `invert` - Swap keys and values in a map (1-to-1 mapping)
  - `invertBy` - Group keys by their transformed values (1-to-many mapping)

## v0.0.1 - 2025-12-12
- Initial release of the Kotlin Lodash library.
- Implemented core utility functions for arrays, strings, numbers, types, and functional programming.
- Added comprehensive test suite using Kotlin Test.
- Configured Kotlin Multiplatform support for JVM, JS, WASM, Linux, Windows, macOS, and iOS.
- Documentation generated using Dokka.
