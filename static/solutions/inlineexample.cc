//@include
inline bool isvowel(char c) {
  return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
}
//@exclude

int main(int argc, char** argv) { isvowel('a'); }
