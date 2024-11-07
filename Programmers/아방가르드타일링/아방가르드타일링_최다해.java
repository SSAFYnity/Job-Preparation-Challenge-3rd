class Solution {
  static long res[] = new long[100001];
  
  
  public long solution(int n) {
      res[0]=1;
      res[1]=1;
      res[2]=3;
      res[3]=10;
      res[4]=23;
      res[5]=62;
      dp(n);
      return res[n] ;
  }
  
  static long dp(int n){
      if(n<0) return 0;
      if(res[n]!=0)return res[n];
      long a = dp(n-1)+(2*dp(n-2))+(6*dp(n-3))+dp(n-4)-dp(n-6);
      res[n]=a%1000000007;
      return a;
  }
}