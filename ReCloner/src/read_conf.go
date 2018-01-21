package main

import (
  "os"
  "bufio"
  "strings"
)

func Get_target_repo() string {
  conf_data := Read_conf_file("./../config.conf")
  return Parse_conf(conf_data)
}

func Parse_conf(conf_data []string) string {
  for _, data_line := range conf_data {
    if strings.HasPrefix(data_line, "Target_Repo_Address:") {
      data_line = strings.TrimPrefix(data_line, "Target_Repo_Address:")
      return strings.TrimSpace(data_line)
    }
  }
  return "#"
}

func Read_conf_file(file_path string) []string {
  file, err := os.Open(file_path)
  Check(err)
  defer file.Close()

  conf_data := make([]string, 0)

  scanner := bufio.NewScanner(file)
  for scanner.Scan() {
    line := scanner.Text()
    if strings.HasPrefix(line, "#") {
      continue
    }
    conf_data = append(conf_data, line)
  }
  Check(err)

  return conf_data
}
