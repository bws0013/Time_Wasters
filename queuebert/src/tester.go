package main

import (
  "fmt"
  "./idea"
  // "container/list"
)

func main() {

  // idea_queue := idea.new()

  // var idea_queue []*idea

  text := "first"

  right := idea.New_Idea(text)

  fmt.Println(right.Get_Text())

  right.Set_Text("second")

  fmt.Println(right.Get_Text())

  // right.Close()
  fmt.Println(right.Is_Open())

  fmt.Println()
}

// func queue() {
//   queue := make([]idea, 0)
//   queue := append(queue, 1)
//   x := queue[0]
//   queue = queue[1:]
//   queue := append(queue, x)
// }
