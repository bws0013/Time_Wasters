package main

import (
  "log"
)

// This file holds our struct and some boiler plate methods like error checking

/*
  Name is synonymous with id in this context, it identifies a song uniquely
  Length is the length of time for a song
  Played refers to a song being played within the last N songs
    Played is useful becuase I dont like hearing the same songs on loop
*/
type Song struct {
  Name    string  `json:"name"`
  Length  int  `json:"length"`
  Played  bool  `json:"played"`
}

// Simple error check
func check(err error) {
  if err != nil {
    log.Fatal(err)
  }
}

// Used for logging actions
func check_with_message(err error, message string) {
  fmt.Println(message)
  check(err)
}
