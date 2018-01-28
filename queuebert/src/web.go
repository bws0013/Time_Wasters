package main

import (
    "fmt"
    "log"
    "./idea"
    "net/http"
    "github.com/gorilla/mux"
)

var (
  idea_queue []idea
)
// Copying from here: https://www.codementor.io/codehakase/building-a-restful-api-with-golang-a6yivzqdo

// our main function
func main() {

	router := mux.NewRouter()

  router.HandleFunc("/queuebert", Get_Idea).Methods("GET")
  // router.HandleFunc("/queuebert/", GetPeople).Methods("GET")
  // router.HandleFunc("/queuebert/{id}", GetPerson).Methods("GET")
  // router.HandleFunc("/queuebert/{id}", CreatePerson).Methods("POST")
  // router.HandleFunc("/queuebert/{ild}", DeletePerson).Methods("DELETE")
  log.Fatal(http.ListenAndServe(":8000", router))
}

// ***** RESTful stuff below *****

func Get_Idea(w http.ResponseWriter, r *http.Request) {

  idea_text := "No ideas yet"

  if len(idea_queue) > 0 {
    idea_text = idea_queue[0]
  }

  fmt.Println("I have been chosen")

  w.Header().Set("Content-Type", "text/plain")
  w.Write([]byte(idea_text))
}

// func GetPeople(w http.ResponseWriter, r *http.Request) {
//     jsonObj := gabs.New()
//     jsonObj.Array("Names")
//     for _, v := range people {
//       jsonObj.ArrayAppend(v.get_name(), "Names")
//     }
//
//     json_string := jsonObj.String()
//     // fmt.Println(json_string)
//
//
//     // fmt.Println(string(bytes))
//     fmt.Println("I have been chosen")
//     // json.NewEncoder(w).Encode(ff)
//     w.Header().Set("Content-Type", "application/json")
//     w.Write([]byte(json_string))
// }
//
// func GetPerson(w http.ResponseWriter, r *http.Request) {
//   params := mux.Vars(r)
//   for _, item := range people {
//     if item.get_name() == params["id"] {
//       // jsonParsedObj, err := gabs.ParseJSON())
//       // check_err(err)
//       w.Header().Set("Content-Type", "application/json")
//       w.Write([]byte(item.Json.String()))
//       return
//     }
//   }
//   json.NewEncoder(w).Encode(&Person{})
// }
//
// func CreatePerson(w http.ResponseWriter, r *http.Request) {
//   params := mux.Vars(r)
//   person := new_person(params["id"])
//   people = append(people, *person)
//   fmt.Println(*person)
//   json.NewEncoder(w).Encode(people)
// }
//
// func DeletePerson(w http.ResponseWriter, r *http.Request) {
//   params := mux.Vars(r)
//   for index, item := range people {
//     fmt.Println(params["ild"])
//   	if item.get_name() == params["ild"] {
//     	people = append(people[:index], people[index+1:]...)
//       break
//   	}
//   	json.NewEncoder(w).Encode(people)
// 	}
//
// }
