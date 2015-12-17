public class Move
{
	private int row;
	private int col;
	private int vertical_sum; //this is row, grid, diagonal (if edge or center)
	private int horizontal_sum;
	private int diag_sum;
	public Move(int row, int col, int vertical, int horizontal, int diag)
	{
		this.row = row;
		this.col = col;
		this.vertical_sum = vertical;
		this.horizontal_sum = horizontal;
		this.diag_sum = diag;
	}
	
	public int getRow() { return row;}
	public int getCol(){return col;}
	public int getSum(){ return vertical_sum+horizontal_sum+diag_sum;}
	public int getVertical(){return vertical_sum;}
	public int getHorizontal(){return horizontal_sum;}
	public int getDiagonal(){return diag_sum;}
	
}