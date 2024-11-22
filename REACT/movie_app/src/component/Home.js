import { useState, useEffect } from 'react';
import styles from './Home.module.css';
import {Movie} from '.';

console.groupCollapsed('src/component/Home.js'); console.groupEnd();

const url = 'https://yts.mx/api/v2/list_movies.json?page=3&minimum_rating=8.8&sort_by=year';

//Get all movie data from external YTS site using openAPI.
const Home = (props) => {
    console.groupCollapsed('Home(', props, ') invoked.'); console.groupEnd();

    // State : changed value as time
    // const initialLoadingState = true;
    // const initialMovies = [];
    const [loading, setLoading] = useState(true);
    const [movies, setMovies] = useState([]);


    console.log('1.loading:', loading);
    console.log('2.movies:', movies);


    useEffect(() => {

        (async () => {
            const movieData = await ((await fetch(url)).json())
            console.log('3.movieData:', movieData.data.movies);
            //Update state: loading, movies

            setMovies(movieData.data.movies);
            console.log('------');
            setLoading(false);
            console.log('------');

        })();

    }, []);

    return (
        <section className={styles.container}>

            {loading? 
            (<div className='spinner-border spinner-border-lg text-danger'>

            </div>)
            : 
            (<div className={styles.movies}>
                {movies.map(function(m){
                    return <Movie key={m.id} {...m} />;
                })}
            </div>)
            }

        </section>
    );
}





export default Home;