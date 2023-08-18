var fetchArticles=null;

function scrollToShowcase() {
    window.scroll({top: window.innerHeight, behavior: "smooth"});
}

$(document).ready(function() {
    fetchArticles = () => {
        $.ajax({
            url: '/api/producter', // 게시글을 가져올 URL
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                $('#post-container').empty();
                
                $.each(data, function(index, article) {
                    var articleHTML = `
                        <div class="post-item" title="상품 자세히 보기" onclick="location.href='/main/sale/${article.id}'">
                            <div class="imgs-container">
                                <img src="/images/${article.producterImage}" alt="Image"> <br>
                            </div>
                            <h3>${article.producterTitle}</h2>
                            <span>${article.producterPrice}</span>
                        </div>
                    `;
                    $('#post-container').append(articleHTML);
                });
            },
            error: function() {
                console.log('오류 발생');
            }
        });
    };
    fetchArticles();
});