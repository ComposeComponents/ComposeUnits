# Units
[![Build](https://github.com/ComposeComponents/Units/actions/workflows/build.yml/badge.svg)](https://github.com/ComposeComponents/Units/actions/workflows/build.yml)
[![Lint](https://github.com/ComposeComponents/Units/actions/workflows/lint.yml/badge.svg)](https://github.com/ComposeComponents/Units/actions/workflows/lint.yml)

A helper library for defining spacing and textsizes as a multiple of some base unit (default 16dp/16sp).

## Installation
![Stable](https://img.shields.io/github/v/release/ComposeComponents/Units?label=Stable)
![Preview](https://img.shields.io/github/v/release/ComposeComponents/Units?label=Preview&include_prereleases)

```
implementation "cl.emilym.compose:units:<latest>"
```

## Usage
```kotlin
CompositionLocalProvider(
    LocalBaseDp provides 8.dp
) {
    // Box is sized to 8dp by 8dp
    Box(Modifier.size(2.rdp))
}
```