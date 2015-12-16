import java.util.ArrayList;
public class TicTacToeAI
{
	private Matrix<Integer> board;
	
	public static void main(String[] args)
	{
		TicTacToeAI ai = new TicTacToeAI();
		ai.playGame();
	}
	
	public TicTacToeAI()
	{
		board = new Matrix<Integer>(3,3);
		int cols = board.getNumColumns();		
		for(int index = 1; index <= (board.getNumColumns() * board.getNumRows()); index++)
		{
			board.setElement(((index -1)/cols + 1), ((index -1)%cols + 1),0); //zero represents an open space
		}
	}
	
	public void playGame()
	{
		Keyboard kb = Keyboard.getKeyboard();
		int cols = board.getNumColumns();
		while(!isWinner())
		{
			this.displayBoard();
			int selection = kb.readInt("\r\nEnter location: ");
			board.setElement(((selection -1)/cols + 1), ((selection -1)%cols + 1),1);
			Move next_move = this.makeMove();
			System.out.println(next_move.getRow());
			System.out.println(next_move.getCol());
			board.setElement(next_move.getRow(), next_move.getCol(), -1);
		}
	}
	public boolean isWinner()
	{
		
		//check diagonal sums
		int sum = diagonalSum(true);
		if(sum == 3)
		{
			System.out.println("X Wins");
			return true;
		}
		else if(sum == -3)
		{
			System.out.println("O Wins");
			return true;
		}
		//check row sums
		for(int r = 1; r <= board.getNumRows(); r++)
		{
			sum = rowSum(r);
			if(sum == 3)
			{
				System.out.println("X Wins");
				return true;
			}
			else if(sum == -3)
			{
				System.out.println("O Wins");
				return true;
			}
		}
		//check vertical sums
		for(int c = 1; c <= board.getNumColumns(); c++)
		{
			sum = columnSum(c);
			if(sum == 3)
			{
				System.out.println("X Wins");
				return true;
			}
			else if(sum == -3)
			{
				System.out.println("O Wins");
				return true;
			}
		}
		return false;
	}
	public void displayBoard()
	{
		int r = 0; 
		int c = 0;
		int cols = board.getNumColumns();
		Object[] grid = (board.getArray());
		for(int i = 1; i <= grid.length; i++)
		{
			r = (i-1)/cols +1;
			c = (i-1)%cols +1;
			int value = board.getElement(r,c);
			if(value == 1)
			{
				System.out.print("X");
			}
			else if(value == -1)
			{
				System.out.print("O");
			}
			else
			{
				System.out.print(i);
			}
			
			if(i != 3 && i != 6 && i != 9)
			{
				System.out.print("|");
			}
			else if(r != 3)
			{
				System.out.println("\r\n------");
			}
		}
	}
	
	
	public Move makeMove()
	{
		ArrayList<Move> moves = new ArrayList<Move>();
		int count = 0;
		int cols = board.getNumColumns();
		
		for(int r = 1; r <= board.getNumRows(); r++)
		{
			for(int c = 1; c <= board.getNumColumns(); c++)
			{
				int horizontal = rowSum(r);
				int vertical = columnSum(c);
				int diag = 0;
				count = (r-1) *board.getNumColumns() + (c-1);
				int i = board.getElement(r,c);
				//System.out.println(count);
				//System.out.println(i);
				if(i == 0)
				{
					if(count == 0 || count == 8)
					{
						diag = diagonalSum(true);
					}
					else if(count == 2 || count == 6)
					{
						diag = diagonalSum(false);
					}
					else if(count == 5)
					{
						diag = (diagonalSum(true) + diagonalSum(false))/2;
					}
					int sum = horizontal+vertical+diag;
					
					moves.add(new Move((count-1)/cols +1, (count-1)%cols +1, sum));
				}
			}
		}
		
		int min_sum = 100;
		Move to_return = null;
		
		for(Move m : moves)
		{
			if(m.getSum() < min_sum)
			{
				min_sum = m.getSum();
				to_return = m;
			}
		}
		
		return to_return;
	}
	
	
	//r is current row we are on
	public int rowSum(int r)
	{
		int sum = 0; 
		for(int i = 1; i <= board.getNumColumns(); i++)
		{
			sum += board.getElement(r,i);
		}
		
		return sum;
	}
	
	//c is current column we are on
	public int columnSum(int c)
	{
		int sum = 0; 
		for(int i = 1; i <= board.getNumRows(); i++)
		{
			sum += board.getElement(i,c);
		}
		
		return sum;
	}
	
	//dir = true = down diagonal
	//dir = false = up diagonal
	public int diagonalSum(boolean dir)
	{
		if(dir)
		{
			return board.getElement(1,1)+board.getElement(2,2)+board.getElement(3,3);
		}
		else
		{
			return board.getElement(3,1)+board.getElement(2,2)+board.getElement(1,3);
		}
	}
}