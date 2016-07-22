
public class NeuNet{

  /*Fields*/

  //Input layer
  public double[][] input; //1x1

  //W1
  public double[][] w1; //1x1
  public double[][] deltaW1; //1x1

  //Hidden layer 1
  public double[][] z1; //1x1
  public double[][] a1; //1x1

  //w2
  public double[][] w2; //1x1
  public double[][] deltaW2; //1x1

  //Output layer
  public double[][] z2; //1x1
  public double[][] output; //1x1

  /*Constructor*/
  public NeuNet(){
    //Input layer
    input = new double[1][1];

    //W1
    w1 = new double[1][1];
    deltaW1 = new double[1][1];
    Matrix.random(w1);

    //Hidden layer 1
    z1 = new double[1][1];
    a1 = new double[1][1];

    //W2
    w2 = new double[1][1];
    deltaW2 = new double[1][1];
    Matrix.random(w2);

    //Output layer
    z2 = new double[1][1];
    output = new double[1][1];

  }

  /*Methods*/

  /////////////////////////**///////////////////////////////
  public void forward(double[][] newInput){

    input = newInput;

    //Hidden layer 1
    z1 = Matrix.mul(input, w1);
    a1 = Matrix.s(z1);

    //Hidden layer 2
    z2 = Matrix.mul(a1, w2);
    output = Matrix.s(z2);

  }

  public void back(double[][] target){

    double[][] sigma3;
    double[][] sigma2;

    sigma3 = Matrix.hMul( Matrix.subtract(output, target), Matrix.sPrime(z2));
    deltaW2 = Matrix.mul( Matrix.transpose(a1), sigma3 );
    sigma2 = Matrix.hMul( Matrix.mul(sigma3, Matrix.transpose(w2)), Matrix.sPrime(z1));
    deltaW1 = Matrix.mul(Matrix.transpose(input), sigma2);

    //Update W
    w1 = Matrix.subtract(w1, deltaW1);
    w2 = Matrix.subtract(w2, deltaW2);

    Matrix.printMat(Matrix.subtract(output, target), "LPrime");
  }
}
