package main

import (
  "log"
  "database/sql"
_ "github.com/mattn/go-sqlite3"
)

func main() {

  db, err := sql.Open("sqlite3", "gems.db")
  if err != nil {
    panic("DB Connection Failed")
  }

  _, err = db.Exec("INSERT INTO RUBYGEMS (ID, Name,Version,Arch,RubyVersion) VALUES (?, ?, ?, ?, ?)",
    nil, "jose", "can", "you", "see")


  if err != nil {
    log.Fatal(err)
  }

}
