public class Move
{
	private int row;
	private int col;
	private int sum; //this is row, grid, diagonal (if edge or center)
	
	public Move(int row, int col, int sum)
	{
		this.row = row;
		this.col = col;
		this.sum = sum;
	}
	
	public int getRow() { return row;}
	public int getCol(){return col;}
	public int getSum(){ return sum;}
	
	
}