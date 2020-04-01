import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static ArrayList<TienTrinh> list = new ArrayList<TienTrinh>();

	public static void main(String[] args) {
		//input
		list.add(new TienTrinh(8, 5, 1));
		list.add(new TienTrinh(1, 16, 2));
		list.add(new TienTrinh(6, 6, 3));
		list.add(new TienTrinh(2, 14, 4));
		list.add(new TienTrinh(5, 8, 5));
		list.add(new TienTrinh(4, 11, 6));
		list.add(new TienTrinh(11, 3, 7));
		list.add(new TienTrinh(15, 0, 8));
		list.add(new TienTrinh(5, 9, 9));
		list.add(new TienTrinh(12, 2, 10));
//		System.out.println("\t\tHệ tiến trình đã cho:");
//		showTable();
		// end input
		
		int Txh = 0, Tkt = 0;
		System.out.println("\n\n\t=================================LẬP LỊCH CPU FCFS================================\n");
		System.out.println("\tGiản đồ Gantt: \n");
		System.out.print("\t|");
		if (check()) {// tat ca xuat hien cung nhau
			sortByCPUb();// sap xep theo thu tu CPU Burst_Time
			int n = list.size();
			for (int i = 0; i < n; i++) {
				Tkt += list.get(i).getCPUb();
				System.out.print(Txh + " P"+list.get(i).getIndex()+" "+Tkt+"|");
				list.get(i).setTime_Wait(Txh-list.get(i).getTxh());
				list.get(i).setTime_Processing(list.get(i).getTime_Wait()+list.get(i).getCPUb());
				Txh = Tkt;
			}
		}else {//thoi gian xuat hien cua cac tien trinh la khac nhau
			sortByTxh();//sap xep theo thoi gian xuat hien
			int n = list.size();
			Txh = list.get(0).getTxh();
			for (int i = 0; i < n; i++) {
				Tkt += list.get(i).getCPUb();
				System.out.print(Txh + " P"+list.get(i).getIndex()+" "+Tkt+"|");
				list.get(i).setTime_Wait(Txh-list.get(i).getTxh());
				list.get(i).setTime_Processing(list.get(i).getTime_Wait()+list.get(i).getCPUb());
				if (i+1<n && Tkt < list.get(i+1).getTxh()) {// tien trinh sau chua xuat hien khi tien trinh i kthuc
					Tkt = list.get(i+1).getTxh();
				}
				Txh = Tkt;
			}
		}
		System.out.println();System.out.println();
		System.out.println("\t\t===============Thời gian đợi TB của hệ tiến trình================\n");
		float sum = 0;
		for (int i = 0; i < list.size(); i++) {
			System.out.println("\t\t-Thời gian đợi của P"+list.get(i).getIndex()+": "+list.get(i).getTime_Wait());
			sum += list.get(i).getTime_Wait();
		}
		System.out.println("\n\t\tThời gian đợi trung bình của hệ tiến trình: "+sum/list.size());
		System.out.println();
		System.out.println("\t\t===============Thời gian xử lý của hệ tiến trình================\n");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("\t\t-Thời gian xử lý của P"+list.get(i).getIndex()+": "+list.get(i).getTime_Processing());
		}
	}
	public static void nhap() {
		System.out.println("\n\tNhập số tiến trình: ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i=0;i<n;i++) {
			
		}
	}
	public static void showTable() {
		System.out.printf("%3s","STT");
		for(int i=0;i<list.size();i++) {
			
		}
	}
	public static boolean check() {// TH ĐB: tất cả Txh = nhau ( coi như = 0 )
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).getTxh() != list.get(0).getTxh())
				return false;
		}
		return true;
	}
	public static void sortByTxh() {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getTxh() > list.get(j).getTxh()) {
					TienTrinh x = list.get(i);
					list.set(i, list.get(j));
					list.set(j, x);
				}
			}
		}
	}

	public static void sortByCPUb() {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getCPUb() > list.get(j).getCPUb()) {
					TienTrinh x = list.get(i);
					list.set(i, list.get(j));
					list.set(j, x);
				}
			}
		}
	}
}
