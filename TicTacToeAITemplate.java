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
	private int num_picked = 0;
	
	public int getXWins() {return x_win;}
	public int getOWins() {return o_win;}
	public int getTies() {return ties;}
	
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
	/*
	public void playGame()
	{
		Keyboard kb = Keyboard.getKeyboard();
		int cols = board.getNumColumns();
		while(!isWinner())
		{
			this.displayBoard();
			System.out.println();
			int selection = kb.readInt("\r\nEnter location: ");
			board.setElement(((selection -1)/cols + 1), ((selection -1)%cols + 1),1);
			this.displayBoard();
			System.out.println();
			Move next_move = this.makeMove();
			//System.out.println(next_move.getRow());
			//System.out.println(next_move.getCol());
			board.setElement(next_move.getRow(), next_move.getCol(), -1);
			this.displayBoard();
			System.out.println();
		}
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
	
	*/
	public boolean isWinner()
	{
		
		System.out.println("In check winner");
		//check diagonal sums
		int sum = upDiagonal();
		if(sum == 3)
		{
			JOptionPane.showMessageDialog(this, "X Wins");
			System.out.println("X Wins");
			x_win++;
			return true;
		}
		else if(sum == -3)
		{
			JOptionPane.showMessageDialog(this, "O Wins");
			System.out.println("O Wins");
			o_win++;
			return true;
		}
		
		sum = downDiagonal();
		if(sum == 3)
		{
			JOptionPane.showMessageDialog(this, "X Wins");
			System.out.println("X Wins");
			x_win++;
			return true;
		}
		else if(sum == -3)
		{
			JOptionPane.showMessageDialog(this, "O Wins");
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
				JOptionPane.showMessageDialog(this, "X Wins");
				System.out.println("X Wins");
				x_win++;
				return true;
			}
			else if(sum == -3)
			{
				JOptionPane.showMessageDialog(this, "O Wins");
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
				JOptionPane.showMessageDialog(this, "X Wins");
				System.out.println("X Wins");
				x_win++;
				return true;
			}
			else if(sum == -3)
			{
				JOptionPane.showMessageDialog(this, "O Wins");
				System.out.println("O Wins");
				o_win++;
				return true;
			}
		}
		
		if(num_picked == 9)
		{
			JOptionPane.showMessageDialog(this, "TIE");
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