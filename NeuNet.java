
public class NeuNet{

  /*Fields*/

  //Input layer
  public static double[][] s; //1x5

  //W1
  public static double[][] w1; //5x6

  //Hidden layer 1
  public static double[][] z1; //1x6
  public static double[][] a1; //1x6

  //W2
  public static double[][] w2; //6x5

  //Hidden layer 2
  public static double[][] z2; //1x5
  public static double[][] a2; //1x5

  //W3
  public static double[][] w3; //5x3

  //Output layer
  public static double[][] z3; //1x3
  public static double[][] q; //1x3


  /*Constructor*/
  public NeuNet(){
    //Input layer
    s = new double[1][5];

    //W1
    w1 = new double[5][6];
    random(w1);

    //Hidden layer 1
    z1 = new double[1][6];
    a1 = new double[1][6];

    //W2
    w2 = new double[6][5];
    random(w2);

    //Hidden layer 2
    z2 = new double[1][5];
    a2 = new double[1][5];

    //W2
    w3 = new double[5][3];
    random(w3);

    //Output layer
    z3 = new double[1][3];
    q = new double[1][3];
  }

  /*Methods*/

  //*Basic matrix operation*//
  //Matrix multiplication
  public static double[][] mul(double[][] a, double[][] b){
    //i ~ row, j ~ column
    double[][] c = new double[a.length][b[0].length];
    for(int i = 0; i<c.length; i++){
      for(int j = 0; j<c[0].length; j++){
        for(int k = 0; k< c[0].length; k++){
          c[i][j] += a[i][k]*b[k][j];
        }
      }
    }

    return c;
  }

  //Print matrix
  public static void printMat(double[][] c){
    for(int i = 0; i<c.length; i++){
      for(int j = 0; j<c[0].length; j++){
        System.out.print(c[i][j] + "  ");
      }
      System.out.println();
    }
  }


  //Randomize weight
  public static void random(double[][] w){
    for(int i = 0; i < w.length; i++){
      for(int j = 0; j < w[0].length; j++){
        w[i][j] = Math.random();
      }
    }
  }

  //*Learning aid*//
  //Sigmoid function
  public static double s(double x){
    return (1 / ( 1 + Math.exp(-x) ));
  }

  //Overload
  public static double[][] s(double[][] w){
    double[][] a = new double[w.length][w[0].length];
    for(int i = 0; i < a.length; i++){
      for(int j = 0; j < a[0].length; j++){
        a[i][j] =  1 / ( 1 + Math.exp( -w[i][j] ) );
      }
    }
    return a;
  }

  public static double sPrime(double x){
    return ( (Math.exp(-x)) / (Math.pow( (1+Math.exp(-x)), 2 )) );
  }

  //Overload
  public static double[][] sPrime(double[][] w){
    double[][] a = new double[w.length][w[0].length];
    for(int i = 0; i < a.length; i++){
      for(int j = 0; j < a[0].length; j++){
        a[i][j] =  ( (Math.exp(-w[i][j])) / (Math.pow( (1+Math.exp(-w[i][j])), 2 )) );
      }
    }
    return a;
  }

  /////////////////////////**///////////////////////////////
  public static void forward(){


    //Hidden layer 1
    z1 = mul(s, w1);
    a1 = s(z1);

    //Hidden layer 2
    z2 = mul(a1, w2);
    a2 = s(z2);

    //Output
    z3 = mul(a2, w3);
    q = s(z3);


  }
}
