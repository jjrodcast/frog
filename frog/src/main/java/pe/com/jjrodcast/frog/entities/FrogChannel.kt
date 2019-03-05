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

/**
 * @param channelId The id of the channel. Must be unique per package
 * @param name The user visible name of the channel.
 *             The recommended maximum length is 40 characters.
 * @param importance The importance of the channel. This controls how interrupting notifications
 *                   posted to this channel are.
 *                   Use NotificationManager importances
 * @param description Sets the user visible description of this channel.
 *             <p>The recommended maximum length is 300 characters</p>
 * @param showBadge Show or hide badge for the app
 * @param enableLights Enable or disable lights of the cellphone
 * @param enableVibration Enable or disable vibration of the cellphone
 *
 */
class FrogChannel @JvmOverloads constructor(val channelId: String,
                                            val name: String,
                                            val importance: Int,
                                            private val description: String = "",
                                            var showBadge: Boolean = true,
                                            var enableLights: Boolean = true,
                                            var enableVibration: Boolean = true)