import {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import PostBox from '../components/post_box';
import posts_json from '../example-json/posts-ex.json';
import "../style.css";

function Home(){
    // 게시물예시 json 가져오기
    var [posts, setPosts]=useState([]);

    useEffect(() => {
        setPosts(posts_json);
    }, []);

    var navigator=useNavigate()
    var routerResisterStore = () => {
        navigator("/main/register-store/img-upload");
    }
    var routerMyPage = () => {
        navigator("/main/mypage");
    };

    return(
        <div className="homepage">
            <div className="cover">
                <div className="top-container">
                    <div></div>
                    <img className="material-ui" src="imgs/material-symbols_logout.svg" alt="logout-button" />
                </div>

                <div className="button-container">
                    <button id="selling-b" onClick={routerResisterStore}>상품 판매하기</button>
                    <button id="mypage-b" onClick={routerMyPage}>판매/구매 관리</button>
                </div>

                <div className='bottom-container'>
                    <img className="material-ui" id="down-arrow" src="imgs/gg_arrow-down-o.svg" alt="next page of main"/>
                </div>
            </div>

            <div className="showcase">
                <div className="search-box">
                    <input type="text" placeholder="Search..."/>
                    <button>Search</button>
                </div>
            
                {/* 상품 게시판 */}
                <section className="post-container">
                    {posts.map((t, i) =>
                        <PostBox key={i} img_paths={t.img_paths} prod_name={t.prod_name} prod_detail={t.prod_detail}/>
                    )}
                </section>
            </div>
        </div>
    );
}

export default Home;