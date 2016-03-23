/**Problem

Alice and Bob want to play a game. The game is played on a chessboard with R rows and C columns, for a total of RC squares. Some of these squares are burned.

A king will be placed on an unburned square of the board, and Alice and Bob will make successive moves with the king.

In a move, the player must move the king to any of its 8 neighboring squares, with the following two conditions:

The destination square must not be burned
The king must never have been in the destination square before.
If a player can't make a move, he or she loses the game. Alice will move first; you need to determine who will win, assuming both players play optimally.

Input

The first line of input gives the number of cases, N.

N test cases follow. The first line of each case will contain two integers, R and C. The next R lines will contain strings of length C, representing the C squares of each row. Each string will contain only the characters '.', '#' and 'K':

'#' means the square is burned;
'.' means the square is unburned, and unoccupied; and
'K' means the king is in that cell at the beginning of the game.
There will be only one 'K' character in each test case.

Output

For each test case, output one line containing "Case #X: " (where X is the case number, starting from 1) followed by A if Alice wins, or B if Bob wins.

Limits

1 ≤ N ≤ 100

Small dataset

1 ≤ R, C ≤ 4

Large dataset

1 ≤ R, C ≤ 15
**/

import java.io.*;
import java.util.*;

public class Point
{
	int x, y;

	Point()
	{
		this.x = 0;
		this.y = 0;
	}

	Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}

public class King
{
	public Point GetStartPt(char board[][], int R, int C)
	{
		for (int i = 0; i < R; i++)
		{
			for (int j = 0; j < C; j++)
			{
				if (board[i][j] == 'K') return new Point(i, j);
			}
		}

		System.err.println("The K does not exist on the board!!!");
		return null;
	}

	public boolean AliceWins(boolean beenHere[][], char board[][], int R, int C)
	{
		Point startPt = GetStartPt(board, R, C);

		Queue<Point> moves = new Queue<Point>();

		int x_diff[] = {-1, -1, -1, 0, 0, 1, 1, 1};
		int y_diff[] = {-1,  0,  1,-1, 1,-1, 0, 1};

		for (int i = 0; i < 8; i++)
		{
			int new_x = startPt.x + x_diff[i];
			int new_y = startPt.y + y_diff[i];
			if ((0<=new_x && new_x<R) && (0<=new_y && new_y<C))
			{
				if (board[new_x][new_y] != '#')
				{
					if (!beenHere[i][j])
					{
						moves.Add(new Point(new_x, new_y));
					}
				}
			}
		}

		if (moves.size == 0) return false;

		boolean result = false;
		while (!moves.isEmpty())
		{
			Point currentPt = moves.remove();
			beenHere[currentPt.x][currentPt.y] = true;
			board[currentPt.x][currentPt.y] = 'K';
			board[startPt.x][startPt.y] = '.';
			result = result || AliceWins(beenHere, board, R, C);
			board[startPt.x][startPt.y] = 'K';
			board[currentPt.x][currentPt.y] = '.';
			beenHere[currentPt.x][currentPt.y] = false;
		}
	}

	public void doit()
	{
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		for (int times = 0; times < t; times++)
		{
			int R = sc.nextInt();
			int C = sc.nextInt();

			char board[][] = new char[R][C];
			boolean beenHere[][] = new boolean[R][C];
			for (int i = 0; i < R; i++)
			{
				char line[] = sc.nextLine().toCharArray();
				for (int j = 0; j < C; j++)
				{
					board[i][j] = line[j];
					beenHere[i][j] = false;
				}
			}


		}
	}

	public static void main(String args[])
	{
		new King().doit();
	}
}