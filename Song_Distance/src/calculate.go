package main

import (
  "fmt"
)

/*
The first part is going to involve a target that is standing still.

We will be given a song list and a number of seconds that will never change.
Any number of songs can be used.
*/



func main() {
  sl := generate_songs(20)

  for _, s := range sl {
    fmt.Println(s.Length)
  }
}
