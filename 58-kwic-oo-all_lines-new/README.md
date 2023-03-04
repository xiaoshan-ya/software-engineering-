# KWIC-OO #

KWIC面向对象风格 -- ALL_Line_Version

1. 类的职责说明
```java
/**
 *  An object of the Alphabetizer class sorts all lines, that it gets
 *  from CircularShifter. 
 *  Alphabetizer类的一个对象对它从CircularShifter获取的所有行进行排序。
*/

public class Alphabetizer{
    
}
/**
 *  An object of the CircularShifter class produces and holds all circular shifts of
 *  a set of lines. 
 *  CircularShifter类的一个对象产生并保存一组直线的所有圆移位。
*/
public class CircularShifter{
    
}
/**
 *  Input class is responsible for reading and parsing the content of 
 *  a KWIC input file. 
 *  输入类负责读取和解析KWIC输入文件的内容。
*/

public class Input{
    
}

/**
 *  LineStorage holds a number of lines and provides a number of public methods
 *  to manipulate the lines. A line is defined as a set of words, and a word consists of a number of
 *  characters. 
 *  LineStorage保存许多行，并提供许多公共方法来操作这些行。一行被定义为一组单词，一个单词由一些字符组成。
*/

public class LineStorage{
    
}

/**
 *  An instance of the Output class prints sorted lines in nice format.
 *  Output类的一个实例以良好的格式打印已排序的行。
*/

public class Output{
    
}

/**
 *  An instance of the KWIC class controls all other objects in the KWIC system
 *  to achieve the desired functionality. 
 *  KWIC类的实例控制KWIC系统中的所有其他对象，以实现所需的功能。
*/

public class KWIC{
    
}
```
2. 作业要求
   + 实现4个类。    
        - LineStorage
        - CircularShift
        - Alphabetizier
        - KWIC
   + 思考如果涉及潜在的变更，需要设计修改哪些的方法
        - 存储的形式发生更改（比如采用index方式，而是记录完整字符串）
        - 循环位移算法发生更改
        - 排序算法发生更改
   
   