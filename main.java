public class main{

  public static double f(double[] x){
    return Math.sqrt(1 - Math.pow(x[0],2) - Math.pow(x[1], 2));
  }

  public static void main(String[] args){
    double cost = 0;

    System.out.println("Test Neural Network");
    NeuNet nn = new NeuNet();

    double[] a = new double[]{0.5, 0.7};

    for(int i = 0; i < 10; i++){
      a[0] = 0.5*Math.random();
      a[1] = 0.5*Math.random();
      nn.forward(a);
      System.out.println(nn.cost(f(a)));
      nn.back();
    }
  }
}
