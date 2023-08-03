import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';


function Mapping(){
    var nav=useNavigate();
    useEffect(() => {
        nav("/login")
    }, [nav]);
    
    return null;
}

export default Mapping;