package DS_TeamProject;

import java.util.*;

import Hw3.ArrayStack;
import Hw3.StackInterface;
import SortingExample.MergeSort;
public class Print {
	public static Double[] Choice_Sort() {
		Scanner scan=new Scanner(System.in);
		Set<Double> Status_Set=readText.z_scenario.keySet();
		int size=Status_Set.size();
		Double arr10[]=new Double[size];
		arr10=(Double[]) Status_Set.toArray(arr10);
		Double[] clone2=arr10;
		String ms;
		boolean found=true;
		while(found) {
			System.out.println("정렬 알고리즘을 선택하세요.");
			System.out.print("1.merge 2.selection 3.next" );
			ms=scan.nextLine();

			switch(Integer.valueOf(ms)) {
			case 1://mergersort 
				Stopwatch time1=new Stopwatch();
				arr10=(Double[]) MergeSort.mergeSort(clone2);
				System.out.println();
				System.out.println("MergeSort 하는데 걸리는시간: "+time1.elapsedTime());
				break;
			case 2://selection sort
				Stopwatch time3=new Stopwatch();
				arr10=Selectionsort.sort(clone2);
				System.out.println();
				System.out.println("selection sort 걸린 시간: "+time3.elapsedTime());
				break;
			case 3:
				System.out.println();
				System.out.println("setting 완료");
				found=false;
				break;

			}}
		return arr10;
	}
	
	public static void UP_ratio_optimalvalue(Integer index,double ratio, Integer when) {
		System.out.println();
		TreeSet<Double>tree=new TreeSet<Double>();
		Iterator<Integer> iter=readText.InvestTable.keySet().iterator();
		int i=1;
		while(iter.hasNext()) {
			int Status=iter.next();
			if(readText.InvestTable.get(Status).get(index)>ratio) {
				tree.add(readText.scenario_z.get(Status));
			}
		}
		Iterator<Double> iter2=tree.iterator();
		while(iter2.hasNext()) {
			double value=iter2.next();
			System.out.println(i+"번째 Risk은 : "+value+" , 해당 시나리오는 : "+ readText.z_scenario.get(value));
			i++;
			if((i-1)==when)break;
		}
	}
	
	public static void Status_InvestRatio(int status, int num) {
		System.out.println();
		ArrayList<Double> list=readText.InvestTable.get(status);
		TreeSet<Double> treeset=new TreeSet<Double>();
		treeset.addAll(list);
		for(int i=0;i<num;i++) {
			double value=treeset.pollLast();
			System.out.println((i+1)+"번째 높은 투자비율: "+value +" 해당 종목: "+list.indexOf(value));

		}

	}
	
	public static void Z_and_Time_Max(String letter, int num,Double[] sorted) {
		System.out.println();
		if(letter.equals("long")) {
			System.out.println("오래 걸린 시간 순으로 "+num +"개의 시간값과 해당 시나리오를 정렬하겠습니다.");
			int j=1;
			for(int i=sorted.length-1;i>=sorted.length-num;i--) {
				System.out.println(j+"번째 시간값은 :"+ sorted[i]+"  시나리오는  "+readText.time_scenario.get(sorted[i]));

				j++;
			}
		}
		else if(letter.equals("short")) {
			System.out.println("짧게 걸린 시간 순으로 "+num +"개의 시간값과 해당 시나리오를 정렬하겠습니다.");
			for(int i=0;i<num;i++) {
				System.out.println((i+1)+"번째 시간값은 :"+sorted[i]+"  시나리오는  "+readText.time_scenario.get(sorted[i]));

			}
		}
	}
	
	public static void printSize(TreeSet<Double> a,int p, String letter) {
		System.out.println();
		if(letter.equals("down")) {
			Iterator<Double> iter=a.iterator();
			int i=1;
			while(iter.hasNext()) {
				double value=iter.next();
				System.out.println(i+"번째 리스크: "+value);
				if(i==p) {
					break;
				}
				i++;
			}}
		else if(letter.equals("up")){
			Iterator<Double> iter=a.descendingIterator();
			int i=1;
			while(iter.hasNext()) {
				double value=iter.next();
				System.out.println(i+"번째 리스크: "+value);
				if(i==p) {
					break;
				}
				i++;
			}

		}
	}

	public static TreeSet<Double> printzvalue(int a) {

		TreeSet<Double> tree=new TreeSet<Double>();

		Set<Integer> set=readText.InvestTable.keySet();
		Iterator<Integer> iter=set.iterator();
		while(iter.hasNext()) {
			Integer n1=iter.next();
			if(readText.InvestTable.get(n1).get(a)>0){
				tree.add(readText.scenario_z.get(n1));
			}
		}
		return tree;
	}
	
	/* TreeSet을 이용한 구현.
	   public static void investratio(double dangerValue,double b,int num) {
		      System.out.println();
		      TreeSet<Integer> tree=new TreeSet<Integer>();
		      ArrayList<Double> list=readText.InvestTable.get(readText.z_scenario.get(dangerValue));
		      for(int i=1;i<list.size()-1;i++) {
		         if(list.get(i)>b) {
		            tree.add(i);
		         }
		      }
		      for(int i=0;i<num;i++) {
		         System.out.println((i+1)+"번째 "+b+"이상인 x의 항목은 : "+ tree.pollFirst());
		      }
		   }*/

	public static void investratio(double dangerValue,double b,int num) {
		System.out.println();
		ArrayList<Double> list=readText.InvestTable.get(readText.z_scenario.get(dangerValue));
		Queue <Integer> queue = new LinkedList<Integer>();
		for(int i=1;i<list.size()-1;i++) {
			if(list.get(i)>=b) {
				queue.add(i);
			}
		}
		for(int i=0;i<num;i++) {
			System.out.println((i+1)+"번째 "+b+"이상인 x의 항목은 : "+ queue.poll());
		}
	}
	
	public static void printxvalue(int a,int size,Double[] c) {
		System.out.println();
		int p=0;
		StackInterface<Double> stack = new ArrayStack<Double>();
		for (int i=size-a; i<size; i++) {
			stack.push(c[i]);
		}
		for(int i=size-1;i>=size-a;i--) {
			System.out.println((p+1)+"번째로 높은 x 값"+stack.pop() +" "+"종목 번호: "+readText.z_scenario.get(c[i]));
			p++;
		}
	}
	
	public static void printword(String p,Double[] a,int num,int size) {
		System.out.println();
		switch(p) {
		case "up":
			for(int i=0;i<size;i++) {
				if(a[i]>num)System.out.println("Risk: "+a[i]+ " 시나리오 번호: "+readText.z_scenario.get(a[i]));
			}
			break;
		case "down":
			for(int i=0;i<size;i++) {
				if(a[i]<num)System.out.println("Risk: "+a[i]+ " 시나리오 번호: "+readText.z_scenario.get(a[i]));
			}
			break;
		case "between":
			int num2;
			Scanner scan=new Scanner(System.in);
			System.out.print("새로운 값을 입력하세요 :");
			num2=Integer.valueOf(scan.nextLine());
			if(num2<num) {
				int num3=num2;
				num2=num;
				num=num3;
			}
			for(int i=0;i<size;i++) {
				if(a[i]>num && a[i]<num2)System.out.println("Risk: "+a[i]+ " 시나리오 번호: "+readText.z_scenario.get(a[i]));
			}
			break;
		default:
			System.out.println("잘못 입력했습니다.");
			break;
		}
	}

	public static double findriskAverage(Double[] arr) {
		double sum =0;
		for (double d : arr) {
			sum += d ;
		}
		double avg = sum / arr.length;
		return avg;
	}

	public static double findtimeAverage() {
		double sum=0;
		int get=0;
		Iterator<Double>iter=readText.time_scenario.keySet().iterator();
		while(iter.hasNext()) {
			double value=iter.next();
			int length=readText.time_scenario.get(value).size();
			value=value*length;
			sum+=value;
			get+=length;
		}
		return sum/get;
	}

	public static Double[] sortandhash(ArrayList<Double> a) {

		for(int i=0;i<a.size();i++) {
			readText.z_scenario.put(a.get(i),i);
		}
		Double []key=new Double[a.size()];
		key=a.toArray(key);

		MergeSort.mergeSort(key);
		return key;
	}
}

