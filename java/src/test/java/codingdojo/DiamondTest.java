package codingdojo;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class DiamondTest{

    @Test
    public void shouldPrintA() throws Exception {
        Diamond diamond = new Diamond('A');

        String result = diamond.print();

        assertThat(result).isEqualTo("A\n");
    }

    @Test
    public void shouldPrintUpperPart() throws Exception {
        Diamond diamond = new Diamond('C');

        ResultDiamond result = new ResultDiamond(diamond.print());

        assertThat(result.line(0)).isEqualTo("  A");
        assertThat(result.line(1)).isEqualTo(" B B");
        assertThat(result.line(2)).isEqualTo("C   C");
    }

    @Test
    public void shouldPrintDiamond() throws Exception {
        Diamond diamond = new Diamond('C');

        ResultDiamond result = new ResultDiamond(diamond.print());

        assertThat(result.line(0)).isEqualTo("  A");
        assertThat(result.line(1)).isEqualTo(" B B");
        assertThat(result.line(2)).isEqualTo("C   C");
        assertThat(result.line(3)).isEqualTo(" B B");
        assertThat(result.line(4)).isEqualTo("  A");
    }

    @Test
    public void testName() throws Exception {

        System.out.printf(new Diamond('Z').print());

    }

    private class ResultDiamond {

        private final List<String> lines;

        public ResultDiamond(String result) {
            lines = asList(result.split("\n"));
        }

        public String line(int i) {
            return lines.get(i);
        }
    }
}