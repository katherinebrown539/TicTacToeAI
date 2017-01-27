import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToeAI extends TicTacToeAITemplate
{
	
	/*
	public static void main(String[] args)
	{
		TicTacToeAI ai = new TicTacToeAI();
		//ai.playGame();
	}
	*/
	
	
	public TicTacToeAI(Matrix<Integer> b)
	{
		super(b);
	}
	
	public TicTacToeAI()
	{
		super();
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
						//System.out.println(count);
					//	System.out.println(r);
					//	System.out.println(c);
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

						int sum = horizontal+vertical+diag;
						
						moves.add(new Move(r, c, horizontal, vertical, diag));
					
				}
			}
		}
		
		System.out.println(moves.size());
		int min_sum = 100;
		Move to_return = next_move;
		
		for(Move m : moves)
		{
			to_return = m;
			if(m.getHorizontal() == -2 || m.getVertical() == -2 || m.getDiagonal() == -2)
			{
				
				return m;
			}
			else if(m.getSum() < min_sum)
			{
				min_sum = m.getSum();
				to_return = m;
			}
			
			
			
		}
		
		
		
		if(to_return == null || (to_return.getHorizontal() != -2 && to_return.getVertical() != -2 && to_return.getDiagonal() != -2 && next_move != null) )
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
		//return next_move;
	}
	
	
}