/**
 * 게시물 컴포넌트
 * @param img_paths 이미지 경로를 담은 배열
 * @param prod_name 상품이름
 * @param prod_detail 상품설명
 * @returns 
 */
function PostBox({img_paths, prod_name, prod_detail}){
    return(
        <div className="post-item">
            {img_paths.map((t, i) =>
                <img key={i} src={t} alt={"상품 이미지"+i}/>
            )}
            <h3>{prod_name}</h3>
            <p>{prod_detail}</p>
        </div>
    )
}

export default PostBox;