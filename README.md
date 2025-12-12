# kotlin-lodash

A Kotlin Multiplatform library that provides lodash-like utility functions for common programming tasks. Simplify operations on strings, collections, arrays, and more across JVM, JS, Native, and WASM platforms.

## Features

✨ **Multiplatform Support** - Works on JVM, JavaScript, WASM, Linux, Windows, macOS, and iOS  
🔧 **Rich Utilities** - Comprehensive set of utility functions for everyday tasks  
🎯 **Type Safe** - Full Kotlin type safety with null-safety built-in  
📦 **Zero Dependencies** - No external runtime dependencies  
🧪 **Well Tested** - Comprehensive test coverage using kotlin.test

## Installation

Add the dependency to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("io.github.zhouqing86:kotlin-lodash:0.0.1")
}
```

## Usage

### Array & List Utilities

```kotlin
import lodash.*

// Chunk - Split into chunks
val numbers = listOf(1, 2, 3, 4, 5, 6, 7)
val chunked = chunk(numbers, 3)
// Result: [[1, 2, 3], [4, 5, 6], [7]]

// Compact - Remove falsy values
val mixed = listOf(0, 1, false, 2, "", 3, null)
val compacted = compact(mixed)
// Result: [1, 2, 3]

// Difference - Find elements in first array not in others
val diff = difference(listOf(1, 2, 3, 4), listOf(2, 4), listOf(3))
// Result: [1]

// Uniq - Get unique values
val unique = uniq(listOf(1, 2, 2, 3, 3, 3))
// Result: [1, 2, 3]

// FlattenDeep - Deep flatten nested arrays
val nested = listOf(1, listOf(2, listOf(3, listOf(4))))
val flattened = flattenDeep<Int>(nested)
// Result: [1, 2, 3, 4]

// Take & Drop
val taken = take(listOf(1, 2, 3, 4, 5), 3)  // [1, 2, 3]
val dropped = drop(listOf(1, 2, 3, 4, 5), 2)  // [3, 4, 5]
```

### String Utilities

```kotlin
import lodash.*

// Case transformations
val camel = camelCase("hello world")  // "helloWorld"
val kebab = kebabCase("Hello World")  // "hello-world"
val snake = snakeCase("Hello World")  // "hello_world"

// Capitalization
val upperFirst = upperFirst("hello")  // "Hello"
val lowerFirst = lowerFirst("HELLO")  // "hELLO"

// Padding
val padded = padStart("5", 3, '0')  // "005"
val paddedEnd = padEnd("5", 3, '0')  // "500"

// Truncate
val long = "This is a very long string"
val truncated = truncate(long, 10)  // "This is..."

// Trim with custom characters
val trimmed = trim("--Hello--", "-")  // "Hello"
```

### Number Utilities

```kotlin
import lodash.*

// Clamp - Keep number within bounds
val clamped = clamp(10, 0, 5)  // 5
val clamped2 = clamp(-5, 0, 10)  // 0

// InRange - Check if number is in range
val inRange = inRange(3, 0, 5)  // true
val notInRange = inRange(10, 0, 5)  // false

// Random - Generate random integers
val randomNum = random(1, 10)  // Random number between 1 and 10 (inclusive)
```

### Type Checking

```kotlin
import lodash.*

// Check if empty
isEmpty(listOf<Int>())  // true
isEmpty("")  // true
isEmpty(null)  // true
isEmpty(listOf(1, 2, 3))  // false

// Check if not empty
isNotEmpty("hello")  // true
isNotEmpty(listOf(1, 2, 3))  // true

// Null checks
isNull(null)  // true
isNotNull("value")  // true
```

### Functional Programming

```kotlin
import lodash.*

// Times - Execute function n times with return values
val squared = times(5) { it * it }
// Result: [0, 1, 4, 9, 16]

// Times - Execute function n times without return
times(3) { index ->
    println("Iteration $index")
}

// Range - Create ranges
val range1 = range(0, 5)  // [0, 1, 2, 3, 4]
val range2 = range(0, 10, 2)  // [0, 2, 4, 6, 8]
val range3 = range(5, 0, -1)  // [5, 4, 3, 2, 1]
```

### Deep Operations

```kotlin
import lodash.*

// CloneDeep - Deep clone collections
val original = listOf(listOf(1, 2), listOf(3, 4))
val cloned = cloneDeep(original)
// cloned is a deep copy, modifying it won't affect original

val mapOriginal = mapOf("a" to listOf(1, 2, 3))
val mapCloned = cloneDeep(mapOriginal)
```

### Object Utilities

```kotlin
import lodash.*

// Get - Safely access nested properties
val data = mapOf(
    "user" to mapOf(
        "name" to "John",
        "address" to mapOf("city" to "NYC")
    )
)

val name = get(data, "user.name")  // "John"
val city = get(data, "user.address.city")  // "NYC"
val missing = get(data, "user.age", "Unknown")  // "Unknown" (default value)

// Works with arrays/lists too
val list = listOf(
    mapOf("id" to 1, "name" to "Alice"),
    mapOf("id" to 2, "name" to "Bob")
)
val firstName = get(list, "[0].name")  // "Alice"
```

## Platform Support

- **JVM** - Java 8+
- **JavaScript** - Browser and Node.js (IR)
- **WASM** - WebAssembly (WASM JS)
- **Native**:
  - Linux (x64)
  - Windows (mingw x64)
  - macOS (x64 and ARM64)
  - iOS (ARM64, Simulator ARM64, x64)

## Documentation

Generate API documentation locally:

```sh
./gradlew dokkaHtml
```

Documentation will be available in `build/dokka/`.

## Contributing

We welcome contributions! Please read our [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines on how to contribute to this project.

## Code of Conduct

Please review our [Code of Conduct](CODE_OF_CONDUCT.md) before contributing.

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.

## Acknowledgments

This library is inspired by the popular [lodash](https://lodash.com/) JavaScript library, adapted for Kotlin's type system and multiplatform capabilities.
