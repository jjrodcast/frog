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

package pe.com.jjrodcast.frog.providers

import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.service.notification.StatusBarNotification
import android.support.annotation.RequiresApi

object FrogNotificationManager {

    private val manager: NotificationManager by lazy { createManager() }

    /**
     * Gets the NotificationManager that will manage the notification creation
     *
     * @see android.support.v4.app.NotificationManager
     * @return Return the current NotificationManager
     *
     */
    internal fun createManager(): NotificationManager {
        return FrogContentProvider.autoContext!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    /**
     * Gets the active notifications
     *
     * @see android.app.NotificationManager.getActiveNotifications
     * @return Return the total notifications that are actives
     *
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun getActiveNotifications(): Array<out StatusBarNotification> {
        return manager.activeNotifications
    }

    /**
     * Cancel an specific notification
     *
     * @param notificationId Id for the notification that will be cancelled
     * @param tag Tag that was assigned to the notification
     *
     * @see android.app.NotificationManager.cancel
     *
     */
    @JvmOverloads
    fun cancelNotification(notificationId: Int, tag: String? = null) {
        tag?.let {
            manager.cancel(tag, notificationId)
        } ?: kotlin.run {
            manager.cancel(notificationId)
        }
    }

    /**
     * Cancel all the notifications
     *
     * @see android.app.NotificationManager.cancelAll
     *
     */
    fun cancelAllNotification() {
        manager.cancelAll()
    }
}