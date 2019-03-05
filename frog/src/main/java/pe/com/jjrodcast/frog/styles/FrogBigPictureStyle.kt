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

import android.graphics.Bitmap
import android.support.annotation.DrawableRes
import android.support.annotation.NonNull
import android.support.v4.app.NotificationCompat

class FrogBigPictureStyle constructor(@NonNull private val picture: Bitmap) : FrogNotificationStyle() {

    private val style: NotificationCompat.BigPictureStyle by lazy { NotificationCompat.BigPictureStyle() }

    init {
        style.bigPicture(picture)
    }

    /**
     * Enables the picture to see as a thumbnail
     *
     * @see android.support.v4.app.NotificationCompat.BigPictureStyle.bigLargeIcon
     *
     */
    fun enableThumbnail(): FrogBigPictureStyle {
        style.bigLargeIcon(null)
        return this
    }

    /**
     * Set the summary text for the FrogBigPictureStyle notification
     *
     * @see android.support.v4.app.NotificationCompat.BigPictureStyle.setSummaryText
     *
     */
    fun summaryText(text: CharSequence): FrogBigPictureStyle {
        style.setSummaryText(text)
        return this
    }

    /**
     * Set the big content title for the FrogBigPictureStyle notification
     *
     * @see android.support.v4.app.NotificationCompat.BigPictureStyle.setBigContentTitle
     *
     */
    fun bigContentTitle(title: CharSequence): FrogBigPictureStyle {
        style.setBigContentTitle(title)
        return this
    }

    /**
     * Creates an instance of NotificationCompat.BigPictureStyle
     *
     * @see android.support.v4.app.NotificationCompat.BigPictureStyle
     * @return Return the style of the Notification.
     *
     */
    override fun create(): NotificationCompat.Style {
        style.bigPicture(picture)
        return style
    }

}