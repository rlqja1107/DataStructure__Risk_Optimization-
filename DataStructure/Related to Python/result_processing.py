import pickle
import cvxopt
import time
import numpy as np
import pandas as pd
start_list = [32001]
finish_list = [48000]

for sf in list(zip(start_list, finish_list)):
    start = sf[0]
    finish = sf[1]
    print("start: {}".format(start))
    print(finish-start+1)
    print('******')
    #start = time.time()
    path = './csv/temp/'
    with open(path+"result_list_{}_{}.txt".format(start, finish), "rb") as f:
        data = pickle.load(f)
    #print(time.time() - start), 68sec
    # status = []
    # x = []
    # z = [] #None or float
    iter = [0 for i in range(len(data))]
    for i in range(len(data)):
        # status.append(data[i]['status'])
        # z.append(data[i]['primal objective'])
        # x.append(data[i]['x'])
        iter[i] = data[i]['iterations']
        #print(iter[i])

    number=[i+start for i in range(len(data))]
    # for i in range(len(x)):
    #     if z[i] == None:
    #         z[i] = -1.0
    #     temp = []
    #     if x[i] != None:
    #         for j in range(len(x[i])):
    #             temp.append(x[i][j])
    #         x[i] = temp
    #     else:
    #         x[i] = []

    # print(57)
    # print(status[57])
    # print(z[57])
    # print(x[57])
    # print(58)
    # print(status[58])
    # print(z[58])
    # print(x[58])
    #start = time.time()
    print(len(number))
    print(len(iter))
    # print(len(status))
    # print(len(z))
    # print(len(x))
    #
    # temp = pd.DataFrame({"number":number, "status":status, "z": z, "x":x})
    # temp.to_csv("szx_{}_{}.csv".format(start, finish), index=False)
    temp = pd.DataFrame({"number":number, "iteration": iter})
    temp.to_csv("./iteration/iteration_{}_{}.csv".format(start, finish), index=False)
    print('{}-start done'.format(start))
    print("")