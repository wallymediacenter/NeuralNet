public class main{
  public static void main(String[] args){
    NeuNet nn = new NeuNet();


    double[][] s = new double[1][5];
    s[0][0]=1;
    s[0][1]=2;
    s[0][2]=3;
    s[0][2]=3;
    s[0][2]=3;

    nn.forward(s);



    nn.back(0.5);

    int a = 10;
    int b;

    b = a;

    b++;

    System.out.println(b + "--" + a);

  }
}
