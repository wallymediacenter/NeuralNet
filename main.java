public class main{
  public static void main(String[] args){
    NeuNet nn = new NeuNet();

    double[][] input = new double[1][1];
    input[0][0] = 0.75;

    double[][] target = new double[1][1];
    target[0][0] = 0.1;

    for(int i = 0; i < 1000000; i ++){
      nn.forward(input);

      nn.back(target);
    }

  }
}
