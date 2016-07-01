public class main{

  public static final String reset = "\u001B[0m";
  public static final String red = "\u001B[31m";

  public static double f(double[] x){
    return Math.sqrt(1 - Math.pow(x[0],2) - Math.pow(x[1], 2));
    //return ( Math.cos(x[0] + x[1]) );
  }

  public static void main(String[] args){
    double cost = 0;

    System.out.println("Test Neural Network");
    NeuNet nn = new NeuNet();

    double[] a = new double[2];

    double[] costArray = new double[2000];
    //Estimation
    //nn.forward(a);
    //nn.test(a);
    //nn.back();
    //nn.norm();

    for(int i = 0; i < costArray.length; i++){
      a[0] = 0.3;
      a[1] = 0.3;
      nn.forward(a);
      nn.cost(f(a));
      nn.back();
      System.out.println(red + "Cost " + nn.cost(f(a)) + " --------- " + reset);
      costArray[i] = nn.cost(f(a));
    }

    System.out.print("[");
    for(int i = 0; i < costArray.length; i++){
      System.out.print(costArray[i]);
      if(i < costArray.length-1){System.out.print(", ");}
    }
    System.out.println("]");
  }
}
