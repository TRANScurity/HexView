/**
 * @author Andreas "PAX" Lück
 */
package com.github.transcurity.hexview

import static com.github.transcurity.hexview.properties.ColumnTypes.*

import com.github.transcurity.hexview.cells.HexCellStyleGenerator
import com.github.transcurity.hexview.cells.StyleRange

import spock.lang.Specification

/**
 * Applies tests to {@link HexCellStyleGenerator}.
 *
 * @author Andreas "PAX" Lück
 */
class CellStyleGeneratorTest extends Specification
{
    def "Adding styles is working" ()
    {
        given:

        def styleGenerator = new HexCellStyleGenerator()
        def firstRange = new StyleRange(0, 1, "styles won't be taken into account for hashcode and equals")
        def secondRange = new StyleRange(2, 1, "not important")
        def thirdRange = new StyleRange(4, 5, "not added to the styles")

        when:

        styleGenerator.addCustomStyleRange(0, 1, "a")
        styleGenerator.addCustomStyleRange(2, 1, "b")
        def ranges = styleGenerator.getCustomStyleRanges()

        then:

        ranges.size() == 2
        ranges.every
        {
            it in Arrays.asList(firstRange, secondRange)
        }
        !ranges.contains(thirdRange)
    }

    def "Removal of styles is working" ()
    {
        given:

        def styleGenerator = new HexCellStyleGenerator()
        def firstRange = new StyleRange(0, 1, "styles won't be taken into account for hashcode and equals")
        def secondRange = new StyleRange(2, 1, "not important")
        def thirdRange = new StyleRange(4, 5, "not important 2")
        def fourthRange = new StyleRange(5, 6, "not added to the styles")

        when:

        styleGenerator.addCustomStyleRange(0, 1, "a")
        styleGenerator.addCustomStyleRange(2, 1, "b")
        styleGenerator.addCustomStyleRange(2, 1, "b2")
        styleGenerator.addCustomStyleRange(4, 5, "c")

        styleGenerator.removeCustomStyleRange(0, 1)
        styleGenerator.removeCustomStyleRange(2, 1)
        styleGenerator.removeCustomStyleRange(4, 4)
        styleGenerator.removeCustomStyleRange(4, 6)
        styleGenerator.removeCustomStyleRange(5, 5)
        styleGenerator.removeCustomStyleRange(3, 5)
        def ranges = styleGenerator.getCustomStyleRanges()

        then:

        ranges.size() == 1
        ranges.every
        {
            it in Arrays.asList(thirdRange)
        }
        !ranges.contains(fourthRange)
    }

    def "Generation of CSS class names string for non-empty ranges" ()
    {
        given:

        def styleGenerator = new HexCellStyleGenerator()
        def firstRange = new StyleRange(3, 4, "first1 first2")
        def secondRange = new StyleRange(7, 3, "second 1")
        def thirdRange = new StyleRange(8, 5, "third1 third2")
        def fourthRange = new StyleRange(16, 3, "fourth1")

        when:

        styleGenerator.addCustomStyleRange(firstRange)
        styleGenerator.addCustomStyleRange(secondRange)
        styleGenerator.addCustomStyleRange(thirdRange)
        styleGenerator.addCustomStyleRange(fourthRange)

        then:

        "" == styleGenerator.getCustomStyles(0);
        "" == styleGenerator.getCustomStyles(2);
        " first1 first2" == styleGenerator.getCustomStyles(3);
        " first1 first2" == styleGenerator.getCustomStyles(6);
        " second 1" == styleGenerator.getCustomStyles(7);
        " second 1 third1 third2" == styleGenerator.getCustomStyles(8);
        " second 1 third1 third2" == styleGenerator.getCustomStyles(9);
        " third1 third2" == styleGenerator.getCustomStyles(10);
        " third1 third2" == styleGenerator.getCustomStyles(12);
        "" == styleGenerator.getCustomStyles(13);
        "" == styleGenerator.getCustomStyles(15);
        " fourth1" == styleGenerator.getCustomStyles(16);
        " fourth1" == styleGenerator.getCustomStyles(18);
        "" == styleGenerator.getCustomStyles(19);
    }
}
