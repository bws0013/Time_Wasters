package Idea

import (
  // "fmt"
  "time"
)

type Idea struct {
    Text string
    Skips  int64
    Time_open int64
    Time_closed int64
}

func New_Idea(text string) *Idea {
  i := new(Idea)
  i.Text = text
  i.Skips = int64(0)
  i.Time_open = time.Now().Unix()
  i.Time_closed = int64(-1)

  return i
  // return Idea{Text: "First not", skips: 0, Time_open: current_time, Time_closed: 0}
}

func (i *Idea) Close() {
  i.Time_closed = time.Now().Unix()
}

func (i *Idea) Get_Text() string {
  return i.Text
}

func (i *Idea) Get_Skips() int64 {
  return i.Skips
}

func (i *Idea) Get_Time_open() int64 {
  return i.Time_open
}

func (i *Idea) Get_Time_closed() int64 {
  return i.Time_closed
}

func (i *Idea) Is_Open() bool {
  if i.Time_closed == -1 {
    return true
  }
  return false
}

func (i *Idea) Set_Text(new_text string) {
  i.Text = new_text
}

func (i *Idea) Add_Skip() {
  i.Skips = i.Skips + 1
}
