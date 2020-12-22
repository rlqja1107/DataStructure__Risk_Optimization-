package DS_TeamProject;

public class Selectionsort {
	public static Double[] sort(Double[] data){
		int size = data.length;
		int min; //최소값을 가진 데이터의 인덱스 저장 변수
		double temp;
		Double [] clone=new Double[data.length];
		for(int i=0; i<size-1; i++){ // size-1 : 마지막 요소는 자연스럽게 정렬됨
			min = i;
			for(int j=i+1; j<size; j++){
				if(data[min] > data[j]){
					min = j;
				}
			}
			temp = data[min];
			data[min] = data[i];
			data[i] = temp;
		}
		clone=data;
		return clone;
	}



}