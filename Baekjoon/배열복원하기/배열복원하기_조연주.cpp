#include <iostream>
#include <vector>
using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    // 1. 위에서 X, 왼쪽에서 Y만큼 구하기
    // 2. X+1, Y+1 ... H,W 까지 구하기

    int H, W, X, Y;
    cin >> H >> W >> X >> Y;

    vector<vector<int>> arr(H + X, vector<int>(W + Y));

    for (int i = 0; i < H + X; i++) {
        for (int k = 0; k < W + Y; k++) {
            cin >> arr[i][k];
        }
    }

    vector<vector<int>> ans(H, vector<int>(W));

    for (int i = 0; i < H; i++) {
        for (int k = 0; k < W; k++) {
            if (i < X || k < Y) {
                ans[i][k] = arr[i][k];
            }
            else {
                ans[i][k] = arr[i][k] - ans[i-X][k-Y];
            }

        }
    }

    for (int i = 0; i < H; i++) {
        for (int k = 0; k < W; k++) {
            cout << ans[i][k] << " ";
        }
        cout << "\n";
    }

    return 0;
}
