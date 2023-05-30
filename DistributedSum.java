import mpi.*;
public class DistributedSum {

	public static void main(String[] args)throws MPIException
	{
		MPI.Init(args);
		int rank = MPI.COMM_WORLD.Rank();
		int size = MPI.COMM_WORLD.Size();
		int[] array= {1,2,3,4,5,6,7,8,9,10};
		int n= array.length;
		int localN = n/size;
		int[] localArray = new int[localN];
		int localSum=0;
		int totalSum =0;
		MPI.COMM_WORLD.Scatter(array,0,localN,MPI.INT,localArray,0,localN,MPI.INT,0);
		for(int i=0;i<localN;i++)
		{
			localSum+=localArray[i];
		}
		int[] globalSum = new int[1];
		MPI.COMM_WORLD.Reduce(new int[] {localSum},0,globalSum,0,1,MPI.INT,MPI.SUM,0);
		System.out.println("Process: "+rank+" Calculated Sum:"+localSum);
		if(rank==0)
		{
			totalSum=globalSum[0];
			System.out.println("TotalSum is :"+totalSum);
		}
		MPI.Finalize();
	}

}
javac -cp .;%MPJ_HOME%/lib/mpj.jar DistributedSum.java
mpjrun.bat -np 2 DistributedSum