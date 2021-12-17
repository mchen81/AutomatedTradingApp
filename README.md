# AutomatedTradingApp
This a project to implement a trading algorithm.  

**[Implementation Details](https://github.com/mchen81/AutomatedTradingApp/blob/main/src/main/java/com/demo/TradeWhenAvgHigherAlgorithm.java)**

# How to run
**Note: This project uses JAVA 11, make sure you have a correct version.**

```
./gradlew clean build
java -jar ./build/libs/AutomatedTradingApp-1.0-SNAPSHOT.jar
```

# How to play
After execution of the jar file, you will see the prompts
```
Please register your products separated by comma(no space)
```
Simply put the collection of product names here separated by comma but no space.(it's ok that space in a product's name).  
For example,
```
Apple Juice,BlueBerry,Cheese
```
Then you will see the prompts:
```
Please enter product_name,price(no space)
```
You just need to put a product and price like
```
Apple Juice,10.00
```
If nothing printed, it means there is not any trade made.  
When a trade is made, the console shows:
```
Apple Juice,10
Apple Juice,20
Apple Juice,30
Apple Juice,40
A trade is made: Apple Juice,BUY,40.00,1000
```






