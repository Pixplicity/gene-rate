# Changes

### 1.1.8 (Sep 2017)

- Fixed: dimissing Snackbar would not store the checkbox state.
- Updated Gradle plugin and wrapper
- Fixed: Snackbar would appear too often.
- Added `getCount` and `getRemainingCount` methods to keep track of app launch counts.
- More elaborate example app
- Java 8 update 
- Support for ridiculous number of app launches

### 1.1.6 (Jul 12, 2017)

Skipped some versions due to publication issues.

- Fixed: checkbox and text would appear as dark text on dark background when using a dark app theme.

### 1.1.3 (Jun 15, 2017)

- SDK 26
- Added `reset` method to clear settings during testing.

### 1.1.2 (Mar 15, 2017)

- Message in SnackBar could appear as black text on dark background.

### 1.1.0 (Jan 17, 2017)

- `launched()` and `check()` deprecated and replaced by `count()` and `showRequest()`.
- Larger **SnackBar** with functionality like the dialog.
- Added 'swipe to dismiss' to SnackBar (note: requires the SnackBar parent to be a `CoordinatorLayout`).
- Replaced `DialogInterface.OnClickListener` with `onFeedbackListener` as type for feedback callbacks.
- Underlined feedback buttons to give the impression of a link.

### 1.0 Initial release (Jan 7, 2017)

First release, includes dialog and (simple) SnackBar.
