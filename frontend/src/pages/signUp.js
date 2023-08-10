import { useNavigate } from "react-router-dom";
import { useState } from "react";
import axios from 'axios';

/**
 * 회원가입 페이지
 * @returns 
 */
function SingUp(){

    // 로그인 페이지 라우팅
    var navigator=useNavigate();
    var routerLogin=() => {
        navigator("/login")
    };

    /**
     * POST 회원가입 작성지
     * /member/signin (post)
     * 
     * memberId: 아이디
     * memberPassword: 비밀번호
     * memberName: 이름
     * memberPhone: 휴대폰 번호
     * memberEmail: 이메일 
     */
    const [memberId, setMemberId] = useState("");
    const [memberPassword, setMemberPassword] = useState("");
    const [memberName, setMemberName] = useState("");
    const [memberPhone, setMemberPhone] = useState("");
    const [memberEmail, setMemberEmail] = useState("");

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/member/signin', {
              memberId: memberId,
              memberPassword: memberPassword,
              memberName: memberName,
              memberPhone: memberPhone,
              memberEmail: memberEmail
            });
      
            alert('회원가입 절차가 끝났습니다! 입력한 아이디/비밀번호로 로그인해주세요.');
            routerLogin();

          } catch (error) {
            console.error('Error:', error);
          }    
    }

    return(
        <div className="singup-container">
            <form onSubmit={handleSubmit}>
                <p>
                    로그인 할 때 이용할 아이디와 비밀번호를 정해 입력해주세요.
                    <br/> 접속하는 매 회 입력해야 하므로, 기억하기 쉬워야 합니다.
                    <br/>타인의 접근을 막기 위해, 예측하기 어려운 비밀번호를 사용해주세요.
                </p>
                <label>아이디: </label>
                <input
                    type="text"
                    name="memberId"
                    value={memberId}
                    onChange={(e) => setMemberId(e.target.value)}
                    required
                />
                <label>비밀번호: </label>
                <input
                    type="password"
                    name="memberPassword"
                    value={memberPassword}
                    onChange={(e) => setMemberPassword(e.target.value)}
                    required
                />

                <p>
                    고객님의 개인정보(성함, 전화번호, 이메일)를 적어주세요.
                </p>
                <label>성함: </label>
                <input
                    type="text"
                    name="memberName"
                    value={memberName}
                    onChange={(e) => setMemberName(e.target.value)}
                    required
                />
                <label>전화번호(연락처): </label>
                <input
                    type="text"
                    name="memberPhone"
                    value={memberPhone}
                    onChange={(e) => setMemberPhone(e.target.value)}
                    required
                />
                <label>이메일: </label>
                <input
                    type="text"
                    name="memberEmail"
                    value={memberEmail}
                    onChange={(e) => setMemberEmail(e.target.value)}
                    required
                />

            </form>
        </div>
    );
}

export default SingUp