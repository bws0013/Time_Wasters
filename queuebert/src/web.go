package main

import (
    "fmt"
    "log"
    "./idea"
    "strings"
    "net/http"
    "io/ioutil"
    "github.com/gorilla/mux"
)

var (
  idea_queue = make([]*Idea.Idea, 0)
)
// Copying from here: https://www.codementor.io/codehakase/building-a-restful-api-with-golang-a6yivzqdo

// our main function
func main() {

	router := mux.NewRouter()

  router.HandleFunc("/queuebert", Get_Idea).Methods("GET")
  router.HandleFunc("/queuebert/", Get_Idea).Methods("GET")
  router.HandleFunc("/queuebert/skip", Skip_Idea).Methods("GET")
  router.HandleFunc("/queuebert/add", Add_Test).Methods("PUT")
  router.HandleFunc("/queuebert/load", Load_Test_Data).Methods("PUT")
  // router.HandleFunc("/queuebert/{id}", CreatePerson).Methods("POST")
  // router.HandleFunc("/queuebert/{ild}", DeletePerson).Methods("DELETE")
  log.Fatal(http.ListenAndServe(":8000", router))
}

// ***** RESTful stuff below *****

// Use for content types https://www.w3.org/Protocols/rfc1341/7_1_Text.html

func Load_Test_Data(w http.ResponseWriter, r *http.Request) {

  filename := "./../storage/database.in"

  bytes, err := ioutil.ReadFile(filename)
  Check(err)

  all_lines := string(bytes)

  lines := strings.Split(all_lines, "\n")

  for _, line := range lines {
    if len(line) == 0 { continue }
    idea := Idea.New_Idea_From_Json(line)
    idea_queue = append(idea_queue, idea)
  }
  Get_Idea(w, r)
}


func Get_Idea(w http.ResponseWriter, r *http.Request) {

  idea_text := "No ideas yet"

  if len(idea_queue) > 0 {
    idea_text = idea_queue[0].Get_Text()
  }

  fmt.Println("I have been chosen")

  w.Header().Set("Content-Type", "text/plain")
  w.Write([]byte(idea_text))
}

func Skip_Idea(w http.ResponseWriter, r *http.Request) {

  if len(idea_queue) > 1 {
    front := idea_queue[0]
    front.Add_Skip()
    idea_queue = idea_queue[1:]
    idea_queue = append(idea_queue, front)
  }

  Get_Idea(w, r)
}

func Add_Test(w http.ResponseWriter, r *http.Request) {

  new_idea := Idea.New_Idea(fmt.Sprintf("Item: %d", len(idea_queue) + 1))

  idea_queue = append(idea_queue, new_idea)
  Get_Idea(w, r)
}

func Check(err error) {
  if err != nil {
    log.Fatal(err)
  }
}

// func queue() {
//   queue := make([]idea, 0)
//   queue := append(queue, 1)
//   x := queue[0]
//   queue = queue[1:]
//   queue := append(queue, x)
// }
