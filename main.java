public class main{

  public static final String reset = "\u001B[0m";
  public static final String red = "\u001B[31m";

  public static double f(double[] x){
    return Math.sqrt(1 - Math.pow(x[0],2) - Math.pow(x[1], 2));
  }

  public static void main(String[] args){
    double cost = 0;

    System.out.println("Test Neural Network");
    NeuNet nn = new NeuNet();

    double[] a = new double[]{0.3, 0.2};

    //Estimation
    //nn.forward(a);
    //nn.test(a);
    //nn.back();
    //nn.norm();

    for(int i = 0; i < 5; i++){
      a[0] = 0.3;
      a[1] = 0.2;
      nn.forward(a);
      nn.cost(f(a));
      nn.back();
      System.out.println(red + "Cost " + nn.cost(f(a)) + " --------- " + reset);
    }
  }
}
