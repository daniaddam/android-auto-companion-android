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

import com.google.android.connecteddevice.calendarsync.Attendee;
import com.google.android.connecteddevice.calendarsync.Event;
import com.google.android.connecteddevice.calendarsync.UpdateAction;
import com.google.android.connecteddevice.calendarsync.common.PlatformContentDelegate.EventContentDelegateFactory;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import com.google.common.collect.Range;
import java.time.Instant;
import java.util.List;
import javax.annotation.Nullable;

/**
 * Manage the {@link Event} relationship with {@link Attendee}s.
 *
 * <p>{@inheritDoc}
 */
public class EventManager extends ContentManager<Event, Event.Builder, Attendee, Attendee.Builder> {
  private static final String TAG = "EventManager";

  private final AttendeeManager attendeeManager;

  @AutoFactory(allowSubclasses = true)
  EventManager(
      @Provided CommonLogger.Factory commonLoggerFactory,
      @Provided EventContentDelegateFactory eventContentDelegateFactory,
      @Provided AttendeeManager attendeeManager,
      @Nullable Range<Instant> timeRange) {
    super(commonLoggerFactory.create(TAG), eventContentDelegateFactory.create(timeRange));
    this.attendeeManager = attendeeManager;
  }

  @Override
  protected void addChildren(Event.Builder builder, Iterable<Attendee> children) {
    builder.addAllAttendees(children);
  }

  @Override
  protected String key(Event event) {
    return event.getKey();
  }

  @Override
  protected List<Attendee> children(Event event) {
    return event.getAttendeesList();
  }

  @Override
  protected ContentManager<Attendee, Attendee.Builder, ?, ?> getChildManager(String unused) {
    return attendeeManager;
  }

  @Override
  protected void setAction(Event.Builder builder, UpdateAction action) {
    builder.setAction(action);
  }

  @Override
  protected void setKey(Event.Builder builder, String key) {
    builder.setKey(key);
  }

  @Override
  protected UpdateAction action(Event event) {
    return event.getAction();
  }

  @Override
  protected Event.Builder newMessageBuilder() {
    return Event.newBuilder();
  }

  @Override
  protected void clearChildren(Event.Builder builder) {
    builder.clearAttendees();
  }
}
