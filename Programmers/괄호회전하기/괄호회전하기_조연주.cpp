#include <string>
#include <vector>
#include <stack>

using namespace std;

int solution(string s) {
    int answer = 0;
    int len = s.length();
    stack<char> st;
    
    for(int i=0; i<len; i++){
        for(int k=0; k<len; k++){
            char c = s.at((i+k)%len);
            if(c == '(' || c == '[' || c == '{'){
                st.push(c);
            }
            else if(c == ')'){
                if(st.top() == '(') st.pop();
                else break;
            }
            else if(c == ']'){
                if(st.top() == '[') st.pop();
                else break;
            }
            else if(c == '}'){
                if(st.top() == '{') st.pop();
                else break;
            }
            if(k == len-1 && st.size() == 0) answer++;
        }
    }
    
    return answer;
}
