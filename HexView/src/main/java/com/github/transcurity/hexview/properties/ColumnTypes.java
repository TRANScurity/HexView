/**
 * @author Andreas "PAX" Lück
 */
package com.github.transcurity.hexview.properties;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import com.github.transcurity.hexview.util.ByteUtilities;

/**
 * Constants for identification of the hex view columns.
 *
 * @author Andreas "PAX" Lück
 */
public enum ColumnTypes
{
    /**
     * This column displays byte offset of the beginning of the concerning hex
     * row.
     *
     * @author Andreas "PAX" Lück
     */
    OFFSET("Offset"),

    /**
     * This column displays the offset by 0 of the concerning cell from the
     * beginning of the row.
     *
     * @author Andreas "PAX" Lück
     */
    COL_0("0", 0),

    /**
     * This column displays the offset by 1 of the concerning cell from the
     * beginning of the row.
     *
     * @author Andreas "PAX" Lück
     */
    COL_1("1", 1),

    /**
     * This column displays the offset by 2 of the concerning cell from the
     * beginning of the row.
     *
     * @author Andreas "PAX" Lück
     */
    COL_2("2", 2),

    /**
     * This column displays the offset by 3 of the concerning cell from the
     * beginning of the row.
     *
     * @author Andreas "PAX" Lück
     */
    COL_3("3", 3),

    /**
     * This column displays the offset by 4 of the concerning cell from the
     * beginning of the row.
     *
     * @author Andreas "PAX" Lück
     */
    COL_4("4", 4),

    /**
     * This column displays the offset by 5 of the concerning cell from the
     * beginning of the row.
     *
     * @author Andreas "PAX" Lück
     */
    COL_5("5", 5),

    /**
     * This column displays the offset by 6 of the concerning cell from the
     * beginning of the row.
     *
     * @author Andreas "PAX" Lück
     */
    COL_6("6", 6),

    /**
     * This column displays the offset by 7 of the concerning cell from the
     * beginning of the row.
     *
     * @author Andreas "PAX" Lück
     */
    COL_7("7", 7),

    /**
     * This column displays the offset by 8 of the concerning cell from the
     * beginning of the row.
     *
     * @author Andreas "PAX" Lück
     */
    COL_8("8", 8),

    /**
     * This column displays the offset by 9 of the concerning cell from the
     * beginning of the row.
     *
     * @author Andreas "PAX" Lück
     */
    COL_9("9", 9),

    /**
     * This column displays the offset by 10 of the concerning cell from the
     * beginning of the row.
     *
     * @author Andreas "PAX" Lück
     */
    COL_A("A", 10),

    /**
     * This column displays the offset by 11 of the concerning cell from the
     * beginning of the row.
     *
     * @author Andreas "PAX" Lück
     */
    COL_B("B", 11),

    /**
     * This column displays the offset by 12 of the concerning cell from the
     * beginning of the row.
     *
     * @author Andreas "PAX" Lück
     */
    COL_C("C", 12),

    /**
     * This column displays the offset by 13 of the concerning cell from the
     * beginning of the row.
     *
     * @author Andreas "PAX" Lück
     */
    COL_D("D", 13),

    /**
     * This column displays the offset by 14 of the concerning cell from the
     * beginning of the row.
     *
     * @author Andreas "PAX" Lück
     */
    COL_E("E", 14),

    /**
     * This column displays the offset by 15 of the concerning cell from the
     * beginning of the row.
     *
     * @author Andreas "PAX" Lück
     */
    COL_F("F", 15),

    /**
     * This column displays the ASCII representation of the concerning row.
     *
     * @author Andreas "PAX" Lück
     */
    ASCII("Dump");

    /**
     * Aggregates all column IDs of a hex view row, inclusive columns that don't
     * show data (offset column) or prepared information (ASCII column).
     *
     * @author Andreas "PAX" Lück
     */
    public static final List<ColumnTypes> ALL_COLUMN_IDS = Arrays.asList(ColumnTypes.OFFSET, ColumnTypes.COL_0, ColumnTypes.COL_1, ColumnTypes.COL_2, ColumnTypes.COL_3, ColumnTypes.COL_4, ColumnTypes.COL_5, ColumnTypes.COL_6, ColumnTypes.COL_7, ColumnTypes.COL_8, ColumnTypes.COL_9, ColumnTypes.COL_A, ColumnTypes.COL_B, ColumnTypes.COL_C, ColumnTypes.COL_D, ColumnTypes.COL_E, ColumnTypes.COL_F, ColumnTypes.ASCII);

    /**
     * Aggregates all column IDs of a hex view row that show the data as hex
     * value.
     *
     * @author Andreas "PAX" Lück
     */
    public static final List<ColumnTypes> DATA_COLUMN_IDS = Arrays.asList(ColumnTypes.COL_0, ColumnTypes.COL_1, ColumnTypes.COL_2, ColumnTypes.COL_3, ColumnTypes.COL_4, ColumnTypes.COL_5, ColumnTypes.COL_6, ColumnTypes.COL_7, ColumnTypes.COL_8, ColumnTypes.COL_9, ColumnTypes.COL_A, ColumnTypes.COL_B, ColumnTypes.COL_C, ColumnTypes.COL_D, ColumnTypes.COL_E, ColumnTypes.COL_F);

    /**
     * The unique identifier for the column.
     *
     * @author Andreas "PAX" Lück
     */
    private final String id;

    /**
     * The byte offset within the hex view row which represents the byte value
     * of the concerning column: <nobr>{@code Row offset + column offset}</nobr>
     * <p/>
     * If the concerning column type doesn't describe a single byte from the
     * data then this value has no meaning.
     *
     * @author Andreas "PAX" Lück
     */
    private int offsetInRow;

    private ColumnTypes(final String id)
    {
        this(id, 0);
    }

    private ColumnTypes(final String id, final int columnOffset)
    {
        this.id = id;
        this.offsetInRow = columnOffset;
    }

    /**
     * @return the id
     *
     * @author Andreas "PAX" Lück
     */
    public String getId()
    {
        return this.id;
    }

    /**
     * @return The byte offset within the hex view row which represents the byte
     *         value of the concerning column: <span style=
     *         "white-space:nowrap">{@code Row offset + column offset}</span>
     *         <p>
     *         &nbsp;
     *         </p>
     *         If the concerning column type doesn't describe a single byte from
     *         the data then this value has no meaning.
     *
     * @author Andreas "PAX" Lück
     */
    public int getOffsetInRow()
    {
        return this.offsetInRow;
    }

    /**
     * Extracts a displayable value for a view component for the current hex
     * view column represented by the current enumeration constant.
     *
     * @param data
     *            The raw bytes displayed by the hex view.
     * @param offset
     *            The offset of the hex view row.
     * @return Either the extracted value which can be displayed by the view,
     *         directly, or {@code null} if there's no data at the requested
     *         location.
     *
     * @author Andreas "PAX" Lück
     */
    public String getDisplayValue(final byte[] data, final int offset)
    {
        if (data == null || offset < 0)
            return null;

        int byteOffset = offset;
        switch (this)
        {
        case OFFSET:
            final String hexOffset = Integer.toHexString(offset).toUpperCase();
            return prependZeros(hexOffset, data.length);
        case ASCII:
            final String asciiDump = new String(Arrays.copyOfRange(data, offset, Math.min(offset
                    + 16, data.length)), StandardCharsets.ISO_8859_1).replaceAll("[\\x00-\\x1F\\x7F-\\x9F]", ".");
            return asciiDump;
        default:
            byteOffset += getOffsetInRow();
            break;
        }

        if (byteOffset < data.length)
            return ByteUtilities.bytesToHex(new byte[]
            { data[byteOffset] });

        return null;
    }

    /**
     * Appends zero digits at the beginning of the specified hex number until:
     * <ul style="list-type: circle">
     * <li>The hex number has an even number of digits</li>
     * <li>The hex number has the full digit length in order to hold the highest
     * row offset number depending on the total number of data bytes to be
     * displayed by the hex view</li>
     * </ul>
     *
     * @param hexNumber
     *            A hex number which represents a row offset of a hex view.
     * @param dataSize
     *            The total number of bytes displayed by the hex view
     *            (influences the totally possible number of hex row offsets.
     * @return The specified hex number with potentially prepended zeros.
     *
     * @author Andreas "PAX" Lück
     */
    private String prependZeros(final String hexNumber, final int dataSize)
    {
        final StringBuilder result = new StringBuilder(hexNumber);
        final int hexRows = dataSize % 16 == 0 ? dataSize / 16
                : dataSize / 16 + 1;
        final int maxValue = hexRows * 16;
        final long requiredDigits = (long) Math.ceil(Math.log10(maxValue)
                / Math.log10(16));

        while (result.length() < requiredDigits)
            result.insert(0, '0');

        if (result.length() % 2 != 0)
            result.insert(0, '0');

        return result.toString();
    }

    @Override
    public String toString()
    {
        return this.id;
    }
}
