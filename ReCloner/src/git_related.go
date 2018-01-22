package main

import (
  "fmt"
  "time"
  "gopkg.in/src-d/go-git.v4"
  "gopkg.in/src-d/go-git.v4/plumbing/object"
)

// TODO rewrite all git methods by hand

func main() {

  conf_data := Get_target_repo()
  repo_addr := "https://github.com/bws0013/Compound_Words.git"

  storage_dir := conf_data + "/" + Get_repo_name(repo_addr)

  r, err := git.PlainOpen(conf_data)

  Check(err)

  w, err := r.Worktree()
	Check(err)

  _, err = w.Add(conf_data)

  status, err := w.Status()
	Check(err)

	fmt.Println(status)

  // fmt.Println(r)

  commit, err := w.Commit("example go-git commit", &git.CommitOptions{
		Author: &object.Signature{
			Name:  "John Doe",
			Email: "john@doe.org",
			When:  time.Now(),
		},
	})

  Check(err)

  obj, err := r.CommitObject(commit)
	Check(err)

	fmt.Println(obj)


  err = r.Push(&git.PushOptions{})

  Check(err)

  // // ... retrieving the HEAD reference
	// ref, err := r.Head()
	// Check(err)
  //
	// // ... retrieves the commit history
	// cIter, err := r.Log(&git.LogOptions{From: ref.Hash()})
	// Check(err)
  //
	// // ... just iterates over the commits
	// var cCount int
	// err = cIter.ForEach(func(c *object.Commit) error {
	// 	cCount++
  //
	// 	return nil
	// })
	// Check(err)

	// fmt.Println(cCount)

}
