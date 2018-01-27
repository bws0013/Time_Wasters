package main

import (
  "fmt"
  "./idea"
)

func main() {

  text := "first"

  right := idea.New_Idea(text)

  fmt.Println(right.Get_Text())

}
