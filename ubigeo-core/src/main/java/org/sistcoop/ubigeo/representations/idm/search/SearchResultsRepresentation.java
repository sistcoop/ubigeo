/*
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sistcoop.ubigeo.representations.idm.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Models a set of beans returned as a result of a search.
 *
 * @author eric.wittmann@redhat.com
 * @param <T>
 *            the bean type
 */
public class SearchResultsRepresentation<T> implements Serializable {

    private static final long serialVersionUID = 7557720618049390833L;

    private List<T> items = new ArrayList<>();
    private int totalSize;

    /**
     * Constructor.
     */
    public SearchResultsRepresentation() {
    }

    /**
     * @return the beans
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * @param beans
     *            the beans to set
     */
    public void setItems(List<T> items) {
        this.items = items;
    }

    /**
     * @return the totalSize
     */
    public int getTotalSize() {
        return totalSize;
    }

    /**
     * @param totalSize
     *            the totalSize to set
     */
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

}
