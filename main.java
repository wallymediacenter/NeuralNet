public class main{
  public static void main(String[] args){
    NeuNet nn = new NeuNet();


    nn.forward();

    double[][] qTarget = new double[1][3];
    qTarget[0][0]=1;
    qTarget[0][1]=2;
    qTarget[0][2]=3;

    nn.back(0.5);

  }
}
