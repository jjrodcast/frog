package pe.com.jjrodcast.frogsample

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*
import pe.com.jjrodcast.frog.FrogNotification
import pe.com.jjrodcast.frog.entities.FrogChannel
import pe.com.jjrodcast.frog.entities.FrogMessage
import pe.com.jjrodcast.frog.entities.FrogPerson
import pe.com.jjrodcast.frog.styles.FrogBigPictureStyle
import pe.com.jjrodcast.frog.styles.FrogBigTextStyle
import pe.com.jjrodcast.frog.styles.FrogInboxStyle
import pe.com.jjrodcast.frog.styles.FrogMessagingStyle
import java.sql.Timestamp
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSimpleNotification.setOnClickListener {
            simpleNotification()
        }

        btnExpandableBigTextNotification.setOnClickListener {
            expandableBigTextNotification()
        }

        btnExpandableBigPictureNotification.setOnClickListener {
            expandableBigPictureNotification()
        }

        btnExpandableInboxNotification.setOnClickListener {
            expandableInboxNotification()
        }

        btnExpandableMessagingNotification.setOnClickListener {
            expandableMessagingNotification()
        }

        btnGroupNotification.setOnClickListener {
            groupNotification()
        }

        btnOpenActivityNotification.setOnClickListener {
            simpleNotificationSendActivity()
        }
    }

    private fun simpleNotification() {
        FrogNotification.get()
            .init(FrogChannel("channelId", "channelName", NotificationManagerCompat.IMPORTANCE_DEFAULT))
            .contentTitle("Simple Notification")
            .contentText("This is a content for the simple notification")
            .smallIcon(R.drawable.frog)
            .showWhen(true)
            .build()
    }

    private fun expandableBigTextNotification() {
        FrogNotification.get()
            .init(FrogChannel("channelExpandId", "channelExpandName", NotificationManagerCompat.IMPORTANCE_DEFAULT))
            .smallIcon(R.drawable.frog)
            .contentTitle("Expandable big text notification")
            .contentText("This is a content for the expandable notification")
            .largeIcon(R.drawable.frog)
            .style(FrogBigTextStyle("This is a content for the expandable notification. Lorem ipsum dolor sit amet consectetur adipiscing elit facilisi congue, dis conubia eleifend facilisis dapibus quam pharetra morbi, a tempor ligula hac suscipit sodales vestibulum lobortis. Diam ac risus dictumst curae facilisis dictum varius viverra, ullamcorper ridiculus curabitur velit at mollis vehicula, phasellus dis accumsan aenean litora vulputate felis. Lobortis proin dis ut tortor tristique enim sapien molestie, dictum imperdiet blandit platea cubilia erat neque, nec pretium montes pellentesque mattis nisl massa."))
            .build()
    }

    private fun expandableBigPictureNotification() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.frog)

        FrogNotification.get()
            .init(FrogChannel("channelExpandId", "channelExpandName", NotificationManagerCompat.IMPORTANCE_DEFAULT))
            .smallIcon(R.drawable.frog)
            .contentTitle("Expandable big picture notification")
            .contentText("Image of my frog")
            .largeIcon(R.drawable.frog)
            .style(FrogBigPictureStyle(bitmap).enableThumbnail())
            .build()
    }

    private fun expandableInboxNotification() {

        val messages = arrayListOf("First mail from Joao", "Second mail from Josue", "Thrid mail from Alex")

        FrogNotification.get()
            .init(FrogChannel("channelExpandId", "channelExpandName", NotificationManagerCompat.IMPORTANCE_DEFAULT))
            .smallIcon(R.drawable.frog)
            .contentTitle("3 New mails from OneCode")
            .contentText("Important mails")
            .largeIcon(R.drawable.frog)
            .style(FrogInboxStyle().addLines(messages))
            .build()
    }

    private fun expandableMessagingNotification() {
        // 1. Create a FrogPerson
        val person = FrogPerson(name = "OneCode", isImportant = true)

        // 2. Create the messages
        val messages = createMessages()

        // 3. Create the notification with MessagingStyle
        FrogNotification.get()
            .init(FrogChannel("channelExpandId", "channelExpandName", NotificationManagerCompat.IMPORTANCE_DEFAULT))
            .smallIcon(R.drawable.frog)
            .largeIcon(R.drawable.frog)
            .style(
                FrogMessagingStyle(person)
                    .conversationTitle("Conversation title")
                    .isGroupConversation(true).addMessages(messages)
            )
            .build()
    }

    private fun createMessages(): List<FrogMessage> {

        val messages = arrayListOf<FrogMessage>()
        for (i in 0..2) {
            val date = Date()
            val timestamp = Timestamp(date.time)
            val person = FrogPerson("Person ${i + 1}")
            messages.add(FrogMessage("This is te message $i", timestamp.time, person))
        }

        return messages.toList()
    }

    private fun groupNotification() {
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
            .style(
                FrogInboxStyle()
                    .addLines(arrayListOf("Person 1 sent new message", "Person 2 sent new message"))
                    .bigContentTitle("Group big content title")
                    .summaryText("Group summary text")
            )
            .build()
    }

    private fun simpleNotificationSendActivity() {
        val bundle = Bundle()
        bundle.putString("name", "OneCode-Frog")
        bundle.putInt("version", 1)
        bundle.putBoolean("fromNotification", true)

        FrogNotification.get()
            .init(FrogChannel("channelId", "channelName", NotificationManagerCompat.IMPORTANCE_DEFAULT))
            .contentTitle("Open Activity Notification")
            .contentText("Click to open other activity")
            .smallIcon(R.drawable.frog)
            .autoCancel(true)
            .click(TargetActivity::class.java, bundle)
            .showWhen(true)
            .build()
    }
}
