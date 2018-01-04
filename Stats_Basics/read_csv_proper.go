package main

import (
  "os"
  "log"
  "bufio"
  "strconv"
  "encoding/csv"
)

// func main() {
//   // read_csv_better("./storage/big_test_1.csv", false)
//   header_to_data_slice := read_csv_better("./storage/big_test_1.csv", true)
//   fmt.Println(header_to_data_slice)
// }

func read_csv_better(filename string, has_header bool) map[string][]string {
  f, err := os.Open(filename)
  check(err)

  // Create a new reader.
  r := csv.NewReader(bufio.NewReader(f))

  header_to_data_slice := make(map[string][]string)

  records, err := r.ReadAll()
	if err != nil {
		log.Fatal(err)
	}
  if has_header {
    header_to_data_slice = get_with_included_headers(records)
  } else {
    header_to_data_slice = get_with_default_headers(records)
  }
  return header_to_data_slice
}

func get_headers(header_to_data_slice map[string][]string) []string {
  headers := make([]string,0,0)
  for key := range header_to_data_slice {
    headers = append(headers, key)
  }
  return headers
}

func get_with_default_headers(matrix [][]string) map[string][]string {
  header_to_data_slice := make(map[string][]string)
  for i := 0; i < len(matrix); i++ {
    for j := 0; j < len(matrix[i]); j++ {
      header_to_data_slice[strconv.Itoa(j)] = append(header_to_data_slice[strconv.Itoa(j)], matrix[i][j])
    }
  }
  return header_to_data_slice
}

func get_with_included_headers(matrix [][]string) map[string][]string {
  header_to_data_slice := make(map[string][]string)
  headers := matrix[0]

  for i := 1; i < len(matrix); i++ {
    for j := 0; j < len(matrix[i]); j++ {
      header_to_data_slice[headers[j]] = append(header_to_data_slice[headers[j]], matrix[i][j])
    }
  }

  return header_to_data_slice
}

func get_with_included_headers_with_order(matrix [][]string) (map[string][]string, map[int]string) {
  header_to_data_slice := make(map[string][]string)
  index_to_header := make(map[int]string)
  headers := matrix[0]

  for index, val := range headers {
    index_to_header[index] = val
  }

  for i := 1; i < len(matrix); i++ {
    for j := 0; j < len(matrix[i]); j++ {
      header_to_data_slice[headers[j]] = append(header_to_data_slice[headers[j]], matrix[i][j])
    }
  }
  return header_to_data_slice, index_to_header
}

// For error checking
func check(err error) {
  if err != nil {
    log.Fatal(err)
  }
}

func get_ordered_headers() {
  /*
    We might be able to use something like this later
    0 -> column 1
    1 -> column 2
    ...
    n -> column n + 1
  */
}
