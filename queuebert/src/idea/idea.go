package idea

import (
  // "fmt"
  "time"
)

type idea struct {
    text string
    skips  int64
    time_open int64
    time_closed int64
}

func New_Idea(text string) *idea {
  i := new(idea)
  i.text = text
  i.skips = int64(0)
  i.time_open = time.Now().Unix()
  i.time_closed = int64(-1)

  return i
  // return Idea{text: "First not", skips: 0, time_open: current_time, time_closed: 0}
}

func (i *idea) Close() {
  i.time_closed = time.Now().Unix()
}

func (i *idea) Get_Text() string {
  return i.text
}

func (i *idea) Get_Skips() int64 {
  return i.skips
}

func (i *idea) Get_Time_Open() int64 {
  return i.time_open
}

func (i *idea) Get_Time_Closed() int64 {
  return i.time_closed
}

func (i *idea) Is_Open() bool {
  if i.time_closed == -1 {
    return true
  }
  return false
}

func (i *idea) Set_Text(new_text string) {
  i.text = new_text
}

func (i *idea) Add_Skip() {
  i.skips = i.skips + 1
}
