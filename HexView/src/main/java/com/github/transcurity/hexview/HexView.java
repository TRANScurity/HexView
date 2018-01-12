/**
 * @author Andreas "PAX" Lück
 */
package com.github.transcurity.hexview;

import com.github.transcurity.hexview.cells.HexCellStyleGenerator;
import com.github.transcurity.hexview.container.ReadonlyHexContainer;
import com.github.transcurity.hexview.properties.AsciiDumpColumn;
import com.github.transcurity.hexview.properties.ColumnTypes;
import com.github.transcurity.hexview.properties.OffsetColumn;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.ui.Grid;

/**
 * Displays data in a hex view. In order to set content data you can invoke
 * {@link #setBytes(byte[])}, belatedly.
 * <p>
 * &nbsp;
 * </p>
 * You can attach your own CSS to the cells by invocation of
 * {@link #addCustomStyleRange(int, int, String)} in order to specify CSS class
 * names for a specific cell range.
 *
 * @author Andreas "PAX" Lück
 */
public class HexView extends Grid
{
    private static final long serialVersionUID = -4803785730336516676L;

    /**
     * The bytes data to be displayed.
     *
     * @author Andreas "PAX" Lück
     */
    protected byte[] data;

    /**
     * Responsible for the active CSS in the single table cells.
     *
     * @author Andreas "PAX" Lück
     */
    protected HexCellStyleGenerator cellStyleGenerator;

    /**
     * Creates an empty hex view which displays no data.
     *
     * @author Andreas "PAX" Lück
     */
    public HexView()
    {
        this(null);
    }

    /**
     * @param data
     *            The bytes data to be displayed. A value of {@code null} would
     *            be interpreted as empty array.
     * @author Andreas "PAX" Lück
     */
    public HexView(final byte[] data)
    {
        build(data);
    }

    /**
     * Constructs the entire UI.
     *
     * @param data
     *            The bytes data to be displayed. A value of {@code null} would
     *            be interpreted as empty array.
     *
     * @author Andreas "PAX" Lück
     */
    private void build(final byte[] data)
    {
        addStyleName("hex-grid");
        initColumnHeaders();
        initCellStyleGenerator();
        initSelectionMode();
        setBytes(data);
    }

    /**
     * Initializes the header with the title columns of the grid.
     *
     * @author Andreas "PAX" Lück
     */
    protected void initColumnHeaders()
    {
        for (final ColumnTypes column : ColumnTypes.ALL_COLUMN_IDS)
            addColumnProperty(column, String.class, null);
    }

    /**
     * Initializes the cell style generator which is responsible for setting the
     * CSS class for each grid cell.
     *
     * @author Andreas "PAX" Lück
     */
    protected void initCellStyleGenerator()
    {
        setCellStyleGenerator(this.cellStyleGenerator = new HexCellStyleGenerator());
    }

    /**
     * Initializes the cell selection mode for the grid.
     *
     * @author Andreas "PAX" Lück
     */
    protected void initSelectionMode()
    {
        setSelectionMode(SelectionMode.NONE);
    }

    /**
     * Specifies the data to be displayed by the view.
     *
     * @param bytes
     *            The bytes data to be displayed. A value of {@code null} would
     *            be interpreted as empty array.
     *
     * @author Andreas "PAX" Lück
     */
    public void setBytes(final byte[] bytes)
    {
        this.data = bytes != null ? bytes : new byte[0];

        final GeneratedPropertyContainer container = new GeneratedPropertyContainer(new ReadonlyHexContainer(this.data));
        container.addGeneratedProperty(ColumnTypes.OFFSET, new OffsetColumn(this.data));
        container.addGeneratedProperty(ColumnTypes.ASCII, new AsciiDumpColumn(this.data));

        setContainerDataSource(container);
        setFrozenColumnCount(1);
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
        this.cellStyleGenerator.addCustomStyleRange(offset, length, cssClasses);
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
        this.cellStyleGenerator.removeCustomStyleRange(offset, length);
    }
}
