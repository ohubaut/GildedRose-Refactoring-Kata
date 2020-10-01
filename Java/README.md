# Gilded Rose Kata

## Build, Run & Test

Compile and execute Unit Tests

On Linux & MacOS:
```shell script
./gradlew build
```

On Windows:
```shell script
gradlew.bat build
```

## Refactoring Decisions

* API should remain backward compatible as much as possible => `GildedRose` class visibility and method parameters
  should stay as is;
* `Item` class should be treated as an immutable dependency => state changes should be propagated, even if handled
  differently;
* New classes should reside in a different package, to reduce the risk of API leakage;
* Inventory goods should own their update logic;
* Extract logic to update `Item` properties as separate mutators for sellIn and quality.

## Notes

* `internal` is not an ideal name for a package, but it is a well-known convention for non-public API (until we
  decide to move to JPMS)
* We expect that the provided `Item[] items` in the constructor to not be modified by the supplier afterward 
  (i.e: swapping an item with another one)
* We don't really have control on initial quality of items. We have to assume that the provided once will respect the
  describe boundaries
