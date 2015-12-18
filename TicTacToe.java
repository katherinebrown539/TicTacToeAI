import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToe extends JFrame
{
	private JPanel score_panel = new JPanel();
	private JLabel score;
	private JPanel button_panel = new JPanel(new GridLayout(3,3));
	private JButton[] buttons = new JButton[9];
	private TicTacToeAI ai;
	private Font meme_font = new Font("Impact", Font.BOLD, 24);
	public static void main(String[] args)
	{
		TicTacToe ttt = new TicTacToe();
	}
	
	public void reset()
	{
		ai.reset();
		score.setText("X: " + ai.getXWins() + "O: " + ai.getOWins() + "Ties: " + ai.getTies());
		for(int i = 0; i <= 8; i++)
		{
			buttons[i].setText("--");	
			
			
		}
	}
	
	public TicTacToe()
	{
		ai = new TicTacToeAI();
		score = new JLabel("X: 0 O: 0 Ties: 0");
		score.setFont(meme_font);
		score_panel.add(score);
		this.add(score_panel, BorderLayout.NORTH);
		for(int i = 0; i <= 8; i++)
		{
			buttons[i] = new JButton();
			buttons[i].setActionCommand((i+1)+"");
			buttons[i].setText("--");
			buttons[i].setFont(meme_font);
			buttons[i].addActionListener(new XOActionListener());
			button_panel.add(buttons[i]);
			
		}
		this.add(button_panel, BorderLayout.CENTER);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,400);
	}
	
	
	
	
	class XOActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			int cols = 3;
			JButton button = (JButton) ae.getSource();
			if((button.getText()).equals("X") || (button.getText()).equals("O")) return;
			int index = Integer.parseInt(button.getActionCommand());
	
			ai.markSpace(((index -1)/cols + 1),  ((index -1)%cols + 1), 1);
			button.setText("X");
			if(ai.isWinner())
			{
				reset();
			}
			
			Move next_move = ai.makeMove();

			System.out.println();
			if(next_move != null)
			{
				ai.markSpace(next_move.getRow(), next_move.getCol(), -1);
				buttons[(next_move.getRow()-1) *cols + (next_move.getCol()-1)].setText("O");
				
			}
			if(ai.isWinner())
			{
				reset();
			}
		}
	}
}