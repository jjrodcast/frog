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

import android.support.v4.app.Person
import android.support.v4.graphics.drawable.IconCompat

data class FrogPerson @JvmOverloads constructor(
    var name: CharSequence? = null,
    var icon: IconCompat? = null,
    var uri: String? = null,
    var key: String? = null,
    var isBot: Boolean = false,
    var isImportant: Boolean = false
) {

    /**
     * Converts a FrogPerson into a Person
     *
     * @see android.support.v4.app.Person
     * @return Return a Person
     *
     */
    fun toPerson(): Person {
        val builder = Person.Builder()
        name?.let { builder.setName(it) }
        icon?.let { builder.setIcon(it) }
        uri?.let { builder.setUri(uri) }
        key?.let { builder.setKey(it) }
        builder.setBot(isBot)
        builder.setImportant(isImportant)

        return builder.build()
    }
}