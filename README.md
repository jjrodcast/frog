Frog
=======
Frog is a library that can handle notifications for Android with compatibility on different devices.

![frog-logo](https://user-images.githubusercontent.com/7152507/53774892-4d1d8780-3ebe-11e9-8738-369a355b0a2a.png)

Download
--------

Step 1: Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

Step 2: Add the dependency
```gradle
dependencies {
  implementation 'com.github.jjrodcast:frog:1.0.0'
}
```

How do I use Frog?
-------------------

To create a notification using Frog you have to do the next:

You can use other configuration methods that the library provides you.

Frog Simple Notification: 
```kotlin
FrogNotification.get()
  .init(FrogChannel("channelId", "channelName", NotificationManagerCompat.IMPORTANCE_DEFAULT))
  .contentTitle("Simple Notification")
  .contentText("This is a content for the simple notification")
  .smallIcon(R.drawable.frog)
  .showWhen(true)
  .build()
```

Frog BigText Notification:
```kotlin
FrogNotification.get()
  .init(FrogChannel("channelExpandId", "channelExpandName", NotificationManagerCompat.IMPORTANCE_DEFAULT))
  .smallIcon(R.drawable.frog)
  .contentTitle("Expandable big text notification")
  .contentText("This is a content for the expandable notification")
  .largeIcon(R.drawable.frog)
  .style(FrogBigTextStyle("This is a content for the expandable notification. Lorem ipsum dolor..."))
  .build()
```

Frog BigPicture Notification
```kotlin
 FrogNotification.get()
  .init(FrogChannel("channelExpandId", "channelExpandName", NotificationManagerCompat.IMPORTANCE_DEFAULT))
  .smallIcon(R.drawable.frog)
  .contentTitle("Expandable big picture notification")
  .contentText("Image of my frog")
  .largeIcon(R.drawable.frog)
  .style(FrogBigPictureStyle(bitmap).enableThumbnail())
  .build()
```

Frog Inbox Notification:
```kotlin
  FrogNotification.get()
    .init(FrogChannel("channelExpandId", "channelExpandName", NotificationManagerCompat.IMPORTANCE_DEFAULT))
    .smallIcon(R.drawable.frog)
    .contentTitle("3 New mails from OneCode")
    .contentText("Important mails")
    .largeIcon(R.drawable.frog)
    .style(FrogInboxStyle().addLines(messages))
    .build()
```

Frog Messaging Notification:
```kotlin
// 1. Create a FrogPerson
val person = FrogPerson(name = "OneCode", isImportant = true)
// 2. Create the messages
val messages = createMessages()
// 3. Create the notification with FrogMessagingStyle
FrogNotification.get()
  .init(FrogChannel("channelExpandId", "channelExpandName", NotificationManagerCompat.IMPORTANCE_DEFAULT))
  .smallIcon(R.drawable.frog)
  .largeIcon(R.drawable.frog)
  .style(FrogMessagingStyle(person)
         .conversationTitle("Conversation title")
         .isGroupConversation(true).addMessages(messages))
  .build()
```

Frog with Activity Navigation:
```kotlin
//1. Metadata for the notification
val bundle = Bundle()
bundle.putString("name", "OneCode-Frog")
bundle.putInt("version", 1)
bundle.putBoolean("fromNotification", true)
//2. Create the notification with click method
FrogNotification.get()
  .init(FrogChannel("channelId", "channelName", NotificationManagerCompat.IMPORTANCE_DEFAULT))
  .contentTitle("Open Activity Notification")
  .contentText("Click to open other activity")
  .smallIcon(R.drawable.frog)
  .autoCancel(true)
  .click(TargetActivity::class.java, bundle)
  .showWhen(true)
  .build()
```

Frog using Groups Notification:
```kotlin
// 1. Group Name
val groupKey = "groupkey"
// 2. Create the channel
val channel = FrogChannel("channelGroupId", "channelGroupName", NotificationManagerCompat.IMPORTANCE_DEFAULT)
// 2. Create the independent notification (set a different id that's why we use System.currentTimeMillis())
FrogNotification.get()
  .init(channel)
  .id(System.currentTimeMillis().toInt())
  .smallIcon(R.drawable.frog)
  .contentTitle("Noti1: Content Title")
  .contentText("Noti1: Content Text information")
  .alertOnce(true)
  .group(groupKey)
  .build()

FrogNotification.get()
  .init(channel)
  .id(System.currentTimeMillis().toInt())
  .smallIcon(R.drawable.frog)
  .contentTitle("Noti2: Content Title")
  .contentText("Noti2: Content Text information")
  .alertOnce(true)
  .group(groupKey)
  .build()

// 3. Create the notification with initGroup (use the same id  or the notification will be updated)
FrogNotification.get()
  .initGroup(channel, groupKey)
  .id(100)
  .contentTitle("Title for group messages")
  .contentText("Content of messages")
  .alertOnce(true)
  .smallIcon(R.drawable.frog)
  .largeIcon(R.drawable.frog)
  .style(FrogInboxStyle()
         .addLines(arrayListOf("Person 1 sent new message", "Person 2 sent new message"))
         .bigContentTitle("Group big content title")
         .summaryText("Group summary text"))
  .build()
```

Author
------
Jorge Rodríguez Castillo - @jjrodcast on GitHub, @jjrodcast on Twitter


Licences
--------
    Copyright 2019 OneCode. All rights reserved.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
