import { useNavigate } from "react-router-dom";

function Mypage(){
    var navigator=useNavigate();

    var routerSales = () => {
        navigator("/main/mypage/sales-table");
    }

    return(
        <div className="mypage">
            <button>설정</button>
            <button>회원정보관리</button>

            <button onclick={routerSales}>판매 현황</button>
            <button>구매 내역</button>
        </div>
    )
}

export default Mypage;