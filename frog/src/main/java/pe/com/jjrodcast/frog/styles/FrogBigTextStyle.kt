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

/**
 * This is the BigTextStyle for notifications using for expandable notification
 *
 * @param text Big text for the expandable notification
 * @see android.support.v4.app.NotificationCompat.BigTextStyle
 *
 */
class FrogBigTextStyle(private val text: String) : FrogNotificationStyle() {

    private val style: NotificationCompat.BigTextStyle by lazy { NotificationCompat.BigTextStyle() }

    /**
     * Creates an instance of NotificationCompat.BigTextStyle
     *
     * @see android.support.v4.app.NotificationCompat.BigTextStyle
     * @return Return the style of the Notification.
     *
     */
    override fun create(): NotificationCompat.Style {
        if (text.isNotEmpty()) style.bigText(text)
        return style
    }

    /**
     * Set the summary text for the FrogBigTextStyle notification
     *
     * @see android.support.v4.app.NotificationCompat.BigTextStyle.setSummaryText
     *
     */
    fun summaryText(text: CharSequence): FrogBigTextStyle {
        style.setSummaryText(text)
        return this
    }

    /**
     * Set the big content title for the FrogBigTextStyle notification
     *
     * @see android.support.v4.app.NotificationCompat.BigTextStyle.setBigContentTitle
     *
     */
    fun bigContentTitle(title: CharSequence): FrogBigTextStyle {
        style.setBigContentTitle(title)
        return this
    }
}