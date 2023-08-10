import { useNavigate } from "react-router-dom";
import { useState } from "react";
import axios from 'axios';

function Login(){
    var navigator=useNavigate();
    var routerSignUp=() => {
        navigator("/singup")
    };
    var routerHome=()=>{
        navigator("/main")
    }

    const [memberId, setMemberId] = useState("");
    const [memberPassword, setMemberPassword] = useState("");

    const handleSubmit = async (event) => {
        event.preventDefault();
    
        try {
          const response = await axios.post('http://localhost:8080/member/login', {
            memberId: memberId,
            memberPassword: memberPassword,
          });
    
          const returnValue = response.data;
    
          if (returnValue === 'main') {
            // Navigate to the main page or take other actions
            console.log('Login successful. Navigating to main...');
            routerHome();
          } else if (returnValue === 'login') {
            // Handle login failure, display error message, etc.
            alert('로그인에 실패했습니다. 아이디와 비밀번호를 다시 확인해주세요');
          }
        } catch (error) {
          console.error('Error:', error);
        }
      };
    
      return (
        <div className="login-container">
          <form onSubmit={handleSubmit}>
            <label htmlFor="username">아이디:</label>
            <input
              type="text"
              name="memberId"
              value={memberId}
              onChange={(e) => setMemberId(e.target.value)}
              required
            />
    
            <label htmlFor="password">비밀번호:</label>
            <input
              type="password"
              name="memberPassword"
              value={memberPassword}
              onChange={(e) => setMemberPassword(e.target.value)}
              required
            />
    
            <button type="submit">로그인</button>
            <button type="button" onClick={routerSignUp}>
              회원가입
            </button>
          </form>
        </div>
      );
    // return(
    //     <div className="login-container">
    //         <form action="http://localhost:8080/member/login" method="post">
    //             <label htmlFor="username">아이디:</label>
    //             <input type="text" name="memberId" required/>

    //             <label htmlFor="password">비밀번호:</label>
    //             <input type="password" name="memberPassword" required/>
            
    //             <button type="submit" onClick={routerHome}>로그인</button>
    //             <button type="button" onClick={routerSingUP}>회원가입</button>
    //         </form>

    //     </div>
    // )
}

export default Login;