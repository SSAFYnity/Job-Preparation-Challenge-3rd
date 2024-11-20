#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <set>
using namespace std;

vector<vector<pair<string,int>>> strs(11);
vector<string> answer;
set<char> apb;
void dfs(vector<string> &orders, vector<int> &course, string str, int depth);
    
vector<string> solution(vector<string> orders, vector<int> course) {
    
    for(int i=0; i<orders.size(); i++){
        for(int k=0; k<orders[i].length(); k++){
            apb.insert(orders[i].at(k));
        }
    }
    
    dfs(orders, course, "", 0);
    
    for(int i=0; i<course.size(); i++){
        int maxCnt = 2;
        for(int k=0; k<strs[course[i]].size(); k++){
            maxCnt = max(maxCnt, strs[course[i]][k].second);
        }
        for(int k=0; k<strs[course[i]].size(); k++){
            if(strs[course[i]][k].second == maxCnt){
                answer.push_back(strs[course[i]][k].first);
            }
        }
    }
    
    sort(answer.begin(), answer.end());    
    return answer;
}

void dfs(vector<string> &orders, vector<int> &course, string str, int depth){
    if(depth > course[course.size()-1]) return;
    
    if(find(course.begin(), course.end(), depth) != course.end()){
        int cnt = 0;
        for(int i=0; i<orders.size(); i++){
            string order = orders[i];
            bool valid = true;
            
            for(int k=0; k<str.length(); k++){
                char c = str.at(k);
                if(order.find(c) == string::npos){
                    valid = false;
                    break;
                }
            }
            if(valid) cnt++;
        }
        strs[depth].push_back(make_pair(str,cnt));
    }
    
    for (int i : apb) {
        if (!str.empty() && str.at(str.length()-1) >= i) continue;
        str.push_back(i); 
        dfs(orders, course, str, depth + 1);
        str.pop_back();
    }
}
