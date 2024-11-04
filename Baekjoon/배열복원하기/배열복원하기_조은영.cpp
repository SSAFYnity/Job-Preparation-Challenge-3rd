#include <iostream>
using namespace std;

int arr[600][600] = {0,};

int main() {

	ios::sync_with_stdio(false);
	cin.tie(0);
		

	int h = 0, w = 0, x = 0, y = 0;


	cin >> h >> w >> x >> y;

	

	for (int i = 0; i < h + x; i++) {
		for (int j = 0; j < w + y; j++) {
			cin >> arr[i][j];
		}
	}

	// 겹치는 부분 구하기
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
			if (i >= x && i < h+x && j>=y && j<w+y) {
				arr[i][j] -= arr[i - x][j - y];
			}
		}
	}

	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
			cout << arr[i][j] << " ";
		}
		cout << "\n";
	}

	return 0;
}