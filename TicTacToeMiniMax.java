import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeMiniMax extends TicTacToeAITemplate
{
	private int ai = -1;
	private int human = 1;
	
	public TicTacToeMiniMax(Matrix<Integer> b)
	{
		super(b);
	}
	public TicTacToeMiniMax()
	{
		super();
	}
	
	//board - copy of current tic tac toe board to pass around
	//depth - how many moves we are in the game [0,8]
	//
	public Move makeMove()
	{
		//System.out.println("MINIMAX");
		int max = -100;
		
		int row = -1;
		int col = -1;
		for(int i = 1; i <= 3; i++)
		{
			for(int c = 1; c <= 3; c++)
			{
				if(getElement(i,c) != 0) continue;
				TicTacToeAITemplate cpy = new TicTacToeMiniMax(board);
				cpy.markSpace(i,c,ai);
				int res = cpy.isWinnerAI();
				String message = (res == 1) ? "X wins" : (res == -1) ? "O wins" : (res == 123) ? "Tie" : "More moves to be made";
				//System.out.println(message);;
			
				res = ((TicTacToeMiniMax)cpy).min(cpy, -100, 100);
				//System.out.print(res + " ");
				
				if(res > max)
				{
					max = res;
					row = i;
					col = c;
				}
				
				
			}
			
		}
		
		System.out.println("\nMove Chosen: (" + row + ", " + col + ")");
		return new Move(row, col, -1,0,0);
	}
	
	public int max(TicTacToeAITemplate b, int alpha, int beta)
	{
		int v = b.isWinnerAI();
		if(v == 0 || v == 1 || v == -1)
		{
			return v;
		}
		//System.out.println("Layer " +  layer);
		int value = -100;
		for(int r = 1; r <= 3; r++)
		{
			for(int c = 1; c <= 3; c++)
			{
				//System.out.println("max: ("+r+", "+ c+")");
				if(b.getElement(r,c) != 0) continue;
				TicTacToeAITemplate cpy = new TicTacToeMiniMax(board);
				cpy.markSpace(r,c,ai);
				//cpy.printBoard();
				int res = cpy.isWinnerAI();
				String message = (res == 1) ? "X wins" : (res == -1) ? "O wins" : (res == 123) ? "Tie" : "More moves to be made";
				//System.out.println(message);
				res = ((TicTacToeMiniMax)cpy).min(cpy, alpha, beta);
				if(res > value)
				{
					value = res;
				}
				if(value >= beta) return value;
				alpha = (alpha > value) ? alpha : value;
					
					
			
			} 
		}
		
		return value;
	}
	
	
	public int min(TicTacToeAITemplate b, int alpha, int beta)
	{
		int v = b.isWinnerAI();
		if(v == 0 || v == 1 || v == -1)
		{
			return v;
		}
		//System.out.println("Layer " +  layer);
		int value = 10000;
		for(int r = 1; r <= 3; r++)
		{
			for(int c = 1; c <= 3; c++)
			{
				if(b.getElement(r,c) != 0) continue;
				TicTacToeAITemplate cpy = new TicTacToeMiniMax(board);
				cpy.markSpace(r,c,human);
				int res = cpy.isWinnerAI();
				String message = (res == 1) ? "X wins" : (res == -1) ? "O wins" : (res == 123) ? "Tie" : "More moves to be made";
				
				res = ((TicTacToeMiniMax)cpy).max(cpy, alpha, beta);
				if(res < value)
				{
					value = res;
				}
				if(value <= alpha)
				{
					return value;
				}								
				beta = (beta < value) ? beta : value;
			}
		}
		
		return value;
	}
	
}