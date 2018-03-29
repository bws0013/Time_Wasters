package main

import (
  "fmt"
)

/*
  Essentially the first part of this is going to be a linked list
  where each node is called an ant. They are called ants for a
  later reason.

  Each ant knows who the ant in front and back of her is
  She also knows what she is holding
  She will not have an explicitly stated id or anything like that
*/

type Ant struct {
  next  *Ant
  prev  *Ant
  Value int
}

var (

)

func main() {
  fmt.Println("hw")
}
