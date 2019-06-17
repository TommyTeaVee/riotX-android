/*
 * Copyright 2019 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.matrix.android.internal.crypto

import im.vector.matrix.android.api.auth.data.Credentials
import java.util.*

internal class ObjectSigner(private val credentials: Credentials,
                            private val olmDevice: MXOlmDevice) {

    /**
     * Sign Object
     *
     * Example:
     * <pre>
     *     {
     *         "[MY_USER_ID]": {
     *             "ed25519:[MY_DEVICE_ID]": "sign(str)"
     *         }
     *     }
     * </pre>
     *
     * @param strToSign the String to sign and to include in the Map
     * @return a Map (see example)
     */
    fun signObject(strToSign: String): Map<String, Map<String, String>> {
        val result = HashMap<String, Map<String, String>>()

        val content = HashMap<String, String>()

        content["ed25519:" + credentials.deviceId] = olmDevice.signMessage(strToSign)!!

        result[credentials.userId] = content

        return result
    }

}