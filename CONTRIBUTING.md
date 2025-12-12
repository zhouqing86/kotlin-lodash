# CONTRIBUTING.md

# Contributing to Kotlin Lodash

Thank you for considering contributing to Kotlin Lodash! We welcome contributions from the community. Please follow the guidelines below to help us maintain a high-quality project.

## How to Contribute

All contributions should be made via **Pull Requests from a forked repository**. Direct pushes to the main repository are not permitted.

### Fork and Clone Workflow

1. **Fork the Repository**: Fork the repository on GitHub to create a copy under your GitHub account.

2. **Clone Your Fork**: Clone your forked repository to your local machine:
   ```
   git clone https://github.com/your-username/kotlin-lodash.git
   cd kotlin-lodash
   ```

3. **Add Upstream Remote** (optional): Track the original repository for staying updated:
   ```
   git remote add upstream https://github.com/zhouqing86/kotlin-lodash.git
   ```

4. **Create a Feature Branch**: Create a new branch in your forked repository for your work:
   ```
   git checkout -b feature/your-feature-name
   ```

5. **Make Your Changes**: Implement your changes following the project's coding standards and guidelines.

6. **Write Tests**: Add comprehensive tests for your changes in test file for instance `src/commonTest/kotlin/lodash/ObjectTest.kt`.

7. **Run Tests**: Verify that all tests pass:
   ```
   ./gradlew test
   ```

8. **Commit Your Changes**: Commit with clear, descriptive messages:
   ```
   git commit -m "Add feature: description"
   ```

9. **Push to Your Fork**: Push your branch to your forked repository:
   ```
   git push origin feature/your-feature-name
   ```

10. **Create a Pull Request**: Open a Pull Request from your fork to the main repository. Provide a clear description of your changes and their purpose.

## Code of Conduct

Please adhere to our [Code of Conduct](CODE_OF_CONDUCT.md) in all interactions related to the project.

## Issues

If you encounter any issues or have suggestions, please open an issue in the GitHub repository.

## Thank You!

We appreciate your contributions and support in making Kotlin Lodash better!