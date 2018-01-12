/**
 * @author Andreas "PAX" Lück
 */
package com.github.transcurity.hexview.cells;

import java.util.Collection;
import java.util.LinkedList;

import com.github.transcurity.hexview.properties.ColumnTypes;
import com.vaadin.ui.Grid.CellReference;
import com.vaadin.ui.Grid.CellStyleGenerator;

/**
 * Specifies the CSS style class name to be set to the different table cells.
 *
 * @author Andreas "PAX" Lück
 */
public class HexCellStyleGenerator implements CellStyleGenerator
{
    private static final long serialVersionUID = 8558390091681239890L;

    /**
     * Stores custom CSS classes to be applied to specific cell ranges within
     * the table.
     *
     * @author Andreas "PAX" Lück
     */
    private final Collection<StyleRange> customStyleRanges = new LinkedList<>();

    @Override
    public String getStyle(final CellReference cellReference)
    {
        if (cellReference.getItemId() != null
                && cellReference.getItemId() instanceof Integer
                && cellReference.getPropertyId() != null
                && cellReference.getPropertyId() instanceof ColumnTypes)
        {
            final int rowOffset = (int) cellReference.getItemId();
            final ColumnTypes column = (ColumnTypes) cellReference.getPropertyId();
            final String customStyle = getCustomStyles(rowOffset
                    + column.getOffsetInRow());
            switch (column)
            {
            case OFFSET:
                return "hex-offset-cell";
            case COL_0:
            case COL_1:
            case COL_2:
            case COL_3:
            case COL_4:
            case COL_5:
            case COL_6:
            case COL_7:
            case COL_8:
            case COL_9:
            case COL_A:
            case COL_B:
            case COL_C:
            case COL_D:
            case COL_E:
            case COL_F:
                return "hex-data-cell" + customStyle;
            case ASCII:
                return "hex-ascii-cell";
            }
        }

        return null;
    }

    /**
     * Obtains potential blank separated CSS class names for the specified table
     * cell if it has a custom style.
     *
     * @param offset
     *            The byte offset of the concerning cell.
     * @return Either an empty string or one or more CSS class names (blank
     *         separated)with a leading blank that belong to the specified table
     *         cell, e.g.: {@code " importantSection boldFont highlighted"}
     *
     * @author Andreas "PAX" Lück
     */
    protected String getCustomStyles(final int offset)
    {
        final StringBuilder result = new StringBuilder();
        getCustomStyleRanges().forEach(range -> {
            if (offset >= range.getOffset()
                    && offset < range.getOffset() + range.getLength())
                result.append(' ').append(range.getCssClasses());
        });

        return result.toString();
    }

    /**
     * @return Stores custom CSS classes to be applied to specific cell ranges
     *         within the table.
     *
     * @author Andreas "PAX" Lück
     */
    protected Collection<StyleRange> getCustomStyleRanges()
    {
        return this.customStyleRanges;
    }

    /**
     * Adds custom CSS classes to be applied to specific cell ranges within the
     * table.
     *
     * @param style
     *            Holds the start offset, length and CSS class names to be
     *            applied to this cell range.
     *
     * @author Andreas "PAX" Lück
     */
    public void addCustomStyleRange(final StyleRange style)
    {
        addCustomStyleRange(style.getOffset(), style.getLength(), style.getCssClasses());
    }

    /**
     * Adds custom CSS classes to be applied to specific cell ranges within the
     * table.
     *
     * @param offset
     *            The start offset of the range where following condition is
     *            valid: <span style=
     *            "white-space:nowrap">{@code 0 <= offset < length}</span>
     * @param length
     *            The number of bytes occupied by this range.
     * @param cssClasses
     *            The name of CSS classes (separated by a blank) to be applied
     *            to this cell range, e.g.:
     *            {@code importantSection boldFont highlighted}
     *
     * @author Andreas "PAX" Lück
     */
    public void addCustomStyleRange(final int offset, final int length,
            final String cssClasses)
    {
        getCustomStyleRanges().add(new StyleRange(offset, length, cssClasses));
    }

    /**
     * Removes all custom styles that match the specified range, exactly. If one
     * of the ranges contain the specified range but don't match its boundaries,
     * exactly, then it won't be removed. In other words: Offset and length must
     * be equal.
     *
     * @param offset
     *            The start offset of the range where following condition is
     *            valid: <span style=
     *            "white-space:nowrap">{@code 0 <= offset < length}</span>
     * @param length
     *            The number of bytes occupied by this range.
     *
     * @author Andreas "PAX" Lück
     */
    public void removeCustomStyleRange(final int offset, final int length)
    {
        getCustomStyleRanges().removeIf(range -> range.getOffset() == offset
                && range.getLength() == length);
    }
}
