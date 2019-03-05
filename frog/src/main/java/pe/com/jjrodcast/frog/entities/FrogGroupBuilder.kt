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

class FrogGroupBuilder(context: Context, private val channel: FrogChannel, private val groupKey: String) :
    Builder(context, channel) {

    init {
        isGroup(true)
        group(groupKey)
    }

    /**
     * Set the notification as a group
     *
     * @param enable Enable the notification as a GroupSummary
     *
     * @see android.support.v4.app.NotificationCompat.Builder.setGroupSummary
     * @return Return a Builder
     *
     */
    private fun isGroup(enable: Boolean): Builder {
        builder.setGroupSummary(enable)
        return this
    }

}