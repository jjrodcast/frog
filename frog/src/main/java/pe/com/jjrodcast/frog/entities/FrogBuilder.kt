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

import android.content.Context

class FrogBuilder constructor(private val context: Context, private val channel: FrogChannel) :
    Builder(context, channel) {

    /**
     * Set the counter for the notifications
     *
     * @param number Number that will set to the counter
     *
     * @see android.support.v4.app.Notification.Builder.setNumber
     * @return Return a Builder
     *
     */
    override fun counter(number: Int): Builder {
        if (number > 0) builder.setNumber(number)
        return this
    }

    /**
     * Set a color for the light led for a specific time
     *
     * @param argb Color in ARGB (alpha, red, green, blue)
     * @param timeOnMs
     * @param timeOffMs
     *
     * @see android.support.v4.app.Notification.Builder.setLights
     * @return Return a Builder
     *
     */
    override fun lights(argb: Int, timeOnMs: Int, timeOffMs: Int): Builder {
        builder.setLights(argb, timeOnMs, timeOffMs)
        return this
    }
}