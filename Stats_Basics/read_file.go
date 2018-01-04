package main

import (
  "fmt"
  "strconv"
)

func main() {
  x, y := read_csv("./storage/big_test_1.csv", true)
  // fmt.Println(len(x), "+", len(y))

  print_math(x, y)
  fmt.Println("done")
}

func read_csv(filename string, has_headers bool) ([]float64, []float64){
  header_to_data_slice := read_csv_better(filename, has_headers)

  dirty_x := header_to_data_slice["TUITIONFEE_IN"]
  dirty_y := header_to_data_slice["completion_rate_4yr_150nt"]

  clean_x := make([]float64, 0, 0)
  clean_y := make([]float64, 0, 0)

  for i := 0; i < len(dirty_x); i++ {
    if dirty_x[i] == "NULL" || dirty_y[i] == "NULL" {
      // Disreguard as we cannot use the data for it cannot be a number
    } else {
      fixed_x, err := strconv.ParseFloat(dirty_x[i], 64)
      check(err)
      fixed_y, err := strconv.ParseFloat(dirty_y[i], 64)
      check(err)

      clean_x = append(clean_x, fixed_x)
      clean_y = append(clean_y, fixed_y)
    }
  }

  return clean_x, clean_y
}
