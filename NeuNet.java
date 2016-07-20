
public class NeuNet{

  /*Fields*/

  //Input layer
  public double[][] input; //1x2

  //W1
  public double[][] w1; //2X3
  public double[][] deltaW1; //2x3

  //Hidden layer 1
  public double[][] z1; //1x3
  public double[][] a1; //1x3

  //w2
  public double[][] w2; //3x1
  public double[][] deltaW2; //3x1

  //Output layer
  public double[][] z2; //1x1
  public double[][] output; //1x1

  /*Constructor*/
  public NeuNet(){
    //Input layer
    input = new double[1][2];

    //W1
    w1 = new double[2][3];
    deltaW1 = new double[2][3];
    random(w1);

    //Hidden layer 1
    z1 = new double[1][3];
    a1 = new double[1][3];

    //W2
    w2 = new double[3][1];
    deltaW2 = new double[3][1];
    random(w2);

    //Output layer
    z2 = new double[1][1];
    output = new double[1][1];

  }

  /*Methods*/

  //*Basic matrix operation*//
  //Matrix multiplication
  public static double[][] mul(double[][] a, double[][] b){
    //i ~ row, j ~ column
    double[][] c = new double[a.length][b[0].length];
    for(int i = 0; i < c.length; i++){
      for(int j = 0; j < c[0].length; j++){
        for(int k = 0; k < a[0].length; k++){
          c[i][j] += a[i][k]*b[k][j];
        }
      }
    }
    return c;
  }

  public static double[][] hMul(double[][] a, double[][] b){ //Hadamard product a.k.a Element wise product. Entry matrix has to have the same dimension
    double[][] c = new double[a.length][a[0].length];
    for(int i = 0; i< a.length; i++){
      for(int j = 0; j < a[0].length; j++){
        c[i][j] = a[i][j]*b[i][j];
      }
    }
    return c;
  }


  public static double[][] sMul(double c, double[][] a){ //Scalar product
    double[][] b = new double[a.length][a[0].length];
    for(int i = 0; i< a.length; i++){
      for(int j = 0; j < a[0].length; j++){
        b[i][j] = c*a[i][j];
      }
    }
    return b;
  }

  public static double[][] subtract(double[][] a, double[][] b){
    double[][] c = new double[a.length][a[0].length];
    for(int i = 0; i< a.length; i++){
      for(int j = 0; j < a[0].length; j++){
        c[i][j] = a[i][j] + b[i][j];
      }
    }
    return c;
  }
  //Print matrix
  public static void printMat(double[][] c, String abc){
    System.out.println(abc);
    System.out.println("-------");
    for(int i = 0; i<c.length; i++){
      for(int j = 0; j<c[0].length; j++){
        System.out.print(c[i][j] + "  ");
      }
      System.out.println();
    }
    System.out.println("-------");
    System.out.println();
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
    double t[][] = new double[a[0].length][a.length];
    for(int i = 0; i < a.length; i++){
      for(int j = 0; j < a[0].length; j++){
        t[j][i] = a[i][j];
      }
    }
    return t;
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
  public void forward(double[][] newInput){

    input = newInput;

    //Hidden layer 1
    z1 = mul(input, w1);
    a1 = s(z1);

    //Hidden layer 2
    z2 = mul(a1, w2);
    output = s(z2);
  }

  public void back(double[][] error){

    double[][] sigma3;
    double[][] sigma2;

    sigma3 = hMul(error, sPrime(z2));
    deltaW2 = mul( transpose(a1), sigma3 );
    sigma2 = hMul( mul(sigma3, transpose(w2)), sPrime(z1));
    deltaW1 = mul(transpose(input), sigma2);

    //Update W
    w1 = subtract(w1,  deltaW1);
    w2 = subtract(w2, deltaW2);

  }
}
