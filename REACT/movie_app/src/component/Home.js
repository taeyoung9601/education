import { useState, useEffect } from 'react';
import styles from './Home.module.css';


console.groupCollapsed('src/component/Home.js'); console.groupEnd();


//Get all movie data from external YTS site using openAPI.
const Home = (props) => {
    console.groupCollapsed('Home(',props,') invoked.'); console.groupEnd();
    
    // State : changed value as time
    // const initialLoadingState = true;
    const [loaing, setLoading] = useState(true);

    // const initialMovies = [];
    const [movies, setMovies] = useState([]);

    useEffect(async()=> {   //Executed once (just one-time) componentDidMount.
        const promise = await fetch('https://yts.mx/api/v2/list_movies.json?page=3&minimum_rating=8.8&sort_by=year');
        const json = await promise;
        
        
    },[]);

    return null;
};


export default Home;