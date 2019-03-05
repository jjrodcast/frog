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

package pe.com.jjrodcast.frog.styles

import android.support.v4.app.NotificationCompat
import pe.com.jjrodcast.frog.entities.FrogMessage
import pe.com.jjrodcast.frog.entities.FrogPerson

class FrogMessagingStyle(private val person: FrogPerson) : FrogNotificationStyle() {

    private val style: NotificationCompat.MessagingStyle by lazy { NotificationCompat.MessagingStyle(person.toPerson()) }

    /**
     * Creates an instance of NotificationCompat.MessagingStyle
     *
     * @see android.support.v4.app.NotificationCompat.MessagingStyle
     * @return Return the style of the Notification.
     *
     */
    override fun create(): NotificationCompat.Style {
        return style
    }

    /**
     * Set a conversation title for group chats with more than two people.
     *
     * @param conversationTitle Title for group conversation
     *
     * @see android.support.v4.app.NotificationCompat.MessagingStyle.setConversationTitle
     * @return Return FrogMessagingStyle
     *
     */
    fun conversationTitle(conversationTitle: String): FrogMessagingStyle {
        style.conversationTitle = conversationTitle
        return this
    }

    /**
     * Sets whether this conversation notification represents a group.
     *
     * @param isGroupConversation true if the conversation represents a group, false otherwise.
     *
     * @see android.support.v4.app.NotificationCompat.MessagingStyle.isGroupConversation
     * @return Return FrogMessagingStyle
     *
     */
    fun isGroupConversation(isGroupConversation: Boolean): FrogMessagingStyle {
        style.isGroupConversation = isGroupConversation
        return this
    }

    /**
     * Adds a message for display in this notification
     *
     * @param text A CharSequence to be displayed as the message content
     * @param timestamp Time at which the message arrived in ms since Unix epoch
     *
     * @see android.support.v4.app.NotificationCompat.MessagingStyle.addMessage
     * @return Return FrogMessagingStyle
     *
     */
    fun addMessage(text: CharSequence, timestamp: Long): FrogMessagingStyle {
        style.addMessage(text, timestamp, person.toPerson())
        return this
    }

    /**
     * Adds a message for display in this notification
     *
     * @param message The message that will be shown
     *
     * @see android.support.v4.app.NotificationCompat.MessagingStyle.addMessage
     * @return Return FrogMessagingStyle
     *
     */
    fun addMessage(message: FrogMessage): FrogNotificationStyle {
        style.addMessage(message.text, message.timeStamp, message.person.toPerson())
        return this
    }

    /**
     * Adds a message for display in this notification
     *
     * @param messages The list of messages that will be shown
     *
     * @see android.support.v4.app.NotificationCompat.MessagingStyle.addMessage
     * @return Return FrogMessagingStyle
     *
     */
    fun addMessages(messages: List<FrogMessage>): FrogNotificationStyle {
        for (message in messages) this.addMessage(message)
        return this
    }

    /**
     * Total number of messages added to this notification
     *
     * @return Return the total number of messages
     *
     */
    fun getTotalMessages(): Int = style.messages.size
}