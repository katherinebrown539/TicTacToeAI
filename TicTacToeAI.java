import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToeAI extends JFrame
{
	//optimize ai by keeping up with last move and looking for local possibilities
	private Move last_move;
	private Matrix<Integer> board;
	private JPanel score_panel = new JPanel();
	private JLabel score;
	private JPanel button_panel = new JPanel(new GridLayout(3,3));
	private JButton[] buttons = new JButton[9];
	private int x_win = 0;
	private int o_win = 0;
	private int num_picked = 0;
	public static void main(String[] args)
	{
		TicTacToeAI ai = new TicTacToeAI();
		//ai.playGame();
	}
	
	public void reset()
	{
		num_picked = 0;
		score.setText("X: " + x_win + "O: " + o_win);
		for(int i = 0; i <= 8; i++)
		{
			buttons[i].setText("--");	
			
		}
		this.add(button_panel);
		board = new Matrix<Integer>(3,3);
		int cols = board.getNumColumns();		
		for(int index = 1; index <= (board.getNumColumns() * board.getNumRows()); index++)
		{
			board.setElement(((index -1)/cols + 1), ((index -1)%cols + 1),0); //zero represents an open space
		}
		
	}
	class XOActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			int cols = board.getNumColumns();
			JButton button = (JButton) ae.getSource();
			if((button.getText()).equals("X") || (button.getText()).equals("O")) return;
			int index = Integer.parseInt(button.getActionCommand());
			//System.out.println("button number " + index);
			board.setElement(((index -1)/cols + 1), ((index -1)%cols + 1),1);
			button.setText("X");
			num_picked++;
			if(isWinner())
			{
				reset();
			}
			Move next_move = TicTacToeAI.this.makeMove();
			//System.out.println(next_move.getRow());
			//System.out.println(next_move.getCol());
			System.out.println();
			//System.out.println(next_move.getRow());
			//System.out.println(next_move.getCol());
			//System.out.println((next_move.getRow()-1) *cols + (next_move.getCol()-1));
			board.setElement(next_move.getRow(), next_move.getCol(), -1);
			buttons[(next_move.getRow()-1) *cols + (next_move.getCol()-1)].setText("O");
			num_picked++;
			if(isWinner())
			{
				reset();
			}
		}
	}
	
	public TicTacToeAI()
	{
		score = new JLabel("X: 0 O: 0");
		score_panel.add(score);
		this.add(score_panel, BorderLayout.NORTH);
		for(int i = 0; i <= 8; i++)
		{
			buttons[i] = new JButton();
			buttons[i].setActionCommand((i+1)+"");
			buttons[i].setText("--");
		
			buttons[i].addActionListener(new XOActionListener());
			button_panel.add(buttons[i]);
			
		}
		this.add(button_panel, BorderLayout.CENTER);
		board = new Matrix<Integer>(3,3);
		int cols = board.getNumColumns();		
		for(int index = 1; index <= (board.getNumColumns() * board.getNumRows()); index++)
		{
			board.setElement(((index -1)/cols + 1), ((index -1)%cols + 1),0); //zero represents an open space
		}
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,400);
	}
	
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
			System.out.println(next_move.getRow());
			System.out.println(next_move.getCol());
			board.setElement(next_move.getRow(), next_move.getCol(), -1);
			this.displayBoard();
			System.out.println();
		}
	}
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
			return true;
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
	
	//uncomment lines 245-248 and comment lines 258-261 for cheating
	public Move makeMove()
	{
		Move next_move = null;
		ArrayList<Move> moves = new ArrayList<Move>();
		int count = 0;
		int cols = board.getNumColumns();
		
		for(int r = 1; r <= board.getNumRows(); r++)
		{
			for(int c = 1; c <= board.getNumColumns(); c++)
			{
				//Move next_move;
				/*
				int horizontal = rowSum(r);
				if(horizontal == 2) return new Move(r,c,horizontal);
				int vertical = columnSum(c);
				if(vertical == 2) return new Move(r,c,horizontal);
				*/
				int diag = 0;
				count = (r-1) *board.getNumColumns() + (c-1);
				int i = board.getElement(r,c);
				//System.out.println(count);
				//System.out.println(i);
				if(i == 0)
				{
					
					int horizontal = rowSum(r);
					if(horizontal == 2) {
						next_move = new Move(r,c,0,horizontal,0);
						//return last_move;
					}
					int vertical = columnSum(c);
					if(vertical == 2) {
						next_move = new Move(r,c,vertical,0,0);
						//return last_move;
					}
					
					if(count == 0 || count == 8)
					{
						diag = downDiagonal();
						if(diag == 2) {
						next_move = new Move(r,c,0,0,diag);
						//return last_move;
						}
					}
					else if(count == 2 || count == 6)
					{
						diag = upDiagonal();
						System.out.println(count);
						System.out.println(r);
						System.out.println(c);
						if(diag == 2) {
						next_move = new Move(r,c,0,0,diag);
						//return last_move;
					}
					}
					else if(count == 4)
					{
						int up = upDiagonal();
						int down = downDiagonal();
						//System.out.println(up);
						//System.out.println(down);
						if(up == 2 )
						{
							next_move = new Move(r,c,0,0,up);
							//return last_move;
						}	
						else if(down == 2)
						{
							next_move = new Move(r,c,0,0,down);
						}
						else{
							diag = ( up + down)/2;
						}
					}
					
					else
					{
						int sum = horizontal+vertical+diag;
						
						moves.add(new Move(r, c, horizontal, vertical, diag));
					}
				}
			}
		}
		
		//System.out.println(moves.size());
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
		
		
		
		if(to_return.getHorizontal() != -2 && to_return.getVertical() != -2 && to_return.getDiagonal() != -2 && next_move != null || to_return == null)
		{
			to_return = next_move;
		}
		
		/*
		System.out.println();
		System.out.println(next_move.getSum());
		System.out.println(next_move.getRow());
		System.out.println(next_move.getCol());
		*/
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
	public int downDiagonal()
	{
		return board.getElement(1,1)+board.getElement(2,2)+board.getElement(3,3);
	}
	public int upDiagonal()
	{
			return board.getElement(3,1)+board.getElement(2,2)+board.getElement(1,3);
		
	}
}