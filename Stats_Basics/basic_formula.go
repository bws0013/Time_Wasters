package main

import (
  "fmt"
  "math"
)

func main() {

  fmt.Println("hw")

  numbers := make([]int, 0, 0)

  numbers = append(numbers, 1)
  numbers = append(numbers, 2)
  numbers = append(numbers, 2)
  numbers = append(numbers, 3)
  numbers = append(numbers, 6)

  avg := average(numbers)
  sstd := sample_standard_deviation(numbers)
  pstd := population_standard_deviation(numbers)
  svar := sample_variance(numbers)
  pvar := population_variance(numbers)

  fmt.Println("Average:\t", avg)
  fmt.Println("Sample Std:\t", sstd)
  fmt.Println("Population Std:\t", pstd)
  fmt.Println("Sample Var:\t", svar)
  fmt.Println("Population Var:\t", pvar)

}

func average(numbers []int) float64 {
  total := 0.0

  for _, num := range numbers {
    total += float64(num)
  }

  return total / float64(len(numbers))
}

func sample_standard_deviation(numbers []int) float64 {

  avg := average(numbers)

  total := 0.0
  for _, num := range numbers {
    total += math.Pow(float64(num) - avg, 2.0)
  }

  total /= float64(len(numbers) - 1)

  return math.Sqrt(total)
}

func sample_variance(numbers []int) float64 {
  variance := sample_standard_deviation(numbers)
  return math.Pow(variance, 2)
}

func population_standard_deviation(numbers []int) float64 {

  avg := average(numbers)

  total := 0.0
  for _, num := range numbers {
    total += math.Pow(float64(num) - avg, 2.0)
  }

  total /= float64(len(numbers))

  return math.Sqrt(total)
}

func population_variance(numbers []int) float64 {
  variance := population_standard_deviation(numbers)
  return math.Pow(variance, 2)
}
