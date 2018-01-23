package main

import (
  "os"
  "fmt"
  "log"
  "strings"
  "gopkg.in/src-d/go-git.v4"
)

func not_main() {

  conf_data := Get_target_repo()
  // repo_addr := "https://github.com/bws0013/Compound_Words.git"
  repo_addr := "https://github.com/bws0013/go_chef.git"

  // storage_dir := "./../storage/" + Get_repo_name(repo_addr)

  storage_dir := conf_data + "/" + Get_repo_name(repo_addr)

  Make_storage_dir(storage_dir)
  Clone_to_dir(storage_dir, repo_addr)
  Remove_git_file(storage_dir)


  // defer os.RemoveAll(storage_dir)
}

func Remove_git_file(git_file_path string) {
  err := os.RemoveAll(git_file_path + "/.git")
  Check(err)
}

// https://github.com/src-d/go-git.git -> go-git
func Get_repo_name(repo_addr string) string {
  addr_elements := strings.Split(repo_addr, "/")
  return strings.TrimSuffix(addr_elements[len(addr_elements) - 1], ".git")
}

func Clone_to_dir(dir, repo_addr string) {
  _, err := git.PlainClone(dir, false, &git.CloneOptions{
    URL: repo_addr,
  })
  Check(err)
}

func Make_storage_dir(path string) {
  if _, err := os.Stat(path); os.IsNotExist(err) {
    os.MkdirAll(path, 0755)
  }
}

func Check_with_message(err error, message string) {
  if err != nil {
    fmt.Println(message)
    log.Fatal(err)
  }
}

func Check(err error) {
  if err != nil {
    log.Fatal(err)
  }
}
