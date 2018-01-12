/**
 * @author Andreas "PAX" Lück
 */
package com.github.transcurity.hexview.cells;

/**
 * Remembers the CSS style and an offset range where it must be used.
 * <p>
 * &nbsp;
 * </p>
 * <b>Note:</b> {@link #hashCode()} and {@link #equals(Object)} only considers
 * the location of the range but not the CSS classes value.
 *
 * @author Andreas "PAX" Lück
 */
public class StyleRange
{
    /**
     * The start offset of the range where following condition is valid:
     * <nobr>{@code 0 <= offset < length}</nobr>
     *
     * @author Andreas "PAX" Lück
     */
    private final int offset;

    /**
     * The number of bytes occupied by this range.
     *
     * @author Andreas "PAX" Lück
     */
    private final int length;

    /**
     * The name of CSS classes (separated by a blank) to be applied to this cell
     * range, e.g.: {@code importantSection boldFont highlighted}
     *
     * @author Andreas "PAX" Lück
     */
    private final String cssClasses;

    /**
     * @param offset
     *            The start offset of the range where following condition is
     *            valid: <nobr>{@code 0 <= offset < length}</nobr>
     * @param length
     *            The number of bytes occupied by this range.
     *
     * @author Andreas "PAX" Lück
     */
    StyleRange(final int offset, final int length)
    {
        this(offset, length, "");
    }

    /**
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
    public StyleRange(final int offset, final int length,
            final String cssClasses)
    {
        this.offset = offset;
        this.length = length;
        this.cssClasses = cssClasses;
    }

    /**
     * @return The start offset of the range where following condition is valid:
     *         <span style=
     *         "white-space:nowrap">{@code 0 <= offset < length}</span>
     *
     * @author Andreas "PAX" Lück
     */
    public int getOffset()
    {
        return this.offset;
    }

    /**
     * @return The number of bytes occupied by this range.
     *
     * @author Andreas "PAX" Lück
     */
    public int getLength()
    {
        return this.length;
    }

    /**
     * @return The name of CSS classes (separated by a blank) to be applied to
     *         this cell range, e.g.:
     *         {@code importantSection boldFont highlighted}
     *
     * @author Andreas "PAX" Lück
     */
    public String getCssClasses()
    {
        return this.cssClasses;
    }

    @Override
    public String toString()
    {
        return "[offset=" + this.offset + ", length=" + this.length
                + ", cssClasses=" + this.cssClasses + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.length;
        result = prime * result + this.offset;
        return result;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final StyleRange other = (StyleRange) obj;
        if (this.length != other.length)
            return false;
        if (this.offset != other.offset)
            return false;
        return true;
    }
}
