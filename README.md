# java challenges
 Various java challenges and solutions from the University of Southampton

## 1 - Find names and info from an email

Takes in a Southampton username and returns various details about tha user

### How to use
Make sure you have your secure.ecs.soton.ac.uk cookies in the Cookies.txt file

## 2 - A barebones interpreter

Interprets barebones files.

```
clear X;
incr X;
incr X;
incr X;
while X not 0 do;
    decr X;
end;
```

Indentation and new lines do not matter. As long as each statement ends with 
a semicolon the file will be valid.

A variable name comprises a string of upper or lower case alphabet characters 
and can be called anything apart from the following reserved keywords:
`clear`, `incr`, `decr`, `while`, `not`, `do`, `end`

### How to use

To be a valid barebones file it must end in the file extension `.bb` or `.barebones`

You will be prompted for the file name / path when running the interpreter
just enter the name. If there are any errors / incorrect syntax the interpreter
will throw an error message

Add the `-s` or `--step` flag to go through the file line by line and inspect
the state of all the variables

Add the `-p` or `--print`flag to print out the final value of all the variables
once the program has finished running

### 3 - Extending the barebones interpreter

Additional functionality added to the barebones interpreter written in challenge 2

This new interpreter contains all the same functionality as last time but now
also supports:
- Comments


