/**
 * @author alexier
 */
package com.github.transcurity.hexview.util;

/**
 * Utilities for byte calculation.
 *
 * @author alexier
 */
public interface ByteUtilities
{
    /**
     * Converts the specified byte with the range {@code [-128, 128)} into the
     * ANSII table index position with the scope {@code [0, 256)}.
     *
     * @param value
     *            The byte value to be converted.
     * @return The associated ANSII table index position within the range
     *         {@code [0, 256)}.
     *
     * @author Andreas "PAX" Lück
     */
    static int byteToInt(final byte value)
    {
        return value & 0xFF;
    }

    /**
     * Converts the specified byte consecution into a human-readable HEX
     * presentation.
     *
     * @param bytes
     *            The byte consecution to be transformed into a HEX string.
     * @return The specified byte array transformed into a string representation
     *         (e.g.: {@code 48656C6C6F2050415820E4F6FCDF00...})
     *
     * @author Andreas "PAX" Lück
     */
    static String bytesToHex(final byte[] bytes)
    {
        return bytesToHex(bytes, false);
    }

    /**
     * Converts the specified byte consecution into a human-readable HEX
     * presentation.
     *
     * @param bytes
     *            The byte consecution to be transformed into a HEX string.
     * @param bytePadding
     *            If {@code true} then a space character gets inserted between
     *            each byte in output.
     * @return The specified byte array transformed into a string representation
     *         (e.g.: {@code 48656C6C6F2050415820E4F6FCDF00...})
     *
     * @author Andreas "PAX" Lück
     */
    static String bytesToHex(final byte[] bytes, final boolean bytePadding)
    {
        StringBuilder result = null;
        String hex;
        int i;

        if (bytes == null)
            return null;

        result = new StringBuilder(bytes.length * 2);
        for (i = 0; i < bytes.length; i++)
        {
            hex = Integer.toHexString(ByteUtilities.byteToInt(bytes[i])).toUpperCase();

            if (hex.length() == 1)
                result.append('0');

            result.append(hex);

            if (bytePadding && i < bytes.length - 1)
                result.append(' ');
        }

        return result.toString();
    }
}
