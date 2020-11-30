import random 
import pandas as pd
import namegenerator

DataFrame = pd.DataFrame();
df_names = pd.read_csv('babies-first-names-all-names-all-years.csv');

names_first = df_names['FirstForename'][0:100] # first name

names_last =[]
for i in range(0,100):
    names_last.append( df_names['FirstForename'][100+i]) # last name


asurite = [] # asaurite id
names_last_temp = names_last;
for i in range(0,100):
    id = names_first[i][1] + names_last_temp[i]
    asurite.append(id)

DataFrame['asurite']=asurite

add = []
for i in range(0,100):
    add.append(random.randint(0,50))
DataFrame['add']=add
DataFrame.to_csv('Input5',header=False,index=False)