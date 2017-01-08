# Gene-rate

**Generate a better rating for your Android app!**

It is easy to present your users with a dialog begging for a 5-star rating, but it takes a bit more attention to do it right. This small library takes away the boiler plate and makes it easy to ask for a rating the friendly way.

#### Quick Start

Gradle: 

```groovy
compile 'com.pixplicity.generate:library:1.0'
```

Java:

```java
Rate rate = new Rate.Builder().build().launched();
...
rate.check();
```


### User friendly

A dialog begging for a rating is intrusive, which is why this library presents two options:

- A complete dialog, with a long, friendly, customizable message and a the option to quit the nagging forever.
- A compact, non-intrusive SnackBar with a short message and a single button.

The choice is yours.


### App store friendly

A grumpy user is often your best client: they are the ones who will let you know what is wrong and what can be improved. Of course you want to listen to them, but rather not through negative app store reviews. That's why Gene-rate directs happy users to the app store, and dissatisfied users to a point of contact of your choice: phone, email, GetSatisfaction, UserVoice, Reddit, /dev/null, whatever you prefer.


### Developer friendly

Just look:

```java
Rate rate = new Rate.Builder(context)
	.setTriggerCount(10)
	.setMinimumInstallTime(TimeUnit.DAYS.toMillis(7))
	.setMessage(R.string.my_message_text)
	.setFeedbackAction(Uri.parse("mailto:example@example.com"))
	.build();

// When launching the app
rate.launched();

// When it is a good time to show a rating request
rate.check();
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
