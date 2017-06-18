public class array {
int [][] edgeMatrix;
    public static void printRow(int[] row) {
        for (int i : row) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int edgeMatrix[][]= new int[5][5];
        
        int i,j,k=1;
        
        int black = (char)'b';
        array f = new array();

        for(i=0;i<5;i++) {
            for(j=0;j<5;j++) {
                edgeMatrix[i][j]=k;
                k++;
            }
        }
        edgeMatrix[4][4] = black;
        if(edgeMatrix[4][4] == 'b') edgeMatrix[4][4] = 1;
        

        for(int[] row : edgeMatrix) {
            printRow(row);
        }
        
    }
    
   
}