#include<iostream>

using namespace std;

int main() {
	int input;
	int count = 0;

	cin >> input;;

	while (input > 0) {
		if (input % 5 == 0) {
			input -= 5;
			count++;
		}
		else if (input % 3 == 0) {
			input -= 3;
			count++;
		}
		else if (input > 5) {
			input -= 5;
			count++;
		}
		else {
			count = -1;
			break;
		}

	}
	cout << count << endl;
}