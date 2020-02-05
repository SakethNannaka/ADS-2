import java.awt.Color;
import java.lang.IllegalArgumentException;
// import edu.princeton.cs.algs4.Picture;
public class SeamCarver {

private Picture picture;

   // create a seam carver object based on the given picture
   public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException();
        }
      this.picture = new Picture(picture);
   }
   // return current picture
   public Picture picture() {
      return picture;
   }

   // width of current picture
   public int width() {
      return picture.width();
   }

   // height of current picture
   public int height() {
      return picture.height();
   }

   // energy of pixel at column x and row y
   public double energy(int x, int y) {

        if(x < 0 || x >= width() || y < 0 || y >= height()) {
                      throw new IllegalArgumentException();

        }
        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1) {
            return 1000;
        }
        // top(x,y+1) , bottom(x,y-1) , left(x-1,y),right(x+1,y)
        
        int x_delta = calculateDeltaEnergy(x + 1, y, x - 1, y);
        int y_delta = calculateDeltaEnergy(x, y + 1, x, y - 1);
        double energy = Math.sqrt(x_delta + y_delta);
        return energy;
   }

   private int calculateDeltaEnergy(int x1, int y1, int x2, int y2) {
        Color c1 = picture.get(x1, y1);
        Color c2 = picture.get(x2, y2);

        int red = c1.getRed() - c2.getRed();
        int green = c1.getGreen() - c2.getGreen();
        int blue = c1.getBlue() - c2.getBlue();

        int rgb = (red * red) + (green * green) + (blue * blue);
        return rgb;
   }
   private double[][] energyMatrix(int width, int height) {
        double[][] energyMat= new double[width][height];
        for (int i = 0; i < width; i++) {
            for (int  j = 0; j < height; j++) {
                energyMat[i][j] = energy(j, i);
            }
        }
        return energyMat;
    }
private double[][] cummulativeMatrix(double[][] energy) {
        int noOfRows = energy.length;
        int noOfColumns = energy[0].length;
        double[][] cummulative_Matrix = new double[noOfRows][noOfColumns];
        for (int row = 0; row < noOfRows; row++) {
            for (int column = 0; column < noOfColumns; column++) {
                if (row == 0) {
                    cummulative_Matrix[row][column] = energy[row][column];
                } else if (row > 0 && column == 0) {
                    double small = Math.min(cummulative_Matrix[row - 1][column], cummulative_Matrix[row - 1][column + 1]);
                    cummulative_Matrix[row][column] = energy[row][column] + small;
                } else if (row > 0 && column < noOfColumns - 1) {
                    double small = Math.min(cummulative_Matrix[row - 1][column - 1], Math.min(cummulative_Matrix[row - 1][column], cummulative_Matrix[row - 1][column + 1]));
                    cummulative_Matrix[row][column] = energy[row][column] + small;
                } else if (row > 0 && column >= noOfColumns - 1) {
                    double small = Math.min(cummulative_Matrix[row - 1][column - 1], cummulative_Matrix[row - 1][column]);
                    cummulative_Matrix[row][column] = energy[row][column] + small;
                }
            }
        }
        return cummulative_Matrix;
    }
     private int[] Seam(double[][] vert) {

        int noOfRows = vert.length;
        
        int noOfColumns = vert[0].length;
        
        int[] SeamArray = new int[noOfRows];
        
        for (int i = noOfRows - 1; i > 0; i--) {
         
            double Max = Double.MAX_VALUE;
         
            double Min = 0;
         
            if (i == noOfRows - 1) {
         
                for (int j = 0; j < noOfColumns; j++) {
                    
                    Min = vert[i][j];

                    if (Min < Max) {

                        Max = vert[i][j];
                        
                        SeamArray[i] = j;
                    }
                }
            }

            if (i < noOfRows - 1 && i > 0) {
              
                int n = SeamArray[i + 1];
              
                if (n - 1 >= 0 &&  n - 1 < noOfColumns - 1 &&  n + 1 <= noOfColumns - 1) {
                 
                    Min = Math.min(vert[i][n - 1], Math.min(vert[i][n], vert[i][n + 1]));
                 
                    for (int j = 0; j < noOfColumns; j++) {
                        if (Min == vert[i][j]) {
                            SeamArray[i] = j;
                            break;
                        }
                    }
                }
                if (n - 1 < 0) {
                    Min = Math.min(vert[i][n], vert[i][n + 1]);
                    for (int j = 0; j < noOfColumns; j++) {
                        if (Min == vert[i][j]) {
                            SeamArray[i] = j;
                            break;
                        }                        
                    }
                }
                if (n + 1 > noOfColumns - 1) {
                    Min = Math.min(vert[i][n - 1], vert[i][n]);
                    for (int j = 0; j < noOfColumns; j++) {
                        if (Min == vert[i][j]) {
                            SeamArray[i] = j;
                            break;
                        }                        
                    }
                }    
            }
            SeamArray[0] = SeamArray[1] - 1;
        }
        return SeamArray;
    }
    /**
     * sequence of indices for horizontal seam
     */
    public int[] findHorizontalSeam() {
        double[][] Energy_Matrix = energyMatrix(picture.height(), picture.width());

        // int a = 
        // int b = Energy_Matrix[0].length; //i.e no.of Columns
        
        double[][] transposeMatrix = new double[Energy_Matrix[0].length][Energy_Matrix.length];

        for (int row = 0; row < Energy_Matrix.length ; row++) {
            for (int column = 0; column < Energy_Matrix[0].length; column++) {
                transposeMatrix[row][column] = Energy_Matrix[column][row];
            }
        }
        return Seam(cummulativeMatrix(transposeMatrix));
    }
 
    /**
     * sequence of indices for vertical seam
     */
    public int[] findVerticalSeam() {
        double[][] vert = cummulativeMatrix(energyMatrix(picture.height(), picture.width()));
        return Seam(vert);
        
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || this.height() <= 1 || seam.length != this.width()) {
            throw new IllegalArgumentException();

        }

        Picture pic = new Picture(this.picture.width(), this.picture.height() - 1);
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < seam[i]; j++) {
                pic.set(i, j, picture.get(i, j));
            }
            for (int j = seam[i] + 1; j < height(); j++) {
                pic.set(i, j - 1, picture.get(i, j));
            }
        }
        this.picture = pic; 
    }
 
    // // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null || this.width() <= 1 || seam.length != this.height()) {
            throw new IllegalArgumentException();

        }
        Picture pic = new Picture(this.picture.width() - 1, this.picture.height());
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < seam[i]; j++) {
                pic.set(j, i, picture.get(j, i));
            }
            for (int j = seam[i] + 1; j < width(); j++) {
                pic.set(j - 1, i, picture.get(j, i));
            }
        }
        this.picture = pic;
    }


   //  unit testing
   public static void main(String[] args) throws IllegalArgumentException {

      Picture pic = new Picture("6x5.png");
      SeamCarver sc = new SeamCarver(pic);
      System.out.println(sc.energy(1,2));
   }
}
