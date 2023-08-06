import {useEffect, useState} from 'react';
import PostBox from '../components/post_box';

function SalesTable(){
    var [posts, setPosts]=useState([]);

    useEffect(() => {
        fetch('example-json/posts-ex.json')
        .then(res => res.json())
        .then(data => setPosts(data));
    }, []);

    return(
        <div className="sales_table">
            <label>총 매출: </label>
            <span id="sales"></span>

            <button>주문 확인</button>
            {/* 해당 유저가 판매 중인 상품 리스트 */}
            <section className="mypost-container">
            {posts.map((t, i) =>
                <PostBox key={i} img_paths={t.img_paths} prod_name={t.prod_name} prod_detail={t.prod_detail}/>
            )}
            </section>
        </div>
    )
}

export default SalesTable