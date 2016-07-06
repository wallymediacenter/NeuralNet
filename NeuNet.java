
public class NeuNet{

  /*Fields*/

  //Input layer
  public double[][] s; //1x5

  //W1
  public double[][] w1; //5x6
  public double[][] deltaW1; //5x6

  //Hidden layer 1
  public double[][] z1; //1x6
  public double[][] a1; //1x6

  //W2
  public double[][] w2; //6x5
  public double[][] deltaW2; //6x5

  //Hidden layer 2
  public double[][] z2; //1x5
  public double[][] a2; //1x5

  //W3
  public double[][] w3; //5x3
  public double[][] deltaW3; //5x3

  //Output layer
  public double[][] z3; //1x3
  public double[][] q; //1x3
  public double oldQ;

  //Error
  public double[] error;


  /*Constructor*/
  public NeuNet(){
    //Input layer
    s = new double[1][5];

    s[0][0] = 0.1;
    s[0][1] = 0.2;
    s[0][2] = 0.3;
    s[0][3] = 0.4;
    s[0][4] = 0.5;

    //W1
    w1 = new double[5][6];
    deltaW1 = new double[5][6];
    random(w1);

    //Hidden layer 1
    z1 = new double[1][6];
    a1 = new double[1][6];

    //W2
    w2 = new double[6][5];
    deltaW2 = new double[6][5];
    random(w2);

    //Hidden layer 2
    z2 = new double[1][5];
    a2 = new double[1][5];

    //W2
    w3 = new double[5][3];
    deltaW3 = new double[5][3];
    random(w3);

    //Output layer
    z3 = new double[1][3];
    q = new double[1][3];
    oldQ = 0.0;

    //Error
    error = new double[3];
  }

  /*Methods*/

  //*Basic matrix operation*//
  //Matrix multiplication
  public static double[][] mul(double[][] a, double[][] b){
    //i ~ row, j ~ column
    double[][] c = new double[a.length][b[0].length];
    for(int i = 0; i<c.length; i++){
      for(int j = 0; j<c[0].length; j++){
        for(int k = 0; k < c.length; k++){
          c[i][j] += a[i][k]*b[k][j];
        }
      }
    }
    return c;
  }

  public static double[][] scallarMul(double[][] a, double x){
    double[][] b = new double[a.length][a[0].length];
    for(int i = 0; i< a.length; i++){
      for(int j = 0; j < a[0].length; j++){
        b[i][j] = a[i][j]*x;
      }
    }
    return b;
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

  public static double[][] transpose(double[][] a){
    double t[][] = new double[a.length][a[0].length];
    for(int i = 0; i < a.length; i++){
      for(int j = 0; j < a[0].length; j++){
        t[i][j] = a[j][i];
      }
    }
    return t;
  }

  public int max(double[][] q){
    int max = 100;
    if(q.length > 1){System.out.println("Error this function ony accept single row matrix");}
    else{
      if(q[0][0] > q[0][1]) max = 0; else max = 1;
      if(q[0][max] < q[0][2]) max = 2;
    }
    return max;
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
  public void forward(){
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

  public void back(){

  }
}
