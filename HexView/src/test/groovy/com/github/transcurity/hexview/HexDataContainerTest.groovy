/**
 * @author Andreas "PAX" Lück
 */
package com.github.transcurity.hexview

import static com.github.transcurity.hexview.properties.ColumnTypes.*

import com.github.transcurity.hexview.container.ReadonlyHexContainer
import com.vaadin.data.Item
import com.vaadin.data.Property

import spock.lang.Shared
import spock.lang.Specification

/**
 * Tests for {@link ReadonlyHexContainer}.
 *
 * @author Andreas "PAX" Lück
 */
class HexDataContainerTest extends Specification
{
    @Shared
    def byte[] usualData = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34] as byte[]

    @Shared
    def length15data = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14] as byte[]

    @Shared
    def length16data = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15] as byte[]

    @Shared
    def length17data = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16] as byte[]

    byte[] createArray(int lastElementValue)
    {
        Collection<Byte> result = new LinkedList<>()
        for (int i = 0; i < lastElementValue+1; i++)
            result.add((byte)i)

        return result.toArray(new byte[0])
    }

    def "Check nextItemId" ()
    {
        given:

        def container = new ReadonlyHexContainer(bytes)

        when:

        def result = container.nextItemId(idParam)

        then:

        result == expectedResult

        where:

        idParam || expectedResult || bytes
        -1      | null            | usualData
        0       | 16              | usualData
        1       | 16              | usualData
        15      | 16              | usualData
        16      | 32              | usualData
        17      | 32              | usualData
        31      | 32              | usualData
        32      | null            | usualData
        33      | null            | usualData
        0       | null            | [] as byte[]
        1       | null            | [] as byte[]
    }

    def "Check prevItemId" ()
    {
        given:

        def container = new ReadonlyHexContainer(bytes)

        when:

        def result = container.prevItemId(idParam)

        then:

        result == expectedResult

        where:

        idParam || expectedResult || bytes
        -1      | null            | usualData
        0       | null            | usualData
        1       | 0               | usualData
        15      | 0               | usualData
        16      | 0               | usualData
        17      | 16              | usualData
        31      | 16              | usualData
        32      | 16              | usualData
        33      | 32              | usualData
        0       | null            | [] as byte[]
        1       | null            | [] as byte[]
    }

    def "Check firstItemId" ()
    {
        given:

        def container = new ReadonlyHexContainer(bytes)

        when:

        def result = container.firstItemId()

        then:

        result == expectedResult

        where:

        expectedResult || bytes
        null            | [] as byte[]
        0               | length15data
        0               | length16data
        0               | length17data
        0               | usualData
    }

    def "Check lastItemId" ()
    {
        given:

        def container = new ReadonlyHexContainer(bytes)

        when:

        def result = container.lastItemId()

        then:

        result == expectedResult

        where:

        expectedResult || bytes
        null            | [] as byte[]
        0               | length15data
        0               | length16data
        16              | length17data
        32              | usualData
    }

    def "Check isFirstId" ()
    {
        given:

        def container = new ReadonlyHexContainer(bytes)

        when:

        def result = container.isFirstId(idParam)

        then:

        result == expectedResult

        where:

        idParam  || expectedResult || bytes
        null     | false           | usualData
        "string" | false           | usualData
        -1       | false           | usualData
        0        | true            | usualData
        1        | false           | usualData
        16       | false           | usualData
        0        | false           | [] as byte[]
        1        | false           | [] as byte[]
    }

    def "Check isLastId" ()
    {
        given:

        def container = new ReadonlyHexContainer(bytes)

        when:

        def result = container.isLastId(idParam)

        then:

        result == expectedResult

        where:

        idParam  || expectedResult || bytes
        null     | false           | usualData
        "string" | false           | usualData
        -1       | false           | usualData
        0        | false           | usualData
        1        | false           | usualData
        16       | false           | usualData
        31       | false           | usualData
        32       | true            | usualData
        33       | false           | usualData
        34       | false           | usualData
        0        | true            | [] as byte[]
        1        | false           | [] as byte[]
    }

    def "Check getItem" ()
    {
        given:

        def container = new ReadonlyHexContainer(bytes)

        when:

        Item result = container.getItem(idParam)
        Integer offset = result != null ? result.offset : null

        then:

        offset == expectedResult

        where:

        idParam  || expectedResult      || bytes
        null     | null                 | usualData
        "string" | null                 | usualData
        -1       | null                 | usualData
        0        | 0                    | usualData
        1        | null                 | usualData
        15       | null                 | usualData
        16       | 16                   | usualData
        17       | null                 | usualData
        31       | null                 | usualData
        32       | 32                   | usualData
        33       | null                 | usualData
        "1"      | null                 | usualData
        0        | null                 | [] as byte[]
        1        | null                 | [] as byte[]
    }

    def "Check getContainerPropertyIds" ()
    {
        given:

        def container = new ReadonlyHexContainer(usualData)

        when:

        def propertyIds = container.getContainerPropertyIds()

        then:

        propertyIds != null
        propertyIds.size() > 1
    }

    def "Check getItemIds" ()
    {
        given:

        def container = new ReadonlyHexContainer(bytes)

        when:

        def result = container.getItemIds()

        then:

        result.size() == expectedResult.size()
        result.every
        { it in expectedResult }

        where:

        expectedResult      || bytes
        []| [] as byte[]
        [0, 16, 32]| usualData
        [0]| length15data
        [0]| length16data
    }

    def "Check getContainerProperty" ()
    {
        given:

        def container = new ReadonlyHexContainer(bytes)

        when:

        Property result = container.getContainerProperty(itemIdParam, propertyIdParam)
        String resultValue = result != null ? result.getValue() : null

        then:

        resultValue == expectedResult

        where:

        itemIdParam ||  propertyIdParam || expectedResult      || bytes
        null        | null                 |  null                | usualData
        0           | null                 |  null                | usualData
        null        | COL_0                |  null                | usualData
        "0"         | COL_0                |  null                | usualData
        0           | COL_0                |  "00"                | usualData
        1           | COL_0                |  null                | usualData
        15          | COL_0                |  null                | usualData
        16          | COL_0                |  "10"                | usualData
        17          | COL_0                |  null                | usualData
        0           | COL_0                |  null                | [] as byte[]
    }

    def "Check column IDs enumeration" ()
    {
        when:

        def result = columnId.getDisplayValue(bytes, offset)

        then:

        result == expectedResult

        where:

        offset || columnId || expectedResult    || bytes
        -1     | COL_0     | null               | null
        -1     | COL_0     | null               | usualData
        0      | COL_0     | null               | null
        0      | COL_0     | "00"               | usualData
        0      | COL_1     | "01"               | usualData
        0      | COL_F     | "0F"               | usualData
        16     | COL_0     | "10"               | usualData
        16     | COL_1     | "11"               | usualData
        16     | COL_F     | "1F"               | usualData
        32     | COL_0     | "20"               | usualData
        32     | COL_2     | "22"               | usualData
        32     | COL_3     | null               | usualData
        0      | OFFSET    | "00"               | usualData
        16     | OFFSET    | "10"               | usualData
        32     | OFFSET    | "20"               | usualData
        0      | OFFSET    | "00"               | length15data
        0      | OFFSET    | "00"               | length17data
        0      | OFFSET    | "00"               | createArray(0x01)
        0      | OFFSET    | "00"               | createArray(0xFF)
        0xF0   | OFFSET    | "F0"               | createArray(0xFF)
        0xF0   | OFFSET    | "00F0"             | createArray(0x100)
        0      | OFFSET    | "0000"             | createArray(0x100)
        0      | OFFSET    | "0000"             | createArray(0xFFFF)
        0xFFF0 | OFFSET    | "FFF0"             | createArray(0xFFFF)
        0xFFF0 | OFFSET    | "00FFF0"           | createArray(0x10000)
        0      | OFFSET    | "000000"           | createArray(0x10000)
        0      | ASCII     | "................" | usualData
        32     | ASCII     | " !\""             | usualData
        0      | ASCII     | ".... !..þÿ"       | [0x00, 0x01, 0x1E, 0x1F, 0x20, 0x21, 0x7F, 0x80, 0xFE, 0xFF] as byte[]
        0      | ASCII     | ".... ~....\u00A0" | [0x00, 0x01, 0x1E, 0x1F, 0x20, 0x7E, 0x7F, 0x80, 0x9E, 0x9F, 0xA0] as byte[]
    }

    def "Check getType" ()
    {
        given:

        def container = new ReadonlyHexContainer(usualData)

        when:

        def result = container.getType(propertyIdParam)

        then:

        result == expectedResult

        where:

        propertyIdParam || expectedResult
        null            | Object.class
        "asdf"          | Object.class
        COL_0           | String.class
        ASCII           | String.class
        OFFSET          | String.class
    }

    def "Check size" ()
    {
        given:

        def container = new ReadonlyHexContainer(bytes)

        when:

        def result = container.size()

        then:

        result == expectedResult

        where:

        expectedResult      || bytes
        0                   | [] as byte[]
        3                   | usualData
        1                   | length15data
        1                   | length16data
        2                   | length17data
    }

    def "Check containsId" ()
    {
        given:

        def container = new ReadonlyHexContainer(bytes)

        when:

        def result = container.containsId(itemIdParam)

        then:

        result == expectedResult

        where:

        itemIdParam || expectedResult     || bytes
        0           | false               | [] as byte[]
        null        | false               | usualData
        0           | true                | usualData
        1           | false               | usualData
        15          | false               | usualData
        16          | true                | usualData
        17          | false               | usualData
        32          | true                | usualData
        33          | false               | usualData
        34          | false               | usualData
        35          | false               | usualData
        48          | false               | usualData
    }

    def "Check indexOfId" ()
    {
        given:

        def container = new ReadonlyHexContainer(bytes)

        when:

        def result = container.indexOfId(itemIdParam)

        then:

        result == expectedResult

        where:

        itemIdParam || expectedResult     || bytes
        null        | -1                  | usualData
        "0"         | -1                  | usualData
        0           | -1                  | [] as byte[]
        1           | -1                  | [] as byte[]
        0           | 0                   | [0] as byte[]
        0           | 0                   | usualData
        1           | -1                  | usualData
        15          | -1                  | usualData
        16          | 1                   | usualData
        17          | -1                  | usualData
        31          | -1                  | usualData
        32          | 2                   | usualData
        34          | -1                  | usualData
        35          | -1                  | usualData
        34553       | -1                  | usualData
    }

    def "Check getIdByIndex" ()
    {
        given:

        def container = new ReadonlyHexContainer(bytes)

        when:

        def result = container.getIdByIndex(indexParam)

        then:

        result == expectedResult

        where:

        indexParam || expectedResult     || bytes
        0          | null                | [] as byte[]
        0          | 0                   | usualData
        1          | 16                  | usualData
        2          | 32                  | usualData
        3          | null                | usualData
    }

    def "Check getItemIds range" ()
    {
        given:

        def container = new ReadonlyHexContainer(bytes)

        when:

        def result = container.getItemIds(startIndexParam, numberOfItemsParam)

        then:

        result.size() == expectedResult.size()
        result.every
        { it in expectedResult }

        where:

        startIndexParam || numberOfItemsParam || expectedResult     || bytes
        0               | 0                   | [] as List          | [] as byte[]
        0               | 1                   | [] as List          | [] as byte[]
        0               | 0                   | [] as List          | usualData
        0               | 1                   | [0] as List         | usualData
        -1              | 1                   | [] as List          | usualData
        -1              | 2                   | [0] as List         | usualData
        -1              | 3                   | [0, 16] as List     | usualData
        0               | 3                   | [0, 16, 32] as List | usualData
        0               | 2                   | [0, 16] as List     | usualData
        1               | 34                  | [16, 32] as List    | usualData
        2               | 1                   | [32] as List        | usualData
        3               | 1                   | [] as List          | usualData
    }
}
