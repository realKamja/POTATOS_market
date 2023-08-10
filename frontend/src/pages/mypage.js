import { useNavigate } from "react-router-dom";

function Mypage(){
    var navigator=useNavigate();

    var routerSales = () => {
        navigator("/main/mypage/sales-table");
    }
    var routerPurch = () => {
        navigator("/main/mypage/purchase-history");
    }

    return(
        <div className="mypage">
            <button>설정</button>
            <button>회원정보관리</button>

            <button onClick={routerSales}>판매 현황</button>
            <button onClick={routerPurch}>구매 내역</button>
        </div>
    )
}

export default Mypage;