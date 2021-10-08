// Copyright 2021 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.android.libraries.car.trusteddevice.storage

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Table entity representing the credential that can unlock the user profile on a remote device.
 *
 * @property carId The unique identifier of the car.
 * @property token A byte array.
 * @property handle A byte array that together with [token] uniquely identify an enrollment session.
 */
@Entity(tableName = "credentials")
data class Credential(@PrimaryKey val carId: UUID, val token: ByteArray, val handle: ByteArray?)
