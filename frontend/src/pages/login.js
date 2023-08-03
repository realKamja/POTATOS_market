import { useNavigate } from "react-router-dom";

function Login(){
    var navigator=useNavigate();
    var routerSingUP=() => {
        navigator("/singup")
    };

    return(
        <div className="login-container">
            <form action="#">
                <label htmlFor="username">아이디:</label>
                <input type="text" id="username" name="username" required/>

                <label htmlFor="password">비밀번호:</label>
                <input type="password" id="password" name="password" required/>

                <button type="submit">로그인</button>
                <button type="button" onClick={routerSingUP}>회원가입</button>
            </form>
        </div>
    )
}

export default Login;