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
  // repo_addr := "https://github.com/bws0013/Compound_Words.git"

  storage_dir := conf_data

  git_add(storage_dir)
  git_commit(storage_dir, "test upload")
  git_push(storage_dir)

}

// Get the current working directory
func get_cwd() string {
  dir, err := os.Getwd()
  Check(err)
  return dir
}

// Change the working directory
func change_cwd(path string) {
  os.Chdir(path)
}

// Use machine to git add --all
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

// Use machine to git commit with a passed in commit message
func git_commit(path, commit_message string) {
  current_dir := get_cwd()
  defer change_cwd(current_dir)

  change_cwd(path)

  cmd := "git"
	args := []string{"commit", "-m", commit_message}
	if err := exec.Command(cmd, args...).Run(); err != nil {
		fmt.Println("Git commit failed!")
    Check(err)
	}
}

// Use machine to git push
func git_push(path string) {
  current_dir := get_cwd()
  defer change_cwd(current_dir)

  change_cwd(path)

  cmd := "git"
  args := []string{"push"}
  if err := exec.Command(cmd, args...).Run(); err != nil {
    fmt.Println("Git push failed!")
    Check(err)
  }
}
