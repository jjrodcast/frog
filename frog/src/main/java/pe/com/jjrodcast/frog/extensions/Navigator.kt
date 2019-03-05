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

package pe.com.jjrodcast.frog.extensions

import android.app.PendingIntent
import android.content.Context
import android.content.Intent

fun createPendingIntent(context: Context, activity: Class<*>, requestCode: Int, params: Intent.() -> Unit): PendingIntent {
    val intent = Intent(context, activity)
    intent.params()
    return PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
}