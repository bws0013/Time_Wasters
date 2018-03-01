### Song_Distance

A program that takes in a list of times and produces a list such that the last time occurs within a specific threshold of a final time.

For example:

```
Current time  7:00
Final time    7:10
Song list     2:30, 3:00, 2:34, 5:20, 4:40, 4:27, 4:00
Threshold 1 second
Minimum Songs = -1
Maximum Songs = -1
```

* If Minimum and Maximum are < 0 then any number is correct.

This example would result in songs 3 and 4 (0 indexed) being played. Because `5:20 + 4:40 = 10:00` which is within + or - 1 seconds of 10:00.
