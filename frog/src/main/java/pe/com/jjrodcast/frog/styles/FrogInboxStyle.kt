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

class FrogInboxStyle @JvmOverloads constructor(
    private val text: String = "",
    private val title: String = ""
) : FrogNotificationStyle() {

    private val style: NotificationCompat.InboxStyle by lazy { NotificationCompat.InboxStyle() }
    private lateinit var line: CharSequence
    private var counter: Int = 0

    /**
     * Creates an instance of NotificationCompat.InboxStyle
     *
     * @see android.support.v4.app.NotificationCompat.InboxStyle
     * @return Return the style of the Notification.
     *
     */
    override fun create(): NotificationCompat.Style {
        if (text.trim().isNotEmpty()) style.setSummaryText(text)
        if (title.trim().isNotEmpty()) style.setBigContentTitle(title)
        return style
    }

    /**
     * Set the summary text for the FrogInboxStyle notification
     *
     * @see android.support.v4.app.NotificationCompat.InboxStyle.setSummaryText
     *
     */
    fun summaryText(text: CharSequence): FrogInboxStyle {
        style.setSummaryText(text)
        return this
    }

    /**
     * Set the big content title for the FrogInboxStyle notification
     *
     * @see android.support.v4.app.NotificationCompat.InboxStyle.setBigContentTitle
     *
     */
    fun bigContentTitle(title: CharSequence): FrogInboxStyle {
        style.setBigContentTitle(title)
        return this
    }

    /**
     * Add a line to the InboxStyle
     *
     * @see android.support.v4.app.NotificationCompat.InboxStyle.addLine
     * @param line Line that will be appended to the InboxStyle
     *
     */
    fun addLine(line: CharSequence): FrogInboxStyle {
        this.line = line
        style.addLine(this.line)
        return this
    }

    /**
     * Add multiple lines to the InboxStyle
     *
     * @see android.support.v4.app.NotificationCompat.InboxStyle.addLine
     * @param lines Lines that will be appended to the InboxStyle
     *
     */
    fun addLines(lines: ArrayList<String>): FrogInboxStyle {
        incrementCounter(lines.size)
        for (line in lines) {
            addLine(line)
        }
        return this
    }

    /**
     * Increments the counter of lines
     *
     * @param value Total number of lines
     *
     */
    private fun incrementCounter(value: Int) {
        counter = value
    }

    /**
     * Gets the total lines that were appended to the InboxStyle
     *
     * @return Return the total lines appended to the InboxStyle
     *
     */
    fun size() = counter

}