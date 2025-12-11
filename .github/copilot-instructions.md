# Kotlin Lodash Project Instructions

## Project Overview
This is a Kotlin Multiplatform library that provides lodash-like utility functions for common programming tasks. The project uses Kotlin 2.0.21 and targets multiple platforms including JVM, JS, WASM, Linux, Windows, macOS, and iOS.

## Project Structure
- **Main source**: `src/commonMain/kotlin/lodash/utils/Lodash.kt` - Contains all utility functions
- **Tests**: `src/commonTest/kotlin/lodash/utils/LodashTest.kt` - Comprehensive test suite using Kotlin Test
- **Build**: `build.gradle.kts` - Kotlin Multiplatform configuration with Dokka documentation
- **Package**: `lodash.utils` namespace

## Code Style Guidelines

### Function Naming and Organization
- All utility functions are **top-level functions** in the `lodash.utils` package
- Use **camelCase** for function names (e.g., `camelCase`, `kebabCase`, `cloneDeep`)
- Provide multiple overloads where appropriate (e.g., `chunk` for both `List` and `Array`)
- Functions should be pure and side-effect-free when possible

### Type Safety
- Use generic type parameters with proper variance (e.g., `<T>`)
- Add `@Suppress("UNCHECKED_CAST")` only when necessary for type-safe casts
- Prefer `require()` for parameter validation with descriptive error messages

### Function Categories Implemented
1. **Array/List utilities**: `chunk`, `compact`, `concat`, `difference`, `differenceBy`, `drop`, `dropRight`, `take`, `takeRight`, `uniq`, `uniqBy`, `flattenDeep`
2. **String utilities**: `camelCase`, `kebabCase`, `snakeCase`, `upperFirst`, `lowerFirst`, `padStart`, `padEnd`, `truncate`, `trim`
3. **Type checking**: `isEmpty`, `isNotEmpty`, `isNull`, `isNotNull`
4. **Number utilities**: `clamp` (Int, Long, Double overloads), `inRange`, `random`
5. **Functional utilities**: `times` (with and without return values), `range`
6. **Deep operations**: `cloneDeep` (supports List, Array, Map, Set)

## Testing Guidelines

### Test Structure
- **Test class**: `LodashTest` in `src/commonTest/kotlin/lodash/utils/`
- Use **kotlin.test** framework (not JUnit directly in common tests)
- Test naming: Use backticks with descriptive names (e.g., `` `chunk - basic usage` ``)
- Group related test cases using descriptive function names

### Test Annotations
```kotlin
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse
```

### Test Examples
```kotlin
@Test
fun `function name - test description`() {
    assertEquals(expected, actual)
}

@Test
fun `edge case - handles null inputs`() {
    assertEquals("", trim(null))
}
```

### Running Tests
```sh
./gradlew test                    # Run all tests
./gradlew jvmTest                 # Run JVM-specific tests
./gradlew cleanTest test          # Clean and run tests
```

## Implementation Patterns

### Handling Nullability
```kotlin
// Use safe calls and Elvis operator
fun trim(s: String?, chars: String? = null): String {
    if (s.isNullOrEmpty()) return ""
    val actualChars = chars ?: " \t\n\r..."
    // implementation
}
```

### Generic Functions
```kotlin
// Use proper generic constraints
fun <T> flattenDeep(list: List<Any?>): List<T> = buildList {
    fun recurse(item: Any?) {
        when (item) {
            is List<*> -> item.forEach { recurse(it) }
            else -> add(item as T)
        }
    }
    list.forEach { recurse(it) }
}
```

### Function Overloading
```kotlin
// Provide overloads for common types
fun clamp(number: Int, lower: Int, upper: Int): Int
fun clamp(number: Long, lower: Long, upper: Long): Long
fun clamp(number: Double, lower: Double, upper: Double): Double
```

## Build Configuration

### Supported Platforms
- JVM (with JUnit 5 for tests)
- JavaScript (IR)
- WebAssembly (WASM JS)
- Linux (x64)
- Windows (mingw x64)
- macOS (x64 and ARM64)
- iOS (ARM64, Simulator ARM64, x64)

### Dependencies
- `kotlin-stdlib-common` for common code
- `kotlin-test-common` and `kotlin-test-annotations-common` for tests
- `kotlin-test-junit5` and `junit-jupiter:5.11.0` for JVM tests

### Documentation
- Use Dokka for API documentation generation
- Run `./gradlew dokkaHtml` to generate docs in `build/dokka`

## Adding New Utilities

When adding a new utility function:

1. **Add the function** to `src/commonMain/kotlin/lodash/utils/Lodash.kt`
2. **Write comprehensive tests** in `src/commonTest/kotlin/lodash/utils/LodashTest.kt`
3. **Test edge cases**: null values, empty collections, boundary conditions
4. **Use descriptive test names** with backticks
5. **Follow existing patterns** for similar functions
6. **Document complex logic** with inline comments if needed

### Example Template
```kotlin
// In Lodash.kt
fun newFunction(input: String): String {
    require(input.isNotEmpty()) { "Input must not be empty" }
    // implementation
    return result
}

// In LodashTest.kt
@Test
fun `newFunction - basic usage`() {
    assertEquals("expected", newFunction("input"))
}

@Test
fun `newFunction - handles edge cases`() {
    assertEquals("", newFunction(""))
}
```

## Version and Publishing
- Version is stored in `version.txt` (currently `0.0.1-SNAPSHOT`)
- Uses `com.vanniktech.maven.publish` plugin for publishing
- Group ID: `lodash.utils`
- Artifact ID: `kotlin-lodash`

## Important Notes
- The old `src/main/kotlin/com/kotlinlodash/` structure is legacy and should not be used
- Current active code is in `src/commonMain/kotlin/lodash/utils/`
- All new code should follow the Kotlin Multiplatform common source set structure
- The `Lodash` object in `src/main/kotlin/com/kotlinlodash/Lodash.kt` is empty and superseded by top-level functions
