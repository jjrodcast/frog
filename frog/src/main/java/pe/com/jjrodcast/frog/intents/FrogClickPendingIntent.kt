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

package pe.com.jjrodcast.frog.intents

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import pe.com.jjrodcast.frog.extensions.createPendingIntent
import pe.com.jjrodcast.frog.interfaces.FrogPendingIntent

class FrogClickPendingIntent(private val target: Class<*>, private val bundle: Bundle?, private val identifier: Int) :
    FrogPendingIntent {

    /**
     * Create a Pending Intent using the context
     *
     * @param context Context where the pending intent will be displayed
     *
     * @see android.app.PendingIntent
     * @return Return a PendingIntent for the notification
     *
     */
    override fun onCreatePendingIntent(context: Context): PendingIntent {
        return createPendingIntent(context, target, identifier) {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
            bundle?.let { putExtras(it) }

        }
    }

}