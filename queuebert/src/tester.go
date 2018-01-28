package main

import (
  "fmt"
  "./idea"
  // "container/list"
)

var (
  idea_queue_test = make([]*Idea.Idea, 0)
)

func main() {

  // idea_queue_test := idea.new()

  // var idea_queue_test []Idea.Idea

  // idea_queue_test := make([]Idea.Idea, 0)

  text := "first"

  right := Idea.New_Idea(text)

  fmt.Println(right.Get_Text())

  right.Set_Text("second")

  fmt.Println(right.Get_Text())

  // right.Close()
  fmt.Println(right.Is_Open())

  idea_queue_test = append(idea_queue_test, right)

  x := idea_queue_test[0]

  fmt.Println(x.Get_Text())

  x.Print_Json()

  text = `{"text":"maybe","skips":0,"time_open":1517167156,"time_closed":-1}`

  Idea.New_Idea_From_Json(text)

}

// func queue() {
//   queue := make([]idea, 0)
//   queue := append(queue, 1)
//   x := queue[0]
//   queue = queue[1:]
//   queue := append(queue, x)
// }
