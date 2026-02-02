# AlphaTrace

A kid-friendly Android app for learning to trace uppercase letters A-Z. Built with Jetpack Compose and Material 3.

## Features

- **26 uppercase letters** with dashed guide paths on a drawing canvas
- **Touch-based tracing** with multi-stroke support (lift and re-touch)
- **Scoring system** (0-3 stars) based on coverage and accuracy
- **Progress tracking** - stars persist locally using DataStore
- **Kid-friendly UI** - warm cream background, coral strokes, gold stars, rounded corners

## Screenshots

| Home Screen | Tracing Screen |
|:-----------:|:--------------:|
| 4-column grid of A-Z tiles with star ratings | Canvas with dashed guides and coral drawing |

## Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose + Material 3
- **Navigation:** Navigation Compose
- **Persistence:** DataStore Preferences
- **Min SDK:** 24 (Android 7.0)
- **Target SDK:** 36

## Project Structure

```
com.alphatrace.app/
├── MainActivity.kt
├── AlphaTraceApp.kt
├── data/
│   ├── LetterData.kt          # Stroke & LetterDefinition models
│   ├── LetterPaths.kt         # All 26 letter polyline definitions
│   └── ProgressRepository.kt  # DataStore star persistence
├── ui/
│   ├── theme/                  # Colors, typography, theme
│   ├── home/
│   │   └── HomeScreen.kt      # Letter grid with star indicators
│   ├── tracing/
│   │   ├── TracingScreen.kt   # Main tracing UI + controls
│   │   ├── TracingCanvas.kt   # Canvas with touch input
│   │   └── HitDetection.kt    # Scoring algorithm
│   └── components/
│       ├── StarRating.kt      # Reusable star display
│       └── LetterTile.kt      # Single grid tile
└── navigation/
    └── NavGraph.kt             # Home <-> Tracing routes
```

## Scoring Algorithm

Two-metric weighted scoring:

- **Coverage (60%):** Fraction of guide path sample points within 7% canvas-width tolerance of a user point
- **Accuracy (40%):** Fraction of user points within tolerance of a guide line segment
- **Stars:** 3 stars (combined >= 0.80), 2 stars (>= 0.55), 1 star (>= 0.35)

## Build

```bash
# Debug build
./gradlew assembleDebug

# Release build (R8 minification enabled)
./gradlew assembleRelease
```

## License

This project is for educational purposes.
