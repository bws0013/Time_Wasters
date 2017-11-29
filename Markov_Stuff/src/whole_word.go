package main

import (
  "math/rand"
  "unicode"
  "errors"
  "bufio"
  "fmt"
  "log"
  "os"
)

func main() {
  lines := read_file("./../storage/books/PandP.txt", 26)
  letter_map := read_by_letter(lines)

  for _, v := range letter_map {
    l := pick_next_letter_index(v)
    fmt.Printf("%c\n", l)
  }

}

func read_file(filename string, lines_to_skip int) []string {
  lines := make([]string, 0)
  file, err := os.Open(filename)
  check_err(err)
  defer file.Close()

  scanner := bufio.NewScanner(file)
  for i := 0; i < lines_to_skip; i++ {
    scanner.Scan()
  }

  for scanner.Scan() {
      lines = append(lines, scanner.Text())
  }

  return lines
}

/*
func random(min, max int) int {
  rand.Seed(time.Now().Unix())
  return rand.Intn(max - min) + min
}

*/

// Used by pick_next_letter_index
func get_letter_from_index(index int) rune {
  switch index {
    case 26:
      return '.'
    case 27:
      return ' '
    default:
      return rune(index) + 97
  }
}

func pick_next_letter_index(char_counts [28]int) rune {
  count := 0

  for _, val := range char_counts {
    count += val
  }

  random_number := rand.Intn(count)

  for i, val := range char_counts {
    random_number -= val
    if random_number <= 0 {
      return get_letter_from_index(i)
    }
  }

  if random_number > 0 {
    check_err(errors.New("Somehow we did not reach 0 in picking the next letter"))
  }
  return 'a';
}

func read_by_letters(lines []string, letter_count int) map[string][]int {
  return nil
}

func read_by_letter(lines []string) map[rune][28]int {
  var char_map = make(map[rune][28]int)

  current_index := 'a' - 97

  for _, line := range lines {
    if len(line) == 0 { continue }
    for _, char := range line {
      if char == '.' {
        letter_count := char_map[current_index]
        letter_count[26]++
        char_map[current_index] = letter_count
        current_index = 26
      } else if char == ' ' {
        letter_count := char_map[current_index]
        letter_count[27]++
        char_map[current_index] = letter_count
        current_index = 27
      } else if unicode.IsLetter(char) {
        char = unicode.ToLower(char)
        letter_count := char_map[current_index]
        letter_count[char - 97]++
        char_map[current_index] = letter_count
        current_index = char - 97
      }
    }
  }
  return char_map
}

func print_map(char_map map[rune][28]int) {
  for k, c := range char_map {
    fmt.Printf("%c", k + 97)
    fmt.Println(" ->", c)
  }
}

func read_by_word() {

}

func write() {

}

func check_err(err error) {
  if err != nil {
    fmt.Println("ERROR!")
    log.Fatal(err)
  }
}
