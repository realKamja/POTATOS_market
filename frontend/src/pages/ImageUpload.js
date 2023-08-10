import { useState } from "react";

function ImageUpload(){
    const [imagePreviews, setImagePreviews] = useState([]);

    const handleUpload = (event) => {
      const files = Array.from(event.target.files);
  
      const newImagePreviews = files.map((file) => ({
        src: URL.createObjectURL(file),
        alt: file.name,
      }));
  
      setImagePreviews(newImagePreviews);
    };
  
    return (
      <div>
        <h1>이미지 업로드 페이지</h1>
  
        <div className="upload-container">
          <h2>이미지 업로드</h2>
          <input type="file" id="imageInput" accept="image/*" multiple onChange={handleUpload} />
        </div>
  
        <div className="preview-container">
          <h2>이미지 미리보기</h2>
          <div id="imagePreviews">
            {imagePreviews.map((preview, index) => (
              <img key={index} src={preview.src} alt={preview.alt} className="image-preview" />
            ))}
          </div>
        </div>
  
        <div>
          <h2>이미지 업로드 방법</h2>
          <p>1. 위의 "파일 선택" 또는 "찾아보기" 버튼을 클릭하세요.</p>
          <p>2. 기기에서 하나 이상의 이미지 파일을 선택하세요.</p>
          <p>3. 이미지가 자동으로 업로드됩니다.</p>
          <p>4. 업로드한 이미지는 "이미지 미리보기" 섹션에 표시됩니다.</p>
        </div>
      </div>
    );
}

export default ImageUpload