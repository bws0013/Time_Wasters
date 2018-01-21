package main

import (
  "os"
  "fmt"
  "log"
  "strings"
  "gopkg.in/src-d/go-git.v4"
)

func main() {
  // fmt.Println("hw")

  storage_dir := "./../storage"

  make_storage_dir(storage_dir)
  // defer os.RemoveAll(storage_dir)



}

// https://github.com/src-d/go-git.git
func get_repo_name(repo_addr string) string {
  addr_elements := strings.Split(repo_addr, "/")
  return strings.TrimSuffix(addr_elements[len(addr_elements) - 1], ".git")
}

func clone_to_dir(dir, repo_addr string) {
  _, err := git.PlainClone(dir, false, &git.CloneOptions{
    URL: repo_addr,
  })
  check(err)
}

func make_storage_dir(path string) {
  if _, err := os.Stat(path); os.IsNotExist(err) {
    os.MkdirAll(path, 0755)
  }
}

func check_with_message(err error, message string) {
  if err != nil {
    fmt.Println(message)
    log.Fatal(err)
  }
}

func check(err error) {
  if err != nil {
    log.Fatal(err)
  }
}
