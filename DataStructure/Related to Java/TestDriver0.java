package DS_TeamProject;
import java.util.*;


class TestDriver0 {

	public static void main(String[] args) {

		Stopwatch st2=new Stopwatch();
		System.out.println("파일을 불러오는 중..........");
		readText.Text("xpd_opt_z_text.txt");
		readText.Valueand("xpd_opt_text.txt");
		readText.Text2("status_and_time(optimal).txt");
		double time=st2.elapsedTime();
		System.out.println("파일을 불러오는데 걸리는 시간: "+time);
		Scanner scan=new Scanner(System.in);

		Double[] arr=new Double[readText.z_scenario.keySet().size()];
		arr=Print.Choice_Sort();
		int size=arr.length;
		System.out.println("안녕하세요");
		//z값 sort


		Stopwatch st=new Stopwatch();
		//Time sort
		Set<Double> Time_Set=readText.time_scenario.keySet();

		Double arr3[]=new Double[Time_Set.size()];

		arr3=(Double[]) Time_Set.toArray(arr3);


		Stopwatch st4=new Stopwatch();
		arr3=(Double[]) MergeSort.mergeSort(arr3);
		System.out.println("<기본정보>");

		System.out.println("optimal의 갯수: "+arr.length);
		System.out.println("Risk의 최소값: "+arr[0]);
		System.out.println("Risk의 최대값: "+arr[arr.length-1]);
		System.out.println("Risk의 평균값: "+Print.findriskAverage(arr));
		System.out.println();

		System.out.println("문제를 푸는데 걸린 최소시간: "+arr3[0]);
		System.out.println("문제를 푸는데 걸린 최대시간: "+arr3[arr3.length-1]);
		System.out.println("문제를 푸는데 걸린 평균시간: " + Print.findtimeAverage());


		ArrayList<Double> list = new ArrayList<Double>();
		String Message1;
		String Message2;
		String p4;
		String remember = null;


		String message;
		boolean found=true;

		while(found) {
			Manual();
			System.out.println();
			System.out.print("궁금한 문항의 번호를 입력하세요: ");
			Message2=scan.nextLine();
			while(Message2.equals("")) {
				System.out.println();
				System.out.print("숫자를 입력하세요: ");
				Message2=scan.nextLine();
			}

			switch(Integer.valueOf(Message2)) {

			case 1:
				System.out.println();
				System.out.println("원하는 범위의 Risk을 입력하시오(ex)5000): ");
				message=scan.nextLine();
				System.out.println();
				System.out.println("up(이상), down(미만), between(사이) 중 하나를 입력하시오. ");
				Message1=scan.nextLine();

				while (Integer.valueOf(message) < arr[0] || Integer.valueOf(message) > arr[arr.length-1]) {
					System.out.println();
					System.out.println("범위에 맞게 다시 입력해주세요.");
					message=scan.nextLine();
				}
				Print.printword(Message1, arr, Integer.valueOf(message),size);
				continue;
				
			case 2:
				System.out.println();
				System.out.println("투자비율을 보고싶은 시나리오 번호를 입력하세요(ex)23310): ");
				Message1=scan.nextLine();
				remember = Message1;
				list = readText.InvestTable.get(Integer.valueOf(Message1));
				System.out.println(list);
				continue;
				
			case 3:
				Double []list2=(Double[])Print.sortandhash(list);
				System.out.println();
				System.out.print("시나리오 #"+remember+"에서 투자비율이 높은 종목 몇 개 까지 보고싶으세요?");
				message=scan.nextLine();
				Print.printxvalue(Integer.valueOf(message), list2.length, list2);
				continue;
				
			case 4:
				System.out.println();
				String message2;
				String message3;
				String message4;
				System.out.print("특정 Risk을 입력하세요(ex(4420.554363): ");
				message2=scan.nextLine();
				System.out.println();
				System.out.print("특정 투자 비율을 설정해주세요(ex(0.003이상): ");
				message3=scan.nextLine();
				System.out.println();
				System.out.println("몇 개의 종목을 보고싶으신가요: ");
				message4=scan.nextLine();
				System.out.println();
				Print.investratio(Double.valueOf(message2),Double.valueOf(message3),Integer.valueOf(message4));
				continue;
				
			case 5:
				System.out.println();
				System.out.println("up(내림차순)이나 down(오름차순)을 입력하세요");
				String me;
				String me2;
				me2=scan.nextLine();
				if(me2.equals("down")) {
					System.out.println();
					System.out.print("포함하고 싶은 특정 종목을 입력하세요: ");
					me=scan.nextLine();
					TreeSet<Double> tree=Print.printzvalue(Integer.valueOf(me));
					System.out.println();
					System.out.println("x"+Integer.valueOf(me)+" 종목이 들어간 리스크를 몇 개까지 보여드릴까요?");
					message=scan.nextLine();
					Print.printSize(tree, Integer.valueOf(message),me2);
					continue;}
				else if(me2.equals("up")) {
					System.out.println();
					System.out.print("포함하고 싶은 특정 종목을 입력하세요: ");
					me=scan.nextLine();
					TreeSet<Double> tree=Print.printzvalue(Integer.valueOf(me));
					System.out.println();
					System.out.println("x"+Integer.valueOf(me)+" 종목이 들어간 리스크를 몇 개까지 보여드릴까요?");
					message=scan.nextLine();
					Print.printSize(tree, Integer.valueOf(message),me2);
					continue;
				}
			
			case 6:
				System.out.println();
				System.out.print("특정 종목을 입력해주세요: ");
				Message1=scan.nextLine();
				System.out.println();
				System.out.print("특정 투자비율을 입력해주세요(ex(0.003이상): ");
				Message2=scan.nextLine();
				System.out.println();
				System.out.print("몇 개까지의 Risk를 보고싶으신가요: ");
				p4=scan.nextLine();
				Print.UP_ratio_optimalvalue(Integer.valueOf(Message1),Double.valueOf(Message2), Integer.valueOf(p4));
				continue;
				
			case 7:
				System.out.println();
				System.out.print("오래 걸린 시간, 적게 걸린 시간 중 무엇을 보여드릴까요(long or short): ");
				message=scan.nextLine();
				System.out.println();
				System.out.println("몇 개의 시간 값을 보여드릴까요: ");
				Message1=scan.nextLine();
				if(message.equals("long")) {
					Print.Z_and_Time_Max(message,Integer.valueOf(Message1),arr3);
					System.out.println();
					System.out.print("위의 시나리오 번호를 입력하시면 투자비율 순으로 해당 종목을 출력해드리겠습니다. (안보고 싶으면 no): ");
					Message2=scan.nextLine();
					System.out.println();
					System.out.println("몇 개까지의 종목을 보여드릴까요: ");
					p4=scan.nextLine();
					Print.Status_InvestRatio(Integer.valueOf(Message2),Integer.valueOf(p4));
					if(Message2.equals("no"))continue;
				}
				else if(message.equals("short")) {
					Print.Z_and_Time_Max(message,Integer.valueOf(Message1),arr3);
					System.out.println();
					System.out.print("위의 시나리오 번호를 입력하시면 투자비율 순으로 해당 종목을 출력해드리겠습니다. (안보고 싶으면 no): ");
					Message2=scan.nextLine();
					System.out.println();
					System.out.println("몇 개까지의 종목을 보여드릴까요?: ");
					p4=scan.nextLine();
					Print.Status_InvestRatio(Integer.valueOf(Message2),Integer.valueOf(p4));
					if(Message2.equals("no"))continue;
				}
				else {
					System.out.println();
					System.out.println("잘못 입력하셨습니다. 다시 처음으로 돌아가겠습니다.");

				}
				continue;
				
			case 8: 
				System.out.println();
				System.out.println("감사합니다.");
				found=false;

				break;
			default:
				System.out.println();
				System.out.println("다시 입력하세요: ");
				continue;

			}
		}
	}
	
	public static void Manual() {
		System.out.println();
		System.out.println("----------------------------------------------------------------");
		System.out.println("1. 특정한 범위의 Risk을 보고싶습니까?");
		System.out.println("2. 특정 시나리오 번호를 통해 각 종목의 투자비율을 보고싶습니까? ");
		System.out.println("3. 위의 시나리오 번호에서 투자비율이 높은 종목을 보고싶습니까?");
		System.out.println("4. 특정 Risk에서, 특정 투자비율 이상을 가진 종목이 궁금하십니까?");
		System.out.println("5. 특정 종목이 포함되었을 때, Risk값이 궁금하십니까?");
		System.out.println("6. 특정 종목이, 특정 투자비율 이상을 가질 때의 Risk값이 궁금하십니까?");
		System.out.println("7. 문제를 푸는데 걸린 시간과 Risk 간의 관계가 궁금하십니까?");
		System.out.println("8. 아무것도 궁금하지 않으신가요?");

	}
}