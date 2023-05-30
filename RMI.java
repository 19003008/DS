import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.*;
public class server {
	public server()throws RemoteException
	{
		super();
	}

	public static void main(String[] args)throws RemoteException
	{
		
		Registry reg = LocateRegistry.createRegistry(2000);
		circleImplementation ci = new circleImplementation();
		reg.rebind("circle",ci);
		System.out.println("Server is waiting............");
	}

}
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Scanner;
public class client {
	public client()
	{}
	

	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("Enter radius of circle:");
		int radius= input.nextInt();
		circleInterface c =(circleInterface)Naming.lookup("rmi://localhost:2000/circle");
		System.out.println("Area:"+c.area(radius));
		System.out.println("Perimeter"+c.perimeter(radius));

	}

}
import java.rmi.*;
public interface circleInterface extends Remote
{
	public double area(int radius)throws RemoteException;
	public double perimeter(int radius)throws RemoteException;
}
import java.rmi.*;
import java.rmi.server.*;
public class circleImplementation extends UnicastRemoteObject implements circleInterface
{
	public circleImplementation()throws RemoteException
	{
		super();
	}
	public double area(int radius)throws RemoteException
	{
		double pi=3.14;
		return pi*radius*radius;
	}
	public double perimeter(int radius)throws RemoteException
	{
		double pi=3.14;
		return 2*pi*radius;
	}
}