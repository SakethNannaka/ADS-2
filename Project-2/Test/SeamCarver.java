import java.awt.Color;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.Picture;
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
        
        int delta_X = calculateDeltaEnergy(x + 1, y, x - 1, y);
        int delta_Y = calculateDeltaEnergy(x, y + 1, x, y - 1);
        double energy = Math.sqrt(delta_X + delta_Y);
        return energy;
   }

   private int calculateDeltaEnergy(int x1, int y1, int x2, int y2) {
        Color c1 = picture.get(x1, y1);
        Color c2 = picture.get(x2, y2);

        int r = c1.getRed() - c2.getRed();
        int g = c1.getGreen() - c2.getGreen();
        int b = c1.getBlue() - c2.getBlue();

        int rgb = (r * r) + (g * g) + (b * b);
        return rgb;
   }
   private double[][] energyMatrix(int wid, int ht) {
        double[][] engMat= new double[wid][ht];
        for (int i = 0; i < wid; i++) {
            for (int  j = 0; j < ht; j++) {
                engMat[i][j] = energy(j, i);
            }
        }
        return engMat;
    }
private double[][] cummulativeMatrix(double[][] energy) {
        int noOfRows = energy.length;
        int noOfColumns = energy[0].length;
        double[][] cummulative = new double[noOfRows][noOfColumns];
        for (int i = 0; i < noOfRows; i++) {
            for (int j = 0; j < noOfColumns; j++) {
                if (i == 0) {
                    cummulative[i][j] = energy[i][j];
                } else if (i > 0 && j == 0) {
                    double small = Math.min(cummulative[i - 1][j], cummulative[i - 1][j + 1]);
                    cummulative[i][j] = energy[i][j] + small;
                } else if (i > 0 && j < noOfColumns - 1) {
                    double small = Math.min(cummulative[i - 1][j - 1], Math.min(cummulative[i - 1][j], cummulative[i - 1][j + 1]));
                    cummulative[i][j] = energy[i][j] + small;
                } else if (i > 0 && j >= noOfColumns - 1) {
                    double small = Math.min(cummulative[i - 1][j - 1], cummulative[i - 1][j]);
                    cummulative[i][j] = energy[i][j] + small;
                }
            }
        }
        return cummulative;
    }
     private int[] Seam(double[][] vert) {
        int noOfRows = vert.length;
        int noOfColumns = vert[0].length;
        int[] array = new int[noOfRows];
        for (int i = noOfRows - 1; i > 0; i--) {
            double Max = Double.MAX_VALUE;
            double Min = 0;
            if (i == noOfRows - 1) {
                for (int j = 0; j < noOfColumns; j++) {
                    Min = vert[i][j];
                    if (Min < Max) {
                        Max = vert[i][j];
                        array[i] = j;
                    }
                }
            }
            if (i < noOfRows - 1 && i > 0) {
                int n = array[i + 1];
                if (n - 1 >= 0 &&  n - 1 < noOfColumns - 1 &&  n + 1 <= noOfColumns - 1) {
                    Min = Math.min(vert[i][n - 1], Math.min(vert[i][n], vert[i][n + 1]));
                    for (int j = 0; j < noOfColumns; j++) {
                        if (Min == vert[i][j]) {
                            array[i] = j;
                            break;
                        }
                    }
                }
                if (n - 1 < 0) {
                    Min = Math.min(vert[i][n], vert[i][n + 1]);
                    for (int j = 0; j < noOfColumns; j++) {
                        if (Min == vert[i][j]) {
                            array[i] = j;
                            break;
                        }                        
                    }
                }
                if (n + 1 > noOfColumns - 1) {
                    Min = Math.min(vert[i][n - 1], vert[i][n]);
                    for (int j = 0; j < noOfColumns; j++) {
                        if (Min == vert[i][j]) {
                            array[i] = j;
                            break;
                        }                        
                    }
                }    
            }
            array[0] = array[1] - 1;
        }
        return array;
    }
    /**
     * sequence of indices for horizontal seam
     */
    public int[] findHorizontalSeam() {
        double[][] vert = energyMatrix(picture.height(), picture.width());
        int a = vert.length;
        int b = vert[0].length;
        
        double[][] trans_Mat = new double[b][a];
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < a; j++) {
                trans_Mat[i][j] = vert[j][i];
            }
        }
        return Seam(cummulativeMatrix(trans_Mat));
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
   // public static void main(String[] args) throws IllegalArgumentException {

   //    Picture pic = new Picture("chameleon.png");
   //    SeamCarver sc = new SeamCarver(pic);
   //    System.out.println(sc.(1, 1));
   // }
}
