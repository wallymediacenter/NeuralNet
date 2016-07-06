public class main{
  public static void main(String[] args){
    NeuNet nn = new NeuNet();

    nn.forward();

    nn.printMat(nn.q);

  }
}
