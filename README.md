# WordMatchCounter

Program that reads a file and finds matches against a predefined set of words.

The output of the program will look something like this: 

```
Predefined word           Match count 
FirstName                 3500 
LastName                  2700 
Zipcode                   1601 
```
Match will be case insensitive but while printing the output it will preserve the case mentioned in the `predefined_words.txt`.

There can be up to `10K` entries in the list of predefined words. Maximum length of the word can be upto `256` chars.


## Assumptions
In worst case scenario `predefined_words.txt` will have `10k` words each 256 chars long. Current solution
uses map to store predefined words key-value pair estimated data size will be below. 

**Estimated Data Size:**

```
Total data size = Number of pairs * Size per pair = 10,000 pairs * 512 bytes/pair ≈ 5.12 MB
```

Considering modern machine's memory are usually big enough to easily store this much data in memory we used HashMap to store entire predefined words i.e. in memory.



## Prerequisite

Please make sure that JDK12 or above is install and class path is set to execute/run the `javac` and `java` command.

 
## How to run the program? 


**Compile**

```bash
javac <path>/WordMatchCounter.java
```

**Run**

```bash
java <path>/WordMatchCounter.java <path to predefined_words.txt> <path to input_file.txt>
```


## What has been tested
Solution has been tested against the files available in folder `/file`.

**Use Case 1:** `input_file.txt` with `15` different sentences and `20` different `predefined_words.txt`.  

**Use Case 2:** `input_file_english.txt` with `~23400 (1MB)` different sentences and `10K` different predefined_words_english.txt.  


##Files
```bash
.
├── README.md
├── files
│   ├── input_file.txt
│   ├── input_file_english.txt
│   ├── predefined_words.txt
│   └── predefined_words_english.txt
└── src
    └── WordMatchCounter.java
```
  
  
