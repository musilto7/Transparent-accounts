# Transparent Accounts (Android)

Android app for browsing Czech Savings Bank transparent accounts.

## Tech Stack

- Kotlin + Jetpack Compose
- Paging 3
- Koin (DI)
- Ktor client
- Navigation 3 (`NavDisplay` + `NavKey`)
- OpenAPI Generator (DTO/API client generated into `app/build/generated/sources/openapi`)

## Requirements

- JDK 11+
- Android SDK (compileSdk 36)
- API key for `WEB-API-key`

## Setup

Provide API key in one of these ways:

1. Gradle property: `API_KEY_ID`
2. Environment variable: `API_KEY_ID`
3. `local.properties`:

```properties
API_KEY_ID=your_key_here
```

`BuildConfig.API_KEY` is resolved from those sources in this order.

## Build & Run

From project root:

```bash
./gradlew :app:assembleDebug
```

Install debug build:

```bash
./gradlew :app:installDebug
```

Run tests:

```bash
./gradlew :app:testDebugUnitTest
```

## OpenAPI / Generated Sources

Source spec:

- `app/src/main/openapi/transparent-accounts-api.yaml`

Generated code output:

- `app/build/generated/sources/openapi`

Generation task:

```bash
./gradlew :app:openApiGenerate
```

`compileDebugKotlin` and `compileReleaseKotlin` depend on `openApiGenerate`, so generation is triggered automatically on build.

## Project Structure (high-level)

- `app/.../app` – app entry, DI bootstrap, navigation setup
- `features/accounts/data` – datasource, repository, paging
- `features/accounts/domain` – domain models + repository contract
- `features/accounts/presentation` – screens, mappers, viewmodels
- `common/domain` – shared domain primitives/errors
- `common/presentation` – shared UI components/theme/error formatting

## Navigation Notes

The app uses Navigation 3 key-based navigation (no string routes):

- Start key: `Accounts`
- Detail key: `AccountDetail(accountId: AccountId)`
- Back stack: `rememberNavBackStack(...)`
- Display: `NavDisplay(...)` with entry decorators for saveable state + ViewModel store
