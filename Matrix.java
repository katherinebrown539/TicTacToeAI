
public class Matrix<T>
{
	private T[] matrix;
	private int num_rows;
	private int num_cols;
	
	public Matrix(int r, int c)
	{
		num_rows = r;
		num_cols = c;
		matrix = (T[]) new Object[num_cols*num_rows];
		
	}
	
	public T[] getArray()
	{
		return matrix;
	}
	//one based
	public T getElement(int row, int column)
	{
		int index = (row-1) * num_cols + (column-1);
		if(index > (num_cols*num_rows))
		{
			return null;
		}
		return matrix[index];
	}
	
	public int getNumColumns()
	{
		return num_rows;
	}
	
	public int getNumRows()
	{
		return num_cols;
	}
	//one based
	public void setElement(int row, int column, T value)
	{
		
		int index = (row-1) * num_cols + (column-1);
		if(index > (num_cols * num_rows))
		{
			return;
		}
		
		matrix[index] = value;
	}
	
}