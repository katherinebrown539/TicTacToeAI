import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public abstract class TicTacToeAITemplate extends JFrame
{
	protected Matrix<Integer> board;
	private int x_win = 0;
	private int o_win = 0;
	private int ties = 0;
	protected int num_picked = 0;
	
	public int getXWins() {return x_win;}
	public int getOWins() {return o_win;}
	public int getTies() {return ties;}
	
	public TicTacToeAITemplate(Matrix<Integer> b)
	{
		int num = 0;
		
		board = new Matrix<Integer>(3,3);
		for(int r = 1; r <= 3; r++)
		{
			for(int c = 1; c <= 3; c++)
			{
				if(b.getElement(r,c) != 0) num++;
				board.setElement(r,c, b.getElement(r,c));
			}
		}
		this.num_picked = num;
	}
	
	
	public Matrix<Integer> getBoard(){return board;}
	
	public void reset()
	{
		num_picked = 0;
		board = new Matrix<Integer>(3,3);
		int cols = board.getNumColumns();		
		for(int index = 1; index <= (board.getNumColumns() * board.getNumRows()); index++)
		{
			board.setElement(((index -1)/cols + 1), ((index -1)%cols + 1),0); //zero represents an open space
		}
		
	}
	
	public int getNumPicked(){return num_picked;}
	public void printBoard()
	{
		System.out.println("");
		for(int r = 1; r <= 3; r++)
		{
			for(int c = 1; c <= 3; c++)
			{
				int val = board.getElement(r,c);
				String p = (val == 0) ? "_ " : (val == 1) ? "X " : "O ";
				System.out.print(p);
			}
			System.out.println("");
		}
	}
	
	public Integer getElement(int r, int c){return board.getElement(r,c);}
	
	public void markSpace(int row, int col, int val)
	{
		board.setElement(row, col,val);
		num_picked++;
	}
	public TicTacToeAITemplate()
	{
		
		board = new Matrix<Integer>(3,3);
		int cols = board.getNumColumns();		
		for(int index = 1; index <= (board.getNumColumns() * board.getNumRows()); index++)
		{
			markSpace(((index -1)/cols + 1), ((index -1)%cols + 1),0);
		}
		
	}
	
	public abstract Move makeMove();

	//1 if x is winner
	//-1 if o is winner
	//0 if no winner
	//123 if tie
	public int isWinnerAI()
	{
		//check diagonal sums
		int sum = upDiagonal();
		if(sum == 3)
		{
			
			return -1;
		}
		else if(sum == -3)
		{
			
			return 1;
		}
		
		sum = downDiagonal();
		if(sum == 3)
		{
			
			return -1;
		}
		else if(sum == -3)
		{
			
			return 1;
		}
		//check row sums
		for(int r = 1; r <= board.getNumRows(); r++)
		{
			sum = rowSum(r);
			if(sum == 3)
			{
				
				return -1;
			}
			else if(sum == -3)
			{
				
				return 1;
			}
		}
		//check vertical sums
		for(int c = 1; c <= board.getNumColumns(); c++)
		{
			sum = columnSum(c);
			if(sum == 3)
			{
				
				return -1;
			}
			else if(sum == -3)
			{
				
				return 1;
			}
		}
		
		if(num_picked == 9)
		{
			
			return 0;
		}
		return 123;
	}
	
	public boolean isWinner()
	{
		SimpleDialogs.useSystemStyle();
		System.out.println("In check winner");
		String x = "X Wins!";
		String o = "O Wins!";
		String tie = "Tie!";
		//check diagonal sums
		int sum = upDiagonal();
		if(sum == 3)
		{
			SimpleDialogs.NormalOutput("GAME OVER!", x);
			System.out.println("X Wins");
			x_win++;
			return true;
		}
		else if(sum == -3)
		{
			SimpleDialogs.NormalOutput("GAME OVER!", o);
			System.out.println("O Wins");
			o_win++;
			return true;
		}
		
		sum = downDiagonal();
		if(sum == 3)
		{
			SimpleDialogs.NormalOutput("GAME OVER!", x);
			System.out.println("X Wins");
			x_win++;
			return true;
		}
		else if(sum == -3)
		{
			SimpleDialogs.NormalOutput("GAME OVER!", o);
			System.out.println("O Wins");
			o_win++;
			return true;
		}
		//check row sums
		for(int r = 1; r <= board.getNumRows(); r++)
		{
			sum = rowSum(r);
			if(sum == 3)
			{
				SimpleDialogs.NormalOutput("GAME OVER!", x);
				System.out.println("X Wins");
				x_win++;
				return true;
			}
			else if(sum == -3)
			{
				SimpleDialogs.NormalOutput("GAME OVER!", o);
				System.out.println("O Wins");
				o_win++;
				return true;
			}
		}
		//check vertical sums
		for(int c = 1; c <= board.getNumColumns(); c++)
		{
			sum = columnSum(c);
			if(sum == 3)
			{
				SimpleDialogs.NormalOutput("GAME OVER!", x);
				System.out.println("X Wins");
				x_win++;
				return true;
			}
			else if(sum == -3)
			{
				SimpleDialogs.NormalOutput("GAME OVER!", x);
				System.out.println("O Wins");
				o_win++;
				return true;
			}
		}
		
		if(num_picked == 9)
		{
			SimpleDialogs.NormalOutput("GAME OVER!", tie);
			ties++;
			return true;
		}
		return false;
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
	public int downDiagonal()
	{
		return board.getElement(1,1)+board.getElement(2,2)+board.getElement(3,3);
	}
	
	public int upDiagonal()
	{
			return board.getElement(3,1)+board.getElement(2,2)+board.getElement(1,3);
		
	}
}