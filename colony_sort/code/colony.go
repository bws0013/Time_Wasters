package main
//
// import "fmt"
//
// func main() {
//   fmt.Println("hw")
// }

/*
  A colony will be a collection of ants, where each ant is running on its own
  thread when put into the list, it will switch places with any node it has to
  in order to get to its correct location.

  A colony will have anchor points, which will map an integer to an ant. These
  anchor points are going to be used to separate mutexs to know approximately
  where an ant should start

*/


type Colony struct {

}

var (
  var m map[int]Ant
)
