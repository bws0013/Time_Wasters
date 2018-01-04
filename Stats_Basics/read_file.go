package main

import (
  "bufio"
  "encoding/csv"
  "os"
  "fmt"
  "io"
)

func main() {

}

func read_csv(filename string) {
  f, err := os.Open(filename)
  check(err)

  // Create a new reader.
  r := csv.NewReader(bufio.NewReader(f))
  for {
      record, err := r.Read()
      // Stop at EOF.
      if err == io.EOF {
          break
      }
      // Display record.
      // ... Display record length.
      // ... Display all individual elements of the slice.
      fmt.Println(record)
      fmt.Println(len(record))
      for value := range record {
          fmt.Printf("  %v\n", record[value])
      }
  }
}
