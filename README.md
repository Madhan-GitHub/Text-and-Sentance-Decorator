# CSX42: Assignment 5

## Name: Madhan Thangavel

## B NO: B00814916

---

Following are the commands and the instructions to run ANT on your project.

Note: build.xml is present in [textdecorators/src](./textdecorators/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile textdecorators/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile textdecorators/src/build.xml all
```

The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile textdecorators/src/build.xml run -Dinput="Input.txt" -Dmisspelled="MisspelledWords.txt" -Dkeywords="Keywords.txt" -Doutput="Output.txt" -Ddebug="1"
```

Note:

1. Arguments accept the absolute path of the files.
2. Input.txt, MisspelledWords.txt, Keywords.txt and Output.txt should be in [textdecorators/](./textdecorators/) folder.
3. The last argument is the Logger value as per Mylogger. (1.Debug 2.MostfreqWord decorator 3. Keyword decorator  4. SpellCheck decorator 5.Sentence decorator  6.Error)

## Description:

* To build a text decorator using decorator pattern by designing a class InputDetails that has datastructure(s) to store, retrieve and update sentences.
* In this design I used ADT to store the common functionalities which is processInputdata() in AbstractTextDecorator.

###### Implementation of TextDecorator:

The code perfroms the following tasks:

1. Reads the command line arguments.
2. Creates array lists, based on Input1.txt, MisspelledWord.txt and Keyword.txt.
3. Uses InputDetails class to process the data while ignoring the invalid characters.
4. Uses ADT(abstract class) to have a specialized functionality to store data in array and list with the help of AbstractTextDecorator.
5. Uses decorator, via KeywordDecorator, MisspelledWordDecorator, SentenceDecorator and SpellCheckDecorator classes which consistes of processInput() and its required functionalities to process the data.
6. Then calls a method writeToFile, to write the data in Output.txt.

###### Justification for Data structure used in InputDetails:

The data structure used for storing the information is Array list since it supports the operations such as insert, delete and modify.

ArrayList - Used for Storing the input data line by line and also for mainting the list. ArrayList doestn't have fixed size, hence we can dynamically use to modify list more frequently when needed.

Worst Case Time Complexity - O(n) for addition of data from array to the list since we go through each element under normal circumstances.

Best case Time complexity - O(1) for adding a element in a list.
Getting an element from Array List take O(1).

HashSet - used for maintaing the data. Avoids duplicates Data
Time Complexity - The underlying data structure for HashSet is hashtable.
So, (average or usual case) time complexity for add, remove and look-up operation of HashSet takes O(1) time.

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Note: I would like to use two slack day for this assignment.

Date: [AUGUST 5 2020]