# First Assignment - MapReduce - Louis Gentil
##Part 1
>Java Code : Using MapReduce, please compute for each location, the sum of the banking accounts to determine location wealth.

After creating a repository in github I edited  [AreaBalance.java](https://github.com/lomithrani/Hadoop/blob/master/AreaBalance.java) file I compiled created the jar , run the app and then copied the output on a csv.

* `hadoop com.sun.tools.javac.Main AreaBalance.java`
* `jar cf wc.jar AreaBalance*.class`
* `hadoop jar wc.jar AreaBalance /res/mapred_assignment output/areabalance`
* `hadoop fs -cat output/areabalance/part-r-00000 > AreaBalance.csv`

>Question : Is it useful to use the reducer class as a combiner ? Justify.

As we only have one one chunk, it is not, we will perform twice the same operation
>BONUS :Provide a csv export for your MapReduce result

[AreaBalance.csv](https://github.com/lomithrani/Hadoop/blob/master/AreaBalance.csv)

##Part 2
With the same process:
[FriendAverage](https://github.com/lomithrani/Hadoop/blob/master/FriendAverage.java)
