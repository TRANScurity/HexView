/**
 * @author Andreas "PAX" Lück
 */
package com.github.transcurity.hexview.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.vaadin.data.Container.Indexed;
import com.vaadin.data.Container.Sortable;
import com.github.transcurity.hexview.properties.ByteDataItem;
import com.github.transcurity.hexview.properties.ColumnTypes;
import com.github.transcurity.hexview.properties.RowProperty;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

/**
 * Used for holding binary raw data.
 *
 * @author Andreas "PAX" Lück
 */
public class ReadonlyHexContainer implements Indexed, Sortable
{
    private static final long serialVersionUID = -8341383986795200317L;

    /**
     * The binary data to be hold.
     *
     * @author Andreas "PAX" Lück
     */
    private final byte[] data;

    /**
     * @param data
     *            The binary data to be hold.
     *
     * @author Andreas "PAX" Lück
     */
    public ReadonlyHexContainer(final byte[] data)
    {
        this.data = data;
    }

    @Override
    public Object nextItemId(final Object itemId)
    {
        if (itemId != null && itemId instanceof Integer)
        {
            final int offset = (int) itemId;
            if (offset < 0)
                return null;

            int nextOffset = offset + 16;
            if (offset % 16 > 0)
                nextOffset = (offset / 16) * 16 + 16;

            return nextOffset < this.data.length ? nextOffset : null;
        }

        return null;
    }

    @Override
    public Object prevItemId(final Object itemId)
    {
        if (itemId != null && itemId instanceof Integer)
        {
            final int offset = (int) itemId;
            if (offset < 0)
                return null;

            int prevOffset = offset - 16;
            if (prevOffset < 0)
                return offset > 0 && offset < this.data.length ? 0 : null;
            if (offset % 16 > 0)
                prevOffset = (offset / 16) * 16;

            return prevOffset < this.data.length && prevOffset >= 0 ? prevOffset
                    : null;
        }

        return null;
    }

    @Override
    public Object firstItemId()
    {
        if (this.data.length > 0)
            return 0;

        return null;
    }

    @Override
    public Object lastItemId()
    {
        if (this.data.length > 0)
            return this.data.length % 16 == 0 ? this.data.length - 16
                    : (this.data.length / 16) * 16;

        return null;
    }

    @Override
    public boolean isFirstId(final Object itemId)
    {
        if (itemId != null && itemId instanceof Integer)
            return this.data.length > 0 ? ((int) itemId) == 0 : false;

        return false;
    }

    @Override
    public boolean isLastId(final Object itemId)
    {
        if (itemId != null && itemId instanceof Integer)
            return (int) itemId == (this.data.length / 16) * 16;

        return false;
    }

    @Override
    public Object addItemAfter(final Object previousItemId)
            throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("This data container is read only");
    }

    @Override
    public Item addItemAfter(final Object previousItemId,
            final Object newItemId) throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("This data container is read only");
    }

    @Override
    public Item getItem(final Object itemId)
    {
        if (itemId != null && itemId instanceof Integer)
        {
            final int offset = (int) itemId;
            if (this.data.length > 0 && offset >= 0 && (offset % 16 == 0))
                return new ByteDataItem(offset, this.data);
        }

        return null;
    }

    @Override
    public Collection<ColumnTypes> getContainerPropertyIds()
    {
        return ColumnTypes.DATA_COLUMN_IDS;
    }

    @Override
    public Collection<?> getItemIds()
    {
        final List<Integer> result = new ArrayList<>();
        final int total = this.data.length % 16 > 0 ? this.data.length / 16 + 1
                : this.data.length / 16;
        for (int id = 0, i = 0; i < total; id += 16, i++)
            result.add(id);

        return result;
    }

    @Override
    public Property<String> getContainerProperty(final Object itemId,
            final Object propertyId)
    {
        final Item item = getItem(itemId);
        if (item != null && propertyId != null && itemId instanceof Integer)
        {
            // search column of hex row
            final Collection<ColumnTypes> allPropertyIds = getContainerPropertyIds();
            for (final ColumnTypes id : allPropertyIds)
                if (propertyId.equals(id))
                {
                    // extract value of cell
                    final String columnValue = id.getDisplayValue(this.data, (int) itemId);
                    return columnValue != null
                            ? new RowProperty(columnValue)
                            : null;
                }
        }

        return null;
    }

    @Override
    public Class<?> getType(final Object propertyId)
    {
        if (propertyId != null && propertyId instanceof ColumnTypes)
            return String.class;

        return Object.class;
    }

    @Override
    public int size()
    {
        if (this.data.length % 16 == 0)
            return this.data.length / 16;

        return this.data.length / 16 + 1;
    }

    @Override
    public boolean containsId(final Object itemId)
    {
        if (itemId != null && itemId instanceof Integer)
        {
            final int offset = (int) itemId;
            return offset >= 0 && offset < this.data.length && offset % 16 == 0;
        }

        return false;
    }

    @Override
    public Item addItem(final Object itemId)
            throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("This data container is read only");
    }

    @Override
    public Object addItem() throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("This data container is read only");
    }

    @Override
    public boolean removeItem(final Object itemId)
            throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("This data container is read only");
    }

    @Override
    public boolean addContainerProperty(final Object propertyId,
            final Class<?> type, final Object defaultValue)
            throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("This data container is read only");
    }

    @Override
    public boolean removeContainerProperty(final Object propertyId)
            throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("This data container is read only");
    }

    @Override
    public boolean removeAllItems() throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("This data container is read only");
    }

    @Override
    public int indexOfId(final Object itemId)
    {
        if (itemId != null && itemId instanceof Integer)
        {
            final int offset = (int) itemId;
            return offset % 16 == 0 && offset < this.data.length ? offset / 16
                    : -1;
        }

        return -1;
    }

    @Override
    public Object getIdByIndex(final int index)
    {
        final int offset = index * 16;
        return offset < this.data.length ? offset : null;
    }

    @Override
    public List<Integer> getItemIds(final int startIndex,
            final int numberOfItems)
    {
        final List<Integer> result = new ArrayList<>();
        final Object lastItemId = lastItemId();
        for (int i = startIndex; i < startIndex + numberOfItems; i++)
        {
            if (lastItemId == null)
                break;

            if (i < 0)
                continue;

            final int offset = i * 16;
            if (offset <= (int) lastItemId)
                result.add(offset);
        }

        return result;
    }

    @Override
    public Object addItemAt(final int index)
            throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("This data container is read only");
    }

    @Override
    public Item addItemAt(final int index, final Object newItemId)
            throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("This data container is read only");
    }

    @Override
    public void sort(final Object[] propertyId, final boolean[] ascending)
    {
        /*
         * Doesn't sort anything. Only required due to buggy
         * GeneratedPropertyContainer which raises NPE if the wrapped container
         * doesn't implement Sortable.
         */
    }

    @Override
    public Collection<?> getSortableContainerPropertyIds()
    {
        // nothing to be sorted
        return Collections.emptyList();
    }
}
