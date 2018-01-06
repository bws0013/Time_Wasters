package main

import (
  "fmt"
  "strconv"
)

func main() {

  // get_x_y_pairs([]string{"a", "b", "c"})

  // x, y := get_column_data("TUITIONFEE_IN", "completion_rate_4yr_150nt", "./storage/big_test_1.csv", true)
  // // fmt.Println(len(x), "+", len(y))

  // print_math(x, y)

  headers := []string{"TUITIONFEE_IN", "TUITIONFEE_OUT", "completion_rate_4yr_150nt", "act_scores.midpoint.cumulative"}

  evaluate_two_dimensions(headers, "./storage/big_test_1.csv", true)

  fmt.Println("done")
}

func evaluate_two_dimensions(all_headers []string, filename string, has_headers bool) {
  header_to_data_slice := read_csv_better(filename, has_headers)

  all_combinations := get_x_y_pairs(all_headers)

  for _, combo := range all_combinations {
    fmt.Println(combo[0], "<->", combo[1])
    dirty_x := header_to_data_slice[combo[0]]
    dirty_y := header_to_data_slice[combo[1]]

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
    print_math(clean_x, clean_y)
    fmt.Print("\n\n\n")
  }
}

func get_column_data(column_x, column_y, filename string, has_headers bool) ([]float64, []float64) {
  header_to_data_slice := read_csv_better(filename, has_headers)

  // dirty_x := header_to_data_slice["TUITIONFEE_IN"]
  // dirty_y := header_to_data_slice["completion_rate_4yr_150nt"]

  dirty_x := header_to_data_slice[column_x]
  dirty_y := header_to_data_slice[column_y]

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

func get_x_y_pairs(column_names []string) [][]string {

  indices := make([]int, len(column_names))

  for i, _ := range column_names {
    indices[i] = i
  }

  index_combinations := combinations(indices, 2)

  column_combinations := make([][]string, len(index_combinations), cap(index_combinations))

  for i, combo := range index_combinations {
    column_combinations[i] = []string{column_names[combo[0]], column_names[combo[1]]}
  }

  return column_combinations
}


// Modified the algo from https://play.golang.org/p/JEgfXR2zSH
func combinations(iterable []int, r int) [][]int {

  all_combinations := make([][]int, 0)

  pool := iterable
	n := len(pool)

	if r > n {
		return all_combinations
	}

	indices := make([]int, r)
	for i := range indices {
		indices[i] = i
	}

	result := make([]int, r)
	for i, el := range indices {
		result[i] = pool[el]
	}

  local_combination := make([]int, len(result), cap(result))
  copy(local_combination, result)
  all_combinations = append(all_combinations, local_combination)

	for {
		i := r - 1
		for ; i >= 0 && indices[i] == i+n-r; i -= 1 {
		}

		if i < 0 {
			return all_combinations
		}

		indices[i] += 1
		for j := i + 1; j < r; j += 1 {
			indices[j] = indices[j-1] + 1
		}

		for ; i < len(indices); i += 1 {
			result[i] = pool[indices[i]]
		}

    local_combination = make([]int, len(result), cap(result))
    copy(local_combination, result)
    all_combinations = append(all_combinations, local_combination)

	}
  return all_combinations
}
