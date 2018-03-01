package main

import (
  "fmt"
  "log"
  "math/rand"
  "strconv"
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

var (
  song_length_min = 150
  song_length_max = 300
)

// Generates a list of N songs
func generate_songs(song_count int) []Song {
  song_list := make([]Song, 0)

  rand.Seed(42) // Make this dynamic later // rand.Seed(time.Now().Unix())

  for i := 0; i < song_count; i++ {
    name := strconv.Itoa(i)
    length := rand.Intn(song_length_max - song_length_min) + song_length_min
    song_list = append(song_list, Song{name, length, false})
  }

  return song_list
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
