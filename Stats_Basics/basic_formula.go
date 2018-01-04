package main

import (
	"fmt"
	"math"
)

func main_2() {

	// fmt.Println("hw")

	x_numbers := make([]float64, 0, 0)
	y_numbers := make([]float64, 0, 0)

	x_numbers = append(x_numbers, 1.0)
	x_numbers = append(x_numbers, 2.0)
	x_numbers = append(x_numbers, 2.0)
	x_numbers = append(x_numbers, 3.0)
	x_numbers = append(x_numbers, 6.0)

	y_numbers = append(y_numbers, 2.0)
	y_numbers = append(y_numbers, 4.0)
	y_numbers = append(y_numbers, 4.0)
	y_numbers = append(y_numbers, 6.0)
	y_numbers = append(y_numbers, 10.0)

	print_math(x_numbers, y_numbers)
}

func print_math(x_numbers, y_numbers []float64) {
	x_avg,x_sstd,x_pstd,x_svar,x_pvar := key_stats(x_numbers)
  y_avg,y_sstd,y_pstd,y_svar,y_pvar := key_stats(y_numbers)

	fmt.Println("Average:\t", x_avg)
	fmt.Println("Sample Std:\t", x_sstd)
	fmt.Println("Population Std:\t", x_pstd)
	fmt.Println("Sample Var:\t", x_svar)
	fmt.Println("Population Var:\t", x_pvar)
  fmt.Println("===============")
  fmt.Println("Average:\t", y_avg)
	fmt.Println("Sample Std:\t", y_sstd)
	fmt.Println("Population Std:\t", y_pstd)
	fmt.Println("Sample Var:\t", y_svar)
	fmt.Println("Population Var:\t", y_pvar)
	fmt.Println("===============")

	r_val := z_sum(x_numbers, y_numbers)
  fmt.Println("Rval:\t\t", r_val)

	r2_val := r_val * r_val
	fmt.Println("R^2val:\t\t", r2_val)
	fmt.Println("===============")
}

func key_stats(numbers []float64) (float64, float64, float64, float64, float64) {
  avg := average(numbers)
	sstd := sample_standard_deviation(numbers)
	pstd := population_standard_deviation(numbers)
	svar := sample_variance(numbers)
	pvar := population_variance(numbers)

  return avg, sstd, pstd, svar, pvar
}

func z_sum(numbers_x, numbers_y []float64) float64 {
  x_avg := average(numbers_x)
  y_avg := average(numbers_y)
  x_sstd := sample_standard_deviation(numbers_x)
  y_sstd := sample_standard_deviation(numbers_y)

  total := 0.0

  for i := 0; i < len(numbers_x); i++ {
    // total += (((float64(numbers_x[i]) - x_avg) / x_sstd) +
    //          ((float64(numbers_y[i]) - y_avg) / y_sstd))

    // fmt.Println((float64(numbers_x[i]) - x_avg) / x_sstd)
    // fmt.Println((float64(numbers_y[i]) - y_avg) / y_sstd)

    total += (numbers_x[i] - x_avg) * (numbers_y[i] - y_avg)
  }

  total /= (x_sstd * y_sstd)

  return total / float64(len(numbers_x) - 1);
}

func average(numbers []float64) float64 {
	total := 0.0

	for _, num := range numbers {
		total += num
	}

	return total / float64(len(numbers))
}

func sample_standard_deviation(numbers []float64) float64 {

	avg := average(numbers)

	total := 0.0
	for _, num := range numbers {
		total += math.Pow(num - avg, 2.0)
	}

	total /= float64(len(numbers) - 1)

	return math.Sqrt(total)
}

func sample_variance(numbers []float64) float64 {
	variance := sample_standard_deviation(numbers)
	return math.Pow(variance, 2)
}

func population_standard_deviation(numbers []float64) float64 {

	avg := average(numbers)

	total := 0.0
	for _, num := range numbers {
		total += math.Pow(num - avg, 2.0)
	}

	total /= float64(len(numbers))

	return math.Sqrt(total)
}

func population_variance(numbers []float64) float64 {
	variance := population_standard_deviation(numbers)
	return math.Pow(variance, 2)
}

func hello() {
	fmt.Println("hello")
}
