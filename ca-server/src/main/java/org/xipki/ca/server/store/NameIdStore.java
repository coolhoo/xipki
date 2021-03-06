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

package org.xipki.ca.server.store;

import java.util.HashMap;
import java.util.Map;

import org.xipki.util.CollectionUtil;
import org.xipki.util.CompareUtil;
import org.xipki.util.Args;

/**
 * TODO.
 * @author Lijun Liao
 * @since 2.0.0
 */

class NameIdStore {

  private final String table;

  private final Map<String, Integer> entries;

  NameIdStore(String table, Map<String, Integer> entries) {
    this.table = Args.notNull(table, "table");
    this.entries = new HashMap<>();

    if (CollectionUtil.isNonEmpty(entries)) {
      for (String name : entries.keySet()) {
        addEntry(name, entries.get(name));
      }
    }
  }

  final void addEntry(String name, Integer id) {
    Args.notBlank(name, "name");
    Args.notNull(id, "id");

    if (entries.containsKey(name)) {
      throw new IllegalArgumentException("entry with the same name " + name + " already available");
    }

    if (entries.containsValue(id)) {
      throw new IllegalArgumentException("entry with the same id " + id + " already available");
    }

    entries.put(name, id);
  }

  String getName(Integer id) {
    for (String name : entries.keySet()) {
      if (CompareUtil.equalsObject(id, entries.get(name))) {
        return name;
      }
    }

    return null;
  }

  Integer getId(String name) {
    return entries.get(name);
  }

  public String getTable() {
    return table;
  }

}
