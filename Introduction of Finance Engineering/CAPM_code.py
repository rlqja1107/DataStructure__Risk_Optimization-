import numpy as np
import pandas as pd
from math import log
from matplotlib import pyplot as plt
data = pd.read_csv('./dataset.csv')
colname = data.columns
data = data.set_index(data['Date'])
del data['Date']
data[colname[1:-2]] = data[colname[1:-2]]
data['KOR10Y'] = np.log(data['KOR10Y'])
data.head()
temp_data = data.iloc[:, 0:11].astype('float')
for index in range(len(temp_data)):
    if index == 0:
        continue
    temp_data.iloc[index, :] = data.iloc[index, 0:11]/data.iloc[index-1, 0:11]
temp_data.drop(['2011-01-01'], inplace=True)
data.drop(['2011-01-01'], inplace=True)
data[colname[1:12]] = temp_data
data.head()
numeric_df = data.iloc[:, [i for i in range(0,12)]]
which_column = numeric_df.columns
returns = np.array(numeric_df)
S = np.array((pd.DataFrame(returns)).cov())
r = np.mean(returns, axis=0)
data.head()
# 1번 
##  summary
def minus(a,b):
    return a-b
data_1 = data[['SAMSUNG ELECTRONICS', 'NAVER','KOSPI', 'KOR10Y']]
data_1['Samsung-KOR10Y'] = data_1.apply(lambda x: minus(x['SAMSUNG ELECTRONICS'], x['KOR10Y']), axis=1)
data_1['KOSPI-KOR10Y'] = data_1.apply(lambda x: minus(x['KOSPI'], x['KOR10Y']), axis=1)
data_1['Naver-KOR10Y'] = data_1.apply(lambda x: minus(x['NAVER'], x['KOR10Y']), axis=1)
mean_1 = np.mean(data_1)
cov = data_1.cov()

print("Samsung-KOR10Y 평균: {:6f}, Naver-KOR10Y 평균 : {:6f},  KOSPI-KOR10Y : {:6f}".format(mean_1[4], mean_1[6], mean_1[5]))
print("Samsung-KOR10Y 분산: {:6f}, Naver-KOR10Y 분산 : {:6f}, KOSPI-KOR10Y 분산 : {:6f}".format(cov.iloc[4, 4], cov.iloc[6, 6], cov.iloc[5, 5]))
print("Samsung-KOR10Y cov: {:6f}, Naver-KOR10Y cov : {:6f}, KOSPI-KOR10Y cov : {:6f}".format(cov.iloc[4, 5], cov.iloc[6, 5], cov.iloc[5, 5]))

summary_1_df = data_1[['Samsung-KOR10Y', 'Naver-KOR10Y', 'KOSPI-KOR10Y']]
samsung_b = cov.iloc[4,5] / cov.iloc[5,5]
samsung_a = mean_1[4] - samsung_b * mean_1[5]
samsung_error_variance = cov.iloc[4,4] - (samsung_b**2) * cov.iloc[5,5]
naver_b = cov.iloc[6,5] / cov.iloc[5, 5]
naver_a = mean_1[6] - naver_b * mean_1[5]
naver_error_variance = cov.iloc[6,6] - (naver_b**2) * cov.iloc[5,5]
summary_1_df.loc['aver'] = [mean_1[4], mean_1[6], mean_1[5]]
summary_1_df.loc['var'] = [cov.iloc[4, 4], cov.iloc[6, 6], cov.iloc[5, 5]]
summary_1_df.loc['cov'] = [cov.iloc[4, 5], cov.iloc[6, 5], cov.iloc[5, 5]]
summary_1_df.loc['b'] = [samsung_b, naver_b, 1]
summary_1_df.loc['a'] = [samsung_a, samsung_a, 0]
summary_1_df.loc['e-var'] = [samsung_error_variance, naver_error_variance, np.nan]
## Summary
print(summary_1_df)
## Adequacy
error_samsung = data_1['Samsung-KOR10Y'] - (samsung_a + samsung_b * data_1['KOSPI-KOR10Y'])
error_naver = data_1['Naver-KOR10Y'] - (naver_a + naver_b * data_1['KOSPI-KOR10Y'])
correlation = np.corrcoef(np.array([error_samsung, error_naver]))

print("상관 계수 : ", correlation[0,1])

# 2번 - Summary  

def minus(a, b):
    return a-b


data_2 = data[['KAKAO', 'SK TELECOM', 'KOSPI', 'KOR10Y']]
mean_2 = np.mean(data_2)
cov_2 = data_2.cov()
cov_2
mean_2
summary_2_df = data[['KAKAO', 'SK TELECOM', 'KOSPI']]
kakao_b = cov_2.iloc[0, 2] / cov_2.iloc[2, 2]
kakao_a = mean_2[0] - kakao_b * mean_2[2]
kakao_error_variance = cov_2.iloc[0, 0] - (kakao_b**2) * cov_2.iloc[2, 2]
sk_b = cov_2.iloc[1, 2] / cov_2.iloc[2, 2]
sk_a = mean_2[1] - sk_b * mean_2[2]
sk_error_variance = cov_2.iloc[1, 1] - (sk_b**2) * cov_2.iloc[2, 2]
summary_2_df.loc['aver'] = [mean_2[0], mean_2[1], mean_2[2]]
summary_2_df.loc['var'] = [cov_2.iloc[0, 0], cov_2.iloc[1, 1], cov_2.iloc[2, 2]]
summary_2_df.loc['cov'] = [cov_2.iloc[0, 2], cov_2.iloc[1, 2], cov_2.iloc[2, 2]]
summary_2_df.loc['b'] = [kakao_b, sk_b, 1]
summary_2_df.loc['a'] = [kakao_a, sk_a, 0]
summary_2_df.loc['e-var'] = [kakao_error_variance, sk_error_variance, np.nan]
## Summary
print("Summary")
summary_2_df.tail(7)

## Adequacy  
error_kakao = data_2['KAKAO'] - (kakao_a + kakao_b * data_2['KOSPI'])
error_sk = data_2['SK TELECOM'] - (sk_a + sk_b * data_2['KOSPI'])
corr_2 = np.corrcoef(np.array([error_kakao, error_sk]))
print("상관 계수 : ", corr_2[0, 1])
