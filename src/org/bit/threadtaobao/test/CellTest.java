package org.bit.threadtaobao.test; 

import junit.framework.TestCase;
class Cell
{
	private int Column;
	private int Row;
	public int getColumn() {
		return Column;
	}
	public void setColumn(int column) {
		Column = column;
	}
	public int getRow() {
		return Row;
	}
	public void setRow(int row) {
		Row = row;
	}
	public Cell(int column, int row) {
		super();
		Column = column;
		Row = row;
	}
	
}
public class CellTest extends TestCase {

    public void testConstructor() {
        Cell newCell = new Cell(10, 5);
        assertEquals(10, newCell.getColumn());
        assertEquals(5, newCell.getRow());
    }
}
