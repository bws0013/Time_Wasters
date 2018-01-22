package main

import (
  "fmt"
  "os"
	"os/exec"
)

// TODO rewrite all git methods by hand

func main() {
  not_main()

  conf_data := Get_target_repo()
  repo_addr := "https://github.com/bws0013/Compound_Words.git"

  storage_dir := conf_data + "/" + Get_repo_name(repo_addr)

  git_add(storage_dir)
}

func get_cwd() string {
  dir, err := os.Getwd()
  Check(err)
  return dir
}

func change_cwd(path string) {
  os.Chdir(path)
}

func git_add(path string) {
  current_dir := get_cwd()
  defer change_cwd(current_dir)

  change_cwd(path)

  cmd := "git"
  	args := []string{"add", "--all"}
  	if err := exec.Command(cmd, args...).Run(); err != nil {
  		fmt.Println("Git add failed!")
      Check(err)
  	}
}

func git_commit() {

}

func git_push() {

}
