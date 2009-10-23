/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2005-2008, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotoolkit.feature.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.expression.PropertyName;
import org.opengis.filter.identity.FeatureId;
import org.opengis.filter.sort.SortBy;
import org.opengis.filter.sort.SortOrder;


public class SubFeatureList extends SubFeatureCollection implements RandomAccessFeatureCollection {

    /** Order by which content should be sorted */
    private List<SortBy> sort;
    /** List of FeatureIDs in sorted order */
    private List<FeatureId> index;

    public SubFeatureList(final FeatureCollection<SimpleFeatureType, SimpleFeature> list, final Filter filter) {
        this(list, filter, SortBy.NATURAL_ORDER);
    }

    public SubFeatureList(final FeatureCollection<SimpleFeatureType, SimpleFeature> list, final SortBy sort) {
        this(list, Filter.INCLUDE, sort);
    }

    /**
     * Create a simple SubFeatureList with the provided
     * filter.
     *
     * @param filter
     */
    public SubFeatureList(final FeatureCollection<SimpleFeatureType, SimpleFeature> list, final Filter filter,
            final SortBy subSort){
        super(list, filter);

        if (subSort == null || subSort.equals(SortBy.NATURAL_ORDER)) {
            sort = Collections.emptyList();
        } else {
            sort = new ArrayList<SortBy>();
            if (collection instanceof SubFeatureList) {
                SubFeatureList sorted = (SubFeatureList) collection;
                sort.addAll(sorted.sort);
            }
            sort.add(subSort);
        }
        index = null;
    }

    public SubFeatureList(final FeatureCollection<SimpleFeatureType, SimpleFeature> list, final List order) {
        super(list);

        index = order;
        filter = null;
    }

    /**
     * item at the specified index.
     *
     * @param position index of item
     * @return the item at the specified index.
     * @throws IndexOutOfBoundsException
     *             if index is not between 0 and size
     */
    public SimpleFeature get(final int position) {
        if (collection instanceof RandomAccessFeatureCollection) {
            final RandomAccessFeatureCollection random = (RandomAccessFeatureCollection) collection;
            final FeatureId fid = index().get(position);
            return random.getFeatureMember(fid.getID());
        }
        final Iterator<SimpleFeature> it = iterator();
        try {
            for (int i = 0; it.hasNext(); i++) {
                SimpleFeature feature = (SimpleFeature) it.next();
                if (i == position) {
                    return feature;
                }
            }
            throw new IndexOutOfBoundsException();
        } finally {
            close(it);
        }
    }

    /** Lazy create a filter based on index */
    @Override
    protected Filter createFilter() {
        Set featureIds = new HashSet();
        for (Iterator it = index().iterator(); it.hasNext();) {
            featureIds.add(FF.featureId((String) it.next()));
        }

        return FF.id(featureIds);
    }

    protected List<FeatureId> index() {
        if (index == null) {
            index = createIndex();
        }
        return index;
    }

    /** Put this SubFeatureList in touch with its inner index */
    protected List<FeatureId> createIndex() {
        final List<FeatureId> fids = new ArrayList<FeatureId>();
        final Iterator<SimpleFeature> it = collection.iterator();
        try {
            while (it.hasNext()) {
                SimpleFeature feature = it.next();
                if (filter.evaluate(feature)) {
                    fids.add(feature.getIdentifier());
                }
            }
            if (sort != null && !sort.isEmpty()) {
                final SortBy initialOrder = (SortBy) sort.get(sort.size() - 1);
                Collections.sort(fids, new Comparator<FeatureId>() {

                    @Override
                    public int compare(FeatureId key1, FeatureId key2) {
                        SimpleFeature feature1 = getFeatureMember(key1.getID());
                        SimpleFeature feature2 = getFeatureMember(key2.getID());

                        int compare = compare(feature1, feature2, initialOrder);
                        if (compare == 0 && sort.size() > 1) {
                            for (int i = sort.size() - 1; compare == 0 && i >= 0; i--) {
                                compare = compare(feature1, feature2, (SortBy) sort.get(i));
                            }
                        }
                        return compare;
                    }

                    protected int compare(SimpleFeature feature1, SimpleFeature feature2, SortBy order) {
                        PropertyName name = order.getPropertyName();
                        Comparable value1 = (Comparable) name.evaluate(feature1);
                        Comparable value2 = (Comparable) name.evaluate(feature2);

                        if (order.getSortOrder() == SortOrder.ASCENDING) {
                            return value1.compareTo(value2);
                        } else {
                            return value2.compareTo(value1);
                        }
                    }
                });
            }
        } finally {
            collection.close(it);
        }
        return fids;
    }

    /**
     * Appends element.
     * <p>
     * This implementation calls <tt>add(size(), o)</tt>.
     * <p>
     * Note that this implementation throws an
     * <tt>UnsupportedOperationException</tt> unless <tt>add(int, Object)</tt>
     * is overridden.
     *
     * @param feature the feature element to be appended to this list.
     * @return <tt>true</tt> (as per the general contract of
     *         <tt>Collection.add</tt>).
     * @throws UnsupportedOperationException
     *             if the <tt>add</tt> method is not supported by this Set.
     * @throws ClassCastException
     *             if the class of the specified element prevents it from being
     *             added to this set.
     * @throws IllegalArgumentException
     *             some aspect of this element prevents it from being added to
     *             this collection.
     */
    @Override
    public boolean add(final SimpleFeature feature) {
        boolean added = collection.add(feature);
        if (added) {
            index().add(feature.getIdentifier());
        }
        return true;
    }

    public int indexOf(final SimpleFeature feature) {
        return index().indexOf(feature.getIdentifier());
    }

    public int lastIndexOf(final SimpleFeature feature) {
        return index().lastIndexOf(feature.getIdentifier());
    }

    //
    // Feature Collection methods
    //
    /**
     * Sublist of this sublist!
     * <p>
     * Implementation will ensure this does not get out of hand, order
     * is maintained and only indexed once.
     * </p>
     */
    @Override
    public FeatureCollection<SimpleFeatureType, SimpleFeature> subList(final Filter subfilter) {
        if (Filter.INCLUDE.equals(filter)) {
            return this;
        }else if (Filter.EXCLUDE.equals(filter)) {
            // TODO implement EmptyFeatureCollection( schema )
        }
        return new SubFeatureList(collection, FF.and(filter, subfilter), sort.get(0));
    }

    //
    // RandomFeatureAccess
    //

    /**
     * {@inheritDoc }
     */
    @Override
    public SimpleFeature getFeatureMember(final String id) throws NoSuchElementException {
        int position = index.indexOf(id);
        if (position == -1) {
            throw new NoSuchElementException(id);
        }
        if (collection instanceof RandomAccessFeatureCollection) {
            RandomAccessFeatureCollection random = (RandomAccessFeatureCollection) collection;
            random.getFeatureMember(id);
        }
        return (SimpleFeature) get(position);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public SimpleFeature removeFeatureMember(final String id) {
        final int position = index.indexOf(FF.featureId(id));
        if (position == -1) {
            throw new NoSuchElementException(id);
        }
        if (collection instanceof RandomAccessFeatureCollection) {
            final RandomAccessFeatureCollection random = (RandomAccessFeatureCollection) collection;
            if (index != null) {
                index.remove(id);
            }
            return random.removeFeatureMember(id);
        }
        return (SimpleFeature) remove(position);
    }

    public SimpleFeature remove(final int position) {
        if (collection instanceof RandomAccessFeatureCollection) {
            final RandomAccessFeatureCollection random = (RandomAccessFeatureCollection) collection;
            final FeatureId fid = index().get(position);
            return random.removeFeatureMember(fid.getID());
        }
        final Iterator<SimpleFeature> it = iterator();
        try {
            for (int i = 0; it.hasNext(); i++) {
                final SimpleFeature feature = (SimpleFeature) it.next();
                if (i == position) {
                    collection.remove(feature);
                    return feature;
                }
            }
            throw new IndexOutOfBoundsException();
        } finally {
            close(it);
        }
    }

    /**
     * Returns a quick iterator that uses get and size methods.
     * <p>
     * As with all resource collections it is assumed that the iterator will be
     * closed after use.
     * </p>
     *
     * @return an iterator over the elements in this list in proper sequence.
     */
    @Override
    public Iterator<SimpleFeature> openIterator() {
        return new SortedIteratory();
    }

    private class SortedIteratory implements Iterator<SimpleFeature> {

        private final Iterator<FeatureId> iterator = index().iterator();
        private String id;

        @Override
        public boolean hasNext() {
            return iterator != null && iterator.hasNext();
        }

        @Override
        public SimpleFeature next() {
            FeatureId fid = iterator.next();
            id = fid.getID();
            return getFeatureMember(id);
        }

        @Override
        public void remove() {
            removeFeatureMember(id);
        }
    }
}
