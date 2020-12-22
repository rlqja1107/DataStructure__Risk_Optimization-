19-2 데이터구조론 팀 프로젝트
python으로 LP를 풀어 데이터 만들기

<환경 구성>
파이썬 2.7
numpy == 1.16.5(1.17이상은 cvxopt와 호환 에러가 뜹니다)
cvxopt == 1.2.3
time : 기본 내장 라이브러리
pickle : 기본 내장 라이브러리
위에서 기본 내장 라이브러리가 아닌 라이브러리들은 pip을 이용해 설치해 주세요.

###Lp.py사용
LP.py코드와 실험에 쓰이는 text파일들을 같은 폴더에 넣어주세요.
실험에 쓰이는 text : var, matrix_cp_mean, matrix_cp_return, ponint_2x0

<실험 방법>
처음 실행 시 text파일을 LP문제를 풀기 위해 전처리 하는 과정에서 시간이 소요됩니다.
오래 걸리는 부분에서 * 이 출력 되어 동작중임을 확인 할 수 있으니 걱정하지 마세요.
142번 줄에 #save result 부분에서 실험 결과들을 저장하니 실행 후 따로 건드실 건 없습니다.

85번 째 줄 #make c, A, b
다음에 오는 first, done을 각자 맡은 숫자로 설정하세요.(1<=fisrt<=done)
var.txt에서 first~done까지 긁어와 LP문제를 풀겁니다.

예를 들어 자신이 var.txt에서 맡은 부분이 1~16000개라면(실험 할당.txt참고)
자신이 돌릴 수 있는 시간에 맞게 1~100, 101~200, 201~300 이렇게 나누어 first~done을 설정하세요.
코드가 다 돌면 all process finish!!라는 문구와 함께 파이썬 실행 폴더에
result_list_{first}_{done}.txt 과 time_record_{first}_{done}.txt파일이 생성되어 있을겁니다.

result_list.txt파일에는 solve해서 얻을 수 있는 정보가 담겨있습니다. (아래 참고)
time_record.txt파일에는 문제를 푸는데 걸린 시간이 초 단위로 기록될 것입니다.

실험을 다 끝낸 후 이를 보기 좋게 처리하여 자바로 추후 프로젝트를 진행하면 됩니다.

<solve로 얻을 수 있는 정보>
'status': 'optimal',  (str, 문제가 풀린 상태)
'z': <3565x1 matrix, tc='d'>,  (?)
'dual slack': 5.5124865129972634e-05,(?)  
'primal objective': 6096.561549585225, (float, 최적값)
'relative gap': 9.992659172815718e-07, (?)
'dual objective': 6096.561521176949, (float, 듀얼문제 최적값)
'residual as dual infeasibility certificate': None, (?) 
'residual as primal infeasibility certificate': None, (?)
's': <3565x1 matrix, tc='d'>,  (?)
'primal infeasibility': 1.5481473470607095e-08,  (?)
'dual infeasibility': 2.412443050010319e-09, (?)
'iterations': 24, (int, 문제를 풀기위해 entering/leaving을 반복한 횟수)
'primal slack': 4.443812259332652e-12, (?)
'y': <0x1 matrix, tc='d'>, (?)
'x': <1781x1 matrix, tc='d'>, (matrix, 최적해) (?)
'gap': 0.006092086140722418}] (?)


*cvxpopt default solver가 e-12자리 음수와 0을 구분 못하는 것 같다.
*solver를 glpk로 하면 시간도 절약하고, 음수처리도 잘 되는 것 같다.

##result_processing
solve가 풀어 저장한 class 불러오기
앞서서 만든 result_list_xxxx_xxxxx.txt를 불러와 필요한 정보만 취합해서 .csv파일을 만든다.

