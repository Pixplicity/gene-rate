# Gene-rate

**Generate a better rating for your Android app!**

It is easy to present your users with a dialog begging for a 5-star rating, but it takes a bit more attention to do it right. This small library takes away the boiler plate and makes it easy to ask for a rating the friendly way.

![Rating SnackBar](http://i.imgur.com/Yw1KUtS.png)

#### Before you Gradle

Before you start, make sure to decide on when and how to ask a user for a rating. Dan Counsell
wrote [an excellent post about it][1] that we full-heartedly agree with. In short, the 'golden
rules' are as follows:

> 1. Don't ask at launch. Seriously, never do this.
> 2. Choose the perfect moment, after a positive interaction is best.
> 3. Try not to interrupt the users workflow, don't be annoying.
> 4. Only ask once. If they've said no, never ask again.
> 5. Ask passively if possible, place it in the app settings or updates notes.

Which is why this library...

1. ... takes a minimum launch count and install time into account.
2. ... does not automatically show a request on launch, but rather when you call
`showRequest()`.
3. ... supports the use of a SnackBar.
4. ... checks 'never ask again' by default.
5. ... well, go ahead! You don't need this library if you do though.


#### Quick Start

Gradle: 

```groovy
compile 'com.pixplicity.generate:library:1.+'
```

Java:

```java
Rate rate = new Rate.Builder().build().count();
...
rate.showRequest();
```


### 1. User friendly

A dialog begging for a rating is intrusive, which is why this library presents two options:

- An easy-to-implement, complete dialog, with a long, friendly, customizable message and the option to quit the nagging forever.
- A shorter and less intrusive SnackBar with the same options and capabilities.

The choice is yours:

**Dialog example**

![Rating dialog](http://i.imgur.com/bywOtbU.png)


**SnackBar example**

![Rating SnackBar](http://i.imgur.com/Yw1KUtS.png)


### 2. App store friendly

A grumpy user is often your best client: they are the ones who will let you know what is wrong and what can be improved. Of course you want to listen to them, but rather not through negative app store reviews. That's why Gene-rate directs happy users to the app store, and dissatisfied users to a point of contact of your choice: phone, email, GetSatisfaction, UserVoice, Reddit, /dev/null, whatever you prefer.


### 3. Developer friendly

Just look:

```java
Rate rate = new Rate.Builder(context)
	.setTriggerCount(10)
	.setMessage(R.string.my_message_text)
	.setFeedbackAction(Uri.parse("mailto:example@example.com"))
	.build();

// When launching the app
rate.count();

// After the user performs a positive action, when it is a good time to show a rating request
rate.showRequest();
```

That's the gist of it! That bit of code will show **a dialog** when the time is ripe. Personally
though, we prefer showing **a SnackBar** instead - it's a lot less intrusive! To show the SnackBar
instead of the dialog simple call the following on the builder:

```java
	.setSnackBarParent(myRootView)
```

Make sure that `myRootView` or one of its parents is a `CooordinatorLayout` though! Otherwise it
can't be swiped away. If this is the case you can make a workaround by calling
`.setSwipeToDismiss(false)`, which both hides the 'Swipe to dismiss' note and changes the duration
of display from `SnackBar.LENGTH_INDEFINITE` to `SnackBar.LENGTH_LONG`. This way the SnackBar hides
automatically and won't linger forever, however the rating request has a lot of content for a
relatively short duration so this is **not advised**.



Here is a final, complete example of what Gene-rate can do:

```java
Rate rate = new Rate.Builder(context)
	.setTriggerCount(10)								// Optional, defaults to 6
	.setMinimumInstallTime(TimeUnit.DAYS.toMillis(7))	// Optional, defaulst to 7 days
    .setFeedbackAction(new OnFeedbackListener() {		// Optional
        @Override
        public void onFeedbackTapped() {
            Toast.makeText(MainActivity.this, "Meh", Toast.LENGTH_SHORT).show();
        }
    })
    .setSnackBarParent(root)							// Optional, shows dialog by default
    .setMessage(R.string.rating_message)				// Optional
    .setPositiveButton("Sure!")							// Optional
    .setCancelButton("Maybe later")						// Optional
    .setNegativeButton("Nope!")							// Optional
    .setSwipeToDismissVisible(false)					// Add this when using the Snackbar
    													// without a CoordinatorLayout as a parent.
    .build();
```

### License

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

[1]: https://www.dancounsell.com/articles/prompting-for-app-reviews