/**
 * @author Andreas "PAX" Lück
 */
package com.github.transcurity.hexview.properties;

import com.vaadin.data.Property;

/**
 * Represents one column value within a single hex view row.
 *
 * @author Andreas "PAX" Lück
 */
public class RowProperty implements Property<String>
{
    private static final long serialVersionUID = -1781239803235258147L;

    /**
     * The value represented by this column cell.
     *
     * @author Andreas "PAX" Lück
     */
    private String value;

    /**
     * @param value
     *            The value represented by this column cell. A value of
     *            {@code null} will be interpreted as empty string.
     *
     * @author Andreas "PAX" Lück
     */
    public RowProperty(final String value)
    {
        this.value = value != null ? value : "";
    }

    @Override
    public String getValue()
    {
        return this.value;
    }

    @Override
    public void setValue(final String newValue) throws ReadOnlyException
    {
        this.value = newValue;
    }

    @Override
    public Class<? extends String> getType()
    {
        return String.class;
    }

    @Override
    public boolean isReadOnly()
    {
        return true;
    }

    @Override
    public void setReadOnly(final boolean newStatus)
    {
        // currently read only
    }
}
