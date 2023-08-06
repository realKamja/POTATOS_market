import { BrowserRouter, Routes, Route, useLocation } from 'react-router-dom';
import { useEffect } from 'react';
import Mapping from './pages/mapping';
import Login from './pages/login';
import SingUp from './pages/signUp';
import Home from './pages/home';
import Mypage from './pages/mypage';
import SalesTable from './pages/salesTable';

function App(){
    return(
        <BrowserRouter>
            <ScrollToTop/>
            <Routes>       
                <Route path={"/"} element={<Mapping/>}></Route>
                <Route path={"/login"} element={<Login/>}></Route>
                <Route path={"/singup"} element={<SingUp/>}></Route>

                <Route path={"/main"} element={<Home/>}></Route>
                <Route path={"/main/mypage"} element={<Mypage/>}></Route>
                <Route path={"/main/mypage/sales-table"} element={<SalesTable/>}></Route>
            </Routes>
        </BrowserRouter>
    );
}

/**
 * 다른 페이지로 이동했을 때 상단으로 스크롤
 * @returns 
 */
function ScrollToTop() {
    const { pathname } = useLocation();
  
    useEffect(() => {
      window.scrollTo(0, 0);
    }, [pathname]);
  
    return null;
}

export default App;