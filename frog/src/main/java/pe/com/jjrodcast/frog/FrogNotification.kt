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

package pe.com.jjrodcast.frog

import android.annotation.SuppressLint
import android.content.Context
import pe.com.jjrodcast.frog.entities.Builder
import pe.com.jjrodcast.frog.entities.FrogBuilder
import pe.com.jjrodcast.frog.entities.FrogChannel
import pe.com.jjrodcast.frog.entities.FrogGroupBuilder
import pe.com.jjrodcast.frog.providers.FrogContentProvider
import java.lang.NullPointerException

class FrogNotification private constructor() {

    companion object NOTIFICATION {
        @SuppressLint("StaticFieldLeak")
        private var INSTANCE: FrogNotification? = null
        @SuppressLint("StaticFieldLeak")
        internal var autoContext: Context? = null

        /**
         * Gets the Context that will work for the FrogNotification
         *
         * @return Return an FrogNotification instance
         *
         */
        fun get() = initializer()

        /**
         * Create the initialization for the context
         *
         * @return Return an FrogNotification instance
         *
         */
        private fun initializer(): FrogNotification {
            if (INSTANCE == null) {
                synchronized(FrogNotification::class) {
                    autoContext = FrogContentProvider.autoContext
                    if (autoContext == null) throw NullPointerException("FrogContentProvider null pointer exception")
                    else INSTANCE = Creator().build()
                }
            }
            return INSTANCE!!
        }
    }

    /**
     * Initialize the creation of a simple notification
     *
     * @param channel The channel used for Android Oreo or higher versions.
     *
     * @see Builder
     * @see FrogBuilder
     * @return Return a Builder object
     *
     */
    fun init(channel: FrogChannel): Builder = FrogBuilder(autoContext!!, channel)

    /**
     * Initialize the creation of a group notification
     *
     * @see Builder
     * @see FrogGroupBuilder
     * @return Return a Builder object
     *
     */
    fun initGroup(channel: FrogChannel, groupKey: String): Builder = FrogGroupBuilder(autoContext!!, channel, groupKey)

    /**
     * Sets the AndroidNotification instance
     *
     * @return Return an FrogNotification instance
     *
     */
    private class Creator internal constructor() {
        fun build(): FrogNotification {
            return FrogNotification()
        }
    }

}