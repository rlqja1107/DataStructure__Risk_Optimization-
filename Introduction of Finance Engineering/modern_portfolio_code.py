import numpy as np
import pandas as pd
from math import log
from matplotlib import pyplot as plt
data = pd.read_csv('./dataset.csv')
print(data.head())
colname = data.columns
data = data.set_index(data['Date'])
del data['Date']
data[colname[1:-2]] = data[colname[1:-2]]
temp_data = data.iloc[:,0:-2].astype('float')

for index in range(len(temp_data)):
    if index == 0:
        continue
    temp_data.iloc[index,:] = data.iloc[index,:-2]/data.iloc[index-1,:-2]
temp_data.drop(['2011-01-01'], inplace=True)
data.drop(['2011-01-01'], inplace=True)
data[colname[1:-2]] = temp_data
numeric_df = data.iloc[:,[0,1,2,3,4,5,6,7,8,9]]
which_column = numeric_df.columns
returns = np.array(numeric_df)
S = np.array((pd.DataFrame(returns)).cov())
r = np.mean(returns, axis=0)
Si = np.linalg.inv(S)
e = np.ones(len(r))
a = np.matmul(np.matmul(r,Si),r)
b = np.matmul(np.matmul(r,Si), e)
c = np.matmul(np.matmul(e,Si), e)
# 1번 - short-selling allow
range_np = np.arange(0,2,0.0001)
list_w = []
for i in range_np:
    mu = i
    bottom = a*c - b*b
    l1 = (c*mu - b) / bottom
    l2 = (-b*mu + a) / bottom
    w = l1 * (np.matmul(Si, r)) + l2 * (np.matmul(Si, e))
    mean_return = np.sum(r*w)
    sd = np.matmul(np.matmul(w.T,S), w)
    list_w.append((i, mean_return, sd, w))
m_v_df = pd.DataFrame(list_w, columns = ['target return','mean return', 'sd', 'weight'])
sd_min_idx = m_v_df['sd'].idxmin()
print(m_v_df.iloc[sd_min_idx])

# 2번
bottom = a*c - b*b
mu = 1
l1 = (c*mu - b) / bottom
l2 = (-b*mu + a) / bottom
w = l1 * (np.matmul(Si, r)) + l2 * (np.matmul(Si, e))
mean_return = np.sum(r*w)
sd = np.matmul(np.matmul(w.T, S), w)
print("Mean Return : {:4f}, Standard Deviation : {:4f}".format(mean_return, sd))
print("Weight : ", list(w))

# 3번 -no - short selling
from cvxopt import matrix
from cvxopt.solvers import qp
from math import floor

cov = matrix(S)
mean_return = matrix(r)
range_np = np.arange(0, 2, 0.001)
n = len(r)
list_w = []
for ret in range_np:
    G = matrix(np.concatenate((-np.transpose(mean_return), -np.identity(n)), 0))
    h = matrix(np.concatenate((-np.ones((1, 1))*ret, np.zeros((n, 1))), 0))
    A = matrix(np.ones((1,n)))
    b = matrix(1.0)
    q = matrix(np.zeros((n,1)))
    sol = qp(cov, q, G, h, A, b)
    w = np.asarray(sol['x']).squeeze()
    list_w.append((ret, np.sum(w*r), np.round(np.matmul(np.matmul(w.T, S), w), 6), list(sol['x'])))
no_short_df = pd.DataFrame(list_w, columns = ['Target Return','mean rate', 'sd', 'weight'])
x_plt = list(no_short_df['sd'])
y_plt = list(no_short_df['Target Return'])
plt.plot(x_plt, y_plt)
plt.show()
sample = no_short_df[no_short_df['sd'] == 0.015452]
sort_sample = sample.sort_values(by = ['Target Return'], ascending = False)
portfolio = sort_sample.iloc[0]
print("가장 높은 target return point 부분이 minimum variance point")
print(portfolio)
print("가장 비율이 높은 종목 : ", which_column[np.argmax(portfolio[3])], '비율 : ', max(portfolio[3]))

print('포트폴리오 return : {:4f}, Standard Deviation : {:4f}'.format(portfolio[1], portfolio[2]))
print("Weight : ", portfolio[3])


# 4번
target_return = 1
G = matrix(np.concatenate((-np.transpose(mean_return), -np.identity(n)), 0))
h = matrix(np.concatenate((-np.ones((1, 1))*target_return, np.zeros((n, 1))), 0))
A = matrix(np.ones((1, n)))
b = matrix(1.0)
q = matrix(np.zeros((n, 1)))
sol = qp(cov, q, G, h, A, b)
w = np.asarray(sol['x']).squeeze()
print("Target Return : ", target_return)
print("Portfolio Return : ", np.sum(w*r))
print("Standard Deviation : ", np.round(np.matmul(np.matmul(w.T, S), w), 6))
print("Weight : ", list(sol['x']))

# 5번  
target_return = 2
G = matrix(np.concatenate((-np.transpose(mean_return), -np.identity(n)), 0))
h = matrix(np.concatenate((-np.ones((1, 1))*target_return, np.zeros((n, 1))), 0))
A = matrix(np.ones((1, n)))
b = matrix(1.0)
q = matrix(np.zeros((n, 1)))
sol = qp(cov, q, G, h, A, b)
w = np.asarray(sol['x']).squeeze()
print("Target Return : ", target_return)
print("Portfolio Return : ", np.sum(w*r))
print("Standard Deviation : ", np.round(np.matmul(np.matmul(w.T, S), w), 6))
print("Weight : ", list(sol['x']))
