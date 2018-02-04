package Idea

import (
  "time"
  "encoding/json"
)

type Idea struct {
    Text        string  `json:"text"`
    Skips       int64   `json:"skips"`
    Time_open   int64   `json:"time_open"`
    Time_closed int64   `json:"time_closed"`
}

func New_Idea(text string) *Idea {
  i := new(Idea)
  i.Text = text
  i.Skips = int64(0)
  i.Time_open = time.Now().Unix()
  i.Time_closed = int64(-1)

  return i
}

func New_Idea_From_Json(idea_string string) *Idea {
  var idea *Idea
  bytes := []byte(idea_string)
  json.Unmarshal(bytes, &idea)
  return idea
}

func (i *Idea) Get_Json() string {
  b, _ := json.Marshal(i)
  return string(b)
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

func (i *Idea) Get_Time_Open() int64 {
  return i.Time_open
}

func (i *Idea) Get_Time_Closed() int64 {
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
