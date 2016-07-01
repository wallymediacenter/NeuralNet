public class NeuNet{

  //fields
  public double[] input;
  public double[][] w1;

  public double[][] gradientW1;

  public double[][] deltaW1;

  public double[] z1;
  public double[] a1;
  public double[] w2;

  public double[] gradientW2;

  public double[] deltaW2;

  public double z2;
  public double output;
  public double output0;

  public double c;

  /*constructor*/
  public NeuNet(){

    /*Input player*/
    input = new double[2]; // 1 x 2

    /*Hidden layer*/
    //initialize weights
    w1 = new double[2][3]; // 2 x 3
    deltaW1 = new double[2][3]; // 2 x 3
    gradientW1 = new double[2][3]; // 2 x 3
    //Weights are asignned randomly
    for(int i = 0; i < 2; i++){
      for(int j = 0; j < 2; j++){
        w1[i][j] = Math.random();
      }
    }

    z1 = new double[3]; // 1 x 3
    a1 = new double[3]; // 1 x 3

    /*Output layer*/
    //initialize weights
    w2 = new double[3]; // 3 x 1
    deltaW2 = new double[3]; // 3 x 1
    gradientW2 = new double[3]; // 3 x 1
    for(int i = 0; i < 3; i++){
      w2[i] = Math.random();
    }
    z2 = 0;
    output = 0;
  }

  /*Methods*/
  //return the estimated value
  public double forward(double[] newInput){

    input = newInput;

    /*First layer -> Hiden layer*/
    //matrix multiplication z1(1x3) = input(1x2) * w1(2x3)
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 2; j++){
        z1[i] += input[j]*w1[j][i];
      }
    }

    //Activate z1
    for(int i = 0; i < 3; i++){
      a1[i] = s(z1[i]);
    }

    /*Hidden layer -> Output*/
    for(int i = 0; i< 3; i++){
      z2+=a1[i]*w2[i];
    }
    //Activate z2
    output = s(z2);
    return output;
  }

  public void back(){

    double alpha = -(output0 - output)*sPrime(z2);

    //d cost / d w2
    for(int i = 0; i < 3; i++){
      deltaW2[i] = alpha*a1[i];
    }

    //d cost / d w2
    for(int i = 0; i < 2; i++){
      for(int j = 0; j < 3; j++){
        deltaW1[i][j] = alpha*sPrime(z1[j])*w2[j]*input[i];
        w1[i][j] -= deltaW1[i][j]; //Update weights
      }
    }

    for(int i = 0; i < 3; i++){
      w2[i] -= deltaW2[i]; //Update weights
    }

    //Print deltaW1 and deltaW2
    System.out.println("------------- deltaW1 -------------");
    for(int i=0; i < 2; i++){
      for(int j=0; j < 3; j++){
        System.out.print(deltaW1[i][j] + " ");
      }
      System.out.println();
    }

    System.out.println("------------- deltaW2 -------------");
    for(int i = 0; i<3; i++){
      System.out.println(deltaW2[i]);
    }
    System.out.println("----------------------------");

  }

  public void test(double[] input){
    double e = Math.pow(10,-4);

    //W1
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 2; j++){
        gradientW1[j][i] = ( input[j]* (w1[j][i] + e) - input[j]* (w1[j][i] - e) ) / (2*e);
      }
    }

    System.out.println("------------- gradientW1 -------------");
    for(int i=0; i < 2; i++){
      for(int j=0; j < 3; j++){
        System.out.print(gradientW1[i][j] + " ");
      }
      System.out.println();
    }

    //w2
    for(int i = 0; i < 3; i++){
      gradientW2[i] = (a1[i]*(w2[i] + e ) - a1[i]* (w2[i]-e))/ (2*e);
    }

    System.out.println("------------- gradientW2 -------------");
    for(int i = 0; i<3; i++){
        System.out.println(gradientW2[i]);
    }
    System.out.println("---------------------------- -------");

  }


  public void norm(){
    System.out.println("---------------NORMALIZE -------");
    double temp = 0;;
    for(int i=0; i < 2; i++){
      for(int j=0; j < 3; j++){
        temp += gradientW1[i][j] + deltaW1[i][j];
        System.out.print(temp + ", ");
      }
      System.out.println();
    }

    System.out.println("----");

    for(int i=0; i < 2; i++){
      for(int j=0; j < 3; j++){
        temp += gradientW1[i][j] - deltaW1[i][j];
        System.out.print(temp + ", ");
      }
      System.out.println();
    }

  }

  //activation function || Sigmoid function
  public static double s(double x){
    return (1/(1 + Math.exp(-x)));
  }

  public static double sPrime(double x){
    return (Math.exp(-x) / ( Math.pow( (1 + Math.exp(-x)), 2 ) ) );
  }

  //cost function cost = 1/2 ( realOutput - output(Guess) )^2
  public double cost(double realOutput){
    output0 = realOutput;
    c = 0.5*Math.pow( (realOutput - output), 2 );
    return c;
  }

}
