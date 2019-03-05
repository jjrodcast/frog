/*
 * Copyright 2019 OneCode. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pe.com.jjrodcast.frog.entities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import pe.com.jjrodcast.frog.extensions.isOreoOrHigher
import pe.com.jjrodcast.frog.intents.FrogClickPendingIntent
import pe.com.jjrodcast.frog.providers.FrogNotificationManager
import pe.com.jjrodcast.frog.styles.FrogNotificationStyle

abstract class Builder(private val context: Context, private val channel: FrogChannel) {

    protected val builder: NotificationCompat.Builder by lazy {
        NotificationCompat.Builder(
            context,
            channel.channelId.trim()
        )
    }
    private val notificationManager: NotificationManager = FrogNotificationManager.createManager()
    private var tag: String? = null
    private var id: Int = 0
    private var existsSmallIcon = false
    /**
     * Set the identifier for the channel.
     *
     * @param id Identifier for the channel. Default value is 0.
     *
     */
    fun id(id: Int): Builder {
        this.id = id
        return this
    }

    /**
     * Set the tag for the channel.
     *
     * @param tag Tag for the channel. Default value is null.
     *
     */
    fun tag(tag: String): Builder {
        this.tag = tag
        return this
    }

    /**
     * Creates the channel with compatibility. Only devices with Android 8 or higher will create
     * this channel.
     *
     */
    private fun createChannel() {
        if (isOreoOrHigher()) {
            with(channel) {
                val notificationChannel = NotificationChannel(channelId, name, importance).apply {
                    this.description?.let { description = it }
                    setShowBadge(showBadge)
                    enableLights(enableLights)
                    enableVibration(enableVibration)
                }
                val curChannel = notificationManager.getNotificationChannel(channelId)
                if (curChannel == null) notificationManager.createNotificationChannel(notificationChannel)
            }
        }
    }

    /**
     * Notify the system that a new notification has arrived.
     *
     */
    private fun createNotify() {
        tag?.let {
            notificationManager.notify(tag, id, builder.build())
        } ?: kotlin.run {
            notificationManager.notify(id, builder.build())
        }
    }

    /**
     * This function gets the string from the resource assets.
     *
     * @param resourceId A resource ID in the application's package of the
     *                  drawable to use
     *
     */
    private fun getStringResource(@StringRes resourceId: Int): String {
        return context.getString(resourceId)
    }

    /**
     * Control whether the timestamp is shown in the content view.
     *
     * @param show Value to show or hide the timestamp
     * @see android.app.Notification.Builder.setShowWhen
     *
     */
    fun showWhen(show: Boolean): Builder {
        builder.setShowWhen(show)
        return this
    }

    /**
     * Set the timestamp for the current notification.
     *
     * @param timestamp Timestamp value
     * @see android.app.Notification.Builder.setWhen
     *
     */
    fun setWhen(timestamp: Long): Builder {
        builder.setWhen(timestamp)
        return this
    }

    /**
     * Set the small icon resource, which will be used to represent the
     * notification in the status bar.
     *
     * @param smallIcon A resource ID in the application's package of the
     *                  drawable to use.
     * @see android.app.Notification.Builder.setSmallIcon
     *
     */
    fun smallIcon(@DrawableRes smallIcon: Int): Builder {
        builder.setSmallIcon(smallIcon)
        existsSmallIcon = true
        return this
    }

    /**
     * Set the priority of the notification in the status bar.
     *
     * @param priority Priority for the current notification
     *
     *
     */
    fun priority(priority: Int): Builder {
        if (!isOreoOrHigher()) builder.priority = priority
        return this
    }

    /**
     * Set the text (second row) of the notification, in a standard notification.
     *
     * @param text Set the content text
     *
     * @see android.support.v4.app.NotificationCompat.Builder.setContentText
     *
     */
    fun contentText(text: CharSequence?): Builder {
        builder.setContentText(text)
        return this
    }

    /**
     * Set the text (second row) of the notification, in a standard notification.
     *
     * @param text Set the String resource to the content text
     *
     * @see android.support.v4.app.NotificationCompat.Builder.setContentText
     *
     */
    fun contentText(@StringRes text: Int): Builder {
        contentText(getStringResource(text))
        return this
    }

    /**
     * Set the visibility for the notification
     *
     * @param visibility Visibility for the notification
     *
     * @see android.support.v4.app.NotificationCompat.Builder.setVisibility
     *
     */
    fun visibility(visibility: Int): Builder {
        builder.setVisibility(visibility)
        return this
    }

    /**
     * Set the progress this notification represents, which may be represented as a ProgressBar.
     *
     * @param max Maximum value for the progress
     * @param indeterminate Set if the progress will be indeterminate
     *
     * @see android.support.v4.app.NotificationCompat.Builder.setProgress
     *
     */
    fun progress(max: Int, progress: Int, indeterminate: Boolean): Builder {
        builder.setProgress(max, progress, indeterminate)
        return this
    }

    /**
     * Set the notification category. Must be one of the predefined notification categories
     *
     * @param category Category for the notification
     * @see android.support.v4.app.NotificationCompat.Builder.setCategory
     *
     */
    fun category(category: String): Builder {
        builder.setCategory(category)
        return this
    }

    /**
     * Setting this flag will make it so the notification is automatically canceled when the user clicks it in the panel.
     *
     * @param autoCancel Set if the notification is auto cancelable or not
     * @see android.support.v4.app.NotificationCompat.Builder.setAutoCancel
     *
     */
    fun autoCancel(autoCancel: Boolean): Builder {
        builder.setAutoCancel(autoCancel)
        return this
    }

    /**
     * Set the large icon that is shown in the ticker and notification.
     *
     * @param icon Bitmap for the icon
     * @see android.support.v4.app.NotificationCompat.Builder.setLargeIcon
     *
     */
    fun largeIcon(icon: Bitmap?): Builder {
        builder.setLargeIcon(icon)
        return this
    }

    /**
     * Set the large icon that is shown in the ticker and notification.
     *
     * @param icon DrawableRes for the icon
     * @see android.support.v4.app.NotificationCompat.Builder.setLargeIcon
     *
     */
    fun largeIcon(@DrawableRes icon: Int): Builder {
        val resIcon = BitmapFactory.decodeResource(context.resources, icon)
        builder.setLargeIcon(resIcon)
        return this
    }

    /**
     * Set the title (first row) of the notification, in a standard notification.
     *
     * @param title Title for the notification
     * @see android.support.v4.app.NotificationCompat.Builder.setContentTitle
     *
     */
    fun contentTitle(title: CharSequence): Builder {
        builder.setContentTitle(title)
        return this
    }

    /**
     * Set the title (first row) of the notification, in a standard notification.
     *
     * @param title StringRes id for the Title of the notification
     * @see android.support.v4.app.NotificationCompat.Builder.setContentTitle
     *
     */
    fun contentTitle(@StringRes title: Int): Builder {
        contentTitle(getStringResource(title))
        return this
    }

    /**
     * Set whether this is an ongoing notification.
     * Ongoing notifications differ from regular notifications in the following ways:
     *      Ongoing notifications are sorted above the regular notifications in the notification panel.
     *      Ongoing notifications do not have an 'X' close button, and are not affected by the "Clear all" button.
     *
     *  @param ongoing Set whether this is an ongoing notification
     *  @see android.support.v4.app.NotificationCompat.Builder.setOngoing
     *
     */

    fun ongoing(ongoing: Boolean): Builder {
        builder.setOngoing(ongoing)
        return this
    }

    /**
     * Set this flag if you would only like the sound, vibrate and ticker to be played if the notification is not already showing.
     *
     * @param onlyAlertOnce Set if the notification is only alert once time.
     * @see android.support.v4.app.NotificationCompat.Builder.setOnlyAlertOnce
     *
     */
    fun alertOnce(onlyAlertOnce: Boolean): Builder {
        builder.setOnlyAlertOnce(onlyAlertOnce)
        return this
    }

    /**
     * Signature for implementation of lights
     *
     */
    open fun lights(@ColorInt argb: Int, timeOnMs: Int, timeOffMs: Int) = this

    /**
     * Signature for implementation of counter
     *
     */
    open fun counter(number: Int) = this

    /**
     * Set the sound to play. It will play on the default stream.
     * On some platforms, a notification that is noisy is more likely to be presented as a heads-up notification.
     *
     * @param sound Uri for the sound that will be used for the notification
     * @see android.support.v4.app.NotificationCompat.Builder.setSound
     *
     */
    fun sound(sound: Uri): Builder {
        builder.setSound(sound)
        return this
    }

    /**
     * Set the sound to play. It will play on the default stream.
     * On some platforms, a notification that is noisy is more likely to be presented as a heads-up notification.
     *
     * @param type Type of sound that is built-in resoruce
     * @see android.support.v4.app.NotificationCompat.Builder.setSound
     * @see android.media.RingtoneManager.getDefaultUri
     *
     */
    fun sound(type: Int): Builder {
        val uri = RingtoneManager.getDefaultUri(type)
            ?: throw IllegalArgumentException("sound type is not defined")
        sound(uri)
        return this
    }

    /**
     * Set the color for the notification
     *
     * @param ColorRes id (rgba) for the notification color
     * @see ContextCompat.getColor
     * @see android.support.v4.app.NotificationCompat.Builder.setColor
     *
     */
    fun color(@ColorRes argb: Int): Builder {
        builder.color = ContextCompat.getColor(context, argb)
        return this
    }

    /**
     * Set metadata for this notification.
     * A reference to the Bundle is held for the lifetime of this Builder, and the Bundle's current contents are copied into the Notification each time build() is called.
     * Replaces any existing extras values with those from the provided Bundle. Use addExtras(Bundle) to merge in metadata instead.
     *
     * @param extras Metadata for the notification
     * @see android.support.v4.app.NotificationCompat.Builder
     *
     */
    fun extras(extras: Bundle): Builder {
        builder.extras = extras
        return this
    }

    /**
     * Add a rich notification style to be applied at build time.
     * If the platform does not provide rich notification styles, this method has no effect. The user will always see the normal notification style.
     *
     * @param style Style that will be set to the notification
     *
     * @see FrogNotificationStyle
     * @see android.support.v4.app.NotificationCompat.Style
     *
     */
    fun style(style: FrogNotificationStyle): Builder {
        builder.setStyle(style.create())
        return this
    }

    /**
     * Set this notification to be part of a group of notifications sharing the same key. Grouped notifications may display in a cluster or stack on devices which support such rendering.
     * To use this feature also set groupSummary as true
     * @param name Name for the group
     *
     * @see android.support.v4.app.NotificationCompat.Builder.setGroup
     *
     */
    fun group(name: String): Builder {
        if (name.trim().isEmpty()) throw IllegalArgumentException("Group key is empty")
        builder.setGroup(name)
        return this
    }

    /**
     * Supply a PendingIntent to send when the notification is clicked.
     *
     * @param activity Activity that will be started
     * @param bundle Additional options for how the Activity should be started. May be null if there are no options.
     *
     * @see android.app.PendingIntent
     *
     */
    fun click(activity: Class<*>, bundle: Bundle?): Builder {
        builder.setContentIntent(FrogClickPendingIntent(activity, bundle, id).onCreatePendingIntent(context))
        return this
    }

    /**
     * Supply a PendingIntent to send when the notification is clicked.
     *
     * @param activity Activity that will be started
     * @param bundle Additional options for how the Activity should be started. May be null if there are no options.
     * @param requestCode Private request code for the sender
     *
     * @see android.app.PendingIntent
     *
     */
    fun click(activity: Class<*>, bundle: Bundle?, requestCode: Int): Builder {
        builder.setContentIntent(FrogClickPendingIntent(activity, bundle, requestCode).onCreatePendingIntent(context))
        return this
    }

    /**
     * Supply a PendingIntent to send when the notification is clicked.
     *
     * @param pendingIntent PendingIntent to be fired when the action is invoked.
     *
     * @see android.app.PendingIntent
     *
     */
    fun click(pendingIntent: PendingIntent): Builder {
        builder.setContentIntent(pendingIntent)
        return this
    }

    /**
     * Add an action to this notification.
     *
     * @param action The action to add.
     *
     * @see android.support.v4.app.NotificationCompat.Action
     *
     */
    fun addAction(action: NotificationCompat.Action): Builder {
        builder.addAction(action)
        return this
    }

    /**
     * Add an action to this notification.
     *
     * @param icon Resource ID of a drawable that represents the action.
     * @param title Text describing the action
     * @param intent PendingIntent to be fired when the action is invoked.
     *
     * @see android.support.v4.app.NotificationCompat.Builder.addAction
     * @see android.support.v4.app.NotificationCompat.Builder.setContentIntent
     * @see android.app.PendingIntent
     *
     */
    fun addAction(@DrawableRes icon: Int, title: CharSequence, intent: PendingIntent): Builder {
        builder.addAction(icon, title, intent)
        return this
    }


    /**
     * Add an action to this notification.
     *
     * @param icon Resource ID of a drawable that represents the action.
     * @param title Text describing the action
     * @param intent PendingIntent to be fired when the action is invoked.
     *
     * @see android.support.v4.app.NotificationCompat.Builder.addAction
     * @see android.support.v4.app.NotificationCompat.Builder.setContentIntent
     * @see android.app.PendingIntent
     *
     */
    fun addAction(@DrawableRes icon: Int, @StringRes title: Int, intent: PendingIntent): Builder {
        builder.addAction(icon, getStringResource(title), intent)
        return this
    }

    /**
     * Build the channel and the notification
     *
     */
    fun build() {
        if (channel.channelId.trim().isEmpty()) throw IllegalArgumentException("ChannelId must not be empty")
        if (channel.name.trim().isEmpty()) throw IllegalArgumentException("Description must not be empty")
        createChannel()
        createNotify()
    }

}