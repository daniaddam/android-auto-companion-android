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

package com.google.android.connecteddevice.calendarsync.common;

import dagger.BindsInstance;
import dagger.Component;
import java.util.function.Supplier;

/** A component to create a {@link SourceCalendarSync}. */
@Component
public interface SourceCalendarSyncComponent {

  /** Creates a {@link SourceCalendarSync}. */
  SourceCalendarSync createSourceCalendarSync();

  /** Defines the required external dependencies. */
  @Component.Builder
  interface Builder extends BaseComponentBuilder<Builder> {
    @BindsInstance
    Builder timeWindowSupplier(Supplier<TimeWindow> instance);

    SourceCalendarSyncComponent build();
  }
}
