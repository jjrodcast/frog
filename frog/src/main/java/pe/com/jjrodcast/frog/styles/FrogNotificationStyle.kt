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

import android.support.annotation.RestrictTo
import android.support.v4.app.NotificationCompat

@RestrictTo(RestrictTo.Scope.LIBRARY)
abstract class FrogNotificationStyle {

    /**
     * Creates a NotificationCompat.Style
     *
     * @see android.support.v4.app.NotificationCompat.Style
     * @return Return a style instance for the notification
     *
     */
    internal abstract fun create(): NotificationCompat.Style
}