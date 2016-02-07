package com.next.gen;

public class PrintNextGen {

	private static String ALIVE = "X";
	private static String DEAD  = "-";

	public static void main(String[] args) {
		String[][] inputA = {{"X", "X"}, {"X", "X"}};
		String[][] inputB = {{"X", "X", "-"}, {"X", "-", "X"}, {"-", "X", "-"}};
		String[][] inputC = {{"-", "X", "-"}, {"-", "X", "-"}, {"-", "X", "-"}};
		String[][] inputD = {{"-", "X", "X", "X"}, {"X", "X", "X", "-"}};

		String[][] outputA = new String[inputA.length][inputA[0].length];
		String[][] outputB = new String[inputB.length][inputB[0].length];
		String[][] outputC = new String[inputC.length][inputC[0].length];
		String[][] outputD = new String[inputD.length][inputD[0].length];
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Input A:");
		printMatrix(inputA);
		calculateNextGeneration(inputA, outputA);
		System.out.println("Output A:");
		printMatrix(outputA);
		System.out.println("-------------------------------------------------------------------------");

		System.out.println("Input B:");
		printMatrix(inputB);
		calculateNextGeneration(inputB, outputB);
		System.out.println("Output B:");
		printMatrix(outputB);
		System.out.println("-------------------------------------------------------------------------");

		System.out.println("Input C:");
		printMatrix(inputC);
		calculateNextGeneration(inputC, outputC);
		System.out.println("Output C:");
		printMatrix(outputC);
		System.out.println("-------------------------------------------------------------------------");

		System.out.println("Input D:");
		printMatrix(inputD);
		calculateNextGeneration(inputD, outputD);
		System.out.println("Output D:");
		printMatrix(outputD);

	}

	public static void calculateNextGeneration(String[][] currentStatus, String[][] newStatus) {
		int totalLiveNeighbours;
		for (int i = 0; i < currentStatus.length; i++)
			for (int j = 0; j < currentStatus[i].length; j++) {
				totalLiveNeighbours = getLiveNeighboursCount(j, i, currentStatus);

				if (currentStatus[i][j] == ALIVE) {
					if ((totalLiveNeighbours == 2) || (totalLiveNeighbours == 3)) {
						newStatus[i][j] = ALIVE;
					} else {
						newStatus[i][j] = DEAD;
					}
				} else {
					if (totalLiveNeighbours == 3) {
						newStatus[i][j] = ALIVE;
					} else {
						newStatus[i][j] = DEAD;
					}
				}
			}

	}

	public static int getLiveNeighboursCount(int x,int y, String[][] currentStatus) {
		int liveNeighbourCount = 0;
		for (int xPosition = x - 1; xPosition <= x + 1; xPosition++) {
			for (int yPosition = y - 1; yPosition <= y + 1; yPosition++) {
				if (!isCentral(xPosition, yPosition, x, y)) {
					liveNeighbourCount += countLiveNeighboursInCell(xPosition,yPosition, currentStatus);
				}
			}
		}
		return liveNeighbourCount;
	}

	private static int countLiveNeighboursInCell(final int x, final int y,String[][] currentStatus) {
		if (IsOutside(x, y, currentStatus)) {
			return 0;
		}
		if (currentStatus[y][x] == ALIVE) {
			return 1;
		} else {
			return 0;
		}
	}

	private static boolean isCentral(int x,int y,int centerX,int centerY) {
		return (x == centerX) && (y == centerY);
	}

	private static boolean IsOutside(final int x, final int y,String[][] currentStatus) {
		return (y < 0 || y > currentStatus.length - 1) || (x < 0 || x > currentStatus[0].length - 1);
	}
	
	
	private static void printMatrix(String[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("\n");
		}

	}

}
