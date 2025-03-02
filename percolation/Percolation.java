// package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int size;
    private WeightedQuickUnionUF checker;
    private WeightedQuickUnionUF fullChecker;
    private int top;
    private int bottom;
    private boolean[] status;
    private int numOpens;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Percolation: n <= 0");
        }
        size = n;
        checker = new WeightedQuickUnionUF(n*n + 2);
        fullChecker = new WeightedQuickUnionUF(n*n+1);
        status = new boolean[n*n];
        numOpens = 0;
        top = n*n;
        bottom = n*n + 1;
    }

    private int coordinateToIndex(int row, int col) {
        return (row - 1) * size + (col - 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException("Percolation: Invalid coordinate");
        }

        if (isOpen(row, col)) return;

        int index = coordinateToIndex(row, col);
        status[index] = true;
        numOpens += 1;

        if (row == 1) {
            checker.union(index, top);
            fullChecker.union(index, top);
        }
        if (row == size) {
            checker.union(index, bottom);
        }

        if (row > 1 && isOpen(row-1, col)) {   // up
            checker.union(index, coordinateToIndex(row-1, col));
            fullChecker.union(index, coordinateToIndex(row-1, col));
        }

        if (row < size && isOpen(row+1, col)) {   // down
            checker.union(index, coordinateToIndex(row+1, col));
            fullChecker.union(index, coordinateToIndex(row+1, col));
        }

        if (col > 1 && isOpen(row, col-1)) {   // left
            checker.union(index, coordinateToIndex(row, col-1));
            fullChecker.union(index, coordinateToIndex(row, col-1));
        }

        if (col < size && isOpen(row, col+1)) { // right
            checker.union(index, coordinateToIndex(row, col+1));
            fullChecker.union(index, coordinateToIndex(row, col+1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException("Invalid coordinate");
        }

        return status[coordinateToIndex(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException("Invalid coordinate");
        }
        return isOpen(row, col) && fullChecker.find(coordinateToIndex(row, col)) == fullChecker.find(top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpens;
    }

    // does the system percolate?
    public boolean percolates() {
        return checker.find(top) == checker.find(bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}
