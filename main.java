public class main{
  public static void main(String[] args){
    NeuNet nn = new NeuNet();
    double[][] input = new double[1][2];
    double sum = 0;
    double[][] error = new double[1][1];

    double count = 0;

    for(int i = 0; i < 1000000; i++){

      input[0][0] = Math.random();
      input[0][1] = Math.random();

      nn.forward(input);

      sum = input[0][0] + input[0][1];
      if(sum > 1) sum = 1;
      else sum = 0;

      error[0][0] = sum - nn.output[0][0];

      if(error[0][0] < Math.pow(10,-5)) count++;

      nn.back(error);
      System.out.println("---------------");
      System.out.println(input[0][0] + "  " + input[0][1]);
      System.out.println(nn.output[0][0] + "  " +  sum);
      System.out.println(error[0][0]);
      System.out.println("---------------");
    }

  }
}
