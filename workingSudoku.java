import java.util.Scanner;
import java.util.Arrays;
//import java.io.FileReader;
import java.io.IOException;


public class workingSudoku
{
  public static int n,B=0;public static boolean leak=true; public static int numf=0,i=-1,cn=0,rn=0;
  public static int [] Btrk=new int[36]; public static int [] Btrk1=new int[36]; public static int [] Btrk2=new int[36];
  public static int [] Btrk3=new int[36];public static boolean found;
  public static void main(String [] args)throws IOException
  {
    System.out.println("Welcome to Oluwatoni's Sudoku Solver");
    System.out.println("Enter the size of the Matrix you would like to solve");
    System.out.println("Example: For n by n Matrix Enter n");
    Scanner get=new Scanner(System.in);
    n=get.nextInt();
    int [][] MAT=new int [n][n];  
    for(int ii=0;ii<n;ii++)
    {
      System.out.print("[");
      for(int i=0;i<n;i++)
      {
        MAT[ii][i]=get.nextInt();
        System.out.print(" " + MAT[ii][i]);
      }
      System.out.println(" ]");
    }
     try
      {
         solve( MAT,0, 0 ) ;
      }
      catch( Exception e )
      {
      }
    
    // System.out.println(check(MAT,4,4,1));
    System.out.println(checkall(MAT,0,0)+" " +numf);
    for(int ii=0;ii<n;ii++)
    {
      System.out.print("[");
      for(int i=0;i<n;i++)
      {
        System.out.print(" " + MAT[ii][i]);
      }
      System.out.println(" ]");
    }
  }
  
  ///methods
/////////////
  public static void solve( int [][]MAT,int row, int col ) throws Exception
   {
      if( row > n-1 )
         throw new Exception( "Solution found" ) ;
      // If the cell is not empty, continue with the next cell
      if( MAT[row][col] != 0 )
         next(MAT, row, col ) ;
      else
      {
         // Find a valid number for the empty cell
         for( int num = 1; num < n+1; num++ )
         {
            if(check(MAT,row,col,num) )
            {
               MAT[row][col] = num ;
               System.out.print(MAT[row][col]);
               // Delegate work on the next cell to a recursive call
               next(MAT, row, col ) ;
            }
         }
         // No valid number was found, clean up and return to caller
         MAT[row][col] = 0 ;
      }
   }
  public static void next( int [][]MAT,int row, int col )throws Exception
   {
      if( col < n-1 )
         solve( MAT,row, col + 1 ) ;
      else
         solve( MAT,row + 1, 0 ) ;
   }
  //////
  public static boolean check(int Big [][],int ro,int co,int v)
  {
    boolean ans=false;
    int [] smaller=new int [n];int [][] small=new int [n][n];int ii,iii,ci=0,sii=0,ca=0,r=0,chai=0,sb=0,sr=0,sc=0;
    
    for (int i=0;i<n;i++)
    {
      ii=0;
      while (ii<(n/3))
      {
        iii=0;
        while (iii<3)
        {
          //System.out.print(i);System.out.print(sii+" ");System.out.print(r+" ");System.out.println(ci);
          small[i][sii]=Big[r][ci];
          if (ro==r && co==ci) {sb=i;sr=r;sc=ci;}
          iii=iii+1;ci=ci+1;sii=sii+1;
        }
        ii=ii+1;ci=ci-3;r=r+1;
      }
      sii=0;ca=ca+1;r=0;ci=3*ca;
      if (i==((n/3)-1) || (i==((2*(n/3))-1))) {chai=chai+1;r=chai*(n/3);ci=0;ca=0;}
      if (chai>0) {r=chai*(n/3);}
    }
    //check box
    for (int i=0;i<n;i++) {smaller[i]=small[sb][i];}Arrays.sort(smaller); ans=(ans||((Arrays.binarySearch (smaller,v))>=0));
    //check row;
    for (int i=0;i<n;i++) {smaller[i]=Big[sr][i];}Arrays.sort(smaller); ans=(ans||((Arrays.binarySearch (smaller,v))>=0));  
    //check col
    for (int i=0;i<n;i++) {smaller[i]=Big[i][sc];}Arrays.sort(smaller); ans=(ans||((Arrays.binarySearch (smaller,v))>=0));                                                                          
    return !ans;
  }
  public static boolean checkall(int MAT[][],int r,int c)
  {
    int Va=1;
    boolean stop=false,awn=true;
    while (stop==false)
    {
      awn=check(MAT,r,c,Va);
      if (awn==false)
      {stop=true;numf=Va;}
      if (awn==true && Va<n)
      {awn=check(MAT,r,c,Va+1);}
      if (Va==n) stop=true;
      Va=Va+1;
    }
    return (!awn);
  }
  }