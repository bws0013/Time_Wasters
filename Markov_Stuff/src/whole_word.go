package main

import (
  "unicode"
  "bufio"
  "fmt"
  "log"
  "os"
)

func main() {
  lines := read_file("./../storage/books/PandP.txt", 26)
  read_by_letter(lines)
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

func read_by_letter(lines []string) {
  var char_map = make(map[rune][28]int)
  var current_letter rune

  for _, line := range lines {
    if len(line) == 0 { continue }
    for _, char := range line {
      if char == '.' {
        letter_count := char_map['.']
        letter_count[26]++
        char_map['.'] = letter_count
        current_letter = '.'
      } else if char == ' ' {
        letter_count := char_map[' ']
        letter_count[27]++
        char_map[' '] = letter_count
        current_letter = ' '
      } else if unicode.IsLetter(char) {
        char = unicode.ToLower(char)
        letter_count := char_map[char - 97]
        letter_count[char - 97]++
        char_map[char - 97] = letter_count
        current_letter = char - 97
      }
    }
  }

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
