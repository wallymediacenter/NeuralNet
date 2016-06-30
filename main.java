public class main{
  public static void main(String[] args){
    System.out.println("Test Neural Network");
    NeuNet nn = new NeuNet();

    double[] a = new double[]{0.5, 0.7};
    
    System.out.println(nn.forward(a));
  }
}
