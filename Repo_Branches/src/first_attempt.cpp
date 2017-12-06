#include <iostream>
#include <curl/curl.h>

using namespace std;

void call() {
  system("curl -i https://api.github.com/users/bws0013");
}

int main() {
  string username;
  string password;
  cout << "Enter a username\n>";
  cin >> username;
  cout << username << "\n";

  call();
}
