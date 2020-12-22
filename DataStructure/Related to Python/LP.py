"""
19-2 data structure team project
autor huiseung
"""

import numpy as np
from cvxopt import matrix, solvers
import time
import pickle

"""
#cvxopt_ex
A = matrix([[-1.0, -1.0, 0.0, 1.0, 1.0], [1.0, -1.0, -1.0, -2.0, -2.0]])
b = matrix([1.0, -2.0, 0.0, 4.0, 4.0])
c = matrix([2.0, 1.0])

sol = solvers.lp(c, A, b)
print("status : " + sol['status'])
if sol['status'] == "optimal":
    print("x")
    print(sol['x'])
    print("z")
    print(sol['z'])
"""

# var.txt preprocessing
var = open("vars.txt", "r")
lines = var.readlines()
var.close()
temp = [0 for i in range(len(lines))]
i = 0
for line in lines:
    temp[i] = list(map(int, line.split('\t')[:-1]))
    i = i + 1
    if i % 1000 == 0:
        print"*"
var_dict = {}
for i in range(len(temp)):
    var_dict[i + 1] = temp[i]
    if i % 1000 == 0:
        print"*"
print "var.txt preprocessing finised!"

# matirx_cp_mean preprocessing
matrix_cp_mean = open("matrix_cp_mean.txt", "r")
temp_lines2 = matrix_cp_mean.readlines()
matrix_cp_mean.close()
column_name = temp_lines2[0].split('\t')
column_name[-1] = column_name[-1][:-1]
temp_lines2 = temp_lines2[1:]
temp2 = [0 for i in range(len(temp_lines2))]
for i in range(len(temp_lines2)):
    temp2[i] = temp_lines2[i].split('\t')
for i in range(len(temp_lines2)):
    temp2[i][-1] = temp2[i][-1][:-1]
for i in range(len(temp_lines2)):
    temp2[i] = list(map(float, temp2[i]))
    if i % 1000 == 0:
        print"*"
temp2 = np.array(temp2)
temp2 = -temp2.sum(axis=0)
temp2 = list(temp2)
print "matrix_cp_mean preprocessing finised!"

# matrix_cp_return preprocessing
matrix_cp_return = open("matrix_cp_return.txt", "r")
temp_lines3 = matrix_cp_return.readlines()
matrix_cp_return.close()
temp3 = temp_lines3[1].split('\t')
temp3[-1] = temp3[-1][:-1]
temp3 = list(map(float, temp3))
temp3 = [-i for i in temp3]
print "matrix_cp_return preprocessing finised!"

# point_2x0 preprocessing
point = open("point_2x0.txt", "r")
lines4 = point.readlines()
point.close()
colum_name2 = lines4[0].split('\t')
colum_name2[-1] = colum_name2[-1][:-1]
lines4 = lines4[1:-1]
temp4 = [0 for i in range(len(lines4))]
for i in range(len(lines4)):
    temp4[i] = lines4[i].split('\t')
for i in range(len(lines4)):
    temp4[i] = float(temp4[i][1][:-1])
temp4 = list(temp4)
print 'point_2x0 preprocesing finised!'

# make c, A, b
print "start solving LP!"
first = 200
done = 16000
if(first-done > 0):
    print "please check option"
    exit()

const = 0.0963007951203275

ans_list = []
t_list = []

for j in range(first, done + 1):
    now = var_dict[j]
    M = len(now)
    # c
    c = [0 for i in range(M)]
    for i in range(M):
        c[i] = temp2[now[i]]
    # A
    A = np.zeros((M, 2 * M + 3)).tolist()
    for i in range(M):
        A[i][0] = 1.0
        A[i][1] = -1.0
        A[i][2] = temp3[now[i]]
        A[i][i + 3] = A[i][i + 3] + 1.0 #굳이 ? 1.0으로 해도 되지 않나?
        A[i][i + 3 + M] = A[i][i + 3 + M] - 1.0 # -1.0이라 해도 되지 않나?
    # b
    b_temp = [1.0, -1.0, -const]
    b_plus = [0 for i in range(M)]
    for i in range(M):
        b_plus[i] = temp4[i]
    b = b_temp + b_plus + [0.0 for i in range(M)] #여기서 0이 아니라 0.0했어야 했다!!

    # LP make
    lpA = matrix(A)
    lpb = matrix(b)
    lpc = matrix(c)
    start = time.time()
    sol = solvers.lp(lpc, lpA, lpb)
    t_list.append(time.time() - start)
    ans_list.append(sol)

    # garbage collection
    del (now)
    del (A)
    del (b_plus)
    del (b)
    del (c)
    del (lpA)
    del (lpb)
    del (lpc)

    #print result
    print "we done {}-th problem".format(j)
    print "{}-th stauts".format(j)
    print (sol["status"])
    print "{}-th optimal value".format(j)
    print(sol['primal objective'])
    print "\n"

#save result
with open('result_list_{}_{}.txt'.format(first, done), 'wb') as f:
    pickle.dump(ans_list, f)

with open("time_record_{}_{}.txt".format(first, done), "wb") as t:
    pickle.dump(t_list, t)

print "all process finish!!"