/**
 * @author Andreas "PAX" Lück
 */
package com.github.transcurity.hexview.properties;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.github.transcurity.hexview.container.ReadonlyHexContainer;
import com.github.transcurity.hexview.util.ByteUtilities;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

/**
 * Wrapper for item IDs in {@link ReadonlyHexContainer}.
 *
 * @author Andreas "PAX" Lück
 */
public class ByteDataItem implements Item
{
    private static final long serialVersionUID = 3040125623506873209L;

    /**
     * The hex view row offset. Each hex view row represents one item and the
     * start offset positions of each row represent the IDs.
     *
     * @author Andreas "PAX" Lück
     */
    private final int offset;

    /**
     * The underlying byte data.
     *
     * @author Andreas "PAX" Lück
     */
    private final byte[] data;

    /**
     * Identifiers for each single byte represented by this hex view row.
     *
     * @author Andreas "PAX" Lück
     */
    private List<ColumnTypes> propertyIds;

    /**
     * @param offset
     *            The hex view row offset. Each hex view row represents one item
     *            and the start offset positions of each row represent the IDs.
     * @param data
     *            The underlying byte data.
     *
     * @author Andreas "PAX" Lück
     */
    public ByteDataItem(final int offset, final byte[] data)
    {
        this.offset = offset;
        this.data = data;
    }

    @Override
    public Property<String> getItemProperty(final Object propertyId)
    {
        for (int idIndex = 0; idIndex < ColumnTypes.DATA_COLUMN_IDS.size(); idIndex++)
            if (ColumnTypes.DATA_COLUMN_IDS.get(idIndex).equals(propertyId))
            {
                String value = null;
                if (this.offset + idIndex < this.data.length)
                    value = ByteUtilities.bytesToHex(new byte[]
                    { this.data[this.offset + idIndex] });

                return new RowProperty(value);
            }

        return null;
    }

    @Override
    public Collection<?> getItemPropertyIds()
    {
        if (this.propertyIds == null)
        {
            this.propertyIds = new LinkedList<>();
            for (int i = this.offset; i < this.data.length; i++)
                this.propertyIds.add(ColumnTypes.DATA_COLUMN_IDS.get(i
                        - this.offset));
        }

        return this.propertyIds;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean addItemProperty(final Object id, final Property property)
            throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("This data container is read only");
    }

    @Override
    public boolean removeItemProperty(final Object id)
            throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("This data container is read only");
    }
}
