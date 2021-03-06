/*
 *
 * Copyright (c) 2013 - 2018 Lijun Liao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.xipki.audit;

import java.util.Objects;

/**
 * TODO.
 * @author Lijun Liao
 * @since 2.0.0
 */

public class AuditEventData {

  private final String name;

  private String value;

  public AuditEventData(String name, Object value) {
    Objects.requireNonNull(name, "name may not be null");
    if (name.isEmpty()) {
      throw new IllegalArgumentException("name may not be empty");
    }
    Objects.requireNonNull(value, "value may not be null");
    this.name = name;
    if (value instanceof String) {
      this.value = (String) value;
    } else {
      this.value = value.toString();
    }
  }

  public void addValue(Object additionalValue) {
    this.value += ", " + additionalValue;
  }

  public String getName() {
    return name;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return new StringBuilder(name).append(": ").append(value).toString();
  }
}
