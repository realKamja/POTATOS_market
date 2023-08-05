import PostBox from '../components/post_box';
import {useEffect, useState} from 'react';

function Home(){
    var [posts, setPosts]=useState([]);

    useEffect(() => {
        fetch('example-json/posts-ex.json')
        .then(res => res.json())
        .then(data => setPosts(data));
    }, []);

    return(
        <div className="homepage">
        <header>
            <div>
                <button>상품 판매하기</button>
                <button>판매/구매 관리</button>

                <div className="search-box">
                    <input type="text" placeholder="Search..."/>
                    <button>Search</button>
                </div>
            </div>
        </header>
    
        {/* 상품 게시판 */}
        <section className="post-container">
            {posts.map((t, i) =>
                <PostBox key={i} img_paths={t.img_paths} prod_name={t.prod_name} prod_detail={t.prod_detail}/>
            )}
        </section>
    
        </div>
    );
}

export default Home;