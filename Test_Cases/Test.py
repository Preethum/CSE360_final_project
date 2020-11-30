import random 
import pandas as pd
import namegenerator


DataFrame = pd.DataFrame();
df_names = pd.read_csv('babies-first-names-all-names-all-years.csv');
asu_id = []

names_first = df_names['FirstForename'][0:100] # first name

names_last =[]
for i in range(0,100):
    names_last.append( df_names['FirstForename'][100+i]) # last name

for i in range(0,100):
    asu_id.append(random.randint(111111111,9999999999)) # asu id 


df_majors = pd.read_csv('majors-list.csv');
majors = df_majors['Major'][0:100] # majors list


study_lvl_lst = []
study_lvl =["Grad","UnderGrad","PHD"]
for i in range(0,100):
    study_lvl_lst.append(study_lvl[random.randint(0,2)]) # major list

asurite = [] # asaurite id
names_last_temp = names_last;

for i in range(0,100):
    id = names_first[i][1] + names_last_temp[i]
    asurite.append(id)


DataFrame['asu_id'] =asu_id
DataFrame['names_first']=names_first
DataFrame['names_last']=names_last
DataFrame['majors']=majors
DataFrame['study_lvl_lst']=study_lvl_lst
DataFrame['asurite']=asurite

DataFrame.to_csv('tempting',header=False,index=False)

print(DataFrame)
