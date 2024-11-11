#include <string>
#include <vector>
using namespace std;
typedef long long ll;

int solution(vector<int> diffs, vector<int> times, long long limit) {
    int answer = 0;
    int len = diffs.size();
    int start = 1;
    int end = 100000;
    
    // binary search
    while(start <= end){
        int mid = (start + end) / 2; // level
        ll time = 0; 
        for(int i=0; i<len; i++){
            if(mid >= diffs[i]){
                time += times[i];
            } else {
                time += (ll)(diffs[i] - mid) * (times[i] + times[i-1]) + times[i];
            }
            if(time > limit){
                break;
            }
        }
        
        if(time == limit){
            answer = mid;
            break;
        } else if(time < limit){
            end = mid - 1;
            answer = mid;
        } else{
            start = mid + 1;
        }
        
    }
    
    return answer;
}
