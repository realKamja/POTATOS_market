import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

/**
 * 다른 경로로 보냄.
 * 
 * TODO: 로그인 여부 판단하는 방법 알아보기
 * 
 * 로그인 했는가?
 * true -> login
 * false -> 입력한 경로의 페이지
 * 
 * @returns 
 */
function Mapping(){
    var nav=useNavigate();
    useEffect(() => {
        nav("/login")
    }, [nav]);
    
    return null;
}

export default Mapping;